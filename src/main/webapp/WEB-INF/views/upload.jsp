<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 21.10.2017
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading Form</title>
</head>
<body>
    <h3>File Upload:</h3>
    Select a file to upload: <br/>
    <form action="/upload?parentId=${parentId}" method="post"
          enctype="multipart/form-data">
        <input class="pointer" type="file" name="file" size="50"/>
        <br/>
        <input class="pointer" type="submit" value="Upload File"/>
    </form>
</body>
</html>
