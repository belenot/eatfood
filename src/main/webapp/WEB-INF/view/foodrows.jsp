<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
for (Food food : (List<Food>) request.getAttribute("foodRows")) {
  %>

<div class="row">
    <label class="item name"><%= food.getName()%></label>
    <label class="item calories"><%= food.getCalories() %></label>
    <label class="item protein"><%= food.getProtein() %></label>
    <label class="item carbohydrate"><%= food.getCarbohydrate() %></label>
    <label class="item fat"><%= food.getFat() %></label>
    <form class="row-buttons" method="POST" action="/eatfood/foodlist/deletefood">
      <input type="hidden" class="item id" name="id" value="<%=food.getId()%>">
      <input type="image" form="" class="update-img" src="/eatfood/resources/img/pencil.png" name="img1" alt="edit">
      <input type="image" src="/eatfood/resources/img/close.png" name="img2" alt="delete" >
    </form>
</div>
<%
  }
  %>

