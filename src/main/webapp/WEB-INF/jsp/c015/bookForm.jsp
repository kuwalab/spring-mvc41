<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="bookRecv" method="post">
   書名: <input type="text" name="name" size="20"><form:errors path="c015Model.name" /><br>
   価格: <input type="text" name="price" size="20"><form:errors path="c015Model.price" /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
