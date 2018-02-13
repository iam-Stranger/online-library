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


<div class="container  col-lg-8">

    <br/>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="show_current_order_list"/>
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="book.show_find_book" /></h5>
                <div class="card-text" style="padding: 2px">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th>#</th>
                            <th class="w-65"><fmt:message key="book.info.title"/></th>
                            <th class="c"><fmt:message key="book.info.publish_year"/></th>
                            <th class="c"><fmt:message key="book.info"/></th>
                            <th class="c w-10"><fmt:message key="book.check"/></th>
                        </tr>
                        <c:forEach var="book" items="${book_list}" varStatus="loop">

                            <c:choose>
                                <c:when test="${book.realAmount > 0}">
                                    <tr>
                                    <c:set var="disabled" value=""/>
                                </c:when>
                                <c:otherwise>
                                    <tr class="text-muted">
                                    <c:set var="disabled" value="disabled"/>
                                </c:otherwise>
                            </c:choose>

                                <td>${loop.index+1}</td>
                                <td>${book.title}
                                </td>
                                <td class="c">${book.publishYear}</td>
                                <td class="c"><a href="${abs_path}/controller?command=show_book_info&id=${book.id}" rel="modal:open"
                                       class="btn btn-sm btn-outline-primary"><i class="fas fa-info"></i></a></td>
                                <td class="c">
                                    <input type="checkbox" name="items" value="${book.id}" ${disabled}/>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="card-footer text-right">
                    <button class="btn btn-primary" type="submit"><fmt:message key="book.go_to_order" /></button>
                </div>
            </div>
        </div>
    </form>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>
