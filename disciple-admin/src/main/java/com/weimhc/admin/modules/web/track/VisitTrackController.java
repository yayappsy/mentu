/**
 * 
 */
package com.weimhc.admin.modules.web.track;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.admin.core.utils.VisitTrackUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.api.modules.dto.req.VisitTrackRQ;
import com.weimhc.framework.taobao.resp.IpAddressInfo;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.track.entity.VisitTrack;
import com.weimhc.modules.track.service.VisitTrackService;
import com.weimhc.modules.track.utils.VisitBrowserAndOsUtils;
import com.weimhc.modules.track.utils.VisitSearchSourceUtils;

/**
 * 访问跟踪Controller
 * 
 * @author lc
 * @version 2017-04-07
 */
@Controller
@RequestMapping(value = "${adminPath}/track/visitTrack")
public class VisitTrackController extends AdminBaseController {

	@Autowired
	private VisitTrackService visitTrackService;

	@ModelAttribute
	public VisitTrack get(@RequestParam(required = false) String id) {
		VisitTrack entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = visitTrackService.get(id);
		}
		if (entity == null) {
			entity = new VisitTrack();
		}
		return entity;
	}

	@RequiresPermissions("track:visitTrack:view")
	@RequestMapping(value = { "list", "" })
	public String list(VisitTrack visitTrack, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<VisitTrack> page = visitTrackService
				.findPage(new Page<VisitTrack>(request, response), visitTrack);
		model.addAttribute("page", page);
		model.addAttribute("visitTrack", visitTrack);
		return "modules/track/visitTrackList";
	}

	@RequiresPermissions("track:visitTrack:view")
	@RequestMapping(value = "form")
	public String form(VisitTrack visitTrack, Model model) {
		model.addAttribute("visitTrack", visitTrack);
		return "modules/track/visitTrackForm";
	}

	@RequiresPermissions("track:visitTrack:edit")
	@RequestMapping(value = "save")
	public String save(VisitTrack visitTrack, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, visitTrack)) {
			return form(visitTrack, model);
		}
		visitTrackService.save(visitTrack);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/track/visitTrack/?repage";
	}

	@RequiresPermissions("track:visitTrack:edit")
	@RequestMapping(value = "delete")
	public String delete(VisitTrack visitTrack,
			RedirectAttributes redirectAttributes) {
		visitTrackService.deleteEntity(visitTrack);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/track/visitTrack/?repage";
	}

	@RequiresPermissions("track:visitTrack:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		visitTrackService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "insert")
	public void insert(VisitTrackRQ visitTrackRQ, HttpServletRequest request,
			HttpServletResponse response) {
		VisitTrack visitTrack = new VisitTrack();
		BeanUtils.copyProperties(visitTrackRQ, visitTrack);
		visitTrack.setIsNewRecord(true);
		visitTrack.setId(visitTrackRQ.getPageViewId());

		IpAddressInfo ipAddressInfo = AccessUtils.getIpInfo(request);
		BeanUtils.copyProperties(ipAddressInfo, visitTrack);

		VisitBrowserAndOsUtils.dealWithBrowserAndOs(visitTrack,
				UserAgentUtils.getUserAgentString(request));

		VisitSearchSourceUtils.dealWithSearchSource(visitTrack);

		VisitTrackUtils.saveLog(request, visitTrack);

		response.setStatus(200);
	}
}