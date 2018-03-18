/**
 * Created by HuangDanGeeker on 2018/3/17.
 */

window.onload = function () {

}

function login() {
    var userName = $('#userName').val();
    var userPasswd = $('#userPasswd').val();
    console.log("value : " + userName +  "  " + userPasswd);

    //TODO 记住 -> 用户名和密码
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/login/"+userName+"/"+userPasswd,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var loginResult = eval("("+XMLHttpRequest.responseText+")");
            if(loginResult.loginStatus == 'success'){  //登录成功
                $.cookie("userName", userName);
                if(loginResult.loginRole == 'staff'){
                    $.cookie("loginRole", "Staff");

                    window.location.href = "http://localhost:8080/BankDepositSystem/main/staff";
                }else{
                    $.cookie("loginRole", "Custm");
                    window.location.href = "http://localhost:8080/BankDepositSystem/main/custm";
                }
            }else{  //TODO 登录失败

            }
        }});
}
function regist() {
    var registName = $('#registName').val();
    var registPasswd = $('#registPasswd').val();
    var registBirthday= $('#registBirthday').val();
    var registPhone = $('#registPhone').val();
    var registAddress = $('#registAddress').val();

    //TODO 记住 -> 用户名和密码
    $.ajax({
        url:"http://localhost:8080/BankDepositSystem/regist/"+registName+"/"+registPasswd+"/"+registBirthday+"/"+registPhone+"/"+registAddress,
        dataType:'jsonp',
        processData: true,
        type:'put',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.responseText);
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