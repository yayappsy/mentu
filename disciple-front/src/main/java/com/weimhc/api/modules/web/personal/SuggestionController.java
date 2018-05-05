/*
 * 
 * 
 * 
 */
package com.weimhc.api.modules.web.personal;

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.SuggestionRQ;
import com.weimhc.api.modules.dto.resp.SuggestionDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.suggestion.entity.Suggestion;
import com.weimhc.modules.suggestion.entity.SuggestionType;
import com.weimhc.modules.suggestion.service.SuggestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 意见反馈
 * 
 * @author liuchao
 * 
 */
@Api
@ApiIgnore
@Controller("apiSuggestionController")
@RequestMapping("${apiPath}/suggestion")
public class SuggestionController extends ApiBaseController {
	@Autowired
	private SuggestionService suggestionService;

	/**
	 * 意见反馈
	 * 
	 */
	@ApiOperation(value = "添加留言", notes = "添加留言", tags = { "意见反馈" }, authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/onlineMessage/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<SuggestionDto> onlineMessageAdd(SuggestionRQ body,
			HttpServletRequest request) {
		Member member = getCurrentMember(request);
		Suggestion suggestion = new Suggestion();
		BeanUtils.copyProperties(body, suggestion);
		suggestion.setMember(member);
		suggestion.setMemberNickname(member.getNickname());
		suggestion.setSuggestionType(SuggestionType.suggestion);
		suggestionService.save(suggestion);

		ApiResult<SuggestionDto> apiResult = new ApiResult<>();
		SuggestionDto suggestionDto = new SuggestionDto();
		BeanUtils.copyProperties(suggestion, suggestionDto);
		apiResult.setData(suggestionDto);
		return apiResult;

	}

}