var btn1=document.getElementById("btn1");
var form1=document.getElementById("form1");
var btn2=document.getElementById("btn2");
var form2=document.getElementById("form2");
var close1=document.getElementById("close1");
var close2=document.getElementById("close2");
btn1.onclick=function () {
    form1.style.display="block";
};
btn2.onclick=function () {
    form2.style.display="block";
};
close1.onclick=function () {
    form1.style.display="block";
    form2.style.display="none";
};
close2.onclick=function () {
    form1.style.display="none";
    form2.style.display="block";
};
$(function (){
    document.getElementById("login").onclick=function () {
        var email;
        var pwd;
        email = $("#login-email").val();
        //console.log(email);
        pwd = $("#login-pdw").val();

        var formData = new FormData();
        formData.append("email", email);
        formData.append("pwd", pwd);
        if (email !== "" || pwd !== "") {
            $.ajax({
                url : "user/login",
                type : 'POST',
                data : formData,
                cache: false,
                async: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                // 发的数据给后端
                //如果和数据库匹配成功，后端返回结果给我
                //成功的函数 注意res是返回的结果
                success: function (res) {
                    // 如果结果是1，说明成功
                    //  console.log(res.email);
                    if (res.status === 1) {
                        window.location.href="main";
                    }
                    else {
                        alert("登录失败");//弹出失败的窗
                    }
                },
                error:function(){
                    alert("请求后台失败");
                }
            })
        }
        else{
            alert("输入不能为空");
        }
    };
    document.getElementById("resign").onclick=function () {
        var pwd;
        var username;
        var phone;
        var email;
        var sex;
        var birthday;
        username = $("#username").val();
        phone = $("#tel").val();
        email= $("#resign-email").val();
        pwd = $("#resign-pwd").val();
        sex=$('input:radio:checked').val();
        birthday=$('#birth').val();
        /*console.log(sex);
        console.log(username);
        console.log(birthday);*/
        var formData = new FormData();
        formData.append("email", email);
        formData.append("username", username);
        formData.append("pwd", pwd);
        formData.append("phone", phone);
        formData.append("sex", sex);
        formData.append("birthday", birthday);
        if (username !== "" && pwd !== "" && email !=="") {
            $.ajax({
                url : "user/register",
                type : 'POST',
                data : formData,
                cache: false,
                async: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                // 发的数据给后端,只需返回状态1或0给我
                //sex为中文：男，女
                //birth为：2019-12-12格式

                success: function (res) {
                    // 如果结果是1，说明注册成功
                    console.log(res)
                    if (res.status === 1) {
                        alert("注册成功");//弹出成功的窗
                    }
                    else {
                        alert("注册失败");//弹出失败的窗
                    }
                },
                error:function(){
                    alert("请求后台失败");
                }
            })
        }
        else{
            alert("输入不能为空");
        }
    }
});