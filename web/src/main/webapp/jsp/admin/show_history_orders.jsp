<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.admin.show_history_orders"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />

<div class="container">

    <br/>
    <div class="card" style="width: 100%;">
        <div class="card-block">
            <h5 class="card-header"><fmt:message key="label.admin.show_history_orders"/></h5>
            <div class="card-text">

                <table class="table table-bordered table-hover table-responsive" width="100%">
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="user.login"/></th>
                        <th><fmt:message key="book.info.title"/></th>
                        <th><fmt:message key="order.date_from"/></th>
                        <th><fmt:message key="order.date_to"/></th>
                        <th><fmt:message key="order.date_return"/></th>
                        <th><fmt:message key="order.type"/></th>
                        <th><fmt:message key="order.status"/></th>
                    </tr>
                    <c:forEach var="order" items="${order_list}" varStatus="loop">
                        <c:choose>
                            <c:when test="${order.statusId >= 3}">
                                <tr class="text-muted">
                            </c:when>
                            <c:when test="${order.statusId == 2}">
                                <c:choose>
                                    <c:when test="${order.dateTo < today}"><tr class="text-danger"></c:when>
                                    <c:otherwise><tr class="text-success"></c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${order.statusId == 1}">
                                <tr class="text-primary">
                            </c:when>
                            <c:otherwise>
                                <tr>
                            </c:otherwise>
                        </c:choose>
                        <td>${loop.index+1}</td>
                        <%--<td>${order.id}</td>--%>
                        <td>${order.user.login}</td>
                        <td>${order.book.title}</td>
                        <td>${order.dateFrom}</td>
                        <td>${order.dateTo}</td>
                        <td>${order.dateReturn}</td>
                        <td><ftm:message key="order.type.${order.orderTypeId}"/></td>
                        <td><ftm:message key="order.status.${order.statusId}"/></td>
                        <%--<td><a href="${abs_path}/controller?command=edit_user_info&id=" class="btn btn-outline-primary"><i class=""><fmt:message key="label.edit"/></i></a></td>--%>
                        </tr>
                    </c:forEach>

                </table>
            </div>

        </div>
    </div>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>