<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.image" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic }/jquery-toolbar/1.1.0/jquery.toolbar.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic }/jquery-toolbar/1.1.0/jquery.toolbar.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic }/jquery-toolbar/jquery.toolbar.custom.css?ver=0.4">

<script type="text/javascript">
	$(document).ready(
			function() {
				$inputForm = $("#inputForm");
				$(".icheck").iCheck({
					checkboxClass : 'icheckbox_square-blue',
					radioClass : 'iradio_square-blue',
					increaseArea : '20%'
				}).on('ifChecked', function(event) {
					selectedThumbnail($(this).parents(".thumbnail"));
				}).on('ifUnchecked', function(event) {
					unSelectedThumbnail($(this).parents(".thumbnail"));
				});

				$(".thumbnail").on("mouseenter", function() {
					mouseenterThumbnail($(this));
				}).on("mouseleave", function() {
					mouseleaveThumbnail($(this));
				});

				function mouseenterThumbnail(obj) {
					obj.addClass("hover");
					obj.find(".thumbnail-check").show();
					obj.find(".thumbnail-options").show();
				}

				function mouseleaveThumbnail(obj) {
					obj.removeClass("hover");
					if (!obj.hasClass("selected")) {
						obj.find(".thumbnail-check").hide();
					}
					obj.find(".thumbnail-options").hide();
				}

				function selectedThumbnail(obj) {
					obj.addClass("selected");
				}

				function unSelectedThumbnail(obj) {
					obj.removeClass("selected");
				}

				$('div[data-toolbar="user-options"]').toolbar({
					content : '#user-options',
					position : 'left',
					style : "custom"
				});

				$('div[data-toolbar="user-options"]').on('toolbarItemClick',
						function(event, obj) {
							var $this = $(this);
							var $clickedOjb = $(obj);
							var imageData = $this.data();
							if ($clickedOjb.hasClass("option-preview")) {

								imagePreview(imageData.imageUrl);

							} else if ($clickedOjb.hasClass("option-edit")) {

								imageEdit();

							} else if ($clickedOjb.hasClass("option-delete")) {

								$this.parents(".thumbnail").remove();
								imageData.toolbarObj.toolbar.remove();
							}
						});

				function imageEdit() {
					layer.open({
						type : 2,
						title : '预览',
						shadeClose : true,
						area : [ '60%', '80%' ],
						content : "${ctx}/image/image/form"
					});
				}

				function imagePreview(url) {
					$("#imagePreview").find("img").attr("src", url);
					layer.open({
						type : 1,
						title : '预览',
						shadeClose : true,
						area : [ '60%', '80%' ],
						skin : 'layui-layer-nobg', //没有背景色
						content : $("#imagePreview").html()
					});
				}
			});
</script>
<style type="text/css">
.imagePreview.thumbnail {
	overflow: hidden;
	padding: 5px;
	position: relative;
	font-family: 微软雅黑, Arial, Helvetica, sans-serif;
	font-size: 15px;
	overflow-x: hidden;
	overflow-y: hidden;
	text-align: center;
}

.imagePreview.thumbnail .thumbnail-image {
	margin-left: 0px;
}

.imagePreview.thumbnail .thumbnail-image img {
	height: 100%;
	width: 100%;
}

.imageView .thumbnail {
	overflow: hidden;
	padding: 5px;
	position: relative;
	font-family: 微软雅黑, Arial, Helvetica, sans-serif;
	font-size: 15px;
	overflow-x: hidden;
	overflow-y: hidden;
	text-align: center;
}

.imageView .thumbnail .thumbnail-check {
	position: absolute;
	width: 10px;
	height: 10px;
	float: left;
	z-index: 100;
	padding: 5px;
	left: 0;
	top: 0;
}

.imageView .thumbnail .thumbnail-check div {
	background-color: #fff;
}

.imageView .thumbnail .thumbnail-image {
	display: inline-block;
	margin-left: 0px;
}

.imageView .thumbnail .thumbnail-image img {
	height: 160px;
	width: 240px;
}

.imageView .thumbnail .caption {
	display: block;
	text-align: center;
}

.imageView .thumbnail .thumbnail-options {
	float: left;
}

.imageView .thumbnail.hover {
	background-color: #d4d4d4;
}

.imageView .thumbnail.hover .caption {
	color: #fff;
}

.imageView .thumbnail.selected {
	background-color: #4D90CB;
}

.imageView .thumbnail.selected .caption {
	color: #fff;
}

.imageView .thumbnail .thumbnail-options {
	position: absolute;
	float: right;
	z-index: 100;
	padding: 5px;
	right: 0;
	top: 0;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/image/image/manager"><spring:message
					code="图片库" /></a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="image"
		action="${ctx}/image/image/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message code="类型" />：
			</label>
				<div class="btn-group">
					<button class="btn btn-primary" type="button">全部</button>
					<button class="btn" type="button">gif</button>
					<button class="btn" type="button">jpg</button>
					<button class="btn" type="button">png</button>
					<button class="btn" type="button">jpeg</button>
				</div></li>
			<li><label> <spring:message code="上传日期" />：
			</label>
				<div class="btn-group">
					<button class="btn btn-primary" type="button">全部</button>
					<button class="btn" type="button">今天</button>
					<button class="btn" type="button">本周</button>
					<button class="btn" type="button">本月</button>
					<button class="btn" type="button">自定义</button>
				</div></li>
			<li><label> <spring:message code="Image.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="imagePreview" class="hide">
		<div class="imagePreview thumbnail">
			<div class="thumbnail-image">
				<img alt="预览图片" src="" />
			</div>
		</div>
	</div>
	<div id="user-options" class="toolbar-icons hidden">
		<a href="javascript:;" class="option-preview"><i
			class="fa icon-search"></i> <span class="text">预览</span> </a> <a
			href="javascript:;" class="option-edit"><i class="fa icon-pencil"></i><span
			class="text">编辑</span></a> <a href="javascript:;" class="option-delete"><i
			class="fa icon-trash"></i><span class="text">删除</span></a>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<ul class="thumbnails">
					<li class="imageView">
						<div class="thumbnail">
							<div class="thumbnail-check hide">
								<input type="checkbox" class="icheck">
							</div>
							<div class="thumbnail-image">
								<img alt="300x200"
									src="http://www.bootcss.com/p/layoutit/img/people.jpg" />
							</div>
							<div class="caption">
								<p>冯诺尔曼结构</p>
								<p>2017-05-03</p>
							</div>
							<div class="thumbnail-options hide">
								<div data-toolbar="user-options"
									data-image-url="http://www.bootcss.com/p/layoutit/img/people.jpg"
									class="btn-toolbar btn-toolbar-custom pull-left">
									<i class="icon-list"></i>
								</div>
							</div>
						</div>
					</li>
					<li class="imageView">
						<div class="thumbnail">
							<div class="thumbnail-check hide">
								<input type="checkbox" class="icheck">
							</div>
							<div class="thumbnail-image">
								<img alt="300x200"
									src="http://www.bootcss.com/p/layoutit/img/city.jpg" />
							</div>
							<div class="caption">
								<p>哈佛结构</p>
								<p>2017-05-03</p>
							</div>
							<div class="thumbnail-options hide">
								<div data-toolbar="user-options"
									data-image-url="http://www.bootcss.com/p/layoutit/img/city.jpg"
									class="btn-toolbar btn-toolbar-custom pull-left">
									<i class="icon-list"></i>
								</div>
							</div>
						</div>
					</li>
					<li class="imageView">
						<div class="thumbnail">
							<div class="thumbnail-check hide">
								<input type="checkbox" class="icheck">
							</div>
							<div class="thumbnail-image">
								<img alt="300x200"
									src="http://www.bootcss.com/p/layoutit/img/sports.jpg" />
							</div>
							<div class="caption">
								<p>改进型哈佛结构</p>
								<p>2017-05-03</p>
							</div>
							<div class="thumbnail-options hide">
								<div data-toolbar="user-options"
									data-image-url="http://www.bootcss.com/p/layoutit/img/sports.jpg"
									class="btn-toolbar btn-toolbar-custom pull-left">
									<i class="icon-list"></i>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>