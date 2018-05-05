/**
 * 
 */
package com.weimhc.admin.modules.web.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.job.entity.JobLabel;
import com.weimhc.modules.job.service.JobLabelService;

/**
 * 职位标签Controller
 * @author lc
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/job/jobLabel")
public class JobLabelController extends AdminBaseController {

	@Autowired
	private JobLabelService jobLabelService;
	
	@ModelAttribute
	public JobLabel get(@RequestParam(required=false) String id) {
		JobLabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jobLabelService.get(id);
		}
		if (entity == null){
			entity = new JobLabel();
		}
		return entity;
	}
	
	@RequiresPermissions("job:jobLabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(JobLabel jobLabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JobLabel> page = jobLabelService.findPage(new Page<JobLabel>(request, response), jobLabel); 
		model.addAttribute("page", page);
		return "modules/job/jobLabelList";
	}

	@RequiresPermissions("job:jobLabel:view")
	@RequestMapping(value = "form")
	public String form(JobLabel jobLabel, Model model) {
		model.addAttribute("jobLabel", jobLabel);
		return "modules/job/jobLabelForm";
	}
	
	@RequiresPermissions("job:jobLabel:view")
	@RequestMapping(value = "view")
	public String view(JobLabel jobLabel, Model model) {
		model.addAttribute("jobLabel", jobLabel);
		return "modules/job/jobLabelView";
	}

	@RequiresPermissions("job:jobLabel:edit")
	@RequestMapping(value = "save")
	public String save(JobLabel jobLabel, Model model, BindingResult result,RedirectAttributes redirectAttributes) {
		if (!isValid(result, jobLabel)) {
			return form(jobLabel, model);
		}
		jobLabelService.save(jobLabel);
		addMessage(redirectAttributes, "保存职位标签成功");
		return "redirect:"+Global.getAdminPath()+"/job/jobLabel/?repage";
	}
	
	@RequiresPermissions("job:jobLabel:edit")
	@RequestMapping(value = "delete")
	public String delete(JobLabel jobLabel, RedirectAttributes redirectAttributes) {
		jobLabelService.delete(jobLabel);
		addMessage(redirectAttributes, "删除职位标签成功");
		return "redirect:"+Global.getAdminPath()+"/job/jobLabel/?repage";
	}

}