<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>All Users</title>
    <script type="text/javascript" src="../resources/js/script.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/popper.js"></script>
    <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/upload.js"></script>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.2.css">
</head>
<style>
    <%@include file="../resources/css/style.css"%>
</style>


<body>
<div class="container">
    <div class="row">
        <div class="logout">
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout"/>"><button type="button" class="btn btn-success btn-md text-right">Logout</button></a>
            </sec:authorize>
        </div>
    </div>
    <div class="tablediv">
        <h1><b>All History</b></h1>
        <table class="table table-hover table-condensed col-md-4 col-sm-12 col-xs-12 col-lg-4 main-table" >
            <thread>
                <tr>
                    <td><b>Performed Action</b></td>
                    <td><b>User Name</b></td>
                    <td><b>Date</b></td>
                    <td><b>Message</b></td>
                    <td><b>Action</b></td>
                </tr>
            </thread>
            <c:forEach var="history" items="${history}">
                <tr>
                    <td><p>${history.action}</p></td>
                    <td><p>${history.userName}</p></td>
                    <td><p>${history.date}</p></td>
                    <td><p>${history.message}</p></td>
                    <td><a style="color: black" data-target="#confirm-delete" href="/deleteHistory?id=${history.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

<jsp:include page="confirmDeleteModal.jsp"/>


<script>
    $('#confirm-delete').on('show.bs.modal', function(e) {
        $('.debug-url').html('Are you sure to delete this item?');
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });

</script>
</body>
</html>
