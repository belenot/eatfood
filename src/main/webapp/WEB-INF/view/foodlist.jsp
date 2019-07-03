<%@ page import="org.springframework.context.MessageSource" %>
<html>
  <head>
    <title>Food List</title>
    <link rel="stylesheet" href="/eatfood/resources/foodlist.css" type="text/css">
  </head>
  <body>
    <%@ include file="menuBar.html" %>
    <%@ include file="dose.html" %>
    <%@ include file="totalNutrients.html" %>
    <%@ include file="addDoseForm.html" %>
    <br>
    <br>
    <div>
      <label>add food</label>
      <form action="/eatfood/foodlist/addfood" method="post">
	<input type="text" name="name">
	<input type="checkbox" name="common">
	<input type="text" name="calories">
	<input type="text" name="protein">
	<input type="text" name="carbohydrate">
	<input type="text" name="fat">
	<input type="submit" value="ok">
      </form>
      <label>add dose</label>
      <form action="/eatfood/foodlist/adddose" method="post">
	<input type="text" name="food">
	<input type="date" name="date">
	<input type="text" name="gram">
	<input type="submit" value="ok">
      </form>
      <label>update food</label>
      <form action="/eatfood/foodlist/updatefood" method="post">
	<input type="text" name="id">
	<input type="text" name="name">
	<input type="checkbox" name="common">
	<input type="text" name="calories">
	<input type="text" name="protein">
	<input type="text" name="carbohydrate">
	<input type="text" name="fat">
	<input type="submit" value="ok">
      </form>
      <label>update dose</label>
      <form action="/eatfood/foodlist/updatedose" method="post">
	<input type="text" name="id">
	<input type="date" name="date">
	<input type="text" name="gram">
	<input type="submit" value="ok">
      </form>
      <label>delete food</label>
      <form action="/eatfood/foodlist/deletefood" method="post">
	<input type="text" name="id">
	<input type="submit" value="ok">
      </form>
      <label>delete dose</label>
      <form action="/eatfood/foodlist/deletedose" method="post">
	<input type="text" name="id">
	<input type="submit" value="ok">
      </form>
      <a href="/eatfood/foodlist/foods">foods</a>
      <a href="/eatfood/foodlist/doses">doses</a>
    </div>
  </body>
</html>
