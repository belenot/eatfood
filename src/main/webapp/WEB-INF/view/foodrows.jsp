<%@ page import="com.belenot.eatfood.domain.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
for (Food food : (List<Food>) request.getAttribute("foodRows")) {
%>
<div class="row">
<label class="item id" hidden><%= food.getId() %></label>
<label class="item"><%= food.getName() %></label>
<label class="item"><%= food.getCalories() %></label>
<label class="item"><%= food.getProtein() %></label>
<label class="item"><%= food.getCarbohydrate() %></label>
<label class="item"><%= food.getFat() %></label>
</div>
<%
}
%>