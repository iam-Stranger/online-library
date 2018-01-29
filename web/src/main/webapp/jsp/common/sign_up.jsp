<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="user.sign_up"/></title>

    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("confirm").onchange = validatePassword;
        }
        function validatePassword(){
            var pass2=document.getElementById("confirm").value;
            var pass1=document.getElementById("password").value;
            if(pass1!=pass2)
                document.getElementById("confirm").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("confirm").setCustomValidity('');
            //empty string means no validation error
        }
    </script>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>
<%--<jsp:include page="${pageContext.request.contextPath}/jsp/include/common_imports.jspf"/>--%>
<%--is-invalid--%>

<div class="container col-lg-4 col-md-6">
    <form class="form" action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="validate_info_new_user">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="user.sign_up"/></h5>
                <div class="card-text m-2">
                    <%--<div class="alert alert-danger">--%>
                        <%--<a class="close" data-dismiss="alert" href="#">×</a>${errors.login}</div>--%>
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="firstname"><fmt:message key="user.first_name"/></label>
                            <div class="col-9">
                                <input class="form-control" id="firstname" value="${params['firstname']}" name="firstname"
                                       type="text" required="" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{2,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.firstname"/></span>
                                <span class="form-text small text-danger">${errors['firstname']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="lastname"><fmt:message
                                    key="user.last_name"/></label>
                            <div class="col-9">
                                <input class="form-control" id="lastname" value="${params['lastname']}" name="lastname"
                                       type="text" required="" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{3,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.lastname"/></span>
                                <span class="form-text small text-danger">${errors['lastname']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="login"><fmt:message key="user.login"/></label>
                            <div class="col-9">
                                <input class="form-control" id="login" value="${params['login']}" name="login" type="text"
                                       required="" pattern="[a-zA-Z]{1}[a-zA-Z\d]{3,19}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.login"/></span>
                                <span class="form-text small text-danger">${errors['login']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="email"><fmt:message key="user.email"/></label>
                            <div class="col-9">
                                <input class="form-control" id="email" value="${params['email']}" name="email" type="text"
                                       required="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.email"/></span>
                                <span class="form-text small text-danger">${errors['email']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="password"><fmt:message key="user.pass"/></label>
                            <div class="col-9">
                                <input class="form-control" id="password" name="password" type="password"
                                      value="${params['password']}" required="" pattern="[a-zA-Z0-9._*]{3,9}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.password"/></span>
                                <span class="form-text small text-danger">${errors['password']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="confirm"><fmt:message key="user.confirm"/></label>
                            <div class="col-9">
                                <input class="form-control" id="confirm" name="confirm" type="password"
                                      value="${params['confirm']}" required="" pattern="[a-zA-Z0-9._*]{3,9}">
                                <%--<span class="form-text small text-muted"><fmt:message key="tips.user.password"/></span>--%>
                            </div>
                        </div>

                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-primary"><fmt:message key="button.sign_up"/></button>
                </div>
            </div>
        </div>
    </form>
</div>

<%--<c:import url="${abs_path}/jsp/include/footer.jsp"/>--%>

</body>
</html>
