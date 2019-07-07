<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="com.belenot.eatfood.domain.Dose" %>
<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.math.BigDecimal" %>
<%
  Map<String, BigDecimal> totalNutrients = (Map<String, BigDecimal>)request.getAttribute("totalNutrients");
  %>
<html>
  <head>
    <title>Food List</title>
    <link rel="stylesheet" href="/eatfood/resources/foodlist.css" type="text/css">
    <script src="/eatfood/resources/foodlist.js"></script>
  </head>
  <body>
    
    <div id="menu-bar-pane">
      <%@ include file="menuBar.html" %>
    </div>
    
    <div id="doses-pane">
      <% for (Dose dose : (List<Dose>) request.getAttribute("doseList")) { %>
      <%@ include file="dose.html" %>
      <% } %>
      <div>
	<input type="date" name="date">
	<button onclick="onLoadDosesBtnClick(event);">get</button>
      </div>
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

    <div hidden>
      <template id="dose-row-template">
	<%@ include file="doseRowTemplate.html" %>
      </template>
      <template id="update-dose-template">
	<%@ include file="updateDoseTemplate.html" %>
      </template>
      <template id="food-row-template">
	<%@ include file="foodRowTemplate.html" %>
      </template>
      <template id="update-food-template">
	<%@ include file="updateFoodTemplate.html" %>
      </template>
    </div>
    
  </body>
</html>
