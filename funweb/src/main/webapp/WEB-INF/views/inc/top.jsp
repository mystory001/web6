<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>

<!-- 로그인 유무 표시값 세션에서 가져와서 => 있으면 로그인이 됨, 없으면 로그인이 되지 않음 -->

<!-- 로그인 되지 않았을 때 나타내야할 정보(login, join) -->
<c:if test="${empty sessionScope.id }">
<div id="login"><a href="${pageContext.request.contextPath}/member/login">login</a> | <a href="${pageContext.request.contextPath}/member/insert">join</a></div>
</c:if>

<!-- 로그인 되었을 때 나타나야할 정보(logout, update) -->
<c:if test="${!empty sessionScope.id }">
<div id="login">${sessionScope.id}님 | <a href="${pageContext.request.contextPath}/member/logout">logout</a> | <a href="${pageContext.request.contextPath}/member/update">update</a></div>
</c:if>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="${pageContext.request.contextPath}/member/main">HOME</a></li>
	<li><a href="../company/welcome.html">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="${pageContext.request.contextPath}/board/list">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>