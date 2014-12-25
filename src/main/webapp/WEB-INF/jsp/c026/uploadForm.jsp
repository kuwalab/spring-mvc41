<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <c:out value="${errorMessage}" /><br>
  <form action="uploadRecv?uploadUrl=/c026/uploadForm" method="post" enctype="multipart/form-data">
   <input type="text" name="test"><br>
   <input type="file" name="file"><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
