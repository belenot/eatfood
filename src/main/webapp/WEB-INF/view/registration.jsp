<!doctype html>
<html>
  <head>
    <title>Registration</title>
  </head>
  <body>
    <%@ include file="menuBar.html" %>
    <h1>Registration</h1>
    <form action="registration" method="post" id="auth-form">
      <label for="login">Login</label>
      <label for="password">Password</label>
      <label for="name">Name</label>
      <label for="surname">Surname</label>
      <label for="email">Email</label>
      <input type="text" name="login">
      <input type="password" name="password">
      <input type="text" name="name">
      <input type="text" name="surname">
      <input type="email" name="email">
      <input type="submit" value="registrate">
    </form>
    <div id="footer-pane">
      <%@ include file="footer-pane.html" %>
    </div>
  </body>
</html>
