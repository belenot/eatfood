<!doctype html>
<html>
  <head>
    <title>authorization</title>
    <link rel="stylesheet" type="text/css" href="/eatfood/authorization/authorization.css">
  </head>
  <body>
    <div id="content-pane">
      <%@ include file="header.html" %>
      <div id="body">
	<h1>Authorization</h1>
	<form action="authorization" method="post" id="auth-form">
	  <label for="login">Login</label>
	  <label for="password">Password</label>
	  <input type="text" name="login">
	  <input type="password" name="password">
	  <input type="submit" value="authorize">
	</form>

      </div>
    </div>
  </body>
</html>
