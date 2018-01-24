<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <style>
        body {
            margin-bottom: 60px;
        }
    </style>

</head>
<body>

<nav class="fixed-bottom bg-dark">
    <div class="container-fluid text-white py-1">
        <div class="row">
            <div class="col-md-12 text-center"> Copyright Aliaksei Loika 2017-2018 EPAM</div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center text-muted"> Today is <ctg:date-tag/></div>
        </div>
    </div>
</nav>


</body>
</html>