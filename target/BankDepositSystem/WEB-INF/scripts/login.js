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
            console.log("login success");
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("login failed");
        }});
}
