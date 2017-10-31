<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>All Documents</title>
    <script type="text/javascript" src="../resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/popper.js"></script>
    <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../resources/js/script.js"></script>
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
        <div class="row">
            <div class="logout">
                <sec:authorize access="isAuthenticated()">
                    <a href="<c:url value="/logout"/>"><button type="button" class="btn btn-success btn-md text-right">Logout</button></a>
                </sec:authorize>
            </div>
        </div>
        <div class="tablediv" >
            <!--<div class="row col-md-12">
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <h1><b>${folderName}</b></h1>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <input type="text" class="form-control col-md-8 col-xs-8 col-sm-8">
                    <button type="button" class="btn btn-success btn-xs col-md-4 col-xs-4 col-sm-4">Find</button>

                </div>
            </div>-->
            <h1><b>${folderName}</b></h1>
            <table class="table table-hover table-condensed col-md-4 col-sm-12 col-xs-12 col-lg-4 main-table" >
                <thread>
                    <tr>
                        <td><b>Name</b></td>
                        <td><b>Created By</b></td>
                        <td><b>Created Date</b></td>
                        <td><b>Document type</b></td>
                        <td><b>Action</b></td>
                    </tr>
                </thread>
                <tr>
                    <td><i class="fa fa-folder-open" aria-hidden="true"></i>
                        <a style="color: black" href="/document?id=0">..</a>/
                        <c:forEach var="step" items="${stepback}">
                            <a style="color: black" href="/document?id=${step.id}">${step.name}</a>/
                        </c:forEach>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            <c:forEach var="folder" items="${folders}">
                <tr>
                    <td><p><i class="fa fa-folder" aria-hidden="true"></i><a style="color: black" href="/document?id=${folder.id}"> ${folder.name}</a></p></td>
                    <td>${folder.createdBy}</td>
                    <td>${folder.date}</td>
                    <td></td>
                    <td>
                        <a style="color: black" href="#" class="${folder.editable}" id="edit-folder" data-href="/editFolder?id=${folder.id}" data-toggle="modal" data-target="#createModal"  data-name="${folder.name}" data-id="${folder.id}" data-action="edit">
                            <i class="fa fa-pencil" aria-hidden="true"></i></a> |
                        <a style="color: black" id="delete-folder" class="${folder.deletable}" href="#confirm-delete" data-href="/deleteFolder?id=${folder.id}" data-toggle="modal" data-folder="true"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                </tr>
            </c:forEach>
            <c:forEach var="document" items="${documents}">
                <tr>
                    <td><p><img src="../resources/images/${document.type}"><a style="color: black" href="/download/${document._id}"> ${document.name}</a></p></td>
                    <td>${document.createdBy()}</td>
                    <td>${document.date}</td>
                    <td>${document.contentType}</td>
                    <td><a style="color: black" href="/download/${document._id}"><i class="fa fa-download" aria-hidden="true"></i></a> | <a style="color: black" class="${document.deletable}" href="#" data-href="/deleteDoc?id=${document.id}&parentId=${document.parentId}" data-toggle="modal" data-target="#confirm-delete" data-folder="false"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
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

    <script>
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
    </script>
</body>
</html>
