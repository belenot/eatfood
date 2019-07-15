<%@ page import="org.springframework.context.MessageSource" %>
<html>
  <head>
    <title>Food List</title>
    <link rel="stylesheet" href="/eatfood/resources/eatfood.css" type="text/css">
    <script src="/eatfood/resources/eatfood.js"></script>
  </head>
  <body>
    
    <div id="menu-bar-pane">
      <%@ include file="menuBar.html" %>
    </div>

    <div id="foods-pane">
      <button class="add-btn" onclick="onAddFoodBtnClick(event)" name="add">+</button>
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
