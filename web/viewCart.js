/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function showConfirmation() {
    var modal = document.getElementById("confirm");
    modal.style.display = "flex";
}

function confirmPurchase() {
    var form = document.createElement("form");
    form.action = "MainController";
    form.method = "POST";

    var actionInput = document.createElement("input");
    actionInput.type = "hidden";
    actionInput.name = "action";
    actionInput.value = "Buy";

    form.appendChild(actionInput);
    document.body.appendChild(form);
    form.submit();
    closeConfirmation();
}


function cancelPurchase() {
    closeConfirmation();
}

function closeConfirmation() {
    var modal = document.getElementById("confirm");
    modal.style.display = "none";
}


function openModal(imageUrl) {
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("modalImg");
    modalImg.src = imageUrl;
    modal.style.display = "block";
    modal.onclick = function () {
        modal.style.display = "none";
    };
}
    
function showViewMessageDialog() {
    var viewMessageDialog = document.getElementById('viewMessageDialog');
    var viewMessageText = document.getElementById('viewMessageText');
    viewMessageText.textContent = viewMessage;
    viewMessageDialog.style.display = 'flex';
}

function closeViewMessageDialog() {
    var viewMessageDialog = document.getElementById('viewMessageDialog');
    viewMessageDialog.style.display = 'none';
    window.location.href = 'userPage.jsp';
}

window.onload = function () {
    if (viewMessage !== null && viewMessage !== "") {
        showViewMessageDialog(viewMessage);
    }
};



