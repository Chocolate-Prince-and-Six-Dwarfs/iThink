var user_id;
function getUserId() {
    $.ajax({
        url:"/user/getLoginId",
        type:"post",
        async:false,
        success:function (data) {
            //console.log(data);
            user_id=data;
        },
        error:function () {
            console.log("读取用户Id失败.")
        }
    });
}

function getUserInfo(userId,form) {
    $.ajax({
        url:"/user/getById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            console.log(data);
            $("#userImg").attr("src","data:image/png;base64,"+data.head);
            $("#userName").text(data.name);
            $("#userIndustry").text(data.industry);
            $("#srcimgurl").attr("src","data:image/png;base64,"+data.head);
            $(".user_name").val(data.name);
            $(".user_tel").val(data.phone);
            $(".user_industry").val(data.industry);
            $(".user_school").val(data.school);
            checkGender(data.sex,form);
            $(".user_desc").val(data.introduction);
        },
        error:function () {
            console.log("读取用户信息失败！");
        }
    });
}

function updataImg(userId,userImg) {
    $.ajax({
        url:"user/updateInfo",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
            head:userImg,
        },
        success:function (data) {
            console.log("success.");
        },
        error:function () {
            console.log("failed.");
        }
    });
}

/*function getUserInfo(userId) {
    $.ajax({
        url:"/user/getById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            console.log(data);
        },
        error:function () {
            console.log("读取用户信息失败！");
        }
    });
}*/

function checkGender(mValue,form){
    var genderRadio = document.getElementsByName("sex");
    for(var i=0;i<genderRadio.length;i++){
        if(mValue==genderRadio[i].value){
            genderRadio[i].checked=true;
        }else{
            genderRadio[i].checked=false;
        }
        form.render();
    }
}