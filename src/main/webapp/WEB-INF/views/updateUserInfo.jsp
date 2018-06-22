<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/update-info.css">
    <link rel="icon" href="../resources/images/heart.png">
    <title>Обновление информации</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="hidden-add-photo" id="hidden-add-photo">
    <div class="add-photo-form">
        <form method="post" action="${pageContext.request.contextPath}/add-user-photo" enctype="multipart/form-data">
            Выберите файл не более 3 МБ<br/>
            <input type="file" name="file"><br/>
            <button class="confirmation">Загрузить</button>
        </form>
        <button class="cancel" onclick="document.location.href='/update-info'">Отмена</button>
    </div>
</div>
<div class="hidden-update-password" id="hidden-update-password">
    <div class="update-password-form">
        <form method="post" action="${pageContext.request.contextPath}/change-password">
            Старый пароль:<br/>
            <input type="password" name="oldPass" required/><br/>
            Новый пароль:<br/>
            <input type="password" name="newPass" required/><br/>
            Новый пароль еще раз:<br/>
            <input type="password" name="confirmPass" required/><br/>
            <button class="confirmation">Изменить</button>
        </form>
        <button class="cancel" onclick="document.location.href='/update-info'">Отмена</button>
    </div>
</div>
<div class="user-info-form">
    <c:choose>
        <c:when test="${userInfo.mainPhotoId==null}">
            <img src="${pageContext.request.contextPath}../resources/images/no_image.jpg" width="200px"
                 height="200px"/>
        </c:when>
        <c:otherwise>
            <form method="get" action="/user_${userInfo.userId}/photos">
                <input type="image" src="/photo/${userInfo.mainPhotoId}" width="200px" height="200px"/>
            </form>
        </c:otherwise>
    </c:choose>
    <br/>
    ${update}<br/>
    ${result}<br/>
    Имя:${userInfo.name}<br/>
    Город:${userInfo.city}<br/>
    Возраст:${userInfo.age}<br/>
    Пол:<c:choose>
    <c:when test="${userInfo.gender=='Man'}">
        Мужской
    </c:when>
    <c:otherwise>
        Женский
    </c:otherwise>
</c:choose><br/>
    <spring:form method="post" modelAttribute="characteristic" action="/update-info">
        О себе: <br/>
        <spring:textarea cssClass="about-me" path="aboutMe" rows="4" cols="20"
                         value="${characteristic.aboutMe}"/><br/>
        Рост:<br/>
        <spring:input path="growth" cssClass="user-param" type="number" min="70" max="250"
                      value="${characteristic.growth}"/>
        <br/>
        Вес:<br/>
        <spring:input path="weight" cssClass="user-param" type="number" min="30" max="300"
                      value="${characteristic.weight}"/>
        <br/>
        <spring:button>Сохранить</spring:button>
    </spring:form>
    <button onclick="document.getElementById('hidden-update-password').style.display='block'">Изменить пароль
    </button>
    <br/>
    <button onclick="document.getElementById('hidden-add-photo').style.display='block'">Загрузить фотографию
    </button>
    <form method="post" action="${pageContext.request.contextPath}/remove-user">
        <button>Удалить анкету</button>
    </form>
</div>
</body>
</html>