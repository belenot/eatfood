'use strict'
function onUpdateFoodBtnClick(e) {
    var foodDom = ancestor(e.target, 'food');
    var updateBtn = foodDom.querySelector("button[name='update']");
    var deleteBtn = foodDom.querySelector("button[name='delete']");
    var okBtn = updateBtn.cloneNode(true);
    okBtn.innerText = 'ok';
    var cancelBtn = deleteBtn.cloneNode(true);
    cancelBtn.innerText = 'cancel';
    foodDom.replaceChild(okBtn, updateBtn);
    foodDom.replaceChild(cancelBtn, deleteBtn);
    enableElement(foodDom);
    okBtn.onclick = function(e) {
	let data = scanData(foodDom);
	sendJSON("/eatfood/foodlist/updatefood", data, function(xhr) {
	    var data = getJSON(xhr);
	    enableElement(foodDom, false);
	    foodDom.replaceChild(updateBtn, okBtn);
	    foodDom.replaceChild(deleteBtn, cancelBtn);
	    if (data) {
		printData(foodDom, data);
	    } else {
		alert("Can't update food");
	    }
	});
    };
    cancelBtn.onclick = function(e) {
	enableElement(foodDom, false);
	foodDom.replaceChild(updateBtn, okBtn);
	foodDom.replaceChild(deleteBtn, cancelBtn);
    }
}
    
function onAddFoodBtnClick(e) {
    var btn = e.target;
    var foodDom = createTemplate('food-template');
    var updateBtn = foodDom.querySelector("button[name='update']");
    var deleteBtn = foodDom.querySelector("button[name='delete']");
    var okBtn = updateBtn.cloneNode(true);
    okBtn.innerText = 'ok';
    var cancelBtn = deleteBtn.cloneNode(true);
    cancelBtn.innerText = 'cancel';
    foodDom.replaceChild(okBtn, updateBtn);
    foodDom.replaceChild(cancelBtn, deleteBtn);
    enableElement(foodDom);
    okBtn.onclick = function(e) {
	let data = scanData(foodDom);
	sendJSON("/eatfood/foodlist/addfood", data, function(xhr) {
	    var data = getJSON(xhr);
	    if (data) {
		printData(foodDom, data);
		enableElement(foodDom, false);
		foodDom.replaceChild(updateBtn, okBtn);
		foodDom.replaceChild(deleteBtn, cancelBtn);
	    } else {
		alert("Can't add food");
		foodDom.parentElement.removeChild(foodDom);
	    }
	});
    }
    cancelBtn.onclick = function() {
	foodDom.parentElement.removeChild(foodDom);
    };
    foodsPane.querySelector('.add-btn').insertAdjacentElement("afterend", foodDom);
}
function onDeleteFoodBtnClick(e) {
    var foodDom = ancestor(e.target, 'food');
    var data = scanData(foodDom);
    sendJSON('/eatfood/foodlist/deletefood/', data, function(xhr) {
	var data = getJSON(xhr);
	if (data && data.result === true) {
	    foodDom.parentElement.removeChild(foodDom);
	} else {
	    alert("Can't delete food");
	}
    });
}

////////////utils
function ancestor(element, className) {
    var parentElement = element.parentElement;
    while (parentElement && !parentElement.className.split(' ').some(function(clazz) { return clazz===className; })) {
	parentElement = parentElement.parentElement;
    }
    if (parentElement && parentElement.className.split(' ').some(function(clazz) { return clazz===className; })) {
	return parentElement;
    } else {
	return null;
    }
}
function createTemplate(templateId) {
    return document.getElementById(templateId).content.firstElementChild.cloneNode(true);
}
function enableElement(element, enable) {
    enable = enable === undefined || enable === null || enable;
    var inputs = element.querySelectorAll('input');
    for (var i = 0; i < inputs.length; i++) {
	inputs[i].disabled = !enable;
    }
    return element;
}
//Search elements by name, and fetch within scanValue their state(value, checked etc.)
function scanData(element) {
    var inputs = element.querySelectorAll('input');
    var data = {};
    for (var i = 0; i < inputs.length; i++) {
	data[inputs[i].name] = scanValue(inputs[i]);
    }
    return data;
}
//Search elements by name, and insert within printValue data[name]
function printData(element, data) {
    for (var p in data) {
	var input = element.querySelector(`input[name='${p}']`);
	if (input) printValue(input, data[p]);
    }
}
function scanValue(input) {
    switch (input.type.toLowerCase()) {
    case 'text': return input.value;
    case 'checkbox': return input.checked;
    default: return input.innerText
    }
}
function printValue(input, value) {
    switch (input.type.toLowerCase()) {
    case 'text': input.value = value;
    case 'checkbox': input.checked = value;
    default: input.innerText = value;
    }    






    
}
//gets data object, transforms it into JSON and send to specified url. Onload = callback
function sendJSON(url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function() { callback(xhr); };
    xhr.send(JSON.stringify(data));
}
//gets XMLHttpRequest object: if sattus === 200 returns data object, else null;
function getJSON(xhr) {
    if (xhr.status === 200) {
	return JSON.parse(xhr.responseText);
    } else {
	return null;
    }
}
//stock for testing
var foodsPane;
window.onload = function () {
    foodsPane = document.getElementById('foods-pane');
    sendJSON("/eatfood/foodlist/foods", {}, function(xhr) {
	var datas = getJSON(xhr);
	for (let i = 0; i < datas.length; i++) {
	    let data = datas[i];
	    let foodDom = createTemplate('food-template');
	    printData(foodDom, data);
	    foodsPane.insertAdjacentElement("beforeend", foodDom);
	}
    });
}
