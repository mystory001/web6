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
 
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<!-- include 경로 => jsp 문법 => 상대적인 웹경로 적용 
     현파일을 기준으로 상대적으로 경로를 표시 -->
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
<fieldset>
<legend>Basic Info</legend>

<label></label>
<div id="checkdiv"></div><br>

<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="dup. check" class="dup"><br>

<label></label>
<div id="dupdiv"></div><br>

<label>Password</label>
<input type="password" name="pw" class="pw"><br>
<label>Retype Password</label>
<input type="password" name="pw2" class="pw2"><br>
<label>Name</label>
<input type="text" name="name" class="name"><br>
<label>E-Mail</label>
<input type="text" name="email" class="email"><br>
<label>Retype E-Mail</label>
<input type="email" name="email2"><br>
<label>성    별</label>
<input type="radio" name="gender" value="남" class="gender1">남
<input type="radio" name="gender" value="여" class="gender2">여
<br><br>
<label>등    급</label>
<select name="grade" class="grade">
	<option value="">등급을 선택하세요</option>
	<option value="1">1등급</option>
	<option value="2">2등급</option>
	<option value="3">3등급</option>
</select><br>
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

<!-- 
Ajax(Asynchronous JavaScript and XML, 에이잭스)
 비동기적인 웹 애플리케이션의 제작을 위한 웹 개발 기법이다. -->

<script type="text/javascript">
// 	$(function(){
// 		// 대상.함수()
// 		//class="dup" 클릭했을때
// 		$('.dup').click(function(){
// // 			alert("클릭");
// //          class="id" 아이디 상자 대상 => .val() value값
// // 			alert($('.id').val());
			
// // 			${pageContext.request.contextPath}/member/idCheck?id=kim
			
// 			$.ajax({
// 				url:'${pageContext.request.contextPath}/member/idCheck',
// 				data:{ 'id' : $('.id').val() },
// 				success:function(result){
// 					// result 출력 결과값
// 					if(result == "iddup"){
// 						result = "아이디 중복";
// 					}else{
// 						result = "아이디 사용가능";
// 					}
// // 					alert(result);
// // 					id="dupdiv" 결과값 넣기 => html() 대상에 html문서를 넣기
// 					$('#dupdiv').html(result);
// 				}
// 			});
			
			
// 		});
		
// // 		$('.dup').on('click',function(){
// // 			alert("on클릭");
// // 		});

// 	});

//===============================================================
	$(function(){
		// class="dup" 버튼 클릭했을때
		$('.dup').click(function(){
// 			alert("클릭");
			// class="id" 텍스트 상자가 비어있으면 "아이디 입력" 포커스 되돌아감
			if($('.id').val() == ""){
				alert("아이디 입력");
				$('.id').focus();
				return;
			}
			// 아이디 중복체크
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/member/idCheck",
				data:{"id":$('.id').val()},
				dataType:'html',
				success:function(result){
// 					alert(result);
					if(result == "iddup"){
						result ="아이디 중복";
					}else{
						result ="아이디 사용가능";
					}
					// id="dupdiv" => 결과값 출력
					$('#dupdiv').html(result);
				},
				error:function(e){
					alert("에러");
				}
			});
			
		});
	});
	
	

	
//===============================================================
	
// 	$(function(){
// 		// <input type="button" value="버튼" onClick="location.href='주소'">
// 		// <input type="submit" value="Submit" class="submit" > 클릭하면
// 		// submit 전송 동작 => form태그 전체 submit
// 		// id="join" 폼태그 전송
// 		// 대상.함수()
// 		$('#join').submit(function(){
// // 			alert("전송버튼 클릭");
// 			// class="id"  value값가져오기
// // 			alert( "아이디" + $('.id').val());
// 			if($('.id').val() == ""){
// // 				alert("아이디 입력하세요");
// 				// id="checkdiv" 에 "아이디 입력하세요" 내용 넣기
// 				// 대상.함수().함수()
// 				$('#checkdiv').html("아이디 입력하세요").css('color','red');

// 				// 아이디 상자에 포커스 깜박
// 				$('.id').focus();
// 				//전송 못하게(false 신호값) 함수 호출한 곳으로 이동(되돌아가기)
// 				return false;
// 			}
			
// 			// class="pw" value값가져오기
// // 			alert( "비밀번호" + $('.pass').val());
// 			if($('.pw').val() == ""){
// // 				alert("비밀번호 입력하세요");
// 				// id="checkdiv" 에 "비밀번호 입력하세요" 내용 넣기
// 				$('#checkdiv').html("비밀번호 입력하세요");
				
// 				// 아이디 상자에 포커스 깜박
// 				$('.pw').focus();
// 				//전송 못하게(false 신호값) 함수 호출한 곳으로 이동(되돌아가기)
// 				return false;
// 			}
			
// 			// class="name" value값가져오기
// // 			alert( "이름" + $('.name').val());
// 			if($('.name').val() == ""){
// // 				alert("이름 입력하세요");
// 				// id="checkdiv" 에 "이름 입력하세요" 내용 넣기
// 				$('#checkdiv').html("이름 입력하세요");
				
// 				// 아이디 상자에 포커스 깜박
// 				$('.name').focus();
// 				//전송 못하게(false 신호값) 함수 호출한 곳으로 이동(되돌아가기)
// 				return false;
// 			}
			
			
			
// 		});
		
// 	});
	
	
//===============================================================
		
	$(function(){
		// 폼태그 id="join" => 전송 submit() 이벤트
		$('#join').submit(function(){
 			//alert("전송");
			//정규표현식 : 문자열을 처리하기 위한 패턴 기반의 문자열. 
			//		 : 정규 표현식을 통해 처리할 문자열의 패턴 지정. 
			//		 : 패스워드 유효성 검사, 전화번호, 이메일 양식 검사
			//		 : 자바, 자바스크립트 모든 언어에 사용 가능
			//		 : 네트워크, 서버 환경 설정 등 공용으로 사용하는 표준 표현식
			//정규표현식 => 영어만 입력 [a-zA-Z]
			//		 => 숫자만 입력 [0-9]
			//		 => 한글만 입력 [가-힣]
			//		 => [] : 하나 이상 포함, () : 안에 문자열 그대로 포함, {} : 문자열 반복
			//		    ^ : 시작하는 문자열, 
			//			$ : 끝나는 문자열, 
			//			. : 1개 문자, 
			//			+ : 반복, 
			//			* : 0번 이상 반복, 
			//			? : 나올 수도 있고 나오지 않을 수도 있는 문자열, 
			//			| : 또는 포함되는 문자열

			//아이디 : 영문 대소문자, 숫자, 특수문자, _, - 입력이 가능하며, 4~20 자리 입력 체크	
			var idCheck = RegExp(/^[a-zA-Z0-9_\-]{4,20}$/);
			if( ! idCheck.test($('.id').val()) ){
					alert("아이디 형식 아님");
					$('.id').focus();
					return false;
				}
				
			//비밀번호 : 영문 대소문자, 숫자, 특수문자(!@#$%^&*) 하나 이상 포함 4~16자리 입력 체크
			var pwCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*]).{4,16}$/);
				if( ! pwCheck.test($('.pw').val()) ){
					alert("비밀번호 형식 아님");
					$('.pw').focus();
					return false;
				}	
				
			//이름 : 한글 2~20자
			var nameCheck = RegExp(/^[가-힣]{2,6}$/);
				if( ! nameCheck.test($('.name').val()) ){
					alert("이름 형식 아님");
					$('.name').focus();
					return false;
				}
				
			//이메일 => 아이디@주소
			var emailCheck = RegExp(/^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-]/);
				if( ! emailCheck.test($('.email').val()) ){
					alert("이메일 형식 아님");
					$('.email').focus();
					return false;
				}
			//radio 제어(check box)	
			if($('.gender1').is(':checked')==false && $('.gender2').is(':checked')==false){
					alert("성별  체크하세요");
					return false;
				}
				
			//select 상자
			if($('.grade').val() == ""){
					alert("등급  선택하세요");
					$('.grade').focus();
					return false;
				}

			
		});
		
	});
		
	
</script>

</body>
</html>