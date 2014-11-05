<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="bookRecv" method="post">
   書名: <form:input path="c020Model.name" size="20" /><form:errors path="c020Model.name" /><br>
   価格: <form:input path="c020Model.price" size="20" /><form:errors path="c020Model.price" /><form:errors path="c020Model.validPrice" /><br>
   定価: <form:input path="c020Model.listPrice" size="20" /><form:errors path="c020Model.listPrice" /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
