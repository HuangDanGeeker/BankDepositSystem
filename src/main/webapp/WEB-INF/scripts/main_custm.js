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

    //绑定--动态按照用户名返回用户账号List
    //绑定--按照选择的用户账号返回用户的详细信息

    //绑定点击刷新&&重新请求客户的信用卡信息
    $('#custmHistoryRadio').click( function () {
        queryCreditCardInfo();
    });
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
    queryCreditCardInfo();
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

    var custmNo = $('#createCustomerPanel #custmNo').val();
    var creditCardNo = $('#createCustomerPanel #creditCardNum').val();
    var custmBirthday = $('#createCustomerPanel #custmBirthday').val();
    var custmPhone = $('#createCustomerPanel #custmPhone').val();
    var custmAddress = $('#createCustomerPanel #custmAddress').val();

    //客户输入完整性检查
    varifyCustomInput(custmNo, creditCardNo, custmBirthday, custmPhone, custmAddress);

    //TODO 检查用户的确认信息


    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/custm/createcreditcard/"+custmNo+"/"+creditCardNo,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status != 200){
                $('#infoModal .modal-body').html('请检查你的输入是否正确');
                $('#infoModal .modal-title').text("Error");
                $('#infoModal').modal('show');
                return;
            }
            var result = eval("("+XMLHttpRequest.responseText+")");
            $('#infoModal .modal-body').text("创建信用卡账号成功");
            $('#infoModal .modal-title').text("Success");
            $('#infoModal').modal('show');
        }
    });

}


function varifyCustomInput(custmNo, creditCardNo, custmBirthday, custmPhone, custmAddress){
    if(custmNo == "" || creditCardNo == "" || custmBirthday == "" ||
        custmPhone == "" || custmAddress == ""){
        $('#infoModal .modal-body').text("please make sure that you have input all the required informations and all of them are correct");
        $('#infoModal .modal-title').text("Invalid Input");
        $('#infoModal').modal('show');
    }
}

function submitAnyTimeDeposit() {

    //TODO 检查用户的确认信息

    var custmNo = $('#anyTimeDealPanel #custmNo').val();
    var creditCardNum = $('#anyTimeDealPanel #creditCardNum').val();
    var nums = $('#anyTimeDealPanel #custmNums').val();
    var type = 1;
    var dueTime = $('#anyTimeDealPanel #dueTime').val();
    if(nums > 0){
        depositAjax(custmNo, creditCardNum, nums, type, dueTime);
    }else if(nums < 0){
        requireAjax(custmNo, creditCardNum, nums);
    }else{
        return;
    }

}


function submitSpecificDeposit() {

    //TODO 检查用户的确认信息

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

    if(nums > 0){
        depositAjax(custmNo, creditCardNum, nums, type, dueTime);
    }else if(nums < 0){
        requireAjax(custmNo, creditCardNum, nums);
    }else{
        return;
    }

}


function depositAjax(custmNo, creditCardNum, nums, type, dueTime) {
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/custm/deposit/"+custmNo+"/"+creditCardNum+"/"+nums+"/"+type+"/"+dueTime,
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
function requireAjax(custmNo, creditCardNum, nums) {
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/custm/require/"+custmNo+"/"+creditCardNum+"/"+nums,
        dataType:'jsonp',
        processData: true,
        type:'get',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                $('#infoModal .modal-body').text("your request is executed successfully");
                $('#infoModal .modal-title').text("Require Success");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').text(result.reason);
                $('#infoModal .modal-title').text("Require Error");
                $('#infoModal').modal('show');
            }
        }});
}


function generateCreditNum() {

    //TODO unsafe request
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/custm/generateCreditCardNo",
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status != 200){
                $('#infoModal .modal-body').html('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
                $('#infoModal .modal-title').text("Login Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#createCustomerPanel #creditCardNum').val(XMLHttpRequest.responseText);
        }
    });

}

function queryCreditCardInfo() {
    $('#custmHistoryPanel').slideDown();

    //请求用户操作确认
    $('#infoModal .modal-body').html("please input your password to confirm your Operation<br> <form class='form'> <div class='form-group col-sm-8'> <input  type='password' id='comfirmedCustmPW' class='form-control' placeholder='please input your password . . .' /></div><button type='button' class='btn btn-success' onclick='processQueryCustmCridetCardInfo()'>查询</button> </form>");
    $('#infoModal .modal-title').text("Confirm Your Operation");
    $('#infoModal').modal('show');



}

function processQueryCustmCridetCardInfo() {

    //检查用户的确认密码
    if ($('#comfirmedCustmPW').val() != $.cookie("userPasswd")) {
        $('#infoModal').modal('toggle');
        $('#infoModal .modal-body').html("the PassWord you input is NOT correct, please try again");
        $('#infoModal .modal-title').text("Incorrect PassWord");
        $('#infoModal').modal('show');
        return;
    }
    var custmNo = $('#userNo').text();
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/custm/queryCustmIntrest/"+custmNo,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status != 200){
                $('#infoModal .modal-body').html('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
                $('#infoModal .modal-title').text("Internet Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#infoModal').modal('toggle');
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "error"){
                $('#infoModal .modal-body').text(result.reason);
                $('#infoModal .modal-title').text("Query Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#custmHistoryPanel tbody tr').remove();
            var parent = $('#custmHistoryPanel tbody');
            for(var i = 0; i < result.list.length; i++){
                parent.append("<tr><td>"+result.list[i].no+"</td><td>"+result.list[i].nums+"</td><td>"+result.list[i].intrest+"</td><td>"+result.list[i].dueTime+"</td></tr>");
            }
        }
    });
}


