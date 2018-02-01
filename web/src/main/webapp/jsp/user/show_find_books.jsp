<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="book.show_find_book" /></title>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>


<div class="container">

    <br/>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="show_order_list"/>
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="book.show_find_book" /></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="book.info.title" /></th>
                            <th><fmt:message key="book.info.publish_year" /></th>
                            <th><fmt:message key="book.info.real_amount" /></th>
                            <th><fmt:message key="book.info" /></th>
                        </tr>
                        <c:forEach var="book" items="${book_list}" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${book.title}</td>
                                <td>${book.publishYear}</td>
                                <td>${book.realAmount}</td>
                                <td><a href="${abs_path}/controller?command=show_book_info&id=${book.id}" rel="modal:open"
                                       class="btn-sm btn-outline-primary"><i class="fas fa-info-circle"></i></a></td>
                                <td>
                                    <input type="checkbox" name="items" value="${book.id}"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="card-footer text-right">
                    <button class="btn btn-primary my-2 my-sm-0" type="submit"><fmt:message key="book.go_to_order" /></button>
                </div>
            </div>
        </div>
    </form>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>
