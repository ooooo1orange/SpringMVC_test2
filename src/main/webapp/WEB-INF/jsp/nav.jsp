<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="index">Blog GO Go GO</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="blogedit">新增文章</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="post">我的發文</a></li>
				<c:choose>
					<c:when test="${empty pageContext.request.userPrincipal.name}">
						<li class="nav-item"><a class="nav-link" href="login">Login!</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="logout">Hi
								${pageContext.request.userPrincipal.name} !&nbsp;&nbsp;&nbsp;Logout!</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>