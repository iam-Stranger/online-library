<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc"/>

<html>
<head>

    <title><fmt:message key="book.find_book" bundle="${loc}"/></title>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-md-12 col-md-offset-0">
            <div class="panel panel-default">
                <div class="panel-heading">Find Page</div>

                <form action="controller">
                    <input type="hidden" name="command" value="show_find_books"/>
                    <br/>title<br/>
                    <label>

                        <input type="text" name="title" value=""/>
                    </label>
                    <input type="submit" value="Find">
                </form>

            </div>
        </div>
    </div>
</div>

<c:import url="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>


</body>
</html>
