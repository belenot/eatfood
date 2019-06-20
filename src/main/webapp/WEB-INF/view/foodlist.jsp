<html>
  <head>
    <title>foodlist</title>
    <link rel="stylesheet" type="text/css" href="/eatfood/foodlist/foodlist.css">
  </head>
  <body>
    <div id="body">
      <p> here will be list</p>
      <div class="row">
	<form id="addfood" action="foodlist/addfood" method="post">
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
