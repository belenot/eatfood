<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
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
      <button class="add-btn" onclick="onAddDoseBtnClick(event)" name="add">+</button>
      <div class="date-interval">
	<input class="dose-date-first" type="date" name="dateFirst" value="<%=dateFirst%>">
	<input class="dose-date-last" type="date" name="dateLast" value="<%=dateLast%>">
	<button class="load-doses-btn" onclick="onLoadDosesBtnClick(event);">get</button>
      </div>
    </div>


    <div id="total-nutrients-pane">
      <%@ include file="totalNutrients.html" %>
    </div>

    <div hidden>
      <template id="dose-template">
	<%@ include file="doseTemplate.html" %>
      </template>
      <template id="food-template">
	<%@ include file="foodTemplate.html" %>
      </template>
    </div>
    
  </body>
</html>
