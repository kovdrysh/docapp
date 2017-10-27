<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Documents</title>
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
    <div class="tablediv" >
        <table class="table table-hover table-condensed col-md-4 col-sm-12 col-xs-12 col-lg-4 main-table" >
            <thread>
                <tr>
                    <td><b>Name</b></td>
                    <td><b>Surname</b></td>
                    <td><b>Email</b></td>
                    <td><b>Login Name</b></td>
                    <td><b>User Role</b></td>
                    <td><b>Action</b></td>
                </tr>
            </thread>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><p>${user.name}</p></td>
                    <td><p>${user.surname}</p></td>
                    <td><p>${user.email}</p></td>
                    <td><p>${user.nickname}</p></td>
                    <td><p>${user.userRole}</p></td>
                    <td><a style="color: black" href="/editUser?id=${user.id}"><i class="fa fa-pencil" aria-hidden="true"></i></a> | <a style="color: black" href="/deleteUser?id=${user.id}"><i class="fa fa-trash" aria-hidden="true"></i></a> </td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#addUser">Add new User</button>

</div>

<jsp:include page="addUser.jsp"/>
<jsp:include page="uploadModal.jsp"/>
<jsp:include page="confirmDeleteModal.jsp"/>

<!--<script>
    $('#confirm-delete').on('show.bs.modal', function(e) {
        if ($(e.relatedTarget).data('folder'))
            $('.debug-url').html('Are you sure to delete this folder with its files?');
        else
            $('.debug-url').html('Are you sure to delete this file?');
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });
    $('#createModal').on('show.bs.modal', function(e) {
        if ($(e.relatedTarget).data('action') === "edit")
            $(this).find('#exampleModalLabel').html("Edit Folder");
        $(this).find('#folder-name').val($(e.relatedTarget).data('name'));
        $(this).find('#folder-id').val($(e.relatedTarget).data('id'));
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
        $(this).find('#folder-name').focus();
    });
</script>-->
</body>
</html>
