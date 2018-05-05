/**
 * 
 */
package com.weimhc.admin.modules.web.course;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.job.entity.Course;
import com.weimhc.modules.job.entity.CourseLabel;
import com.weimhc.modules.job.service.CourseLabelService;
import com.weimhc.modules.job.service.CourseService;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;


/**
 * 课程培训Controller
 * @author cwl
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/course/course")
public class CourseController extends AdminBaseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseLabelService courseLabelService;
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "modules/course/courseList";
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, BindingResult result, Model model,
			RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		if (!isValid(result, course)){
			return form(course, model);
		}
		// 创建I流--图片文件myFile转化为流--读入程序!
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
		if (course.getQrCodeFile().getSize() != 0) {
			String qrCodeStr = getNewName(course.getQrCodeFile());
			//二 维 码
			InputStream inputStreamQR = course.getQrCodeFile().getInputStream();
			// 程序 写出 上传服务器!
			String qrCodeName = realPath + "/" + qrCodeStr;
			System.out.println("name:"+qrCodeName);
			FileOutputStream fileOutputStreamQR = new FileOutputStream(qrCodeName);
			// 复制多功能文件(图片)以及关闭流
			IOUtils.copy(inputStreamQR, fileOutputStreamQR);
			inputStreamQR.close();
			fileOutputStreamQR.close();
			course.setQrCode(requestUrl+qrCodeStr);
		}
		if (course.getBackImageFile().getSize() != 0) {
			String backImageFileStr = getNewName(course.getBackImageFile());
			// 背 景 图 片
			InputStream inputStreamBack = course.getBackImageFile().getInputStream();
			// 程序 写出 上传服务器!
			String imgBackName = realPath + "/" + backImageFileStr;
			System.out.println("name:"+imgBackName);
			FileOutputStream fileOutputStreamBack = new FileOutputStream(imgBackName);
			// 复制多功能文件(图片)以及关闭流
			IOUtils.copy(inputStreamBack, fileOutputStreamBack);

			inputStreamBack.close();
			fileOutputStreamBack.close();
			course.setBackImage(requestUrl+backImageFileStr);
		}

		courseService.save(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	
	@RequiresPermissions("course:course:delete")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.deleteEntity(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}

	@RequiresPermissions("course:course:delete")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		courseService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
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


	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();

		CourseLabel industry = new CourseLabel();
		List<CourseLabel> list = courseLabelService.findAllList(industry);
		for (int i = 0; i < list.size(); i++) {
			CourseLabel e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null
					&& !extId.equals(e.getId())
					)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}