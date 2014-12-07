<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="checkboxesRecv">
   <form:checkboxes path="c037Form.selectedIsbn" items="${c037ModelList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
