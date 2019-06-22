    <%@ page import="com.belenot.eatfood.domain.Food" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
<html>
  <head>
    <title>foodlist</title>

    <link rel="stylesheet" type="text/css" href="/eatfood/foodlist/foodlist.css">

    <!---->
    <!--<script src="http://localhost:8080/skewer"></script>-->
    <!---->
    
  </head>
  <body>
    <%@ include file="header.html" %>

    <div id="body">
      <p> here will be list</p>
      <div class="spinner">
	<%
	  for (Food food : (List<Food>) request.getAttribute("foodList")) {
	  %>
	<div class="row">
	  <label class="item"><%= food.getName() %></label>
	  <label class="item"><%= food.getCalories() %></label>
	  <label class="item"><%= food.getProtein() %></label>
	  <label class="item"><%= food.getCarbohydrate() %></label>
	  <label class="item"><%= food.getFat() %></label>
	</div>
	<%
	  }
	  %>
      </div>

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
    </div>
  </body>
</html>
