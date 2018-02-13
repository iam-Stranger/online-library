<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>

</head>
<body>
<div class="container">
    <br/>
    <div class="card">
        <div class="card-block">
            <h6 class="card-header"><fmt:message key="book.show_book_info"/></h6>
            <div class="card-text" style="padding: 2px;">
                <table class="table table-responsive">
                    <tr>
                        <td><fmt:message key="book.info.id"/></td>
                        <td>${book.id}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.title"/></td>
                        <td>${book.title}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.publish_year"/></td>
                        <td>${book.publishYear}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.real_amount"/></td>
                        <td>${book.realAmount}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.genre"/></td>
                        <td>
                            <c:forEach var="genre" items="${book.genres}">
                            ${genre.type}
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="book.info.author"/></td>
                        <td>
                            <c:forEach var="author" items="${book.authors}">
                                ${author.name}
                            </c:forEach>
                        </td>
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

