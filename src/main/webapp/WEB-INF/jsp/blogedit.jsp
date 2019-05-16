<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 加下面這兩個meta才有辦法上傳 -->
<meta name="_csrf" th:content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" th:content="${_csrf.headerName}" />

<title>this is editor</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="http://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="//cdn.ckeditor.com/4.11.4/full/ckeditor.js"></script>
<script>
//文章送出
function processData() {
		// alert(CKEDITOR.instances.content.getData());
		var data = {
			${id}
			"title" : $('#title').val(),
			"content" : CKEDITOR.instances.content.getData(),
			"owner" : ${username},
			"tag":$('#tag').val()
		};
		var token = $("meta[name='_csrf']").attr("th:content");
		var header = $("meta[name='_csrf_header']").attr("th:content");
		
		if(data.id==undefined){
			// insert
			$.ajax({
				url : "/test2/insertblog",
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				// dataType : 'json',
				data : data,
				beforeSend: function(xhr) {
			        xhr.setRequestHeader(header, token);  //发送请求前将csrfToken设置到请求头中
			    },
				success : function(data) {
					alert("OK");
					console.log(data);
				}
			});
		}else{
			// update
			$.ajax({
				url : "/test2/updateblog",
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				// dataType : 'json',
				data : data,
				beforeSend: function(xhr) {
			        xhr.setRequestHeader(header, token);  //发送请求前将csrfToken设置到请求头中
			    },
				success : function() {
					alert("OK");
					// console.log(data);
				},
		        error: function(){
		        	alert("XXOK");
	        	}
			});
		}
	}



	
</script>
</head>
<body>
	<!-- .navbar-expand-{sm|md|lg|xl}決定在哪個斷點以上就出現漢堡式選單 -->
	<!-- navbar-dark 文字顏色 .bg-dark 背景顏色 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<!-- .navbar-brand 左上LOGO位置 -->
		<a class="navbar-brand" href="/test2/"> <svg
				xmlns="http://www.w3.org/2000/svg" width="36" height="36"
				class="d-block" viewBox="0 0 612 612" role="img" focusable="false">
				<title>Bootstrap</title><path fill="currentColor"
					d="M510 8a94.3 94.3 0 0 1 94 94v408a94.3 94.3 0 0 1-94 94H102a94.3 94.3 0 0 1-94-94V102a94.3 94.3 0 0 1 94-94h408m0-8H102C45.9 0 0 45.9 0 102v408c0 56.1 45.9 102 102 102h408c56.1 0 102-45.9 102-102V102C612 45.9 566.1 0 510 0z"></path>
				<path fill="currentColor"
					d="M196.77 471.5V154.43h124.15c54.27 0 91 31.64 91 79.1 0 33-24.17 63.72-54.71 69.21v1.76c43.07 5.49 70.75 35.82 70.75 78 0 55.81-40 89-107.45 89zm39.55-180.4h63.28c46.8 0 72.29-18.68 72.29-53 0-31.42-21.53-48.78-60-48.78h-75.57zm78.22 145.46c47.68 0 72.73-19.34 72.73-56s-25.93-55.37-76.46-55.37h-74.49v111.4z"></path></svg>
		</a>
		<!-- .navbar-toggler 漢堡式選單按鈕 -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<!-- .navbar-toggler-icon 漢堡式選單Icon -->
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- .collapse.navbar-collapse 用於外層中斷點群組和隱藏導覽列內容 -->
		<!-- 選單項目&漢堡式折疊選單 -->
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<!-- active表示當前頁面 -->
				<li class="nav-item active"><a class="nav-link" href="/test2/">首頁<span
						class="sr-only">(current)</span></a></li>
				
			</ul>
			
		</div>

	</nav>

	<!-- <img src="<c:url value="/resources/2.jpg" />"></img> -->
	<form id="form_upload" style="width: 40%">
		<div class="custom-file">
			<input name="file" type="file" class="custom-file-input"
				id="customFile" accept="image/*"><label
				class="custom-file-label" for="customFile">第一步：請上傳圖檔</label>
			<!-- 添加隐藏域 -->
			<input id="csrf" type="hidden" th:name="${_csrf.parameterName}"
				th:value="${_csrf.token}" />
		</div>
		<button type="submit">upload</button>

	</form>

	<script>
	//檔案上傳
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});

	$("#form_upload").submit(function(e) {
		e.preventDefault();
		console.log("file upload");
		var formdata = new FormData($("#form_upload")[0]);
		
		var token = $("meta[name='_csrf']").attr("th:content");
		var header = $("meta[name='_csrf_header']").attr("th:content");

		//var a =$("#csrf").attr("th:value");
		//var b =$("#csrf").attr("th:name");

		//header = b;
		//token = a;
		
		$.ajax({
			type : "post",
			url : "/test2/upload",
			enctype : 'multipart/form-data',
			data : formdata,
			cache : false,
			processData : false,
			contentType : false,
			dataType : 'text',
			timeout : 100000,
			 beforeSend: function(xhr) {
			        xhr.setRequestHeader(header, token);  //发送请求前将csrfToken设置到请求头中
			    },
			success : function(data) {
				alert(data);
				console.log('data:' + data);
				console.log('sucess');
			},
			fail : function(data) {
				console.log('data:' + data);
				console.log('fail');
			},
		});
	});
	</script>
	<form name='form' action='#' method='post'>
		<!-- <c:forEach items="${ListBlogBean}" var="item">
		<input type="text" id="title" placeholder="請輸入標題...." required value='${item.title}'/>
		<textarea name="content" id="content" rows="10" cols="80">
		${item.content}
		</textarea>
		<input type="text" id="tag" placeholder="看你要不要輸入tag...." /> <input
			type='button' value='送出' onclick='processData()'>
		</c:forEach> -->

		<input type="text" id="title" placeholder="請輸入標題...." required
			value='${BlogTitleBean}' />
		<textarea name="content" id="content" rows="10" cols="80">
		${BlogContentBean}
		</textarea>
		<input type="text" id="tag" placeholder="看你要不要輸入tag...." /><input
			type='button' value='送出' onclick='processData()'>

	</form>
</body>
<script>
	CKEDITOR.replace('content', {startupMode:${startupMode}});
</script>
</html>