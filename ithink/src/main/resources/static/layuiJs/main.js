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
    comment();
    change();
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
                    "                                    <a class='zan'><i class=\"layui-icon layui-icon-praise\" topicId='" + data.data[i].id + "'>顶一个</i></a><span class='likeNum' topicId='"+data.data[i].id+"'>"+data.data[i].like+"</span>\n" +
                    "                                   <a class='cai'><i class=\"layui-icon layui-icon-tread\" topicId='" + data.data[i].id + "'>踩一下</i></a> \n" +
                    "                                   <a><i class=\"layui-icon layui-icon-rate\" topicId='" + data.data[i].id + "'>收藏</i></a>\n" +
                    "                                </p>\n" +
                    "                                <p style=\"text-align: right\"><a id=\"view"+data.data[i].id+"\" class=\"view\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></a></p>\n" +
                    "                            </div>\n" +
                    "                        </fieldset>\n" +
                    "                    </div>";
                $("#ideaList").append(idea);

                // 根据点赞点踩收藏情况修改颜色
                var like = $(".layui-icon-praise[topicId='" + data.data[i].id + "']");
                var dislike = $(".layui-icon-tread[topicId='" + data.data[i].id + "']");
                var collect = $(".layui-icon-rate[topicId='" + data.data[i].id + "']");
                var formData = new FormData();
                formData.append("id", data.data[i].id);
                formData.append("userId", user_id);
                console.log(data.data[i].id);
                $.ajax(
                    {
                        url:"/idea/getCollect",
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
                        url:"/idea/getLike",
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
            var a="<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id=\"ret\"><i class=\"layui-icon\">&#xe65c;返回"+id.substring(4,)+"</i></button>";
            var achievements="";
            for(var i in data.achievements){
                var comment_list="";
                for(var j in data.achievements[i].comments){
                    comment_list+="<div class=\"imgdiv\"><img class=\"imgcss idea_achievement_comment_user"+data.achievements[i].comments[j].comment.commentId+"\" src=\"/img/头像.png\"/></div>\n" +
                        "                                        <div class=\"conmment_details\">\n" +
                        "                                            <span class=\"comment_name idea_achievement_comment_user_name"+data.achievements[i].comments[j].comment.commentId+"\">"+data.achievements[i].comments[j].comment.fromName+" </span>     <span class=\"idea_achievement_comment_user_time"+data.achievements[i].comments[j].comment.commentId+"\">"+data.achievements[i].comments[j].comment.time.substring(0,10)+"</span>\n" +
                        "                                            <div class=\"comment_content idea_achievement_comment_user_content"+data.achievements[i].comments[j].comment.commentId+"\">  "+data.achievements[i].comments[j].comment.content+"</div>\n" +
                        "                                            <div class=\"del\">\n" +
                        "                                                <i class=\"icon layui-icon layui-icon-praise  idea_achievement_comment_user_good"+data.achievements[i].comments[j].comment.commentId+"\">赞("+data.achievements[i].comments[j].comment.like+")</i>\n" +
                        "                                                <!--<a class=\"del_comment\" data-id=\"1\"><i class=\"icon layui-icon\">回复</i></a>-->\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"idea_achievement_comment_reply"+data.achievements[i].comments[j].comment.commentId+"\"><!--回复-->\n" +
                        "\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                        <hr>";

                }
                achievements+="<fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 50px;\">\n" +
                    "                                <legend>创意实现"+i+"</legend>\n" +
                    "                            </fieldset>\n" +
                    "                            <div class=\"layui-card idea_achievement"+data.achievements[i].achievement.id+"\">\n" +
                    "                                <div class=\"layui-card-header  idea_achievement_title"+data.achievements[i].achievement.id+"\">"+data.achievements[i].achievement.topicName+"</div>\n" +
                    "                                <div class=\"layui-card-body  idea_achievement_content"+data.achievements[i].achievement.id+"\">\n" +
                    "                                    "+data.achievements[i].achievement.content+"\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"idea_achievement_comment"+data.achievements[i].achievement.id+"\" style=\"width: 90%; position: relative; left:10%;\">\n" +
                    "                                <input type=\"text\" name=\"title\" required lay-verify=\"required\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-input idea-comment-content idea_achievement_comment_content"+data.achievements[i].achievement.id+"\">\n" +
                    "                                <p style=\"text-align: right\"><button type=\"button\" class=\"layui-btn layui-btn-primary idea-comment-button idea_achievement_comment_button"+data.achievements[i].achievement.id+"\">发布评论</button></p>\n" +
                    "                                <div class=\"idea_achievement_comment_list"+data.achievements[i].achievement.id+"\">\n" +
                    "                                    <h3 >全部评论</h3>\n" +
                    "                                    <hr>\n" +
                    "                                    <div class=\"comment idea_achievement_comment_user_list"+data.achievements[i].achievement.id+"\">\n" +
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
                "                        <!--<p style=\"text-align: right\">\n" +
                "                            <a><i class=\"layui-icon\">&#xe770;用户名</i></a>\n" +
                "                            <a><i class=\"layui-icon\">&#xe637;日期</i></a>\n" +
                "                            <a><i class=\"layui-icon\">&#xe6c6;顶一个</i></a>\n" +
                "                            <a><i class=\"layui-icon\">&#xe6c5;踩一下</i></a>\n" +
                "                            <a><i class=\"layui-icon layui-icon-rate\"></i>收藏</a>\n" +
                "                            <a class=\"comment-idea\"><i class=\"layui-icon\">&#xe611;评论</i></a>\n" +
                "                        </p>\n-->" +
                "                        <div class=\"idea_achievements\" style=\"width: 95%; position: relative; left:5%;\">\n"+
                achievements+
                "                        </div></div>";
            $("#viewIdea").append(a);
            $("#viewIdea").append(b);
        },
        error:function () {
            console.log("读取创意详情失败！");
        }
    })
}

function comment() {
    $(document).on('click','.idea-comment-button',function () {
        var thisClass=$(this).attr('class');
        console.log(thisClass.substring(79,));
        var contentClass="idea-comment-content"+thisClass.substring(79,);
        $.ajax({
            url:"/idea/detail",
            type:"post",
            dataType: "json",
            data:{
                id: id,
            },
            success:function () {

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

function change() {
    $(document).on('click',"i[class*=layui-icon-praise]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr("topicId"));
        formData.append("userId", user_id);
        formData.append("type", "true");
        console.log(self.attr("topicId"));
        $.ajax(
            {
                url:"/idea/like",
                type: "post",
                dataType: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    console.log(data);
                    var likeElement = $(".likeNum[topicId='"+ data.id + "']");
                    console.log(likeElement.attr("topicId"));
                    likeElement.html(data.data.like);
                    if(data.data.status === 1)
                    {
                        self.css('color', 'rgb(255,0,0)');
                        $(".layui-icon-tread[topicId='"+ data.id + "']").css("color", 'grey');
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

    $(document).on('click',"i[class*=layui-icon-tread]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr("topicId"));
        formData.append("userId", user_id);
        formData.append("type", "false");
        console.log(self.attr("topicId"));
        $.ajax(
            {
                url:"/idea/like",
                type: "post",
                dataType: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    console.log(data);
                    var likeElement = $(".likeNum[topicId='"+ data.id + "']");
                    console.log(likeElement.attr("topicId"));
                    likeElement.html(data.data.like);
                    if(data.data.status === 0)
                    {
                        self.css('color', 'rgb(0,0,255)');
                        $(".layui-icon-praise[topicId='"+ data.id + "']").css("color", 'grey');
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

    $(document).on('click',"i[class*=layui-icon-rate]",function () {
        var self = $(this);
        var formData = new FormData();
        formData.append("id", self.attr("topicId"));
        formData.append("userId", user_id);
        console.log(self.attr("topicId"));
        $.ajax(
            {
                url:"/idea/collect",
                type: "post",
                dataType: false,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type
                data:formData,
                success:function (data) {
                    console.log(data);
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

    // $(document).on('click','.cai',function () {
    //     var color1=$(".zan").css('color')//赞的颜色
    //     var color2=$(".cai").css('color')//踩的颜色
    //     if(color2=== 'rgb(0, 0, 255)')
    //     {
    //         $(".cai").css("color",'grey');
    //     }
    //     else{
    //         $(".cai").css("color",'rgb(0,0,255)');
    //         $(".zan").css("color",'grey');
    //     }
    // });
    //
    // $(document).on('click','#star',function () {
    //     //var span=$("#star").html();
    //     if($("#star").attr('class') === 'layui-icon layui-icon-rate-solid'){
    //         $("#star").empty();
    //         $("#star").attr('class','layui-icon layui-icon-rate');
    //     }
    //     else {
    //         $("#star").empty();
    //         $("#star").attr('class','layui-icon layui-icon-rate-solid');
    //     }
    //
    // });
}

