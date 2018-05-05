/**
 * 
 */
package com.weimhc.admin.modules.web.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.base.utils.EducationUtils;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.entity.JobLabel;
import com.weimhc.modules.job.service.JobLabelService;
import com.weimhc.modules.job.service.JobService;

/**
 * 职位Controller
 * @author lc
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/job/job")
public class JobController extends AdminBaseController {

	@Autowired
	private JobService jobService;
	@Autowired
	private JobLabelService jobLabelService;
	
	@ModelAttribute
	public Job get(@RequestParam(required=false) String id) {
		Job entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jobService.get(id);
		}
		if (entity == null){
			entity = new Job();
		}
		return entity;
	}
	
	@RequiresPermissions("job:job:view")
	@RequestMapping(value = {"list", ""})
	public String list(Job job, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Job> page = jobService.findPage(new Page<Job>(request, response), job); 
		model.addAttribute("page", page);
		if (dealWithSearchType(request, model)) {
			return "modules/job/jobSearchList";
		}
		return "modules/job/jobList";
	}

	@RequiresPermissions("job:job:view")
	@RequestMapping(value = "form")
	public String form(Job job, Model model) {
		model.addAttribute("job", job);
		model.addAttribute("educations", EducationUtils.findAll());
//		model.addAttribute("jobStates", JobState.values());
//		model.addAttribute("languages", Language.values());
//		model.addAttribute("jobChances", JobChance.values());
		model.addAttribute("jobLabels", jobLabelService.findAllList(new JobLabel()));
		if(StringUtils.isNoneBlank(job.getId())) {
			JobLabel jobLabel=new JobLabel();
			jobLabel.setJob(new Job(job.getId()));
			List<JobLabel> jobLabels=jobLabelService.findJobLabel(jobLabel);
			List<String> jobLabelIds=Lists.newArrayList();
			for (JobLabel jobLabel2 : jobLabels) {
				jobLabelIds.add(jobLabel2.getId());
			}
			job.setJobLabelIdList(jobLabelIds);
		}
		return "modules/job/jobForm";
	}
	
	@RequiresPermissions("job:job:view")
	@RequestMapping(value = "view")
	public String view(Job job, Model model) {
		model.addAttribute("job", job);
		return "modules/job/jobView";
	}

	@RequiresPermissions("job:job:edit")
	@RequestMapping(value = "save")
	public String save(Job job, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		// 角色数据有效性验证，过滤不在授权内的角色
		List<JobLabel> jobLabelList = Lists.newArrayList();
		List<String> jobLabelIdList = job.getJobLabelIdList();
		List<JobLabel> jobLabelAllList = jobLabelService.findAllList(new JobLabel());
		for (JobLabel jl : jobLabelAllList) {
			if (jobLabelIdList.contains(jl.getId())) {
				jobLabelList.add(jl);
			}
		}
		job.setJobLabelList(jobLabelList);
		String requestUrl = request.getScheme() //当前链接使用的协议
				+ "://" + request.getServerName()//服务器地址
				+ ":" + request.getServerPort() //端口号
				+ request.getContextPath() //应用名称，如果应用名称为
				+ "/static/images/"; //请求参数
		// 查找即将上传到服务器中的真实路径!
		String realPath = request.getServletContext().getRealPath("/static/images");
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (job.getQrCodeFile() != null && job.getQrCodeFile().getSize() != 0) {
			String qrCodeStr = getNewName(job.getQrCodeFile());
			//二 维 码
			InputStream inputStreamQR = job.getQrCodeFile().getInputStream();
			// 程序 写出 上传服务器!
			String qrCodeName = realPath + "/" + qrCodeStr;
			System.out.println("name:" + qrCodeName);
			FileOutputStream fileOutputStreamQR = new FileOutputStream(qrCodeName);
			// 复制多功能文件(图片)以及关闭流
			IOUtils.copy(inputStreamQR, fileOutputStreamQR);
			inputStreamQR.close();
			fileOutputStreamQR.close();
			job.setQrCode(requestUrl + qrCodeStr);
		}
		jobService.saveJob(job);
		addMessage(redirectAttributes, "保存职位成功");
		return "redirect:"+Global.getAdminPath()+"/job/job/?repage";
	}
	
	@RequiresPermissions("job:job:edit")
	@RequestMapping(value = "delete")
	public String delete(Job job, RedirectAttributes redirectAttributes) {
		jobService.delete(job);
		addMessage(redirectAttributes, "删除职位成功");
		return "redirect:"+Global.getAdminPath()+"/job/job/?repage";
	}

	public String getNewName(MultipartFile file) {
		// 找到原始文件名
		String originalFilename = file.getOriginalFilename();
		// 找到后缀名.的位置
		int lastIndexOf = originalFilename.lastIndexOf(".");
		// 截取后缀名
		String substring = originalFilename.substring(lastIndexOf);
		// 生成uuid
		String uuid = UUID.randomUUID().toString();
		String newName = uuid + substring;

		return newName;

	}

}