/**
 * Created by 1 on 2018/3/18.
 */
var p;
var creditCardNum = -1;
window.onload = function () {
    console.log($.cookie("userName"));
    console.log($.cookie("userNo"));
    console.log($.cookie("loginRole"));

    document.getElementById('userName').innerHTML = $.cookie("userName");
    document.getElementById('userNo').innerHTML = $.cookie("userNo");
    //TODO
    // ${'#custmName').oninputt 提供用户账号提示功能

    //绑定--动态按照用户名返回用户账号List
    $('#createCustomerPanel #custmName').bind('input', function(param){checkCustmName(param);});
    $('#anyTimeDealPanel #custmName').bind('input', function(param){checkCustmName(param);});
    $('#scheduleDealPanel #custmName').bind('input', function(param){checkCustmName(param);});
    //绑定--按照选择的用户账号返回用户的详细信息
    $('#createCustomerPanel #custmNo').change(function(param){fillCustmInfomation(param);});
}


function logout() {
    window.location.href = "http://localhost:8080/BankDepositSystem/loginpage";
}



function createCustom() {
    displayPanel("block", "none", "none", "none");
}
function anyTimeDeal() {
    displayPanel("none", "none", "block",  "none");
}
function scheduleDeal() {
    displayPanel("none", "block", "none", "none");
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
    creditCardNum = $('#createCustomerPanel #creditCardNum').val();
}
function pasteCreditNum() {
     $('#anyTimeDealPanel #creditCardNum').val(creditCardNum);
     $('#scheduleDealPanel #creditCardNum').val(creditCardNum);
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
            if(result.status == "success"){
                $('#infoModal .modal-body').text("your request is executed successfully");
                $('#infoModal .modal-title').text("Deposit Success");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').text(result.reason);
                $('#infoModal .modal-title').text("Deposit Error");
                $('#infoModal').modal('show');
            }
        }
    });
}


function submitSpecificDeposit() {
    var custmNo = $('#scheduleDealPanel #custmNo').val();
    var creditCardNum = $('#scheduleDealPanel #creditCardNum').val();
    var nums = $('#scheduleDealPanel #custmNums').val();
    var type = 2;
    var dueTime = $('#scheduleDealPanel #dueTime').val();
    console.log(custmNo);
    console.log(creditCardNum);
    console.log(nums);
    console.log(type);
    console.log(dueTime);


    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/deposit/"+custmNo+"/"+creditCardNum+"/"+nums+"/"+type+"/"+dueTime,
        dataType:'jsonp',
        processData: true,
        type:'get',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                $('#infoModal .modal-body').text("your request is executed successfully");
                $('#infoModal .modal-title').text("Deposit Success");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').text(result.reason);
                $('#infoModal .modal-title').text("Deposit Error");
                $('#infoModal').modal('show');
            }
        }});
}

function checkCustmName(param){

    var userName = param.currentTarget.value;
    var parent = $(param.currentTarget).parentsUntil('#operatePanel')[3];
    var custmNoSelect = $(parent).find('#custmNo')[0];
    if(userName == ""){
        return;
    }
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/checkUser/" + userName,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            var info = $(parent).find('#info')[0];
            $(custmNoSelect).empty();
            if(result.count != 0){
                for(var i = 0; i < result.resultList.length; i++){
                    $(custmNoSelect).append("<option>"+result.resultList[i]+"</option>>")
                }
                $(info).text("");
            }else{
                $(info).text("NO UserNo");
            }
        }
    });
}


function fillCustmInfomation(param) {

    var parent = $(param.currentTarget).parentsUntil('#operatePanel')[3];
    var userNameInput = $(parent).find('#custmName')[0];
    var userNo = $(param.currentTarget).val();
    var userName = $(userNameInput).val();
    console.log("userName " + userName);
    console.log("userNo " + userNo);
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/checkUser/"+userName+"/"+userNo,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status != 200){
                $('#infoModal .modal-body').text('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
                $('#infoModal .modal-title').text("Login Error");
                $('#infoModal').modal('show');
                return;
            }
            var result = eval("("+XMLHttpRequest.responseText+")");
            console.log(result);
            $('#createCustomerPanel #custmBirthday').val(result.custm['birthday']);
            $('#createCustomerPanel #custmPhone').val(result.custm['phone']);
            $('#createCustomerPanel #custmAddress').val(result.custm['address']);
        }
    });

}

function generateCreditNum() {

    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/generateCreditCardNo",
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status != 200){
                $('#infoModal .modal-body').text('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
                $('#infoModal .modal-title').text("Login Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#createCustomerPanel #creditCardNum').val(XMLHttpRequest.responseText);
        }
    });

}
