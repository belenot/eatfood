<html>
  <head>
    <title>foodlist</title>

    <link rel="stylesheet" type="text/css" href="/eatfood/foodlist/foodlist.css">
    <script src="/eatfood/foodlist/foodlist.js"></script>
    <!---->
    <!--<script src="http://localhost:8080/skewer"></script>-->
    <!---->
    
  </head>
  <body>
    <%@ include file="header.html" %>

    <div id="body">
      <%@ include file="addfood.html" %>
      <div class="spinner">
	<%@ include file="foodrows.jsp" %>
      </div>
    </div>
  </body>
</html>
