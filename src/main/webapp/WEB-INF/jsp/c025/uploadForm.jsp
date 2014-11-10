<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="uploadRecv" method="post" enctype="multipart/form-data">
   <input type="text" name="test"><br>
   <input type="file" name="file"><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
