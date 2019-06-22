<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
  <head>
    <title>foodlist</title>
    <link rel="stylesheet" type="text/css" href="/eatfood/foodlist/foodlist.css">
  </head>
  <body>
    <%@ include file="header.html" %>
    <div id="body">
      <p> here will be list</p>

      <%
	for (Food food : (List<Food>) request.getAttribute("foodList")) {
	%>
      <div class="row">
	<label><%= food.getName() %></label>
	<label><%= food.getCalories() %></label>
	<label><%= food.getProtein() %></label>
	<label><%= food.getCarbohydrate() %></label>
	<label><%= food.getFat() %></label>
      </div>
      <%
	}
	%>
	
      
      <div class="row">
	<form id="addfood" action="/eatfood/foodlist/addfood" method="post">
	  <label for"name">Naming</label>
	  <label for"name">Calories</label>
	  <label for"name">Protein</label>
	  <label for"name">Carbohydrate</label>
	  <label for"name">Fat</label>
	  
	  <input type="text" name="name">
	  <input type="text" name="calories">
	  <input type="text" name="protein">
	  <input type="text" name="carbohydrate">
	  <input type="text" name="fat">
	  <input type="submit" value="add">
	</form>
	<form action="logout">
	  <input type="submit" value="logout">
	</form>
      </div>
    </div>
  </body>
</html>
