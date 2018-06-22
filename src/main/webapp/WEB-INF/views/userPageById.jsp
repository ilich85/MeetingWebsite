<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/user-info.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>${userInfo.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="hidden-send-message" id="hidden-send-message">
    <div class="send-message-form">
        <spring:form modelAttribute="message" method="post" action="/send-message">
            <spring:textarea cssClass="area" type="text" path="text"/><br/>
            <input hidden name="user" value="${userInfo.id}"/>
            <spring:button>Отправить</spring:button>
        </spring:form>
        <button onclick="document.getElementById('hidden-send-message').style.display = 'none'">Отмена
        </button>
    </div>
</div>
<div class="user-info">
    <c:choose>
    <c:when test="${userInfo==null}">
    <h1>Запрашиваемая вами страница не найдена</h1>
    </c:when>
    <c:otherwise>
    <c:choose>
    <c:when test="${userInfo.mainPhotoId==null}">
    <img src="${pageContext.request.contextPath}../resources/images/no_image.jpg" class="user-photo"/>
    </c:when>
    <c:otherwise>
    <form method="get" action="/user_${userInfo.userId}/photos">
        <input type="image" src="/photo/${userInfo.mainPhotoId}" class="user-photo"/>
    </form>
    </c:otherwise>
    </c:choose>
    <div class="info">
        <h2>
                ${userInfo.name}<br/>
                ${userInfo.age}<br/>
                ${userInfo.city}<br/>
        </h2>
        <c:if test="${characteristic.aboutMe!=null}">
            <h2>''${characteristic.aboutMe}''</h2><br/>
        </c:if>
        <c:if test="${characteristic.growth!=null}">
            Рост: ${characteristic.growth}<br/>
        </c:if>
        <c:if test="${characteristic.weight!=null}">
            Вес: ${characteristic.weight}<br/>
        </c:if>
        <c:if test="${userInfo.userId!=currentUser}">
            <button class="show-message-form"
                    onclick="document.getElementById('hidden-send-message').style.display = 'block'">Отправить сообщение
            </button>
        </c:if>
    </div>
    </c:otherwise>
    </c:choose>
</body>
</html>