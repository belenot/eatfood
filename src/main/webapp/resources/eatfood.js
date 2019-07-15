'use strict'
function onUpdateFoodBtnClick(e) {
    var foodDom = ancestor(e.target, 'food');
    var origData = scanData(foodDom);
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
	printData(foodDom, origData);
	foodDom.replaceChild(updateBtn, okBtn);
	foodDom.replaceChild(deleteBtn, cancelBtn);
    }
}    
function onAddFoodBtnClick(e) {
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

function onLoadDosesBtnClick(e) {
    var dateInterval = {
	dateFirst: dosesPane.querySelector('.dose-date-first').value,
	dateLast: dosesPane.querySelector('.dose-date-last').value
    }
    sendJSON("/eatfood/doselist/doses", dateInterval, function(xhr) {
	var datas = getJSON(xhr);
	for (let i = 0; i < datas.length; i++) {
	    let data = datas[i];
	    let doseDom = createTemplate('dose-template');
	    
	    printData(doseDom, data);
	    dosesPane.querySelector('.date-interval').insertAdjacentElement("beforebegin", doseDom);
	}
	populateTotalPane(calculateTotal());
    });
}
function onUpdateDoseBtnClick(e) {
    var doseDom = ancestor(e.target, 'dose');
    var origData = scanData(doseDom);
    var updateBtn = doseDom.querySelector("button[name='update']");
    var deleteBtn = doseDom.querySelector("button[name='delete']");
    var okBtn = updateBtn.cloneNode(true);
    okBtn.innerText = 'ok';
    var cancelBtn = deleteBtn.cloneNode(true);
    cancelBtn.innerText = 'cancel';
    doseDom.replaceChild(okBtn, updateBtn);
    doseDom.replaceChild(cancelBtn, deleteBtn);
    enableElement(doseDom);
    doseDom.querySelector('.name').disabled = true;
    okBtn.onclick = function(e) {
	let data = scanData(doseDom);
	sendJSON("/eatfood/doselist/updatedose", data, function(xhr) {
	    var data = getJSON(xhr);
	    enableElement(doseDom, false);
	    doseDom.replaceChild(updateBtn, okBtn);
	    doseDom.replaceChild(deleteBtn, cancelBtn);
	    if (data) {
		printData(doseDom, data);
	    } else {
		alert("Can't update dose");
	    }
	    populateTotalPane(calculateTotal());
	});
    };
    cancelBtn.onclick = function(e) {
	enableElement(doseDom, false);
	printData(doseDom, origData);
	doseDom.replaceChild(updateBtn, okBtn);
	doseDom.replaceChild(deleteBtn, cancelBtn);
	populateTotalPane(calculateTotal());
    }
}
function onAddDoseBtnClick(e) {
    var doseDom = createTemplate('dose-template');
    var updateBtn = doseDom.querySelector("button[name='update']");
    var deleteBtn = doseDom.querySelector("button[name='delete']");
    var okBtn = updateBtn.cloneNode(true);
    okBtn.innerText = 'ok';
    var cancelBtn = deleteBtn.cloneNode(true);
    cancelBtn.innerText = 'cancel';
    doseDom.replaceChild(okBtn, updateBtn);
    doseDom.replaceChild(cancelBtn, deleteBtn);
    enableElement(doseDom);
    var selectFood = document.createElement('select');
    selectFood.setAttribute("class", "name");
    selectFood.setAttribute("data-name", "dose.food.name");
    selectFood.setAttribute("name", "name");
    for (let i = 0; i < foods.length; i++) {
	let option = document.createElement('option');
	option.value = foods[i].name;
	option.innerText = foods[i].name;
	selectFood.insertAdjacentElement("afterbegin", option);
    }
    var inputName = doseDom.querySelector('.name');
    doseDom.replaceChild(selectFood, inputName);
    okBtn.onclick = function(e) {
	let data = scanData(doseDom);
	sendJSON("/eatfood/doselist/adddose", data, function(xhr) {
	    var data = getJSON(xhr);
	    if (data) {
		var dateFirst = new Date(dosesPane.querySelector('.dose-date-first').value);
		var dateLast = new Date(dosesPane.querySelector('.dose-date-last').value);
		var doseDate = new Date(data.date);
		doseDom.replaceChild(inputName, selectFood);
		printData(doseDom, data);
		enableElement(doseDom, false);
		doseDom.replaceChild(updateBtn, okBtn);
		doseDom.replaceChild(deleteBtn, cancelBtn);
		if (doseDate < dateFirst || doseDate > dateLast) {
		    doseDom.parentElement.removeChild(doseDom);
		}
		populateTotalPane(calculateTotal());
	    } else {
		alert("Can't add dose");
		doseDom.parentElement.removeChild(doseDom);
		populateTotalPane(calculateTotal());
	    }
	});
    }
    cancelBtn.onclick = function() {
	doseDom.parentElement.removeChild(doseDom);
	populateTotalPane(calculateTotal());
    };
    dosesPane.querySelector('.add-btn').insertAdjacentElement("afterend", doseDom);
}
function onDeleteDoseBtnClick(e) {
    var doseDom = ancestor(e.target, 'dose');
    var data = scanData(doseDom);
    sendJSON('/eatfood/doselist/deletedose/', data, function(xhr) {
	var data = getJSON(xhr);
	if (data && data.result === true) {
	    doseDom.parentElement.removeChild(doseDom);
	} else {
	    alert("Can't delete dose");
	}
	populateTotalPane(calculateTotal());
    });
}

function spanName(e) {
    var foodEl = e.target;
    var nameText = foodEl.querySelector('.name');
    var gramText = foodEl.querySelector('.gram');
    var gram = gramText.value;
    if (!gramText.disabled) return;
    for (var i = 0; i < foods.length; i++) {
	if (foods[i].name === nameText.value) {
	    var food = foods[i];
	}
    }
    if (!food) return;
    var nutrients = {fat: "", carbohydrate: "", protein: "", calories: ""};
    var labelTemplate = document.createElement('label'); labelTemplate.style.flex=1;
    nameText.style.flex=1;
    for (let nut in nutrients) {
	nutrients[nut] = labelTemplate.cloneNode(true);
	nutrients[nut].innerText = Math.ceil(food[nut] / 100 * gram);
	nameText.insertAdjacentElement("afterend", nutrients[nut]);
    }
    foodEl.onmouseleave = function(e) {
	for (let nut in nutrients) {
	    nutrients[nut].parentElement.removeChild(nutrients[nut]);
	    nameText.style.flex = 5;
	}
	foodEl.onmouseleave = undefined;
    }
}
function calculateTotal() {
    var doses = dosesPane.querySelectorAll('.dose');
    var total = {calories: 0, protein: 0, carbohydrate: 0, fat: 0};
    for (let i = 0; i < doses.length; i++) {
	let name = doses[i].querySelector('.name').value;
	let gram = doses[i].querySelector('.gram').value;
	let foodEl = Array.from(foods).filter(function(x) { return x.name === name; })[0];
	if (!foodEl) continue;
	for (let nut in total) {
	    total[nut] += Math.ceil(foodEl[nut] / 100 * gram);
	}
    }
    return total;
}
function populateTotalPane(total) {
    var totalPane = document.querySelector('.total-nutrients');
    for (let nut in total) {
	totalPane.querySelector(`.${nut}`).innerText = total[nut];
    }
}
    

////////////utils
function charCount(str, c) {
    var count = 0;
    for (let i = 0; i < str.length; i++) { 
	count += str[i] == c ? 1 : 0;
    }
    return count;
}
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
function scanData(element, dataObject) {
    dataObject = dataObject || element.dataset.object;
    var data = {};

    var dataNameElements = Array.from(element.querySelectorAll('[data-name]'));
    dataNameElements = dataNameElements.filter(function(x) {return x.dataset.name.match("^"+dataObject.replace(/\./, '\\.'))});
    for (let i = 0; i < dataNameElements.length; i++) {
	let dataNameElement = dataNameElements[i];
	let fullDataName = dataNameElement.dataset.name;
	let remainDataName = fullDataName.split('.').slice(charCount(dataObject, '.') + 1).join('.');
	let lastDataName = fullDataName.split('.').slice(-1).join('.');
	if (remainDataName === lastDataName) {
	    data[lastDataName] = scanValue(dataNameElement);
	    continue;
	}
	let nextDataName = remainDataName.split('.').slice(0,1).join('.');
	if (nextDataName in data) continue;
	data[nextDataName] = scanData(element, `${dataObject}.${nextDataName}`);
    }
    return data;
}
function printData(element, data, dataObject) {
    var dataObject = dataObject || element.dataset.object;
    for (let property in data) {
	if (!data.hasOwnProperty(property)) continue;
	if (data[property] instanceof Object) {
	    printData(element, data[property], `${dataObject}.${property}`);
	    continue;
	}
	let input = element.querySelector(`input[data-name='${dataObject}.${property}'`);
	if (input) {
	    printValue(input, data[property]);
	}
    }
}
function scanValue(input) {
    switch (input.type.toLowerCase()) {
    case 'select-one':
    case 'date': 
    case 'hidden':
    case 'text': return input.value;
    case 'checkbox': return input.checked;
    default: return input.innerText
    }
}
function printValue(input, value) {
    switch (input.type.toLowerCase()) {
    case 'select': 
    case 'date':
    case 'hidden': 
    case 'text': input.value = value; break;
    case 'checkbox': input.checked = value; break;
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
//stock
var foodsPane;
var dosesPane;
var foods = [];
window.onload = function () {
    if (location.pathname === '/eatfood/foodlist') {
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
    if (location.pathname === '/eatfood/doselist') {
	dosesPane = document.querySelector('#doses-pane');
	onLoadDosesBtnClick();
	sendJSON("/eatfood/foodlist/foods", {} , function(xhr) {
	    foods = getJSON(xhr) || null;
	});
    }
}
