'use strict'
function from(element) {
    for (var p1 in this) {
	this[p1] = null;
	let subelement = element.querySelector(`.${p1}`);
	if (!subelement)continue;
	if (subelement.className && subelement.className.split(' ').some(function(x){return x.split('-')[1] === 'object';})) {
	    let objectClass = subelement.className.split(' ').filter(function(x){return x.split('-')[1]==='object';})[0].split('-')[0];
	    if (objectClass in validObjects) {
		let object = new validObjects[objectClass]();
		object.from(subelement);
		this[p1] = object || null;
	    }
	} else {
	    this[p1] = subelement.value || subelement.innerText || null;
	}   
    }
    return this;
}
function to(element) {
    for (var p1 in this) {
	if (this[p1] instanceof Object) {
	    var subelement = element.querySelector(`.${p1}-object`);
	    this[p1].to(element);
	} else {
	    var subelement = element.querySelector(`.${p1}`);
	    if (!subelement) continue;
	    if (subelement.tagName.toLowerCase() === 'input') {
		subelement.value = this[p1];
	    } else {
		subelement.innerText = this[p1];
	    }
	}
    }
    return this;
}
function copy(obj) {
    for (var p1 in this) {
	if (p1 in validObjects) {
	    this[p1] = new validObjects[p1]();
	    this[p1].copy(obj[p1]);
	    continue
	}
	this[p1] = obj[p1] || this[p1];
    }
}

function ElementData(data, element) {
    Object.defineProperty(this, 'data', {
	configurable: false,
	get: function() { return data; },
	set: function(newData) { data = newData; data.to(element); }
    });
    Object.defineProperty(this, 'element', {
	configurable: false,
	get: function() { return element; },
	set: function(newElement) { element = newElement; data.from(element); }
    });
}

Object.defineProperty(ElementData.prototype, 'updateElement', {
    value: function() { this.data.to(this.element); },
    enumerable: false
});
Object.defineProperty(ElementData.prototype, 'updateData', {
    value: function() { this.data.from(this.element); },
    enumerable: false
});

 
function Food() {
    this.id = -1;
    this.name = '';
    this.common = undefined;
    this.calories = undefined;
    this.protein = undefined;
    this.carbohydrate = undefined;
    this.fat = undefined;
}
Food.prototype.constructor = Food;
Object.defineProperty(Food.prototype, 'from', { value: from, enumerable: false });
Object.defineProperty(Food.prototype, 'to', { value: to, enumerable: false });
Object.defineProperty(Food.prototype, 'copy', { value: copy, enumerable: false });
function Dose() {
    this.id = undefined;
    this.gram = undefined;
    this.date = undefined;
    this.food = undefined;
}
Dose.prototype.constructor = Dose;
Object.defineProperty(Dose.prototype, 'from', { value: from, enumerable: false });
Object.defineProperty(Dose.prototype, 'to', { value: to, enumerable: false });
Object.defineProperty(Dose.prototype, 'copy', { value: copy, enumerable: false });
var validObjects = {
    food: Food,
    dose: Dose
}


var totalNutrientDivs = null;
window.onload = function () {
    loadFood();
    if (document.title == "Dose List") {
	initDoseList();
    }
    if (document.title == "Food List") {
	initFoodList();
    }
}

//Ajax functions
function doses(dateFirst, dateLast, elementDataResult) {
    elementDataResult = elementDataResult instanceof Array ? elementDataResult : [];
    dateFirst = encodeURIComponent(dateFirst);
    dateLast = encodeURIComponent(dateLast);
    var request = `dateFirst=${dateFirst}&dateLast=${dateLast}`;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/doselist/doses");
    //xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    let doseArray = JSON.parse(xhr.responseText);
	    for (let i = 0; i < doseArray.length; i++) {
		let newDose = new Dose();
		newDose.copy(doseArray[i]);
		let newElement = document.querySelector('#dose-row-template').content.firstElementChild.cloneNode(true);
		newDose.to(newElement);
		elementDataResult.push(new ElementData(newDose, newElement));
	    }
	    updateDosesPane(elementDataResult);
	}
    }
    xhr.send(request);
}
function updateDose(dose) {
    var requestBody = JSON.stringify(dose);
    dose.id = -1;
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/doselist/updatedose");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
	    dose.copy(JSON.parse(xhr.responseText));
	}
    }
    xhr.send(requestBody);
}
    
    
    

//Dom modify functions
function updateDosesPane(elementDataArray) {
    for (let i = 0; i < elementDataArray.length; i++) {
	let elementData = elementDataArray[i];
	document.querySelector('#doses-pane').insertAdjacentElement("afterbegin", elementData.element);
    }
}

		
		
		
