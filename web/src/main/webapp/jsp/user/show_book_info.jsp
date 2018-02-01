<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc"/>

<html>
<head>

</head>
<body>
<div class="container">

    <br/>
    <div class="card">
        <div class="card-block">
            <h6 class="card-header"><fmt:message key="book.show_book_info" bundle="${loc}"/></h6>
            <div class="card-text">


                <table class="table table-responsive table-sm">

                    <tr>
                        <td><fmt:message key="book.info.id" bundle="${loc}"/></td>
                        <td>${book.id}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.title" bundle="${loc}"/></td>
                        <td>${book.title}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.publish_year" bundle="${loc}"/></td>
                        <td>${book.publishYear}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.real_amount" bundle="${loc}"/></td>
                        <td>${book.realAmount}</td>
                    </tr>


                </table>

            </div>
            <div class="card-footer text-right">
                <%--<a href="#" class="btn btn-primary">Go somewhere</a>--%>
            </div>
        </div>
    </div>
    <br/>


</div>

</body>
</html>

