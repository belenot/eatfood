<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
for (Food food : (List<Food>) request.getAttribute("foodRows")) {
  %>
<div class="row">
  <label class="item id" hidden><%= food.getId() %></label>
  <label class="item name"><%= food.getName()%></label>
  <label class="item calories"><%= food.getCalories() %></label>
  <label class="item protein"><%= food.getProtein() %></label>
  <label class="item carbohydrate"><%= food.getCarbohydrate() %></label>
  <label class="item fat"><%= food.getFat() %></label>
  <div class="row-buttons">
    <a href="/eatfood/foodlist/update">
      <img src="/eatfood/img/pencil.jpg">
    </a>
    <a href="/eatfood/foodlist/delete">
      <img src="/eatfood/img/close.jpg">
    </a>
  </div>
</div>
<%
}
%>
