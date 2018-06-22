<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/messages.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>Сообщения</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="interlocutor-info">
    <form method="get" action="${pageContext.request.contextPath}/user_${interlocutor}">
        <c:choose>
            <c:when test="${photoId==null}">
                <input type="image" src="${pageContext.request.contextPath}../resources/images/no_image.jpg"
                       class="photo"/>
            </c:when>
            <c:otherwise>
                <input type="image" src="/photo/${photoId}" class="photo"/>
            </c:otherwise>
        </c:choose>
    </form>
    ${interlocutorName}
</div>
<div class="messages">
    <c:forEach items="${messages}" var="msg">
        <div class="message">
            <div class="author">${msg.authorName}</div>
            <div class="date">${msg.date}</div>
            <div class="text">${msg.text}</div>
        </div>
    </c:forEach>
</div>
<div class="add-message-form">
    <spring:form modelAttribute="message" method="post" action="/add-message">
        <spring:textarea cssClass="area" type="text" path="text"/>
        <input hidden name="user" value="${interlocutor}"/>
        <spring:button class="button">Отправить</spring:button>
    </spring:form>
</div>
</body>
</html>