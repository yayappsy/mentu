<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('system.name')}</title>
<meta name="decorator" content="default" />
    <c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
<script type="text/javascript">
		$(function(){
		
			$("#memberAdd").click(function(){
				/**查找父类的方法*/
				 var $memberMenu = parent.$("#menu").find("a.menu[data-id='162a508915fd40e58f54ec92f27b68e0']");
				 $memberMenu.click();
				
			});
			$("#storeAdd").click(function(){
				/**查找父类的方法*/
				 var $menu = parent.$("#menu").find("a.menu[data-id='ec6f1e52ef6848ed9ee721d0506403d1']");
				 $menu.click();
				
			});
			$("#productAdd").click(function(){
				/**查找父类的方法*/
				 var $menu = parent.$("#menu").find("a.menu[data-id='90a82078bcb4488da2b8c60c7d8e5dc2']");
				 $menu.click();
				
			});
		});
	</script>
<%--有H-ui打包 --%>
<link href="${ctxStatic}/Hui-iconfont/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<%--直接从http://www.iconfont.cn下载的 --%>
<link href="${ctxStatic}/iconfont/iconfont.css" rel="stylesheet"
	type="text/css" />
<link href="${ctxStatic}/common/welcome.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/home/index">系统信息</a></li>
	</ul>
	<br />

	<div class="page">
		<div class="info-panel">
			<dl class="member">
				<dt>
					<div class="ico">
						<i class="icon Hui-iconfont  Hui-iconfont-user"></i><sub
							title="会员总数"><span><em id="statistics_member">${statsPlateform.memberTotalNum }</em></span></sub>
					</div>
					<h3>会员</h3>
					<h5>新增会员</h5>
				</dt>
				<dd>
					<ul>
						<li class="w100pre normal" style="width: 100%"><a
							href="javascript:void(0)" id="memberAdd">本月新增<sub><em
									id="statistics_week_add_member">${statsPlateform.memberAddNum }</em></sub></a></li>
					</ul>
				</dd>
			</dl>
			<dl class="shop">
				<dt>
					<div class="ico">
						<i class="icon Hui-iconfont Hui-iconfont-dianpu"></i><sub
							title="新增店铺数"><span><em id="statistics_store">${statsPlateform.storeTotalNum }
							</em></span></sub>
					</div>
					<h3>店铺</h3>
					<h5>新开店铺审核</h5>
				</dt>
				<dd>
					<ul>
						<li class="w100pre none"><a  id="storeAdd" href="javascript:void(0)">开店审核<sub><em
									id="statistics_store_joinin">${statsPlateform.storeAddNum }</em></sub></a></li>

					</ul>
				</dd>
			</dl>
			<dl class="goods">
				<dt>
					<div class="ico">
						<i class="icon Hui-iconfont Hui-iconfont-goods"></i><sub
							title="商品总数"><span><em id="statistics_goods">${statsPlateform.productTotalNum }</em></span></sub>
					</div>
					<h3>商品</h3>
					<h5>新增商品</h5>
				</dt>
				<dd>
					<ul>
						<li class="w100pre normal"><a id="productAdd"
							href="javascript:void(0)">本月新增<sub title=""><em
									id="statistics_week_add_product">${statsPlateform.productAddNum }</em></sub></a></li>

					</ul>
				</dd>
			</dl>
			<div class=" clear"></div>
		</div>
	</div>

</body>
</html>