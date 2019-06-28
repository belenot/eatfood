window.onload = function () {
    var spinner = document.getElementsByClassName("spinner")[0];
    var updateImgArray = document.querySelectorAll(".row .update-img");
    for (var i = 0; i < updateImgArray.length; i++) {
	updateImgArray[i].addEventListener("click", onUpdateFoodClick);
    }
    spinner.addEventListener("scroll", onSpinnerScroll);
    totalNutrient("protein");
    updateStats();
}

function onSpinnerScroll(e) {
    e = e.target;
    if (e.scrollHeight - e.scrollTop ==  e.clientHeight) {
	var length = document.getElementsByClassName("item id").length;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/eatfood/foodlist/morefood?last=" + length)
	xhr.send()
	xhr.onreadystatechange = function () {
	    if (xhr.status != 200) {
		alert("Can't derive your food");
	    } else {
		var spinner = document.getElementsByClassName("spinner")[0];
		var fictDiv = document.createElement('div');
		fictDiv.innerHTML = xhr.response;
		var els = fictDiv.getElementsByClassName("row");
		for (var i = 0; i < els.length; i++ ) {
		    spinner.appendChild(els[i]);
		}
		updateStats();
	    }
	}
    }
}

function totalNutrient(name) {
    var nutrientList = document.querySelectorAll(".row > ." + name);
    var sum = 0;
    for (var i = 0; i < nutrientList.length; i++) {
	sum += Number(nutrientList[i].innerText);
    }
    return sum;
}
function updateStats() {
    document.getElementById("calories").innerText = totalNutrient("calories");
    document.getElementById("protein").innerText = totalNutrient("protein");
    document.getElementById("carbohydrate").innerText = totalNutrient("carbohydrate");
    document.getElementById("fat").innerText = totalNutrient("fat");
}

function onUpdateFoodClick(e) {
    var row = e.target.parentElement.parentElement;
    var food = {
	"name": row.getElementsByClassName("name")[0].innerText,
	"calories": row.getElementsByClassName("calories")[0].innerText,
	"protein": row.getElementsByClassName("protein")[0].innerText,
	"carbohydrate": row.getElementsByClassName("carbohydrate")[0].innerText,
	"fat": row.getElementsByClassName("fat")[0].innerText,
    }
    var addfood = document.querySelector("#addfood .row");

    row.style.borderColor = "orange";
    addfood.getElementsByClassName("item")[0].value = food.name;
    addfood.getElementsByClassName("item")[1].value = food.calories;
    addfood.getElementsByClassName("item")[2].value = food.protein;
    addfood.getElementsByClassName("item")[3].value = food.carbohydrate;
    addfood.getElementsByClassName("item")[4].value = food.fat;
    addfood.querySelector("input[type='submit']").value="Update";
    addfood.querySelector("input[type='submit']").style.color = "orange";
    addfood.getElementsByClassName("item")[0].style.backgroundColor = "orange";
    addfood.getElementsByClassName("item")[1].style.backgroundColor = "orange";
    addfood.getElementsByClassName("item")[2].style.backgroundColor = "orange";
    addfood.getElementsByClassName("item")[3].style.backgroundColor = "orange";
    addfood.getElementsByClassName("item")[4].style.backgroundColor = "orange";
    var idDiv = document.createElement("input")
    idDiv.type = "hidden";
    idDiv.name = "id";
    idDiv.value = row.querySelector("input[name='id']").value;
    addfood.appendChild(idDiv);
    addfood.parentElement.action = "/eatfood/foodlist/updatefood";
    addfood.getElementsByClassName("item")[0].addEventListener("input", onNameInput);
    /*Add callback that sets properties to origin on sending update click
      Need for AJAX */
}

function onNameInput(e) {
    if (e.target.value == "") {
	stopUpdateFoodProcess(e.target);
	addfood.getElementsByClassName("item")[0].removeEventListener("input", onNameInput);
    }
}

function stopUpdateFoodProcess(nameField) {
    var addfood = nameField.parentElement;
    var idField = addfood.querySelector("input[name='id']");
    var row = document.querySelector("input[name='id'][value='" + idField.value + "']").parentElement;
    row.parentElement.style.borderColor = "hsl(190, 10%, 90%)";
    addfood.getElementsByClassName("item")[0].value = "";
    addfood.getElementsByClassName("item")[1].value = "";
    addfood.getElementsByClassName("item")[2].value = "";
    addfood.getElementsByClassName("item")[3].value = "";
    addfood.getElementsByClassName("item")[4].value = "";
    addfood.querySelector("input[type='submit']").value="Add";
    addfood.querySelector("input[type='submit']").style.color = "hsl(0, 0%, 80%)";
    addfood.getElementsByClassName("item")[0].style.backgroundColor = "inherit";
    addfood.getElementsByClassName("item")[1].style.backgroundColor = "inherit";
    addfood.getElementsByClassName("item")[2].style.backgroundColor = "inherit";
    addfood.getElementsByClassName("item")[3].style.backgroundColor = "inherit";
    addfood.getElementsByClassName("item")[4].style.backgroundColor = "inherit";
    addfood.parentElement.action="/eatfood/foodlist/addfood";
}
