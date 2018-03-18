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

function submitCreateCredit(){

    var custmNo = $('#createCustomerPanel #custmNo').find("option:selected").text();
    var creditCardNum= $('#createCustomerPanel #creditCardNum').val();

    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/createcreditcard/"+custmNo+"/"+creditCardNum,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            console.log("create credit card success");
        }
    });

}

function submitAnyTimeDeposit() {

    console.log("hehehe");

    var custmNo = $('#anyTimeDealPanel #custmNo').val();
    var creditCardNum = $('#anyTimeDealPanel #creditCardNum').val();
    var nums = $('#anyTimeDealPanel #custmNums').val();
    var type = 1;
    var dueTime = $('#anyTimeDealPanel #dueTime').val();

    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/deposit/"+custmNo+"/"+creditCardNum+"/"+nums+"/"+type+"/"+dueTime,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            console.log("deposit succcess");
        }
    });
    console.log("dadawda");
}


function submitSpecificDeposit() {
    var custmNo = $('#scheduleDealPanel #custmNo').val();
    var creditCardNum = $('#scheduleDealPanel #creditCardNum').val();
    var nums = $('#scheduleDealPanel #custmNums').val();
    var type = 2;
    var dueTime = $('#scheduleDealPanel #dueTime').val();

    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/deposit/"+custmNo+"/"+creditCardNum+"/"+nums+"/"+type+"/"+dueTime,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            console.log("deposit succcess");
        }});
}

