<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="java.math.BigDecimal" %>	
<%
for (Food food : (List<Food>) request.getAttribute("foodRows")) {
  %>

<div class="row">
    <label class="item name"><%= food.getName()%></label>
    <label class="item calories"><% out.print(food.getCalories().divide(new BigDecimal(100)).multiply(food.getGram()).setScale(2, RoundingMode.FLOOR)); %></label>
    <label class="item protein"><% out.print(food.getProtein().divide(new BigDecimal(100)).multiply(food.getGram()).setScale(2, RoundingMode.FLOOR)); %></label>
    <label class="item carbohydrate"><% out.print(food.getCarbohydrate().divide(new BigDecimal(100)).multiply(food.getGram()).setScale(2, RoundingMode.FLOOR)); %></label>
    <label class="item fat"><% out.print(food.getFat().divide(new BigDecimal(100)).multiply(food.getGram()).setScale(2, RoundingMode.FLOOR)); %></label>
    <label class="item gram"><%= food.getGram() %></label>
    <form class="row-buttons" method="POST" action="/eatfood/foodlist/deletefood">
      <input type="hidden" class="item id" name="id" value="<%=food.getId()%>">
      <input type="image" form="" class="update-img" src="/eatfood/resources/img/pencil.png" name="img1" alt="edit">
      <input type="image" src="/eatfood/resources/img/close.png" name="img2" alt="delete" >
    </form>
</div>
<%
  }
  %>

