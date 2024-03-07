<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/front.css" rel="stylesheet" type="text/css">

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
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>
<div id="main_img"><img src="${pageContext.request.contextPath}/resources/images/main_img.jpg"
 width="971" height="282"></div>
<!-- 메인이미지 들어가는곳 -->
<!-- 메인 콘텐츠 들어가는 곳 -->
<article id="front">
<div id="solution">
<div id="hosting">
<h3>Web Hosting Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="security">
<h3>Web Security Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="payment">
<h3>Web Payment Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
</div>
<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">Security</span> News</h3>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
</div>
<div id="news_notice">
<h3 class="brown">News &amp; Notice</h3>
<table>

<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td> -->
<!--     <td>2012.11.02</td></tr> -->
<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td> -->
<!--     <td>2012.11.02</td></tr> -->
<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td> -->
<!--     <td>2012.11.02</td></tr> -->
<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td> -->
<!--     <td>2012.11.02</td></tr> -->
<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td> -->
<!--     <td>2012.11.02</td></tr> -->
</table>
</div>
</article>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터 들어가는 곳 -->
</div>

<!-- 
dataType: 'json' => 데이터 리턴타입 종류
xml(eXtensible Markup Language)형태 : 확장된 마크업 언어, 사용자가 정의하는 태그 사용
							ex)		<member>
										<person><id>abc</id><pw>123</pw><name>태조</name></person>
										<person><id>bcd</id><pw>456</pw><name>정조</name></person>
										<person><id>cde</id><pw>789</pw><name>태종</name></person>
									</member>
json(JavaScript Object Notation)형태 : 자바스크립트 객체(배열) 속성,값의 쌍 형태로 데이터 표현, 
									 웹과 컴퓨터 프로그램에서 용량이 적은 데이터를 교환하기 위해 데이터 객체 속성 값의 쌍 형태로 표현하는 형식
							ex)		 [
									 	{"id" : "abc", "pw" : "123", "name" : "태조"},
									 	{"id" : "bcd", "pw" : "456", "name" : "정조"},
									 	{"id" : "cde", "pw" : "789", "name" : "태종"},
									 ]
 -->


<script type="text/javascript">
	$(function(){
		//class="brown"을 클릭했을 때
		$('.brown').click(function(){
			//alert('클릭');
			//table 화면 초기화
			$('table').html('');
			
			
			$.ajax({
				//type : 'get', type은 기본적으로(작성하지 않으면) get방식
				url : '${pageContext.request.contextPath}/board/listjson',
				dataType: 'json',
				success : function(result){
					//alert(result);
					//[object Object],[object Object],[object Object],[object Object],[object Object]
					//json => 자바스크립트 배열 => 반복문을 이용한 접근 => 출력
					//$.each(배열 대상, 접근(function));
					//.each() 반복문, result 배열변수, index 배열접근 순서값, item 배열의 하나의 값, 한칸의 배열내용을 저장
					$.each(result,function(index, item){
						//alert(index);
						//tiem.키 이름 접근
						//alert(item.subject);
						
						//.html() => table 대상에 html 내용을 넣음. 기존 내용은 사라짐
						//$('table').html('<tr><td class="contxt"><a href="#">'+item.subject+'</a></td><td>'+item.date+'</td></tr>');
						//.append() => table 대상에 내용 뒷부분에 추가해서 넣음. 기존 내용은 유지
						//item.date 숫자 => 날짜 변경
						var date = new Date(item.date);
						var d = date.getFullYear() +"."+ (date.getMonth()+1) +"."+ date.getDate();
						$('table').append('<tr><td class="contxt"><a href="#">'+item.subject+'</a></td><td>'+d+'</td></tr>');
						
					});//$.each
				}//function(result)
			});//ajax
			
			//이벤트 중지 => 클릭한 이벤트 자기자신 this => unbind()
			//$(this).unbind();	
			//$('.brown').off('click');
		});//$('.brown')
		
	});//$(function()

</script>

</body>
</html>