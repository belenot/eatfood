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
    closeFormBtn.setAttribute("data-dose-row", doseRow.outerHTML);
    doseRow.parentElement.replaceChild(updateDoseForm, doseRow);
}

function onCloseFormBtnClick(e) {
    var closeFormBtn = e.target;
    var template = document.createElement("template");
    template.innerHTML = closeFormBtn.getAttribute("data-dose-row");
    var originDoseRow = template.content.firstChild;
    var updateDoseForm = closeFormBtn.parentElement.parentElement;
    updateDoseForm.parentElement.replaceChild(originDoseRow, updateDoseForm);
}
