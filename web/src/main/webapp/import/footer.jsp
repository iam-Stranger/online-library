<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
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


<script type="text/javascript" src="bootstrap/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>