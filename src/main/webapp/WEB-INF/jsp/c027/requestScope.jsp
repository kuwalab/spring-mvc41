<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
HttpServletRequest: <c:out value="${requestScope.req1}" /><br>
WebRequest: <c:out value="${requestScope.req2}" /><br>
Model: <c:out value="${requestScope.req3}" />
 </body>
</html>
