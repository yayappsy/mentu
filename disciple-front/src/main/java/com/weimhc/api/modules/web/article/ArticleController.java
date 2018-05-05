/**
 * 
 */
package com.weimhc.api.modules.web.article;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.ArticleCategoryDto;
import com.weimhc.api.modules.dto.resp.ArticleDto;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.PageRS;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.ArticleCategory;
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.utils.ArticleCategoryUtils;
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
 * 其他信息Controller
 * 
 * @author lc
 * @version 2016-12-19
 */
@Api
@ApiIgnore
@Controller("apiArticleController")
@RequestMapping(value = "${apiPath}/new/")
public class ArticleController extends ApiBaseController {

	@Autowired
	private ArticleService articleService;

	@ApiOperation(value = "获取学院动态分类", notes = "获取学院动态分类", tags = "学院动态")
	@RequestMapping(value = "category", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<ArticleCategoryDto>> getRisk(HttpServletRequest request) {
		ApiResult<List<ArticleCategoryDto>> result = new ApiResult<>();
		List<ArticleCategory> articleCategoryList = ArticleCategoryUtils.findByParentId("1");
		List<ArticleCategoryDto> lists = Lists.newArrayList();
		lists = CustomBeanUtils.copyList(articleCategoryList, ArticleCategoryDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "获取学院动态列表", notes = "获取学院动态列表", tags = "学院动态")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<PageRS<ArticleDto>> getRiskList(DefaultPageRQ body,
			@ApiParam(value = "分类id") @RequestParam String id, HttpServletRequest request) {
		ApiResult<PageRS<ArticleDto>> apiResult = new ApiResult<>();
		PageRS<ArticleDto> pageRS = new PageRS<>();

		Article article = new Article();
		article.setCategory(new ArticleCategory(id));
		Page<Article> page = articleService
				.findPage(new Page<Article>(body.getPageNo(), body.getPageSize()), article);
		if (page.getList().size() < 1 || page.getPageable().getLast() < body.getPageNo()) {
			pageRS.setCurrentCount(0);
		} else {
			List<ArticleDto> lists = Lists.newArrayList();
			lists = CustomBeanUtils.copyList(page.getList(), ArticleDto.class);
			pageRS.setPageable(page.getPageable());
			pageRS.setDataList(lists);
		}
		apiResult.setData(pageRS);
		return apiResult;
	}

	@ApiOperation(value = "获取文章详情", notes = "文章详情", tags = { "学院动态" })
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<ArticleDto> get(@ApiParam(value = "id") @RequestParam String id,
			HttpServletRequest request) {
		ApiResult<ArticleDto> api = new ApiResult<ArticleDto>();
		Article article = articleService.get(id);
		if (article == null) {
			return ApiResult.error("该内容不存在");
		}
		ArticleDto articleContentDto = new ArticleDto();
		BeanUtils.copyProperties(article, articleContentDto);
		api.setData(articleContentDto);
		return api;
	}

}