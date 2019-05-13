<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Clean Blog - Start Bootstrap Theme</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css" />" rel="stylesheet" type="text/css">
	
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/clean-blog.min.css" />" rel="stylesheet">

</head>

<body>

	${NavAndFooter.nav}

	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('/test2/resources/img/home-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Clean Blog</h1>
						<span class="subheading">A Blog Theme by Start Bootstrap</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				
				<c:forEach items="${ListBlogContentBean}" var="item">
				
				<div class="post-preview">
				
					<a href="/test2/post?bid=${item.id}">
						<h2 class="post-title">${item.title}</h2>
						<h3 class="post-subtitle">${item.id}</h3>
					</a>
					<p class="post-meta">
						Posted by <a href="#">${item.name}</a> on ${item.modifydate}
					</p>
					
				</div>
				<hr>
				
				</c:forEach>
				
				
				<!-- Pager -->
				<div class="clearfix">
					<a class="btn btn-primary float-right" href="#">Older Posts
						&rarr;</a>
				</div>
			</div>
		</div>
	</div>

	<hr>

	${NavAndFooter.footer}

	<!-- Bootstrap core JavaScript -->
	<script src="/test2/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/test2/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="/test2/resources/js/clean-blog.min.js"></script>

</body>

</html>
