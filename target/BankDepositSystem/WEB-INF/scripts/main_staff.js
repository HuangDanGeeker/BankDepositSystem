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
    //绑定--查询客户&&柜员操作记录
    $('#staffHistoryRadio').click( function () {
        if($('#staffHistoryPanel').css('display') == 'block'){
            return;
        }
        queryHistoryView();
        qeruyStaffOperRecrd();
    });
    $('#custmHistoryRadio').click( function () {
        if($('3custmHistoryPanel').css('display') == 'block'){
            return;
        }
        queryHistoryView();});
}


function logout() {
    window.location.href = "http://localhost:8080/BankDepositSystem/loginpage";
}



function createCustom() {
    displayPanel("block", "red", "none", "chocolate", "none", "chocolate", "none", "chocolate");

}
function anyTimeDeal() {
    displayPanel("none", "chocolate", "none", "chocolate", "block", "red", "none", "chocolate");
}
function scheduleDeal() {
    displayPanel("none", "chocolate", "block", "red", "none", "chocolate", "none", "chocolate");
}
function accountDetail() {
    displayPanel("none", "chocolate", "none", "chocolate", "none", "chocolate", "block", "red");
    $('#staffHistoryRadio').trigger('click');
}

function displayPanel(b1, c1, b2, c2, b3, c3, b4, c4) {
    $('#createCustomerPanel').css('display', b1);
    $('#anyTimeDealPanel').css('display', b2);
    $('#scheduleDealPanel').css('display', b3);
    $('#accountDetailPanel').css('display', b4);
    $('#createCustmDiv').css('background-color', c1);
    $('#scheduleDealDiv').css('background-color', c2);
    $('#anyTimeDealDiv').css('background-color', c3);
    $('#accountDetailDiv').css('background-color', c4);
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

function submitAnyTimeDeposit() {

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
                $(custmNoSelect).append('<option disabled selected>click and select a customer No</option>');
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


function depositAjax(custmNo, creditCardNum, nums, type, dueTime) {
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
function requireAjax(custmNo, creditCardNum, nums) {
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/require/"+custmNo+"/"+creditCardNum+"/"+nums,
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

function fillCustmInfomation(param) {
    console.log("herhr");
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
                $('#infoModal .modal-body').html('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
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
                $('#infoModal .modal-body').html('连接失败<br>请检查您的网络后,尝试刷新以解决错误');
                $('#infoModal .modal-title').text("Login Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#createCustomerPanel #creditCardNum').val(XMLHttpRequest.responseText);
        }
    });

}

function queryHistoryView(){
    console.log("herherh");
    $('#staffHistoryPanel').slideToggle();
    $('#custmHistoryPanel').slideToggle();
}


function qeruyStaffOperRecrd(){

    var staffNo = $('#userNo').text();
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/queryStaffOperRecord/"+staffNo,
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
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "error"){
                $('#infoModal .modal-body').text(result.reason);
                $('#infoModal .modal-title').text("Query Error");
                $('#infoModal').modal('show');
                return;
            }
            $('#staffHistoryPanel tbody tr').remove();
            var parent = $('#staffHistoryPanel tbody');
            for(var i = 0; i < result.list.length; i++){
                parent.append("<tr><td>"+result.list[i].operType+"</td>><td>"+result.list[i].custmName+"</td><td>"+result.list[i].custmNo+"</td><td>"+result.list[i].creditCardNo+"</td><td>"+result.list[i].nums+"</td><td>"+result.list[i].operDate+"</td></tr>");
            }
        }
    });
}

function qeruyCustmOperRecrd(){

    var custmNo = $('#accountDetailPanel #custmNo').val();
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/staff/queryCustmIntrest/"+custmNo,
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

