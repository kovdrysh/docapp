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

<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">-->
<!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<script src="../resources/js/upload.js"></script>-->
<body>
    <div class="container">
        <div class="tablediv" >
            <table class="table table-hover table-condensed     col-md-4 col-sm-12 col-xs-12 col-lg-4 main-table" >
                <thread>
                    <tr>
                        <td><b>Name</b></td>
                        <td><b>Created Date</b></td>
                        <td><b>Action</b></td>
                    </tr>
                </thread>
                <tr>
                    <td><i class="fa fa-folder-open" aria-hidden="true"></i><a style="color: black" href="/document?id=${stepback}"> ..</a> </td>
                    <td></td>
                    <td></td>
                </tr>
            <c:forEach var="folder" items="${folders}">
                <tr>
                    <td><p><i class="fa fa-folder" aria-hidden="true"></i><a style="color: black" href="/document?id=${folder.id}"> ${folder.name}</a></p></td>
                    <td>${folder.date}</td>
                    <td><a style="color: black" href="/editFolder?id=${folder.id}"><i class="fa fa-pencil" aria-hidden="true"></i></a> | <a style="color: black" href="#confirm-delete" data-href="/deleteFolder?id=${folder.id}" data-toggle="modal" data-folder="true"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                </tr>
            </c:forEach>
            <c:forEach var="document" items="${documents}">
                <tr>
                    <td><p><img src="../resources/images/${document.type}"><a style="color: black" href="/download/${document._id}"> ${document.name}</a></p></td>
                    <td>${document.date}</td>
                    <td><a style="color: black" href="/download/${document._id}"><i class="fa fa-download" aria-hidden="true"></i></a> | <a style="color: black" href="#" data-href="/deleteDoc?id=${document.id}&parentId=${document.parentId}" data-toggle="modal" data-target="#confirm-delete" data-folder="false"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                </tr>
            </c:forEach>
            </table>
        </div>
        <button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#createModal">Create folder</button>
        <button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#uploadModal">Add document</button>
    </div>

    <jsp:include page="createModal.jsp"/>
    <jsp:include page="uploadModal.jsp"/>
    <jsp:include page="confirmDeleteModal.jsp"/>


</body>
</html>
