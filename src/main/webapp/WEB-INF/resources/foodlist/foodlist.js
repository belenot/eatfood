window.onload = function () {
    var spinner = document.getElementsByClassName("spinner")[0];
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
