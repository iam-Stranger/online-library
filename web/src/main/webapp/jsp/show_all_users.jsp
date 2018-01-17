<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All users In the PAGE</title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/import/header.jsp"/>

<div class="container">

    <b>All users in page</b></b><br/>
    <table class="table">
        <c:forEach var="user" items="${user_list}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>

    <b>All users in page</b></b><br/>
    <table class="table">
        <c:forEach var="user" items="${user_list}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>

    <b>All users in page</b></b><br/>
    <table class="table">
        <c:forEach var="user" items="${user_list}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<c:import url="${pageContext.request.contextPath}/import/footer.jsp"/>
</body>
</html>
