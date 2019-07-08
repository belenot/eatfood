window.onload = function () {
    
}
function jsonValidate(jsonObject) {
    try {
	JSON.parse(jsonObject);
	return true;
    } catch (e) {
	return false;
    }
}
function onCloseFormBtnClick(e) {
    var closeFormBtn = e.target;
    var template = document.createElement("template");
    template.innerHTML = closeFormBtn.getAttribute("data-origin");
    var originX = template.content.firstChild;
    var updateXForm = closeFormBtn.parentElement.parentElement;
    updateXForm.parentElement.replaceChild(originX, updateXForm);
}

function onUpdateDoseBtnClick(e) {
    var doseRow = e.target.parentElement.parentElement;
    var updateDoseForm = document.getElementById("update-dose-template").content.firstElementChild.cloneNode(true);
    updateDoseForm.getElementsByClassName("dose-id")[0].value = doseRow.getElementsByClassName("dose-id")[0].value;
    updateDoseForm.getElementsByClassName("food-name")[0].innerText = doseRow.getElementsByClassName("food-name")[0].innerText;
    updateDoseForm.getElementsByClassName("dose-gram")[0].value = doseRow.getElementsByClassName("dose-gram")[0].innerText;
    updateDoseForm.getElementsByClassName("dose-date")[0].value = doseRow.getElementsByClassName("dose-date")[0].innerText;
    var closeFormBtn = updateDoseForm.getElementsByClassName("dose-update-close-btn")[0];
    closeFormBtn.addEventListener("click", onCloseFormBtnClick);
    closeFormBtn.setAttribute("data-origin", doseRow.outerHTML);
    doseRow.parentElement.replaceChild(updateDoseForm, doseRow);
}
function onUpdateDoseSubmitBtnClick(e) {
    var addDoseForm = e.target.parentElement.parentElement;
    var doseId = encodeURIComponent(addDoseForm.querySelector(".dose-id").value);
    addDoseForm.querySelector(".dose-id").value = null;
    var doseGram = encodeURIComponent(addDoseForm.querySelector(".dose-gram").value);
    addDoseForm.querySelector(".dose-gram").value = null;
    var doseDate = encodeURIComponent(addDoseForm.querySelector(".dose-date").value);
    addDoseForm.querySelector(".dose-date").value = null;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/updatedose");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200 &&
	    jsonValidate(xhr.responseText)) {
	    var doseRow = populateDoseRow(JSON.parse(xhr.responseText));
	    addDoseForm.parentElement.replaceChild(doseRow, addDoseForm);
	} else if (xhr.readyState === 4) {
	    alert("Can't update dose: " + xhr.responseText);
	}
    }
    xhr.send("id="+doseId+"&&"+
	     "gram="+doseGram+"&&"+
	     "date="+doseDate);
    
}
function onDeleteDoseBtnClick(e) {
    var doseRow = e.target.parentElement.parentElement;
    var id = doseRow.querySelector(".dose-id").value;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/deletedose");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    id = encodeURIComponent(id);
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200 && xhr.responseText === "true") {
	    deleteDoseFromPane(doseRow);
	}
	else if (xhr.readyState === 4) {
	    alert("Dose wasn't deleted: " + xhr.responseText);
	}
    }
    xhr.send("id="+id);
}
function onAddDoseBtnClick(e) {
    var addDoseForm = e.target.parentElement.parentElement;
    var foodName = encodeURIComponent(addDoseForm.querySelector(".food-name").value);
    addDoseForm.querySelector(".food-name").value = null;
    var doseGram = encodeURIComponent(addDoseForm.querySelector(".dose-gram").value);
    addDoseForm.querySelector(".dose-gram").value = null;
    var doseDate = encodeURIComponent(addDoseForm.querySelector(".dose-date").value);
    addDoseForm.querySelector(".dose-date").value = null;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/adddose");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200 &&
	    jsonValidate(xhr.responseText)) {
	    doseRow = populateDoseRow(JSON.parse(xhr.responseText));
	    addDoseRowToPane(doseRow);
	} else if (xhr.readyState === 4) {
	    alert("Can't add dose: " + xhr.responseText);
	}
    }
    xhr.send("food.name="+foodName+"&&"+
	     "gram="+doseGram+"&&"+
	     "date="+doseDate);
}
function deleteDoseFromPane(doseRow) {
    doseRow.parentElement.removeChild(doseRow);
}
function populateDoseRow(jsonObject, doseRow) {
    if (doseRow == null || doseRow === "undefined") {
	doseRow = document.getElementById("dose-row-template").content.firstElementChild.cloneNode(true);
    }
    doseRow.querySelector(".food-name").innerText = jsonObject.foodModel.name;
    doseRow.querySelector(".dose-gram").innerText = jsonObject.gram;
    doseRow.querySelector(".dose-date").innerText = jsonObject.date;
    doseRow.querySelector(".dose-id").value = jsonObject.id;
    return doseRow;
}
function addDoseRowToPane(doseRow) {
    var dosePane = document.getElementById("doses-pane");
    dosePane.insertAdjacentElement("afterbegin", doseRow);
}

function onUpdateFoodBtnClick(e) {
    var foodRow = e.target.parentElement.parentElement;
    var updateFoodForm = document.getElementById("update-food-template").content.firstElementChild.cloneNode(true);
    updateFoodForm.getElementsByClassName("food-id")[0].value = foodRow.getElementsByClassName("food-id")[0].value;
    updateFoodForm.getElementsByClassName("food-name")[0].value = foodRow.getElementsByClassName("food-name")[0].innerText;
    updateFoodForm.getElementsByClassName("food-common")[0].checked = foodRow.getElementsByClassName("food-common")[0].innerText === "common" ? true : false;
    updateFoodForm.getElementsByClassName("food-calories")[0].value = foodRow.getElementsByClassName("food-calories")[0].innerText;
    updateFoodForm.getElementsByClassName("food-protein")[0].value = foodRow.getElementsByClassName("food-protein")[0].innerText;
    updateFoodForm.getElementsByClassName("food-carbohydrate")[0].value = foodRow.getElementsByClassName("food-carbohydrate")[0].innerText;
    updateFoodForm.getElementsByClassName("food-fat")[0].value = foodRow.getElementsByClassName("food-fat")[0].innerText;
    var closeFormBtn = updateFoodForm.getElementsByClassName("food-update-close-btn")[0];
    closeFormBtn.addEventListener("click", onCloseFormBtnClick);
    closeFormBtn.setAttribute("data-origin", foodRow.outerHTML);
    foodRow.parentElement.replaceChild(updateFoodForm, foodRow);
}
function onUpdateFoodSubmitBtnClick(e) {
    var updateFoodForm = e.target.parentElement.parentElement;
    var id = encodeURIComponent(updateFoodForm.querySelector(".food-id").value);
    updateFoodForm.querySelector(".food-id").value = null;    
    var name = encodeURIComponent(updateFoodForm.querySelector(".food-name").value);
    updateFoodForm.querySelector(".food-name").value = null;
    var common = encodeURIComponent(updateFoodForm.querySelector(".food-common").checked ? "true" : "false");
    updateFoodForm.querySelector(".food-common").checked = null;
    var calories = encodeURIComponent(updateFoodForm.querySelector(".food-calories").value);
    updateFoodForm.querySelector(".food-calories").value = null;
    var protein = encodeURIComponent(updateFoodForm.querySelector(".food-protein").value);
    updateFoodForm.querySelector(".food-protein").value = null;
    var carbohydrate = encodeURIComponent(updateFoodForm.querySelector(".food-carbohydrate").value);
    updateFoodForm.querySelector(".food-carbohydrate").value = null;
    var fat = encodeURIComponent(updateFoodForm.querySelector(".food-fat").value);
    updateFoodForm.querySelector(".food-fat").value = null;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/updatefood");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200 &&
	    jsonValidate(xhr.responseText)) {
	    var doseRow = populateFoodRow(JSON.parse(xhr.responseText));
	    updateFoodForm.parentElement.replaceChild(doseRow, updateFoodForm);
	} else if (xhr.readyState === 4) {
	    alert("Can't update dose: " + xhr.responseText);
	}
    }
    xhr.send("id="+id+"&&"+
	     "name="+name+"&&"+
	     "common="+common+"&&"+
	     "calories="+calories+"&&"+
	     "protein="+protein+"&&"+
	     "carbohydrate="+carbohydrate+"&&"+
	     "fat="+fat);	    
    
}
function onDeleteFoodBtnClick(e) {
    var foodRow = e.target.parentElement.parentElement;
    var id = foodRow.querySelector(".food-id").value
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/deletefood");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    id = encodeURIComponent(id);
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200 && xhr.responseText === "true") {
	    deleteFoodFromPane(foodRow);
	} else if (xhr.readyState === 4) {
	    alert("Can't delete food: " + xhr.responseText);
	}
    }
    xhr.send("id="+id);
    
}
function onAddFoodBtnClick(e) {
    var addFoodForm = e.target.parentElement;
    var name = encodeURIComponent(addFoodForm.querySelector(".name").value);
    addFoodForm.querySelector(".name").value = null;
    var common = encodeURIComponent(addFoodForm.querySelector(".common").checked ? "true" : "false");
    addFoodForm.querySelector(".common").checked = null;
    var calories = encodeURIComponent(addFoodForm.querySelector(".calories").value);
    addFoodForm.querySelector(".calories").value = null;
    var protein = encodeURIComponent(addFoodForm.querySelector(".protein").value);
    addFoodForm.querySelector(".protein").value = null;
    var carbohydrate = encodeURIComponent(addFoodForm.querySelector(".carbohydrate").value);
    addFoodForm.querySelector(".carbohydrate").value = null;
    var fat = encodeURIComponent(addFoodForm.querySelector(".fat").value);
    addFoodForm.querySelector(".fat").value = null;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/addfood");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200
	    && jsonValidate(xhr.responseText)) {
	    foodRow = populateFoodRow(JSON.parse(xhr.responseText))
	    addFoodRowToPane(foodRow);
	} else if (xhr.readyStat === 4) {
	    alert("Can't  add food: " + xhr.responseText);
	}
    }
    xhr.send("name="+name+"&&"+
	     "common="+common+"&&"+
	     "calories="+calories+"&&"+
	     "protein="+protein+"&&"+
	     "carbohydrate="+carbohydrate+"&&"+
	     "fat="+fat);	    
}
function deleteFoodFromPane(foodRow) {
    foodRow.parentElement.removeChild(foodRow);
}
function populateFoodRow(jsonObject, foodRow) {
    if (foodRow == null || doseRow === "undefined") {
	foodRow = document.getElementById("food-row-template").content.firstElementChild.cloneNode(true);
    }
    foodRow.querySelector(".food-name").innerText = jsonObject.name;
    foodRow.querySelector(".food-common").innerText = jsonObject.common ? "common" : "uncommon";
    foodRow.querySelector(".food-calories").innerText = jsonObject.calories;
    foodRow.querySelector(".food-protein").innerText = jsonObject.protein;
    foodRow.querySelector(".food-carbohydrate").innerText = jsonObject.carbohydrate;
    foodRow.querySelector(".food-fat").innerText = jsonObject.fat;
    foodRow.querySelector(".food-id").value = jsonObject.id;
    return foodRow;
}
function addFoodRowToPane(foodRow) {
    var foodsPane = document.getElementById("foods-pane");
    foodsPane.insertAdjacentElement("afterbegin", foodRow);
}
    
function onLoadDosesBtnClick(e) {
    var date = encodeURIComponent(e.target.previousElementSibling.value);
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/doses", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    refreshDosesPane(JSON.parse(xhr.responseText));
	}
    };
    xhr.send('date=' + date);
}
function refreshDosesPane(doses) {
    var dosesPane = document.getElementById("doses-pane")
    var dosesPaneChildren = dosesPane.querySelectorAll("div.dose-row");
    for (var i = 0; i < dosesPaneChildren.length; i++) {
	dosesPane.removeChild(dosesPaneChildren[i]);
    }
    for (var i = 0; i < doses.length; i++) {
	var doseRow = document.getElementById("dose-row-template").content.firstElementChild.cloneNode(true);
	doseRow.getElementsByClassName("food-name")[0].innerText = doses[i].foodModel.name;
	doseRow.getElementsByClassName("dose-gram")[0].innerText = doses[i].gram;
	doseRow.getElementsByClassName("dose-date")[0].innerText = doses[i].date;
	doseRow.getElementsByClassName("dose-id")[0].value = doses[i].id;
	dosesPane.insertAdjacentElement('afterbegin', doseRow);
    }
	
}

	    
	    
    
