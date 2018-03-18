/**
 * Created by 1 on 2018/3/18.
 */

var creditCardNum = -1;

window.onload = function () {
    console.log($.cookie("userName"));
    console.log($.cookie("userNo"));
    console.log($.cookie("loginRole"));

    document.getElementById('userName').innerHTML = $.cookie("userName");
    document.getElementById('userNo').innerHTML = $.cookie("userNo");
    //TODO
    // ${'#custmName').oninputt 提供用户账号提示功能
}


function logout() {
    window.location.href = "http://localhost:8080/BankDepositSystem/loginpage";
}



function createCustom() {
    displayPanel("block", "none", "none", "none");
}
function anyTimeDeal() {
    displayPanel("none", "block", "none",  "none");
}
function scheduleDeal() {
    displayPanel("none", "none", "block", "none");
}
function accountDetail() {
    displayPanel("none", "none", "none", "block");
}

function displayPanel(b1, b2, b3, b4) {
    $('#createCustomerPanel').css('display', b1);
    $('#anyTimeDealPanel').css('display', b2);
    $('#scheduleDealPanel').css('display', b3);
    $('#accountDetailPanel').css('display', b4);
}


function copyCreditNum() {
    creditCardNum = $('#creditCardNum');
}
function pasteCreditNum() {
     $('#creditCardNum').val(creditCardNum);
}

