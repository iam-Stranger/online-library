<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="user.edit_user"/>: ${user.login}</title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<%--is-invalid--%>

<div class="container col-lg-4">

    <form class="form" action="${abs_path}/controller" method="get">
        <input type="hidden" name="command" value="validate_info_edit_user">
        <input type="hidden" name="id" value="${user.id}">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="user.edit_user"/></h5>
                <div class="card-text m-2">
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="firstname"><fmt:message key="user.first_name"/></label>
                            <div class="col-9">
                                <input class="form-control " id="firstname" value="${user.firstName}" name="firstname"
                                       type="text" required="" pattern="[A-ZА-Я]{1}[a-zа-я]{2,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.firstname"/></span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="lastname"><fmt:message key="user.last_name"/></label>
                            <div class="col-9">
                                <input class="form-control" id="lastname" value="${user.lastName}" name="lastname"
                                       type="text" required="" pattern="[A-ZА-Я]{1}[a-zа-я]{3,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.lastname"/></span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="login"><fmt:message key="user.login"/></label>
                            <div class="col-9">
                                <input class="form-control" id="login" value="${user.login}" name="login" type="text"
                                       required="" pattern="[a-zA-Z]{1}[a-zA-Z\d]{3,19}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.login"/></span>
                            </div>
                        </div>

                        <!-- Password input-->
                        <%--<div class="form-group">--%>
                        <%--<label class="col-md-4 form-control-label" for="password">Password</label>--%>
                        <%--<div class="col-md-4">--%>
                        <%--<input id="password" value="${user.password}" name="password" type="password" placeholder="mypassword"--%>
                        <%--class="form-control input-md" required="">--%>
                        <%--</div>--%>
                        <%--</div>--%>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="role"><fmt:message key="user.role"/></label>
                            <div class="col-9">
                                <select id="role" name="role" class="form-control">
                                    <c:forEach var="i" begin="1" end="3">
                                        <c:choose>
                                            <c:when test="${i == user.roleId}">
                                                <option value="${i}" selected><fmt:message
                                                        key="user.role.${i}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${i}"><fmt:message key="user.role.${i}"/></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select></div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="email"><fmt:message key="user.email"/></label>
                            <div class="col-9">
                                <input class="form-control" id="email" value="${user.email}" name="email" type="text"
                                       required="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.email"/></span>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${user.isDeleted eq 'true'}">
                                <c:set var="bool" value="1"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="bool" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="status"><fmt:message key="user.status"/></label>
                            <div class="col-9">
                                <select id="status" name="status" class="form-control">
                                    <c:forEach var="j" begin="0" end="1">
                                        <c:choose>
                                            <c:when test="${j == bool}">
                                                <option value="${j}" selected><fmt:message key="user.status.${j}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${j}"><fmt:message key="user.status.${j}"/></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-success"><fmt:message key="button.dialog.apply"/></button>
                    <button type="reset" class="btn btn-danger"><fmt:message key="button.dialog.cancel"/></button>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
