<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="user.sign_in"/></title>

    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("confirm").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("confirm").value;
            var pass1 = document.getElementById("password").value;
            if (pass1 != pass2)
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
        <input type="hidden" name="command" value="sign_in">
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="user.sign_in"/></h5>
                <div class="card-text m-2">
                    <%--<div class="alert alert-danger">--%>
                    <%--<a class="close" data-dismiss="alert" href="#">×</a>${errors.login}</div>--%>
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-3" for="login"><fmt:message key="user.login"/></label>
                            <div class="col-9">
                                <input class="form-control" id="login" name="login" type="text"
                                       value="" required="">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-form-label col-3" for="password"><fmt:message key="user.pass"/></label>
                            <div class="col-9">
                                <input class="form-control" id="password" name="password" type="password"
                                       value="" required="">
                            </div>
                        </div>

                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-primary"><fmt:message key="button.sign_in"/></button>
                </div>
            </div>
        </div>
    </form>
    <div style="text-align: center">
        <a class="btn btn-sm btn-outline-primary" href="${abs_path}/controller?command=to_sign_up"><fmt:message key="user.sign_up"/></a>
    </div>
</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>

</body>
</html>
