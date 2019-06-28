<html>
  <head>
    <title>foodlist</title>

    <link rel="stylesheet" type="text/css" href="/eatfood/resources/foodlist/foodlist.css">
    <script src="/eatfood/resources/foodlist/foodlist.js"></script>
    <!---->
    <!--<script src="http://localhost:8080/skewer"></script>-->
    <!---->
    
  </head>
  <body>
    <div id="content-pane">
      <%@ include file="header.html" %>
      <div id="body">
	<div id="left-pane">
	  <div class="spinner">
	    <%@ include file="foodrows.jsp" %>
	  </div>
	  <%@ include file="addfood.html" %>
	</div>
	<div id="right-pane">
	  <%@ include file="foodstat.html" %>
	</div>
      </div>
    </div>
  </body>
</html>
