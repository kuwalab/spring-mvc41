<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  チェックされた項目は<br>
  <c:forEach items="${isbns}" var="isbn">
   <c:out value="${isbn}" />
  </c:forEach>
 </body>
</html>
