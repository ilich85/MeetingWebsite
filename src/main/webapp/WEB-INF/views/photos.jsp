<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Фотографии</title>
    <link rel="stylesheet" href="../resources/css/photo.css">
    <link rel="icon" href="../resources/images/heart.png">
</head>
<body>
<script type="text/javascript" src="../resources/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../resources/js/commentsForPhoto.js"></script>
<jsp:include page="header.jsp"/>
<input type="hidden" id="userId" value="${userId}">
<input type="hidden" id="currentUser" value="${currentUser}">
<div class="photos">
    <c:forEach items="${photos}" var="photoId">
        <div class="photo-frame">
            <input type="image" class="photo" src="/photo/${photoId}" value="${photoId}"/>
        </div>
    </c:forEach>
</div>
</body>
</html>