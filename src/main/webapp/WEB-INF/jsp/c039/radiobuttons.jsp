<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="radiobuttonsRecv">
   <form:radiobuttons path="c039Form.selectedIsbn" items="${c039ModelList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
