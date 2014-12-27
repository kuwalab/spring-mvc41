<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
HttpSession: <c:out value="${sessionScope.session1}" /><br>
WebRequest: <c:out value="${sessionScope.session2}" /><br>
<a href="sessionScope">セッションをクリアせず再表示</a><br>
<a href="sessionClear">セッションをクリアして再表示</a>
 </body>
</html>
