/**
 * 
 */
package com.weimhc.admin.modules.web.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weimhc.framework.dto.resp.ApiResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.http.protocol.HttpContext;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.modules.base.utils.BaseIndustryUtils;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.entity.Company.CompanyType;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.company.service.CompanyLabelService;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.utils.AreaUtils;

/**
 * 企业Controller
 * @author lc
 * @version 2017-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/company/company")
public class CompanyController extends AdminBaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyLabelService companyLabelService;
	
	@ModelAttribute
	public Company get(@RequestParam(required=false) String id) {
		Company entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyService.get(id);
		}
		if (entity == null){
			entity = new Company();
		}
		return entity;
	}
	
	@RequiresPermissions("company:company:view")
	@RequestMapping(value = {"listIsPass", ""})
	public String listIsPass(Company company, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Company> page = companyService.findPage(new Page<Company>(request, response), company); 
		model.addAttribute("page", page);
		if (dealWithSearchType(request, model)) {
			return "modules/company/companySearchList";
		}
		return "modules/company/companyListIsPass";
	}

	@RequiresPermissions("company:company:view")
	@RequestMapping(value = {"list"})
	public String list(Company company, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Company> page = companyService.findPage(new Page<Company>(request, response), company);
		model.addAttribute("page", page);
		if (dealWithSearchType(request, model)) {
			return "modules/company/companySearchList";
		}
		return "modules/company/companyList";
	}

	@RequiresPermissions("company:company:view")
	@RequestMapping(value = "form")
	public String form(Company company, Model model) {
		fromCompany(company, model);
		return "modules/company/companyForm";
	}

	@RequiresPermissions("company:company:view")
	@RequestMapping(value = "formPass")
	public String formPass(Company company, Model model) {
		fromCompany(company, model);
		return "modules/company/companyFormPass";
	}

	private void fromCompany(Company company, Model model) {
		model.addAttribute("company", company);
		model.addAttribute("companyTypes", CompanyType.values());
		model.addAttribute("baseIndustrys", BaseIndustryUtils.findAll());
		model.addAttribute("companyLabels", companyLabelService.findAllList(new CompanyLabel()));
		if(StringUtils.isNoneBlank(company.getId())) {
			CompanyLabel companyLabel=new CompanyLabel();
			companyLabel.setCompany(new Company(company.getId()));
			List<CompanyLabel> companyLabels=companyLabelService.findCompanyLabel(companyLabel);
			List<String> companyLabelIds= Lists.newArrayList();
			for (CompanyLabel companyLabel2 : companyLabels) {
				companyLabelIds.add(companyLabel2.getId());
			}
			company.setCompanyLabelIdList(companyLabelIds);
		}
	}

	@RequiresPermissions("company:company:view")
	@RequestMapping(value = "view")
	public String view(Company company, Model model) {
		model.addAttribute("company", company);
		return "modules/company/companyView";
	}

	@RequiresPermissions("company:company:edit")
	@RequestMapping(value = "save")
	public String save(Company company, Model model,BindingResult result, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
		// 角色数据有效性验证，过滤不在授权内的角色
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
		if (company.getLogoFile().getSize() != 0) {
			String logoStr = getNewName(company.getLogoFile());
			//二 维 码
			InputStream inputStreamQR = company.getLogoFile().getInputStream();
			// 程序 写出 上传服务器!
			String qrCodeName = realPath + "/" + logoStr;
			System.out.println("name:"+qrCodeName);
			FileOutputStream fileOutputStreamQR = new FileOutputStream(qrCodeName);
			// 复制多功能文件(图片)以及关闭流
			IOUtils.copy(inputStreamQR, fileOutputStreamQR);
			inputStreamQR.close();
			fileOutputStreamQR.close();
			company.setLogo(requestUrl+logoStr);
		}

		saveCompany(company, redirectAttributes);
		return "redirect:"+Global.getAdminPath()+"/company/company/list/?repage";
	}
	@RequiresPermissions("company:company:edit")
	@RequestMapping(value = "savePass")
	public String savePass(Company company, Model model,BindingResult result, RedirectAttributes redirectAttributes) {
		saveCompany(company, redirectAttributes);

		return "redirect:"+Global.getAdminPath()+"/company/company/?repage";
	}

	private void saveCompany(Company company, RedirectAttributes redirectAttributes) {
		// 角色数据有效性验证，过滤不在授权内的角色
		List<CompanyLabel> companyLabelList = Lists.newArrayList();
		List<String> companyLabelIdList = company.getCompanyLabelIdList();
		List<CompanyLabel> companyLabelAllList = companyLabelService.findAllList(new CompanyLabel());
		for (CompanyLabel cl : companyLabelAllList) {
			if (companyLabelIdList.contains(cl.getId())) {
				companyLabelList.add(cl);
			}
		}
		company.setCompanyLabelList(companyLabelList);
		companyService.saveCompany(company);
		addMessage(redirectAttributes, "保存企业成功");
	}

	@RequiresPermissions("company:company:edit")
	@RequestMapping(value = "delete")
	public String delete(Company company, RedirectAttributes redirectAttributes) {
		companyService.delete(company);
		addMessage(redirectAttributes, "删除企业成功");
		return "redirect:"+Global.getAdminPath()+"/company/company/?repage";
	}


	@RequestMapping(value = "/upFile", method = RequestMethod.POST)
	@ResponseBody
	public void upLoad(@RequestParam MultipartFile myFile, HttpServletRequest request,HttpContext context) throws Exception {

		InputStream inputStream = myFile.getInputStream();
		String newFileName = getNewName(myFile);
		// 查找即将上传到服务器中的真实路径!
		String realPath = request.getServletContext().getRealPath("/static/images");
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 程序 写出 上传服务器!
		String name = realPath + "/" + newFileName;
		System.out.println("name:"+name);
		FileOutputStream fileOutputStream = new FileOutputStream(name);
		// 复制多功能文件(图片)以及关闭流
		IOUtils.copy(inputStream, fileOutputStream);

		inputStream.close();
		fileOutputStream.close();
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