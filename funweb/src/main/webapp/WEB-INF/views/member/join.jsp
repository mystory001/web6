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
<!-- include 경로 => jsp 문법 => 상대적인 웹 경로를 적용. 현 파일을 기준으로 상대적으로 경로를 표시 -->
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
<h1>Join Us</h1>
<form action="${pageContext.request.contextPath}/member/insertPro" id="join" method="post">
<!-- 비밀번호가 포함되어있으면 method를 post -->
<fieldset>
<legend>Basic Info</legend>
<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="dup.check" class="dup"><br>
<label></label>
<div id="dupdiv"></div><br>


<label>Password</label>
<input type="password" name="pw"><br>
<label>Retype Password</label>
<input type="password" name="pw2"><br>
<label>Name</label>
<input type="text" name="name"><br>
<label>E-Mail</label>
<input type="email" name="email"><br>
<label>Retype E-Mail</label>
<input type="email" name="email2"><br>
</fieldset>

<fieldset>
<legend>Optional</legend>
<label>Address</label>
<input type="text" name="address"><br>
<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
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

<!-- Ajax(Asynchronous JavaScript and XML) 비동기적인 웹 애플리케이션의 제작을 위해 웹 개발 기법 -->
<script type="text/javascript">

//<input type="button" value="dup. check" class="dup">
	$(function() {
		//대상.함수()
		$(".dup").click(function(){
// 			alert("클릭");
			//$(대상).함수 이지만, ajax는 주소값
			//속성이 여러 개 있을 경우
			
			//alert($('.id').val()); //class="id" 아이디 상자 대상 => .val() value값
			//${pageContext.request.contextPath}/member/idCheck?id=값
			$.ajax({
				//속성 : 값, 속성 : 값,...
				url : '${pageContext.request.contextPath}/member/idCheck',
				data : {'id' : $('.id').val()}, //data : {이름(아이디):값, 이름: 값}
				success : function(result){
					//result 출력 결과값
					if(result=="iddup"){
						result = "사용불가능한 아이디입니다.";
					}else{
						result = "사용가능한 아이디입니다.";
					}
					
// 					alert(result);
// 					id="dupdiv" 결과값 넣기 => 대상에 html 문서를 넣기
					$('#dupdiv').html(result);
				}
			});
		
		});
		
		
// 		$(".dup").on("click",function(){
// 			alert("on클릭");			
// 		});
		
	});
	


</script>


</body>
</html>