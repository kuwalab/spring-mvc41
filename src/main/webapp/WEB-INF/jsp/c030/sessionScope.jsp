<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
書名: <c:out value="${c030Model.name}" /><br>
価格: <c:out value="${c030Model.price}" /><br>
<a href="sessionScope">画面遷移</a><br>
<a href="sessionClear">セッションクリア</a>
 </body>
</html>
