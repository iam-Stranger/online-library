<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="book.find_book_by_author"/></title>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>

<div class="container col-lg-4">

    <form action="controller" method="get">
        <input type="hidden" name="command" value="show_find_books_by_author"/>
        <div class="card shadow">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="book.find_book_by_author"/></h5>
                <div class="card-text m-2" style="padding: 5px">
                    <fieldset>
                        <div class="form-group row">

                            <label class="col-form-label col-2" for="id"><fmt:message key="book.info.author"/></label>
                            <div class="col-10">
                                <select id="id" name="id" class="form-control">
                                    <c:forEach var="author" items="${authors}">
                                        <option value="${author.id}">${author.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                    </fieldset>
                </div>
                <div class="card-footer text-right">
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.button.find"/></button>
                </div>
            </div>
        </div>
    </form>

</div>

<c:import url="${abs_path}/jsp/include/footer.jsp"/>


</body>
</html>
