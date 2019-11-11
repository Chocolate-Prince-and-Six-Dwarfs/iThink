layui.define(['laypage','layer','form','jquery'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery;

    getUserId();//user_id已保存
    getUserInfo(user_id,form);//获取头像
    search(layer);//搜索
    cutPage(laypage,6);//分页
    refresh(5);
    viewIdea();//查看创意详情
    comment(layer);
    exports('main', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use('element',function () {
    var element=layui.element;//导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
});

function getIdeas(pageSize){
    $.ajax({
        url:"idea/load",
        type:"post",
        dataType: "json",
        data: {
            number: pageSize
        },
        beforeSend:function(){
            var load="<i class=\"layui-icon\">&#xe63d;请稍后，正在获取数据。。。</i>";
            $("#ideaList").append(load);
        },
        success:function (data) {
            $("#ideaList").empty();
            //sortLikenumInt(data);
            for(var i in data.data){
                var idea="<div style=\"padding: 15px;\" id=\"idea"+data.data[i].id+"\">\n" +
                    "                        <fieldset class=\"layui-elem-field\">\n" +
                    "                            <legend>"+data.data[i].title+"</legend>\n" +
                    "                            <div class=\"layui-field-box\">\n" +
                    "                                <p>"+subStringContent(data.data[i].content)+"...</p>\n" +
                    "                                <p style=\"text-align: right\"><i class=\"layui-icon\">&#xe770;"+data.data[i].publisher+"</i><i class=\"layui-icon\">&#xe637;"+data.data[i].time.substring(0,10)+"</i></p>\n" +
                    "                                <p style=\"text-align: right\">\n" +
                    "                                    <a class='zan'><i class=\"layui-icon layui-icon-praise praise-topic topic-intro\" topicId='" + data.data[i].id + "'>顶一个</i></a>(<span class='likeNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].like+"</span>)\n" +
                    "                                   <a class='cai'><i class=\"layui-icon layui-icon-tread tread-topic topic-intro\" topicId='" + data.data[i].id + "'>踩一下</i></a> \n" +
                    "                                   <a><i class=\"layui-icon layui-icon-rate rate-topic topic-intro\" topicId='" + data.data[i].id + "'>收藏</i></a>(<span class='collectNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].collect+"</span>)\n" +
                    "                                </p>\n" +
                    "                                <p style=\"text-align: right\"><a id=\"view"+data.data[i].id+"\" class=\"view\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></a></p>\n" +
                    "                            </div>\n" +
                    "                        </fieldset>\n" +
                    "                    </div>";
                $("#ideaList").append(idea);


                var type = "topic";
                var className = "topic-intro";
                var id = data.data[i].id;
                //console.log(user_id);
                precess(type, className, id, user_id);
            }
        },
        error:function () {
            console.log("读取数据失败！");
        }
    });
}

function getIdeaInfo(id){
    $.ajax({
        url:"/idea/detail",
        type:"post",
        dataType: "json",
        data:{
            id: id,
        },
        success:function (data) {
            console.log(data);
            var a="<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id=\"ret\"><i class=\"layui-icon\">&#xe65c;返回</i></button>";
            var achievements="";
            for(var i in data.achievements){
                var comment_list="";
                var ach=data.achievements[i].achievement;
                for(var j in data.achievements[i].comments){
                    var comment=data.achievements[i].comments[j].comment;
                    comment_list+="<div class=\"imgdiv\"><img class=\"imgcss idea_achievement_comment_user"+comment.commentId+"\" src=\"/img/头像.png\"/></div>\n" +
                        "                                        <div class=\"conmment_details\">\n" +
                        "                                            <span class=\"comment_name idea_achievement_comment_user_name"+comment.commentId+"\">"+comment.fromName+" </span>     <span class=\"idea_achievement_comment_user_time"+comment.commentId+"\">"+comment.time.substring(0,10)+"</span>\n" +
                        "                                            <div class=\"comment_content idea_achievement_comment_user_content"+comment.commentId+"\">  "+comment.content+"</div>\n" +
                        "                                            <div class=\"del\">\n" +
                        "                                                <i class=\"icon layui-icon layui-icon-praise idea_achievement_comment_user_good"+comment.commentId+"\">赞("+comment.like+")</i>\n" +
                        "                                                <!--<a class=\"del_comment\" data-id=\"1\"><i class=\"icon layui-icon\">回复</i></a>-->\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"idea_achievement_comment_reply"+comment.commentId+"\"><!--回复-->\n" +
                        "\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                        <hr>";

                }
                achievements+="<fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 50px;\">\n" +
                    "                                <legend>创意实现"+i+"</legend>\n" +
                    "                            </fieldset>\n" +
                    "                            <div class=\"layui-card idea_achievement"+ach.id+"\">\n" +
                    "                                <div class=\"layui-card-header  idea_achievement_title"+ach.id+"\"></div>\n" +
                    "                                <div class=\"layui-card-body idea_achievement_content"+ach.id+"\">\n" +
                    "                                    "+ach.content+"\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                            <div style=\"text-align: right\"><a class=\"comment-idea\" achid=\""+ach.id+"\"><i class=\"layui-icon\">&#xe611;评论</i></a></div>\n"+
                    "                            <div class=\"idea_achievement_comment idea_achievement_comment"+ach.id+"\" style=\"width: 90%; position: relative; left:10%;\">\n" +
                    "                                <input type=\"text\" name=\"title\" required lay-verify=\"required\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-input idea-comment-content idea_achievement_comment_content"+ach.id+"\">\n" +
                    "                                <p style=\"text-align: right\"><button type=\"button\" toId=\""+ach.userId+"\" class=\"layui-btn layui-btn-primary idea-comment-button idea_achievement_comment_button"+ach.id+"\">发布评论</button></p>\n" +
                    "                                <div class=\"idea_achievement_comment_list"+ach.id+"\">\n" +
                    "                                    <h3 >全部评论</h3>\n" +
                    "                                    <hr>\n" +
                    "                                    <div class=\"comment idea_achievement_comment_user_list"+ach.id+"\">\n" +
                    comment_list+
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>";
            }
            var b="<div style=\"padding: 15px;\"><fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 30px;\">\n" +
                "                        <legend>创意详情</legend>\n" +
                "                    </fieldset>\n" +
                "                    <div class=\"layui-col-md12\">\n" +
                "                        <div class=\"layui-card\">\n" +
                "                            <div class=\"layui-card-header\">"+data.topic.title+"</div>\n" +
                "                            <div class=\"layui-card-body\">\n" +
                "                                "+data.topic.content+"\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <p style=\"text-align: right\">\n" +
                "                            <a><i class=\"layui-icon\">&#xe770;"+data.topic.publisher+"</i></a>\n" +
                "                            <a><i class=\"layui-icon\">&#xe637;"+data.topic.time.substring(0,10)+"</i></a>\n" +
                "                            <a><i class=\"layui-icon layui-icon-praise praise-topic topic-detail\" topicId='" + data.topic.id + "'>顶一个</i></a>(<span class='likeNum-topic topic-detail' topicId='"+data.topic.id+"'>"+data.topic.like+"</span>)\n" +
                "                            <a><i class=\"layui-icon layui-icon-tread tread-topic topic-detail\" topicId='" + data.topic.id + "'>踩一下</i></a>\n" +
                "                             <a><i class=\"layui-icon layui-icon-rate rate-topic topic-detail\" topicId='" + data.topic.id + "'>收藏</i></a>(<span class='collectNum-topic topic-detail' topicId='"+data.topic.id+"'>"+data.topic.collect+"</span>)\n" +
                "                        </p>\n" +
                "                        <div class=\"idea_achievements\" style=\"width: 95%; position: relative; left:5%;\">\n"+
                achievements+
                "                        </div></div>";
            $("#viewIdea").append(a);
            $("#viewIdea").append(b);

            var type = "topic";
            var className = "topic-detail";
            var id = data.topic.id;
            //console.log(user_id);
            precess(type, className, id, user_id);
        },
        error:function () {
            console.log("读取创意详情失败！");
        }
    })
}
function commentShow() {
    $(document).ready(function () {
        $(".idea_achievement_comment").hide();
    });
    $(document).on('click','.comment-idea',function () {
        var achid=$(this).attr('achid');
        $(".idea_achievement_comment"+achid).show();
    })
}
function getNowDate() {//得到当前时间
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
function comment(layer) {
    $(document).on('click','.idea-comment-button',function () {
        var thisClass=$(this).attr('class');
        //正则表达式匹配achievementID
        var rep=new RegExp("(?<=idea_achievement_comment_button)[1-9][0-9]{0,}");
        var achievementId=(rep.exec(thisClass))[0];
        //console.log(thisClass);
        //console.log(achievementId);
        var contentClass="idea_achievement_comment_content"+achievementId;
        var content=$("."+contentClass).val();
        if(content==null||content==""){
            layer.msg("请输入评论");
            return false;
        }

        var toId=$(this).attr('toId');
        //console.log("ai:"+achievementId+"c:"+content+"ui:"+user_id+"ti:"+toId);

        $.ajax({
            url:"comment/do",
            type:"post",
            dataType: "json",
            data:{
                achievementId: achievementId,
                content:content,
                fromId:user_id,
                toId:toId,
            },
            success:function (data) {
                var currentdate=getNowDate();
                if(data.status==1){
                    var name=$("#userName").text();
                    var a="<div class=\"imgdiv\"><img class=\"imgcss idea_achievement_comment_user"+data.id+"\" src=\"/img/头像.png\"/></div>\n" +
                        "                                        <div class=\"conmment_details\">\n" +
                        "                                            <span class=\"comment_name idea_achievement_comment_user_name"+data.id+"\">"+name+" </span>     <span class=\"idea_achievement_comment_user_time"+data.id+"\">"+currentdate+"</span>\n" +
                        "                                            <div class=\"comment_content idea_achievement_comment_user_content"+data.id+"\">  "+content+"</div>\n" +
                        "                                            <div class=\"del\">\n" +
                        "                                                <i class=\"icon layui-icon layui-icon-praise  idea_achievement_comment_user_good"+data.id+"\">赞(0)</i>\n" +
                        "                                                <!--<a class=\"del_comment\" data-id=\"1\"><i class=\"icon layui-icon\">回复</i></a>-->\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"idea_achievement_comment_reply"+data.id+"\"><!--回复-->\n" +
                        "\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                        <hr>";
                    $(".idea_achievement_comment_user_list"+achievementId).append(a);
                    $("."+contentClass).val("");
                    layer.msg("评论成功");
                }
            },
            error:function () {
                console.log("评论失败");
            }
        });
    });
}

function viewIdea() {
    $(document).on("click",".view",function () {
        $("#viewIdea").empty();
        $("#ideaList").hide();
        $("#refreshIdeas").hide();
        var viewId=$(this).attr("id"); //获取id属性值
        viewId=viewId.substring(4,);
        getIdeaInfo(viewId);
    });
    $(document).on("click","#ret",function () {
        $("#ideaList").show();
        $("#refreshIdeas").show();
        $("#viewIdea").empty();
    });
}

function subStringContent(ideaContent){ //截取部分创意内容
    ideaContent = ideaContent.replace(/(\n)/g, "");
    ideaContent = ideaContent.replace(/(\t)/g, "");
    ideaContent = ideaContent.replace(/(\r)/g, "");
    ideaContent = ideaContent.replace(/<\/?[^>]*>/g, "");
    ideaContent = ideaContent.replace(/\s*/g, "");
    var ic=ideaContent.substring(0,200);
    return ic;
}

function refresh(pageSize) {
    $(document).ready(function () {
        getIdeas(pageSize);
    });
    $(document).on("click","#refreshIdeas",function (){
        getIdeas(pageSize);
    });
}

function cutPage(laypage,pageSize) {
    //getIdeas(pageSize);//sort后的
    laypage.render({
        elem: 'cutPage'
        ,count: 30
        ,limit: pageSize
        ,theme: '#1E9FFF'
        ,jump:function (obj,first) {
            //console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            //console.log(obj.limit); //得到每页显示的条数
            //首次不执行
            if(!first){
                //getIdeas(pageSize);
            }
        }
    });//分页
}

function change(type, className, id, user_id) {
    var request = "";
    if(type === "topic")
    {
        request = "/idea";
    }
    else if (type === "achievement")
    {
        request = "/ach";
    }
    else
    {
        request = "/comment";
    }

    $(document).on('click',"i[class*=praise-" + type + "]["+ type +"Id=" + id + "][class*=" + className +"]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr(type + "Id"));
        formData.append("userId", user_id);
        formData.append("type", "true");
        //console.log(self.attr("topicId"));
        $.ajax(
            {
                url: request + "/like",
                type: "post",
                dataType: false,
                async: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    //console.log(data);
                    var likeElement = $(".likeNum-" + type + "[" + type + "Id="+ data.id + "][class*=" + className +"]");
                    //console.log(likeElement.attr("topicId"));
                    likeElement.html(data.data.like);
                    if(data.data.status === 1)
                    {
                        self.css('color', 'rgb(255,0,0)');
                        $(".tread-" + type + "[" + type + "Id="+ data.id + "][class*=" + className +"]").css("color", 'grey');
                    }
                    else
                    {
                        self.css("color", 'grey');
                    }
                },
                error:function () {
                    console.log("点赞失败");
                }
            }
            )
    });

    $(document).on('click',"i[class*=tread-" + type + "]["+ type +"Id=" + id + "][class*=" + className +"]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr(type + "Id"));
        formData.append("userId", user_id);
        formData.append("type", "false");
        //console.log(self.attr("topicId"));
        $.ajax(
            {
                url: request + "/like",
                type: "post",
                dataType: false,
                async: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    //console.log(data);
                    var likeElement = $(".likeNum-" + type + "[" + type + "Id="+ data.id + "][class*=" + className +"]");
                    //console.log(likeElement.attr("topicId"));
                    likeElement.html(data.data.like);
                    if(data.data.status === 0)
                    {
                        self.css('color', 'rgb(0,0,255)');
                        $(".praise-" + type + "[" + type + "Id="+ data.id + "][class*=" + className +"]").css("color", 'grey');
                    }
                    else
                    {
                        self.css("color", 'grey');
                    }
                },
                error:function () {
                    console.log("点踩失败");
                }
            }
        )
    });

    $(document).on('click',"i[class*=rate-" + type + "]["+ type +"Id=" + id + "][class*=" + className +"]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr(type + "Id"));
        formData.append("userId", user_id);
        //console.log(self.attr("topicId"));
        $.ajax(
            {
                url: request + "/collect",
                type: "post",
                dataType: false,
                async: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    //console.log(data);
                    var collectElement = $(".collectNum-" + type + "[" + type + "Id="+ data.id + "][class*=" + className +"]");
                    //console.log(likeElement.attr("topicId"));
                    collectElement.html(data.data.collect);
                    if(data.data.status === 1)
                    {
                        self.css('color', 'rgb(255,0,0)');
                    }
                    else
                    {
                        self.css("color", 'grey');
                    }
                },
                error:function () {
                    console.log("点踩失败");
                }
            }
        )
    });
}

function changeStatus(type, className, id, user_id)
{
    // 根据点赞点踩收藏情况修改颜色
    var like = $(".layui-icon-praise[" + type + "Id=" + id + "][class*=" + className +"]");
    var dislike = $(".layui-icon-tread[" + type + "Id=" + id + "][class*=" + className +"]");
    var collect = $(".layui-icon-rate[" + type + "Id=" + id + "][class*=" + className +"]");
    var formData = new FormData();
    formData.append("id", id);
    formData.append("userId", user_id);
    //console.log(formData.get("id"));
    //console.log(formData.get("user_id"));
    var request = "";
    if(type === "topic")
    {
        request = "/idea";
    }
    else if (type === "achievement")
    {
        request = "/ach";
    }
    else
    {
        request = "/comment";
    }
    $.ajax(
        {
            url: request + "/getCollect",
            type: "post",
            dataType: false,
            async: false,
            processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
            contentType : false,  //必须false才会自动加上正确的Content-Type
            data:formData,
            success:function (data) {
                if(data.data.status === 1)
                {
                    collect.css('color', 'rgb(255,0,0)');
                }
                else if(data.data.status === 0)
                {
                    collect.css('color', 'grey');
                }
            },
            error:function () {
                console.log("获取点赞失败");
            }
        }
    );
    $.ajax(
        {
            url: request + "/getLike",
            type: "post",
            dataType: false,
            async: false,
            processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
            contentType : false,  //必须false才会自动加上正确的Content-Type
            data:formData,
            success:function (data) {
                if(data.data.status === 1)
                {
                    like.css('color', 'rgb(255,0,0)');
                    dislike.css("color", 'grey');
                }
                else if(data.data.status === 0)
                {
                    like.css('color', 'grey');
                    dislike.css("color", 'rgb(0,0,255)');
                }
                else
                {
                    like.css('color', 'grey');
                    dislike.css("color", 'grey');
                }
            },
            error:function () {
                console.log("获取收藏信息失败");
            }
        }
    );
}

function precess(type, className, id, user_id)
{
    // 根据点赞点踩收藏情况修改颜色
    changeStatus(type, className, id, user_id);
    // 添加点击事件
    change(type, className, id, user_id);
}


