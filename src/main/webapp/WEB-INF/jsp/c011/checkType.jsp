<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 バインディングエラー <form:errors path="c011Model.*" /><br>
c011Model.nameの値は <c:out value="${c011Model.name}" /><br>
c011Model.priceの値は <c:out value="${c011Model.price}" /><br>
 </body>
</html>
