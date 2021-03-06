<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="book.find_book_by_title"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-7">

    <form action="controller">
        <input type="hidden" name="command" value="show_find_books"/>
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="book.find_book_by_title"/></h5>
                <div class="card-text m-2" style="padding: 5px">
                    <fieldset>
                        <div class="form-group row">
                            <label class="col-form-label col-5" for="title"><fmt:message key="find.welcome"/></label>
                            <div class="col-7">
                                <input class="form-control " id="title" value="" name="title"
                                       type="text" required="">
                                <%--<span class="form-text small text-muted"><fmt:message key="tips.genre_author"/></span>--%>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.button.find"/></button>
                    <%--<a href="${abs_path}/controller?command=to_sign_in_page" name="return" class="btn btn-success">--%>
                        <%--<fmt:message key="button.dialog.ok"/></a>--%>
                </div>
            </div>
        </div>
    </form>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>
