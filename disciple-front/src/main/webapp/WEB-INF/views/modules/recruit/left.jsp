<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

 <!--left--->
  <div class="l_box left">
    	<dl class="menu">
        	<dt>招贤纳士</dt>
        	<c:forEach items="${rfns:getArticleByCategory('6')}" var="article">
            <dd><a href="#${article.id}" >${article.title} </a></dd>
            </c:forEach>
        </dl>
  </div>
  <!--left end--> 