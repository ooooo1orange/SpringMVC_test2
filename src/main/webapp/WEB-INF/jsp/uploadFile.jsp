<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="http://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<form id="form_upload">
		<div class="custom-file">
			<input name="file" type="file" class="custom-file-input"
				id="customFile"> <label class="custom-file-label"
				for="customFile">Choose file</label>
		</div>
		<button type="submit">upload</button>
	</form>

	<script>
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
				success : function(data) {
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
</body>
</html>