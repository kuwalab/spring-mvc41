<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="radiobuttonRecv">
   <form:radiobutton path="c038Model.tel" label="固定電話" value="home" /><br>
   <form:radiobutton path="c038Model.tel" label="携帯電話" value="mobile" /><br>
   <form:radiobutton path="c038Model.tel" label="なし" value="nothing" /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
