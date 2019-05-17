<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
	
</script>
<style>
.active-pink-2 input[type=text]:focus:not([readonly]) {
    border-bottom: 1px solid #f48fb1;
    box-shadow: 0 1px 0 0 #f48fb1;
}
.active-pink input[type=text] {
    border-bottom: 1px solid #f48fb1;
    box-shadow: 0 1px 0 0 #f48fb1;
}
.active-purple-2 input[type=text]:focus:not([readonly]) {
    border-bottom: 1px solid #ce93d8;
    box-shadow: 0 1px 0 0 #ce93d8;
}
.active-purple input[type=text] {
    border-bottom: 1px solid #ce93d8;
    box-shadow: 0 1px 0 0 #ce93d8;
}
.active-cyan-2 input[type=text]:focus:not([readonly]) {
    border-bottom: 1px solid #4dd0e1;
    box-shadow: 0 1px 0 0 #4dd0e1;
}
.active-cyan input[type=text] {
    border-bottom: 1px solid #4dd0e1;
    box-shadow: 0 1px 0 0 #4dd0e1;
}
.active-cyan .fa, .active-cyan-2 .fa {
    color: #4dd0e1;
}
.active-purple .fa, .active-purple-2 .fa {
    color: #ce93d8;
}
.active-pink .fa, .active-pink-2 .fa {
    color: #f48fb1;
}
</style>

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
								${pageContext.request.userPrincipal.name}
								!&nbsp;&nbsp;&nbsp;Logout!</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<!-- Search form -->
			<form class="form-inline md-form form-sm active-cyan-2 mt-2">
			  <input id="search" class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" aria-label="Search">
			  <i class="fas fa-search" aria-hidden="true" onclick="javascript:location.href ='/test2/search?keyword='+$('#search').val();"></i>
			</form>
		</div>
	</div>
</nav>