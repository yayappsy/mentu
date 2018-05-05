package com.weimhc.admin.core.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.FileUploadUtils;
import com.weimhc.framework.utils.UploadType;

import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "${adminPath}/fileupload")
public class FileUploadController extends AdminBaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String fileuploadForm() {
		return "modules/fileupload/upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> processUpload(
			@ApiParam(value = "文件类型", example = "image") @RequestParam String type,
			HttpServletRequest request) throws IOException {

		ApiResult<Map<String, Object>> apiResult = new ApiResult<>();
		List<Map<String, Object>> resutls = FileUploadUtils.processUpload(
				request, "file", AdminUserUtils.getUser().getId(),
				UploadType.image);
		if (resutls == null) {
			return ApiResult.error("上传图片错误");
		}
		Map<String, Object> result = resutls.get(0);
		apiResult.setData(result);
		logger.debug(resutls.toString());
		return apiResult;
	}

}
