function register() {
    var client = {
	login: document.querySelector("input[name='login']").value,
	password: document.querySelector("input[name='password']").value,
	name: document.querySelector("input[name='name']").value,
	surname: document.querySelector("input[name='surname']").value,
	email: document.querySelector("input[name='email']").value
    }
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/eatfood/client/register");
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.onload = () => {
	if (xhr.status === 200) {
	    if (xhr.responseText == 'true') {
		window.location.href="/eatfood/foodlist.html";
	    } else {
		alert(xhr.responseText);
	    }
	}
    }
    xhr.send(JSON.stringify(client));
}
	    
