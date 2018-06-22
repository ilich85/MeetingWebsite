<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Поиск</title>
    <link rel="stylesheet" href="../resources/css/search.css">
    <link rel="icon" href="../resources/images/heart.png">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="user-search-form">
    <spring:form method="get" modelAttribute="searchRequest" action="/search">
        Укажите город:<br/>
        <spring:input type="text" path="city" value="${searchRequest.city}"/><br/>
        Ищу:<br/>
        <spring:select path="gender" items="${genders}"
                       value="${searchRequest.gender}" label="${genders.get(searchRequest.gender)}">
        </spring:select><br/>
        В возрасте <br/>
        от
        <spring:input cssClass="min age" type="number" path="minAge" value="${searchRequest.minAge}"/>
        до
        <spring:input cssClass="max age" type="number" path="maxAge" value="${searchRequest.maxAge}"/><br/>
        <spring:button>Найти</spring:button>
    </spring:form>
</div>
<div class="search-result">
    <c:choose>
        <c:when test="${searchResult.size()==0}">
            Вы искали
            <c:choose>
                <c:when test="${searchRequest.gender=='Man'}">
                    парней
                </c:when>
                <c:when test="${searchRequest.gender=='Woman'}">
                    девушек
                </c:when>
                <c:otherwise>
                    парней и девушек
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${searchRequest.city==''}">
                    из любого города
                </c:when>
                <c:otherwise>
                    из города ${searchRequest.city}
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${searchRequest.minAge!=null&&searchRequest.maxAge!=null}">
                    от ${searchRequest.minAge} до ${searchRequest.maxAge} лет
                </c:when>
                <c:when test="${searchRequest.minAge!=null&&searchRequest.maxAge==null}">
                    от ${searchRequest.minAge}
                    <c:choose>
                        <c:when test="${searchRequest.minAge>20&&searchRequest.minAge%10==1}">
                            года
                        </c:when>
                        <c:otherwise>лет</c:otherwise>
                    </c:choose>
                </c:when>
                <c:when test="${searchRequest.minAge==null&&searchRequest.maxAge!=null}">
                    до ${searchRequest.maxAge}
                    <c:choose>
                        <c:when test="${searchRequest.maxAge>20&&searchRequest.maxAge%10==1}">
                            года
                        </c:when>
                        <c:otherwise>лет</c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
            <br/>
            К сожалению, по заданным критериям поиска пользователи не найдены.<br/>
            Попробуйте выбрать другие параметры поиска.<br/>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${searchRequest.gender=='Man'}">
                    Анкеты парней
                </c:when>
                <c:when test="${searchRequest.gender=='Woman'}">
                    Анкеты девушек
                </c:when>
                <c:otherwise>
                    <c:if test="${searchRequest.gender!=null}">
                        Анкеты парней и девушек
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:if test="${searchRequest.city!=''&&searchRequest.city!=null}">
                из города ${searchRequest.city}
            </c:if>
            <c:choose>
                <c:when test="${searchRequest.minAge!=null&&searchRequest.maxAge!=null}">
                    от ${searchRequest.minAge} до ${searchRequest.maxAge} лет
                </c:when>
                <c:when test="${searchRequest.minAge!=null&&searchRequest.maxAge==null}">
                    от ${searchRequest.minAge}
                    <c:choose>
                        <c:when test="${searchRequest.minAge>20&&searchRequest.minAge%10==1}">
                            года
                        </c:when>
                        <c:otherwise>лет</c:otherwise>
                    </c:choose>
                </c:when>
                <c:when test="${searchRequest.minAge==null&&searchRequest.maxAge!=null}">
                    до ${searchRequest.maxAge}
                    <c:choose>
                        <c:when test="${searchRequest.maxAge>20&&searchRequest.maxAge%10==1}">
                            года
                        </c:when>
                        <c:otherwise>лет</c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
            <div class="users">
                <c:forEach items="${searchResult}" var="user">
                    <div class="user">
                        <form method="get" action="${pageContext.request.contextPath}/user_${user.userId}">
                            <c:choose>
                                <c:when test="${user.mainPhotoId==null}">
                                    <input type="image" class="user-photo"
                                           src="${pageContext.request.contextPath}../resources/images/no_image.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="image" class="user-photo" src="/photo/${user.mainPhotoId}"/>
                                </c:otherwise>
                            </c:choose>
                        </form>
                        <div class="user-info">
                            <a href="/user_${user.userId}">${user.name}</a> , ${user.age}
                            <c:choose>
                                <c:when test="${user.age>20&&user.age%10==1}">
                                    год
                                </c:when>
                                <c:when test="${user.age>20&&user.age%10==2||user.age%10==3||user.age%10==4}">
                                    года
                                </c:when>
                                <c:otherwise>
                                    лет
                                </c:otherwise>
                            </c:choose>
                            , г. ${user.city}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>