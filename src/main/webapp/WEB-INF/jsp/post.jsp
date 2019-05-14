<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Clean Blog - Start Bootstrap Theme</title>

  <!-- Bootstrap core CSS -->
  <link href="/test2/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/test2/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/test2/resources/css/clean-blog.min.css" rel="stylesheet">

</head>

<body>

  <c:import url="/nav"/>

  <!-- Page Header -->
  <header class="masthead" >
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading" style = 'padding: 10px 0 50px'>
            <!-- <h1>Man must explore, and this is exploration at its greatest</h1>
            <h2 class="subheading">Problems look mighty small from 150 miles up</h2>
            <span class="meta">Posted by
              <a href="#">Start Bootstrap</a>
              on August 24, 2019</span> -->
          </div>
        </div>
      </div>
    </div>
  </header> 

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <c:forEach items="${ListBlogContentBean}" var="item">
			<br>
			<h1>${item.title}</h1>
			<br>
			${item.content}
			<div>作者 ${item.name}</div><div>編輯時間 ${item.modifydate}</div>
			<c:if test="${item.owner == UserID}">
			<button  class="btn btn-primary"
						onclick="javascript:location.href='/test2/blogedit?id=${item.id}'">edit</button>
			</c:if>
			<hr>	
		</c:forEach>
        </div>
      </div>
    </div>
  </article>

  <hr>

  <c:import url="/footer"/>

  <!-- Bootstrap core JavaScript -->
  <script src="/test2/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/test2/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/test2/resources/js/clean-blog.min.js"></script>

</body>

</html>
