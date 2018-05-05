package com.weimhc.api.core.web;

import java.io.*;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weimhc.api.modules.dto.resp.cadet.MyFileDto;
import com.weimhc.api.modules.dto.resp.cadet.PathDto;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.FileUploadUtils;
import com.weimhc.framework.utils.UploadType;
import com.weimhc.modules.member.entity.Member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

@Api
@Controller("apiFileUploadController")
@RequestMapping(value = "${apiPath}/fileupload")
public class FileUploadController extends ApiBaseController {

    @ApiOperation(value = "文件上传", notes = "文件上传", tags = {"文件上传", "图片上传"}, authorizations = {
            @Authorization(value = "token")})
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<List<Map<String, Object>>> processUpload(
            @ApiParam(value = "文件类型", allowableValues = "image,file", example = "image") @RequestParam String fileType,
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Member member = getCurrentMember(request);
        ApiResult<List<Map<String, Object>>> apiResult = new ApiResult<>();
        List<Map<String, Object>> resutls = null;
        try {
            resutls = FileUploadUtils.processUpload(request, "file", member.getId(),
                    UploadType.valueOf(fileType));
        } catch (Exception e) {
            logger.error("上传图片错误", e);
        }
        if (resutls == null) {
            return ApiResult.error("上传图片错误");
        }
        apiResult.setData(resutls);
        return apiResult;
    }

    @ApiOperation(value = "文件上传.", notes = "文件上传.", tags = {"文件上传", "图片上传"}, authorizations = {
            @Authorization(value = "token")})
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<MyFileDto> upLoad(@RequestParam MultipartFile myFile, HttpServletRequest request) throws Exception {
        ApiResult<MyFileDto> apiResult = new ApiResult<>();
        // 创建I流--图片文件myFile转化为流--读入程序!
        String requestUrl = request.getScheme() //当前链接使用的协议
                + "://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath() //应用名称，如果应用名称为
                + "/static/images/"; //请求参数
        InputStream inputStream = myFile.getInputStream();
        String newFileName = getNewName(myFile);
        String originalFilename = myFile.getOriginalFilename();
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
        MyFileDto myFileDto = new MyFileDto();
        myFileDto.setFileName(newFileName);
        myFileDto.setRealPath(requestUrl + newFileName);
        myFileDto.setOriginalFilename(originalFilename);
        apiResult.setData(myFileDto);


//        String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
//        System.out.println("获取全路径（协议类型：//域名/项目名/命名空间/action名称?其他参数）url="+url);
//        String url2=request.getScheme()+"://"+ request.getServerName();//+request.getRequestURI();
//        System.out.println("协议名：//域名="+url2);
//
//
//        System.out.println("获取项目名="+request.getContextPath());
//        System.out.println("获取参数="+request.getQueryString());
//        System.out.println("获取全路径="+request.getRequestURL());
        return apiResult;
    }



    @ApiOperation(value = "文件下载.", notes = "文件下载.", tags = {"文件上传", "图片上传"}, authorizations = {
            @Authorization(value = "token")})
    @RequestMapping(value = "/down", method = RequestMethod.POST)
    public void down(String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {

        // 设置响应头:内容处理方式 → attachment(附件,有为下载,没有为预览加载) →指定文件名
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        // 从服务器上下载图片,要找到图片在服务器中的真实位置
        String realPath = request.getServletContext().getRealPath("/disciple-front/static/images");
        // 从服务器上读入程序中
        InputStream fileInputStream = new FileInputStream(realPath + "\\" + fileName);
        // 从程序中写出下载到客户端
        OutputStream outputStream = response.getOutputStream();
        // copy以及关闭流资源
        IOUtils.copy(fileInputStream, outputStream);
        outputStream.close();
        fileInputStream.close();
    }

    @ApiOperation(value = "文件删除.", notes = "文件删除.", tags = {"文件上传", "图片上传"}, authorizations = {
            @Authorization(value = "token")})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> delFile(@ApiParam(value = "物理存在路径") @RequestParam String path,
                                       @ApiParam(value = "文件名称")@RequestParam String filename) throws Exception {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        File file=new File(path+"/"+filename);
        if (file.exists() && file.isFile()) {
            file.delete();
        } else {
            return ApiResult.error(-1, "路径或文件名错误！");
        }
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "获取路径.", notes = "获取路径.", tags = {"文件上传", "图片上传"}, authorizations = {
            @Authorization(value = "token")})
    @RequestMapping(value = "/getPath", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PathDto> getPath(HttpServletRequest request) throws Exception {
        ApiResult<PathDto> apiResult = new ApiResult<>();
        PathDto pathDto = new PathDto();
        String realPath = request.getServletContext().getRealPath("/static/images");
        String requestUrl = request.getScheme() //当前链接使用的协议
                + "://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath() //应用名称，如果应用名称为
                + "/static/images"; //请求参数
        pathDto.setExistPath(realPath);
        pathDto.setUrlPath(requestUrl);

        apiResult.setData(pathDto);
        return apiResult;
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
