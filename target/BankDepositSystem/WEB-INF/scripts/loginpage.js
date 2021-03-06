/**
 * Created by HuangDanGeeker on 2018/3/17.
 */

window.onload = function () {

}

function login() {
    var userName = $('#userName').val();
    var userNo = $('#userNo').val();
    var userPasswd = $('#userPasswd').val();
    console.log("value : " + userName +  "  " + userPasswd);

    //TODO 记住 -> 用户名和密码
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/login/"+userName+"/"+userNo+"/"+userPasswd,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var loginResult = eval("("+XMLHttpRequest.responseText+")");
            if(loginResult.loginStatus == 'success'){  //登录成功
                $.cookie("userName", userName);
                $.cookie("userName", loginResult.userName);
                $.cookie("userNo", loginResult.userNo);
                if(loginResult.loginRole == 'staff'){
                    $.cookie("loginRole", "staff");
                    window.location.href = "http://localhost:8080/BankDepositSystem/main/staff";
                }else{
                    $.cookie("loginRole", "custm");
                    $.cookie("userPasswd", userPasswd);
                    window.location.href = "http://localhost:8080/BankDepositSystem/main/custm";
                }
            }else{  //登录失败
                $('#infoModal .modal-body').html('登录失败<br>请检查您的账号和密码');
                $('#infoModal .modal-title').html("Login Error");
                $('#infoModal').modal('show');
            }
        }});
}
function regist() {
    var registName = $('#registName').val();
    var registRole = $('#registRole').val();
    var registPasswd = $('#registPasswd').val();
    var registBirthday= $('#registBirthday').val();
    var registPhone = $('#registPhone').val();
    var registAddress = $('#registAddress').val();

    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/regist/"+registName+"/"+registRole+"/"+registPasswd+"/"+registBirthday+"/"+registPhone+"/"+registAddress,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var registResult = eval("("+XMLHttpRequest.responseText+")");
            if(registResult.registStatus == "success"){
                $.cookie("userName", registName);
                $.cookie("userName", registPasswd);
                signin();
                $('#userName').val(registName);
                $('#userPasswd').val(registPasswd);
                $('#infoModal .modal-body').html('注册成功<br>请检查您的账号是 ' + registResult.registNo);
                $('#infoModal .modal-title').text("Regist Success");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').html(result.reason);
                $('#infoModal .modal-title').text("Regist Error");
                $('#infoModal').modal('show');
            }
        }});
}


function signin(){
    $('#loginPage').show();
    $('#registPage').hide();
}
function signup(){
    $('#registPage').show();
    $('#loginPage').hide();
}