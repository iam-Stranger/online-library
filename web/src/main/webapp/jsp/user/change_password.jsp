<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="user.change_password"/></title>

    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("confirm").onchange = validatePassword;
        };

        function validatePassword() {
            var pass2 = document.getElementById("confirm").value;
            var pass1 = document.getElementById("password").value;
            if (pass1 !== pass2)
                document.getElementById("confirm").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("confirm").setCustomValidity('');
            //empty string means no validation error
        }
    </script>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-5 col-md-6">
    <form class="form" action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="change_user_password">
        <input type="hidden" name="login" value="${sessionScope.user.login}">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="user.change_password_message"/></h5>
                <div class="card-text m-2">
                    <fieldset>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="old_password"><fmt:message key="user.pass_old"/></label>
                            <div class="col-9">
                                <input class="form-control" id="old_password" name="old_password" type="password"
                                       value="${params['old_password']}" required="" pattern="[a-zA-Z0-9._*]{3,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.password"/></span>
                                <span class="form-text small text-danger">${errors['old_password']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="password"><fmt:message key="user.pass_new"/></label>
                            <div class="col-9">
                                <input class="form-control" id="password" name="password" type="password"
                                       value="${params['password']}" required="" pattern="[a-zA-Z0-9._*]{3,20}">
                                <span class="form-text small text-muted"><fmt:message key="tips.user.password"/></span>
                                <span class="form-text small text-danger">${errors['password']}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="confirm"><fmt:message key="user.confirm"/></label>
                            <div class="col-9">
                                <input class="form-control" id="confirm" type="password"
                                       value="${params['confirm']}" required="" pattern="[a-zA-Z0-9._*]{3,20}">
                                <%--<span class="form-text small text-muted"><fmt:message key="tips.user.password"/></span>--%>
                            </div>
                        </div>

                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-primary"><fmt:message key="button.dialog.ok"/></button>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
