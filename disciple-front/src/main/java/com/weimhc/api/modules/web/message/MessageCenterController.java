/**
 * 
 */
package com.weimhc.api.modules.web.message;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.MessageDto;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.PageRS;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.message.entity.ReceivedMessage;
import com.weimhc.modules.message.service.ReceivedMessageService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 信息Controller
 * 
 * @author lc
 * @version 2017-06-16
 */
@Api
@ApiIgnore
@Controller("apiMessageController")
@RequestMapping(value = "${apiPath}/message/center")
public class MessageCenterController extends ApiBaseController {

	@Autowired
	private ReceivedMessageService receivedMessageService;

	@ApiOperation(value = "消息列表", notes = "消息列表", tags = "消息中心", authorizations = {
			@Authorization(value = "token") })
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<PageRS<MessageDto>> list(DefaultPageRQ body, HttpServletRequest request) {
		Member member = getCurrentMember(request);
		if (StringUtils.isBlank(member.getId())) {
			return ApiResult.error(-3,
					MessageSourceUtils.getMessage("error.common.tokenNotExist"));
		}
		ApiResult<PageRS<MessageDto>> apiResult = new ApiResult<>();
		PageRS<MessageDto> pageRS = new PageRS<>();
		ReceivedMessage receivedMessage = new ReceivedMessage();
		receivedMessage.setRecipientId(member.getId());
		Page<ReceivedMessage> page = receivedMessageService.findPage(
				new Page<ReceivedMessage>(body.getPageNo(), body.getPageSize()), receivedMessage);
		if (page.getList().size() < 1 || page.getPageable().getLast() < body.getPageNo()) {
			pageRS.setCurrentCount(0);
		} else {
			List<MessageDto> lists = Lists.newArrayList();
			lists = CustomBeanUtils.copyList(page.getList(), MessageDto.class);
			pageRS.setPageable(page.getPageable());
			pageRS.setDataList(lists);
		}
		apiResult.setData(pageRS);
		return apiResult;
	}

	@ApiOperation(value = "查看消息", notes = "查看消息", tags = "消息中心", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<MessageDto> getMessage(HttpServletRequest request,
			@ApiParam(value = "预约id", required = true) @RequestParam String id) {
		ApiResult<MessageDto> result = new ApiResult<>();
		ReceivedMessage receivedMessage = receivedMessageService.get(id);

		if (StringUtils.isBlank(receivedMessage.getId())) {
			return ApiResult.error(-3,
					MessageSourceUtils.getMessage("error.common.entityNotExist"));
		}
		if (receivedMessage.getIsRead() == false) {
			receivedMessage.setIsRead(true);
			receivedMessageService.save(receivedMessage);
		}
		MessageDto messageDto = new MessageDto();
		BeanUtils.copyProperties(receivedMessage, messageDto);
		result.setData(messageDto);
		return result;
	}

}