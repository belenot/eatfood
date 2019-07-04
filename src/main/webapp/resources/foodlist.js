window.onload = function () {
    
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

function onCloseFormBtnClick(e) {
    var closeFormBtn = e.target;
    var template = document.createElement("template");
    template.innerHTML = closeFormBtn.getAttribute("data-origin");
    var originX = template.content.firstChild;
    var updateXForm = closeFormBtn.parentElement.parentElement;
    updateXForm.parentElement.replaceChild(originX, updateXForm);
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
    
function onLoadDosesBtnClick(e) {
    var date = encodeURIComponent(e.target.previousElementSibling.value);
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/foodlist/doses", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    alert(xhr.responseText);
	}
    };
    xhr.send('date=' + date);
}
