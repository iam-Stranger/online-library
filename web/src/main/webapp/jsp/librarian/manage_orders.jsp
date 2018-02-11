<%--@elvariable id="abs_path" type="${pageContext.request.contextPath}"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="label.libr.manage_orders"/></title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />

<div class="container">

    <form action="${abs_path}/controller" method="post">
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="label.libr.manage_orders"/></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive align-content-center" width="100%">
                        <tr>
                            <th>#</th>
                            <th style=""><fmt:message key="user.name"/></th>
                            <th style="width: available"><fmt:message key="book.info.title"/></th>
                            <th class="c"><fmt:message key="order.date_from"/></th>
                            <th class="c"><fmt:message key="order.date_to"/></th>
                            <th class="c"><fmt:message key="order.type"/></th>
                            <th class="c"><fmt:message key="order.status"/></th>
                            <th class="c"><fmt:message key="order.action"/></th>
                            <%--<th class="c"><fmt:message key="list.edit"/></th>--%>
                        </tr>

                        <c:forEach var="order" items="${order_list}" varStatus="loop">
                            <c:choose>
                                <c:when test="${order.statusId == 2}">
                                    <c:choose>
                                        <c:when test="${order.dateTo < today}"><tr class="text-danger"></c:when>
                                        <c:otherwise><tr class="text-success"></c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${order.statusId == 1}">
                                    <form action="/controller" method="get">
                                    <input type="hidden" name="command" value="dialog_give_book"/>
                                    <tr class="text-primary">
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                </c:otherwise>
                            </c:choose>
                            <td>${loop.index+1}</td>
                            <td>${order.user.firstName} ${order.user.lastName}</td>
                            <td>${order.book.title}</td>
                            <td class="c">
                                <fmt:parseDate value="${order.dateFrom}" var="dateFrom" pattern="yyyy-MM-dd"/>
                                <fmt:formatDate value="${dateFrom}" type="date" pattern="dd.MM.yyyy"/>
                            </td>
                            <c:choose>
                                <c:when test="${order.statusId == 2}">
                                    <td class="c">
                                        <fmt:parseDate value="${order.dateTo}" var="dateTo" pattern="yyyy-MM-dd"/>
                                        <fmt:formatDate value="${dateTo}" type="date" pattern="dd.MM.yyyy" />
                                    </td>
                                </c:when>
                                <c:when test="${order.statusId == 1}">
                                    <td class="c"></td>
                                </c:when>
                            </c:choose>
                            <td class="c">
                            <c:choose>
                                <c:when test="${order.statusId == 2}">
                                    <fmt:message key="order.type.${order.orderTypeId}"/>
                                </c:when>
                                <c:when test="${order.statusId == 1}">
                                    <select id="order_type" name="order_type" class="form-control form-control-sm text-primary">
                                        <c:forEach var="i" begin="0" end="1">
                                            <c:choose>
                                                <c:when test="${i == order.orderTypeId}">
                                                    <option value="${i}" selected><fmt:message key="order.type.${i}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${i}"><fmt:message key="order.type.${i}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:when>
                            </c:choose>
                            </td>
                            <td class="c"><fmt:message key="order.status.${order.statusId}"/></td>
                            <td class="c">
                            <c:choose>
                                <c:when test="${order.statusId == 2}">
                                   <a href="${abs_path}/controller?command=dialog_take_book&id=${order.id}" name="return" class="btn btn-sm btn-outline-secondary"><fmt:message key="button.orders.return"/></a>
                                </c:when>
                                <c:when test="${order.statusId == 1}">
                                        <a href="${abs_path}/controller?command=dialog_dismiss_book&id=${order.id}" name="dismiss" class="btn btn-sm btn-outline-danger"><fmt:message key="button.orders.dismiss"/></a>
                                        <button type="submit" name="id" value="${order.id}" class="btn btn-sm btn-outline-success"><fmt:message key="button.orders.issue"/></button>
                                </c:when>
                            </c:choose>
                            </td>
                            <%--<td style="text-align: center"><a href="${abs_path}/controller?command=edit_genre_info&id=${order.id}"--%>
                                    <%--class="btn-sm btn btn-outline-primary"><i class="far fa-edit"></i></a>--%>
                            <%--</td>--%>
                            </tr>
                        </form>
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