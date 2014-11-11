<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
書名: <c:out value="${requestScope.c028Model.name}" /><br>
価格: <c:out value="${requestScope.c028Model.price}" />
 </body>
</html>
