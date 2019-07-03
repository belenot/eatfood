<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="com.belenot.eatfood.domain.Dose" %>
<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<html>
  <head>
    <title>Food List</title>
    <link rel="stylesheet" href="/eatfood/resources/foodlist.css" type="text/css">
  </head>
  <body>
    
    <div id="menu-bar-pane">
      <%@ include file="menuBar.html" %>
    </div>
    
    <div id="doses-pane">
      <% for (Dose dose : (List<Dose>) request.getAttribute("doseList")) { %>
      <%@ include file="dose.html" %>
      <% } %>
    </div>
    
    <div id="add-dose-form-pane" >
      <%@ include file="addDoseForm.html" %>
    </div>

    <div id="foods-pane">
      <% for (Food food : (List<Food>) request.getAttribute("foodList")) { %>
      <%@ include file="food.html" %>
      <% } %>
    </div>      

    <div id="add-food-form-pane">
      <%@ include file="addFoodForm.html"  %>
    </div>

    <div id="total-nutrients-pane">
      <%@ include file="totalNutrients.html" %>
    </div>
    
  </body>
</html>
