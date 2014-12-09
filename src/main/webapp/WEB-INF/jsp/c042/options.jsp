<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form:select path="c042Form.selectedIsbn">
   <form:options items="${c042ModelList}" itemLabel="name" itemValue="isbn" />
  </form:select>
 </body>
</html>
