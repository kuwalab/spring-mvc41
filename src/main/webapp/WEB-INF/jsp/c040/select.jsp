<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="selectRecv">
   <form:select path="c040Form.selectedIsbn" items="${c040ModelList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
   <form:select path="c040Form.selectedIsbn" multiple="true" size="3" items="${c040ModelList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
  </form>
 </body>
</html>
