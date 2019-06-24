<!doctype html>
<html>
  <head>
    
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/eatfood/authorization/authorization.css">
  </head>
  <body>
    <div id="content-pane">
      <%@ include file="header.html" %>    
      <div id="body">
	<h1>Registration</h1>
	<form action="registration" method="post" id="auth-form">
	  <label for="login">Login</label>
	  <label for="password">Password</label>
	  <input type="text" name="login">
	  <input type="password" name="password">
	  <input type="submit" value="registrate">
	</form>
      </div>
    </div>
  </body>
</html>
