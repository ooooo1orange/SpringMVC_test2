<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>this is blog</title>
<script
	src="http://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<!-- .navbar-expand-{sm|md|lg|xl}決定在哪個斷點以上就出現漢堡式選單 -->
	<!-- navbar-dark 文字顏色 .bg-dark 背景顏色 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<!-- .navbar-brand 左上LOGO位置 -->
		<a class="navbar-brand" href="/test2/blog"> <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" class="d-block" viewBox="0 0 612 612" role="img" focusable="false"><title>Bootstrap</title><path fill="currentColor" d="M510 8a94.3 94.3 0 0 1 94 94v408a94.3 94.3 0 0 1-94 94H102a94.3 94.3 0 0 1-94-94V102a94.3 94.3 0 0 1 94-94h408m0-8H102C45.9 0 0 45.9 0 102v408c0 56.1 45.9 102 102 102h408c56.1 0 102-45.9 102-102V102C612 45.9 566.1 0 510 0z"></path><path fill="currentColor" d="M196.77 471.5V154.43h124.15c54.27 0 91 31.64 91 79.1 0 33-24.17 63.72-54.71 69.21v1.76c43.07 5.49 70.75 35.82 70.75 78 0 55.81-40 89-107.45 89zm39.55-180.4h63.28c46.8 0 72.29-18.68 72.29-53 0-31.42-21.53-48.78-60-48.78h-75.57zm78.22 145.46c47.68 0 72.73-19.34 72.73-56s-25.93-55.37-76.46-55.37h-74.49v111.4z"></path></svg>
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
				<li class="nav-item active"><a class="nav-link" href="/test2/blog">首頁<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="/test2/blogedit">新增文章</a></li>
				<li class="nav-item"><a class="nav-link" href="#">個人頁</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search
				</button>
			</form>
		</div>

	</nav>
	
	<table class="table table-striped table-dark table-rwd-name ">
		<c:forEach items="${ListBlogContentBean}" var="item">
			<tr>
				<td>${item.id}</td>
				<!-- <td>${item.title}</td> -->
				<td>${item.content}</td>
				<td>${item.name}</td>
				<td>${item.modifydate}</td>
				<td><button class="btn btn-primary"
						onclick="javascript:location.href='/test2/blogedit?id=${item.id}'">edit</button></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>