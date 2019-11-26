var user_id;
function getUserId() {
    $.ajax({
        url:"/user/getLoginId",
        type:"post",
        async:false,
        success:function (data) {
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
            $(".user_addr").val(data.address);
        },
        error:function () {
            console.log("读取用户信息失败！");
        }
    });
}

function saveUserInfo(form,layer){//保存个人信息
    $(document).off('click','#userInfoForm').on('click','#userInfoForm',function () {
        var formData=new FormData();
        formData.append('id',user_id);
        formData.append('name',$(".user_name").val());
        formData.append('phone',$(".user_tel").val());
        formData.append('address',$(".user_addr").val());
        formData.append('industry',$(".user_industry").val());
        formData.append('school',$(".user_school").val());
        formData.append('introduction',$(".user_desc").val());
        formData.append('sex',$('input:radio[name="sex"]:checked').val());
        $.ajax({
            url:"user/updateInfo",
            type:"post",
            dataType: "json",
            data:formData,
            processData: false,
            contentType: false,
            success:function () {
                //layer.msg("修改信息成功");
                form.render();
            },
            error:function () {
                layer.msg("修改信息失败");
            }
        });
    })
}

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

function getUserIdeas(layer,userId) {
    $.ajax({
        url:"user/getInfoByUserId",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
            opinion:"topic",
        },
        success:function (data) {
            //console.log(data);
            if(data.count==0){
                return false;
            }
            $(".userIdeasContent").empty();
            //console.log("读取个人创意信息成功");
            var myIdeas="";
            var text="<h2  style=\"text-align: center;font-weight: bold\">我的创意</h2>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul class=\"userIdeasContentUL\"><hr>\n" +
                "            \n" +
                "        </ul>\n" +
                "    </div>";
            $(".userIdeasContent").append(text);
            for(var i in data.data){
                myIdeas="<li>\n" +
                    "                <div class=\"title\" style=\"text-align: center;margin-top: 20px\">"+data.data[i].title+"</div>\n" +
                    "                <div>\n" +
                    "                    <span>"+subStringIdeaContent(data.data[i].content)+"...</span>\n" +
                    "                </div>\n" +
                    "                <div style=\"text-align: right\" class=\"ideaDivBut\">\n" +
                    "                    <span>时间:"+data.data[i].time.substring(0,10)+"</span>\n" +
                    "                    <span style=\"margin-left: 10px\">收藏数:"+data.data[i].collect+"</span>\n" +
                    "                    <span style=\"margin-left: 10px\">点赞数:"+data.data[i].like+"</span>\n" +
                    "                    <span  style=\"margin-left: 10px\"><button data-type=\"changeIdea\" class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary changeIdea\" ideaId=\""+data.data[i].id+"\">修改创意</button></span>\n" +
                    "                    <span  style=\"margin-left: 10px\"><button class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary deleteIdea\" ideaId=\""+data.data[i].id+"\">删除创意</button></span>\n" +
                    "                    <span  style=\"margin-left: 10px\"><button class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary createGroup\" ideaId=\""+data.data[i].id+"\">生成团组</button></span>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "            <hr>";
                $(".userIdeasContentUL").append(myIdeas);
            }

        },
        error:function () {
            layer.msg("读取个人创意信息失败");
        }
    })
}

function getUserFans(userId) {
    $.ajax({
        url:"user/getInfoByUserId",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
            opinion:"fans",
        },
        success:function (data) {
            //console.log(data);
            if(data.count==0){
                return false;
            }
            $(".userFansContent").empty();
            var myFans="";
            var text="<h2  style=\"text-align: center;font-weight: bold\">我的粉丝</h2>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul class=\"userFansContentUL\" style=\"border: 1px solid #c0c0c0;\">\n" +
                "            \n" +
                "        </ul>\n" +
                "    </div>";
            $(".userFansContent").append(text);
            for(var i in data.data){
                myFans="<li >\n" +
                    "                            <div class=\"title\">"+data.data[i].name+"</div>\n" +
                    "                            <div class=\"user-head\">\n" +
                    "                                <img src=\"data:image/png;base64,"+data.data[i].head+"\" alt=\"\">\n" +
                    "                            </div>\n" +
                    "                        </li>\n" +
                    "                        <hr>";
                $(".userFansContentUL").append(myFans);
            }
        },
        error:function () {
            console.log("读取失败！");
        }
    })
}

function getUserNotice(userId){
    var source=new EventSource('/notify?id='+userId);
    source.onmessage = function (event) {
        console.info(event.data);
    };
}

function subStringIdeaContent(ideaContent){ //截取部分创意内容
    ideaContent = ideaContent.replace(/(\n)/g, "");
    ideaContent = ideaContent.replace(/(\t)/g, "");
    ideaContent = ideaContent.replace(/(\r)/g, "");
    ideaContent = ideaContent.replace(/<\/?[^>]*>/g, "");
    ideaContent = ideaContent.replace(/\s*/g, "");
    var ic=ideaContent.substring(0,100);
    return ic;
}