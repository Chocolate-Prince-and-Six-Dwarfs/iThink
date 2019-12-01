var user_id;
var source;
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
            url:"/user/updateInfo",
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
        url:"/user/getInfoByUserId",
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

function getUserCollect(userId) {
    $.ajax({
        url:"user/getCollectById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            //console.log("成功"+data.achievement[0]+"---"+data.topic);
            if(data.achievement.length==0&&data.topic.length==0){
                return false;
            }
            $(".userCollectionsContent").empty();
            var myCollection="";
            var text="<h2  style=\"text-align: center;font-weight: bold\">我的收藏</h2>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul class=\"userCollectionsContentUL\"><hr>\n" +
                "            \n" +
                "        </ul>\n" +
                "    </div>";
            $(".userCollectionsContent").append(text);
            for(var i in data.achievement) {
                myCollection = "<li>\n" +
                    "                <div class=\"title\" style=\"text-align: center;margin-top: 20px;font-weight: bold\">创意实现</div>\n" +
                    "                <div>\n" +
                    "                    <span>" + getNoticeAchievementContent(data.achievement[i]) + "</span>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "            <hr>";
                $(".userCollectionsContentUL").append(myCollection);
            }
            for(var j in data.topic) {
                myCollection = "<li>\n" +
                    "                <div class=\"title\" style=\"text-align: center;margin-top: 20px;font-weight: bold\">创意标题："+getNoticeTopicName(data.topic[j])+"</div>\n" +
                    "                <div>\n" +
                    "                    <span><b style=\"font-weight: bold\">创意内容： </b>" + subStringIdeaContent(getNoticeTopicContent(data.topic[j])) + "...</span>\n" +
                    "                </div>\n" +
                    "<div style=\"text-align: right\">" +
                    "<button  class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary viewIdeaInfoFromUserCollections\" ideaId=\""+data.topic[j]+"\">查看详情</button>" +
                    "</div>" +
                    "            </li>\n" +
                    "            <hr>";
                $(".userCollectionsContentUL").append(myCollection);
            }
            $(document).off('click','.viewIdeaInfoFromUserCollections').on('click','.viewIdeaInfoFromUserCollections',function(){
                var ideaId=$(this).attr('ideaId');
                window.location.href='/viewIdea?ideaId='+ideaId;
            });
        },
        error:function () {
            console.log("读取个人收藏失败");
        }
    })
}

function getUserFans(userId) {
    $.ajax({
        url:"/user/getInfoByUserId",
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
                    "                                <b class=\"clickUserFans\" userId=\""+data.data[i].id+"\"><img src=\"data:image/png;base64,"+data.data[i].head+"\" alt=\"\"></b>\n" +
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

function getNoticeUserName(userId) {
    var content;
    $.ajax({
        url:"/user/getById",
        type:"post",
        dataType: "json",
        async:false,
        data:{
            id:userId,
        },
        success:function (data) {
            content=data.name;
        },
        error:function () {
            console.log("获取用户名失败");
        }
    });
    return content;
}

function getNoticeTopicName(topicId) {
    var content;
    $.ajax({
        url:"/idea/detail",
        type:"post",
        dataType: "json",
        async:false,
        data:{
            id:topicId,
        },
        success:function (data) {
            content=data.topic.title;
        },
        error:function () {
            console.log("获取创意标题失败");
        }
    });
    return content;
}

function getNoticeTopicContent(topicId) {
    var content;
    $.ajax({
        url:"/idea/detail",
        type:"post",
        dataType: "json",
        async:false,
        data:{
            id:topicId,
        },
        success:function (data) {
            content=data.topic.content;
        },
        error:function () {
            console.log("获取创意内容失败");
        }
    });
    return content;
}

function getNoticeCommentContent(commentId) {
    var content;
    $.ajax({
        url:"/comment/getById",
        type:"post",
        dataType: "json",
        async:false,
        data:{
            id:commentId,
        },
        success:function (data) {
            content=data.content;
        },
        error:function () {
            console.log("获取评论内容失败");
        }
    });
    return content;
}

function getNoticeAchievementContent(achievementId) {
    var content;
    $.ajax({
        url:"/ach/getById",
        type:"post",
        dataType: "json",
        async:false,
        data:{
            id:achievementId,
        },
        success:function (data) {
            content=data.content;
        },
        error:function () {
            console.log("获取创意实现内容失败");
        }
    });
    return content;
}

function getUserNotice(userId){
    source=new EventSource('/notify?id='+userId);
    $(".userNoticesContent").empty();
    var text="<h2  style=\"text-align: center;font-weight: bold\">我的新消息</h2>\n" +
        "    <div class=\"layui-tab-content\">\n" +
        "        <ul class=\"userNoticesContentUL\"><hr>\n" +
        "            \n" +
        "        </ul>\n" +
        "    </div>";
    $(".userNoticesContent").append(text);
    source.onmessage = function (event) {
        var d=JSON.parse("["+event.data+"]");
        var notice="";

        //console.log(d);

        var ach=d[0].achievementLike;
        if(ach!=""&&ach!=null&&ach!="[]"){
            var info1="";
            if(ach[0][0]["type"]==true){
                info1="赞";
            }else{
                info1="踩";
            }
            notice="<li>" +getNoticeUserName(ach[0][0]["userId"])+
                " 在"+getMSDate(ach[0][0]["time"])+"给你的创意实现：\"" +getNoticeAchievementContent(ach[0][0]["achievementId"])+"\"点了一个"+info1+
                "</li><hr>";
            $(".userNoticesContentUL").append(notice);
        }

        var top=d[0]["topicLike"];
        if(top!=""&&top!=null&&top!="[]"){
            var info2="";
            if(top[0][0]["type"]==true){
                info2="赞";
            }else{
                info2="踩";
            }
            notice="<li>" +getNoticeUserName(top[0][0]["userId"])+
                " 在"+getMSDate(top[0][0]["time"])+"给你的创意：\"" +getNoticeTopicName(top[0][0]["topicId"])+"\"点了一个"+info2+
                "</li><hr>";
            $(".userNoticesContentUL").append(notice);
        }

        var com=d[0]["commentLike"];
        if(com!=""&&com!=null&&com!="[]"){
            var info3="";
            if(com[0][0]["type"]==true){
                info3="赞";
            }else{
                info3="踩";
            }
            notice="<li>" +getNoticeUserName(com[0][0]["userId"])+
                " 在"+getMSDate(com[0][0]["time"])+"创意实现：\"" +getNoticeAchievementContent(com[0][0]["commentId"])+"\"下你的评论上点了一个"+info3+
                "</li><hr>";
            $(".userNoticesContentUL").append(notice);
        }

        var toc=d[0]["topicCollect"];
        if(toc!=""&&toc!=null&&toc!="[]"){
            notice="<li>" +getNoticeUserName(toc[0][0]["userId"])+
                " 在"+getMSDate(toc[0][0]["time"])+"收藏了你的创意：\"" +getNoticeTopicName(toc[0][0]["topicId"])+"\""+
                "</li><hr>";
            $(".userNoticesContentUL").append(notice);
        }
    };

    $(window).on("beforeunload", function () {
        $.ajax({
            url:"/stopNotify",
            type:"post",
            async:false,
            data:{
                "id":user_id,
            },
            success:function () {
                console.log("关闭通知成功");
            },
            error:function () {
                console.log("关闭通知失败");
            }
        });
    })
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

function getUserCapsule(userId) {
    $.ajax({
        url:"/user/getCapsuleById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            //console.log(data);
            $(".capsuleList").empty();
            var myCapsule="";
            for(var i in data.data){
                myCapsule="<li>\n" +
                    "                <div class=\"title\" style=\"text-align: center;margin-top: 20px\">"+data.data[i].name+"</div>\n" +
                    "                <div>\n" +
                    "                    <span>"+subStringIdeaContent(data.data[i].content)+"...</span>\n" +
                    "                </div>\n" +
                    "                <div style=\"text-align: right\" class=\"ideaDivBut\">\n" +
                    "                    <span>发布者:"+data.data[i].userName+"</span>\n" +
                    "                    <span style=\"margin-left: 10px\">最近更新时间:"+formatDateTime(data.data[i].uploadTime)+"</span>\n" +
                    "                    <span  style=\"margin-left: 10px\"><button class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary changeCapsule\" capsuleId=\""+data.data[i].id+"\">修改创意胶囊</button></span>\n" +
                    "                    <span  style=\"margin-left: 10px\"><button class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary releaseCapsule\" capsuleId=\""+data.data[i].id+"\">将创意胶囊发布</button></span>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "            <hr>";
                $(".capsuleList").append(myCapsule);
            }
        },
        error:function () {
            layer.msg("读取个人创意胶囊信息失败");
        }
    })
}