<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
 <script src="${pageContext.request.contextPath}/resources/script/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/member/loginPro" id="join" method="post">
<fieldset>
<legend>Login Info</legend>
<label></label>
<div id="checkdiv"></div><br>
<label>User ID</label>
<input type="text" name="id" class="id"><br>
<label>Password</label>
<input type="password" name="pw" class="pw"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="Submit" class="submit">
<input type="reset" value="Cancel" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
<script>
$(function(){
	
	$('#join').submit(function(){
		
		if($('.id').val() === ''|| $('.id').val() === null){
// 			alert('아이디를 입력해주세요.');
			$('#checkdiv').html("아이디를 입력해주세요.").css("color", "red");
			$('.id').focus();
			return false;
		}
		
		if($('.pw').val() === '' || $('.pw').val() === null){
// 			alert('비밀번호를 입력해주세요.');
			$('#checkdiv').html("비밀번호를 입력해주세요.").css("color", "red");
			$('.pw').focus();
			return false;
		}
		
		//값을 어디서 가져와야함? ajax 처리
// 		if($('.id').val() && $('.pw').val()){
// 			$('#checkdiv').html("회원 정보를 다시 확인해주세요.").css("color","red");
// 		}
		
		
	});
	
});


</script>
</body>
</html>