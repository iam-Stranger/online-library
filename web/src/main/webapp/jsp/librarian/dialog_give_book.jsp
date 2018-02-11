<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="dialog.select_date"/>: #${params['id']}</title>
</head>
<body>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
<c:set var="prep_date" value="<%=new Date(new Date().getTime() + 1000 * 60 * 60 *24 *15)%>"/>
<fmt:formatDate var="weekx2" value="${prep_date}" pattern="yyyy-MM-dd" />

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="get">
        <input type="hidden" name="command" value="give_book_to_user">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="order_type" value="${order_type}"/>
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="dialog.select_date"/></h5>
                <div class="card-text m-2">
                    <fieldset>
                        <c:choose>
                            <c:when test="${order_type == 1}">
                                <h6><fmt:message key="dialog.select_date_from_subcr"/></h6>
                            </c:when>
                            <c:otherwise>
                                <h6><fmt:message key="dialog.select_date_from_readr"/></h6>
                            </c:otherwise>
                        </c:choose>
                    </fieldset>
                </div>

                <c:choose>
                    <c:when test="${order_type == 1}">
                        <td class="c">
                            <div class="col-5">
                            <input type="date" class="form-control form-control-sm " name="date_to" value="${weekx2}">
                            </div>
                        </td>
                    </c:when>
                    <c:otherwise>
                            <input type="hidden" name="date_to" value="${today}">
                    </c:otherwise>
                </c:choose>
                <br/>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-success"><fmt:message key="button.orders.issue"/></button>
                    <a href="${abs_path}/controller?command=manage_orders" name="cancel" class="btn btn-primary">
                        <fmt:message key="button.dialog.cancel"/></a>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
