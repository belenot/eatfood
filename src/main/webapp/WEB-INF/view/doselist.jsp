<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="com.belenot.eatfood.domain.Dose" %>
<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.math.BigDecimal" %>
<%
  Map<String, BigDecimal> totalNutrients = (Map<String, BigDecimal>)request.getAttribute("totalNutrients");
  String dateFirst = (String) request.getAttribute("dateFirst");
  String dateLast = (String) request.getAttribute("dateLast");
  %>
<html>
  <head>
    <title>Dose List</title>
    <link rel="stylesheet" href="/eatfood/resources/eatfood.css" type="text/css">
    <script src="/eatfood/resources/eatfood.js"></script>
  </head>
  <body>
    
    <div id="menu-bar-pane">
      <%@ include file="menuBar.html" %>
    </div>
    
    <div id="doses-pane">
      <div>
	<input class="dose-date-first" type="date" name="dateFirst" value="<%=dateFirst%>">
	<input class="dose-date-last" type="date" name="dateLast" value="<%=dateLast%>">
	<button class="load-doses-btn" onclick="onLoadDosesBtnClick(event);">get</button>
      </div>
    </div>

    
    <div id="add-dose-form-pane" >
      <%@ include file="addDoseForm.html" %>
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
