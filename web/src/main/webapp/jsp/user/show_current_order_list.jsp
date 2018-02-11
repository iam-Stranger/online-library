<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title><fmt:message key="booklist_order.name"/></title>

    <script>
        function inspectChecked(id) {
            if (document.getElementById('chk' + id).checked === true) {
                document.getElementById('sel' + id).disabled = false;
            } else {
                document.getElementById('sel' + id).disabled = true;
            }
        }
    </script>

</head>
<body>

<c:import url="${pageContext.request.contextPath}/jsp/include/header.jsp"/>


<div class="container col-lg-8">


    <h6 class="text-danger" style="text-align: center"><fmt:message key="dialog.ordering1"/> ${count_psb_books}
        <fmt:message key="dialog.ordering2"/>
        <fmt:message key="dialog.ordering3"/> ${count_psb_books} <fmt:message key="dialog.ordering4"/></h6>
    <br/>
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="create_order"/>
        <div class="card shadow" style="width: 100%;">
            <div class="card-block">
                <h5 class="card-header"><fmt:message key="booklist_order.name"/></h5>
                <div class="card-text">

                    <table class="table table-bordered table-hover table-responsive" width="100%">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="book.info.title"/></th>
                            <th class="c"><fmt:message key="book.info.publish_year"/></th>
                            <th class="c"><fmt:message key="book.info"/></th>
                            <th class="c"><fmt:message key="order.type"/></th>
                            <th class="c"><fmt:message key="book.check"/></th>
                        </tr>
                        <c:forEach var="book" items="${book_list}" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td class="w-50">${book.title}</td>
                                <td class="c">${book.publishYear}</td>
                                <td class="c"><a href="/controller?command=show_book_info&id=${book.id}" rel="modal:open"
                                                 class="btn btn-sm btn-outline-primary"><i class="fas fa-info"></i></a>
                                </td>
                                <td class="c">
                                    <select id="sel${book.id}" name="order_type" disabled class="form-control form-control-sm">
                                        <c:forEach var="i" begin="0" end="1">
                                            <option value="${i}"><fmt:message key="order.type.${i}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td class="c">
                                    <input id="chk${book.id}" type="checkbox" name="items" value="${book.id}"
                                           onClick="inspectChecked(${book.id})"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="card-footer text-right"><button class="btn btn-primary" type="submit"><fmt:message key="booklist_order.button.order"/></button>

                </div>
            </div>
        </div>
    </form>

</div>

<c:import url="${pageContext.request.contextPath}/jsp/include/footer.jsp"/>


</body>
</html>
