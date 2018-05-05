<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

 <!--left--->
  <div class="l_box left">
    	<dl class="menu">
        	<dt>${article.title }</dt>
        	<c:forEach items="${rfns:getArticleByCategoryId(article.category.id)}" var="article">
            <dd><a href="${ctx}/aboutUs/index?articleId=${article.id}" id="${article.id}">${article.title} </a></dd>
            </c:forEach>
        </dl>
  </div>
  <!--left end--> 