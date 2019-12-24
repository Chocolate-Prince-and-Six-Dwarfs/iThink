var btn1=$("#btn1");
var form1=$("#form1");
var btn2=$("#btn2");
var form2=$("#form2");
var close1=$("#close1");
var close2=$("#close2");
btn1.on("click", function () {
    form1.show(200);
    form2.hide(200);
});
btn2.on("click", function () {
    form1.hide(200);
    form2.show(200);
});
close1.on("click", function () {
    form1.hide(200);
});
close2.on("click", function () {
    form2.hide(200);
});
function Login(layer) {
    $(document).on('click','#login',function () {
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
                    if (res.status === 1) {
                        layer.msg("登陆成功");
                        window.location.href="main";
                    }else if(res.status === 0){
                        layer.msg("用户密码错误");
                    }else if(res.status === -1){
                        layer.msg("用户名不存在");
                    }else if(res.status === -2){
                        layer.msg("用户名或密码格式不正确");
                    }
                    else {
                        layer.msg("网络错误");
                    }
                },
                error:function(){
                    layer.msg("网络错误");
                }
            })
        }
        else{
            layer.msg("用户名或密码不能为空");
        }
    });
}
function Register(layer,birthday){
    //document.getElementById("login").onclick=function () {};
    $(document).on('click','#register',function () {
        var pwd;
        var username;
        var phone;
        var email;
        var sex;
        //var birthday;
        username = $("#username").val();
        phone = $("#tel").val();
        email= $("#resign-email").val();
        pwd = $("#resign-pwd").val();
        sex=$('input:radio:checked').val();
        //birthday=$('#birth').val();
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
        if(birthday==null||birthday==""){
            layer.msg("生日为空");
            return false;
        }
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
                    //console.log(res);
                    if (res.status === 1) {
                        layer.msg("注册成功");
                        form1.show(200);
                        form2.hide(200);
                    }else if(res.status === 0){
                        layer.msg("邮箱已被占用");
                    }else if(res.status === -2){
                        layer.msg("用户名、密码或者邮箱格式不正确");
                    }
                    else {
                        layer.msg("网络错误");
                    }
                },
                error:function(){
                    layer.msg("网络错误");
                }
            })
        }
        else{
            layer.msg("输入不能为空");
        }
    });
    //document.getElementById("resign").onclick=function (){}
}
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month
        + seperator1 + strDate + " " + date.getHours() + seperator2
        + date.getMinutes() + seperator2 + date.getSeconds();
    return currentdate;
}