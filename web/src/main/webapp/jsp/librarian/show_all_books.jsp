<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.libr.show_all_books"/></title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container">

    <form action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="dialog_delete"/>
        <input type="hidden" name="type" value="delete_book"/>
        <input type="hidden" name="return" value="show_all_books"/>
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.libr.show_all_books"/></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th class="">#</th>
                            <th class="w-50"><fmt:message key="book.info.title"/></th>
                            <th class=""><fmt:message key="book.info.publish_year"/></th>
                            <th class=""><fmt:message key="book.info.total_amount"/></th>
                            <th class=""><fmt:message key="book.info.real_amount"/></th>
                            <th class="w-10" style="text-align: center"><fmt:message key="list.edit"/></th>
                            <th class="w-10" style="text-align: center"><fmt:message key="list.delete"/></th>
                        </tr>
                        <c:forEach var="book" items="${book_list}" varStatus="loop">

                            <c:choose>
                                <c:when test="${book.isDeleted}">
                                    <tr class="table-light text-muted">
                                    <c:set var="color" value="btn-outline-light"/>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                    <c:set var="color" value="btn-outline-primary"/>
                                </c:otherwise>
                            </c:choose>

                            <td>${loop.index+1}</td>
                            <td>${book.title}</td>
                            <td>${book.publishYear}</td>
                            <td>${book.totalAmount}</td>
                            <td>${book.realAmount}</td>

                            <td style="text-align: center"><a
                                    href="${abs_path}/controller?command=edit_book_info&id=${book.id}"
                                    class="${color} btn-sm btn"><i class="far fa-edit"></i></a>
                            </td>
                            <td style="text-align: center">
                                <c:if test="${!book.isDeleted}">
                                    <button type="submit" name="id" value="${book.id}" class="btn btn-outline-danger btn-sm"><i
                                            class="far fa-times-circle"></i></button>
                                </c:if>
                            </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="card-footer text-right">
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>
</body>
</html>