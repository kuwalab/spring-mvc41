<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
書名: <form:input path="c033Model.name" size="20" /><br>
価格: <form:input path="c033Model.price" size="10" maxlength="6" readonly="true" /><br>
定価: <form:input path="c033Model.listPrice" size="10" disabled="true" /><br>
 </body>
</html>
