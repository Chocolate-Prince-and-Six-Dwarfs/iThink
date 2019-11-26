layui.define(['laypage','layer','form','jquery'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery;
    getUserId();//user_id已保存
    getUserInfo(user_id,form);//获取头像
    search(layer);//搜索
    getSortIdeas(10);//热搜榜
    refresh(5);
    viewIdea(".viewIdea",".ideaList");//查看创意详情
    comment(layer);//评论
    commentShow();
    viewOtherInfo();
    var chatRoom = new ChatRoom(user_id);
    chatRoom.setLayuiLayer(layer);
    chatRoom.appendTo(".test");
    exports('main', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use('element',function () {
    var element=layui.element;//导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
});
function getIdeas(pageSize){//得到创意列表
    $.ajax({
        url:"idea/load",
        type:"post",
        dataType: "json",
        data: {
            number: pageSize
        },
        beforeSend:function(){
            var load="<i class=\"layui-icon\">&#xe63d;请稍后，正在获取数据。。。</i>";
            $(".ideaList").append(load);
        },
        success:function (data) {
            $(".ideaList").empty();
            //sortLikenumInt(data);
            for(var i in data.data){
                var idea="<div style=\"padding: 15px;\" class=\"idea"+data.data[i].id+"\">\n" +
                    "                        <fieldset class=\"layui-elem-field\">\n" +
                    "                            <legend>"+data.data[i].title+"</legend>\n" +
                    "                            <div class=\"layui-field-box\">\n" +
                    "                                <p>"+subStringContent(data.data[i].content)+"...</p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\"><i data-type=\"viewOtherInfoLayui\" userId=\""+data.data[i].publisherId+"\" class=\"layui-icon layui-view-user-info\">&#xe770;"+data.data[i].publisher+"</i><i class=\"layui-icon\">&#xe637;"+myTime(data.data[i].time)+"</i></p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\">\n" +
                    "                                    <a class='zan'><i class=\"layui-icon layui-icon-praise praise-topic topic-intro\" topicId='" + data.data[i].id + "'>顶一个</i></a>(<span class='likeNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].like+"</span>)\n" +
                    "                                   <a class='cai'><i class=\"layui-icon layui-icon-tread tread-topic topic-intro\" topicId='" + data.data[i].id + "'>踩一下</i></a> \n" +
                    "                                   <a><i class=\"layui-icon layui-icon-rate rate-topic topic-intro\" topicId='" + data.data[i].id + "'>收藏</i></a>(<span class='collectNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].collect+"</span>)\n" +
                    "                                </p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\"><a viewClass=\"view"+data.data[i].id+"\" class=\"view\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></a></p>\n" +
                    "                            </div>\n" +
                    "                        </fieldset>\n" +
                    "                    </div>";
                $(".ideaList").append(idea);

                var type = "topic";
                var className = "topic-intro";
                var processId = data.data[i].id;
                //console.log(user_id);
                precess(type, className, processId, user_id);
            }
        },
        error:function () {
            console.log("读取数据失败！");
        }
    });
}

function getSortIdeas(numSize){//热搜榜
    $.ajax({
        url:"idea/getHot",
        type:"post",
        dataType: "json",
        data: {
            number: numSize
        },
        beforeSend:function(){
            var load="<i class=\"layui-icon\">&#xe63d;请稍后，正在获取数据。。。</i>";
            $(".sortIdeaList").append(load);
        },
        success:function (data) {
            $(".sortIdeaList").empty();
            //sortLikenumInt(data);
            for(var i in data.data){
                //console.log(data.data[i].time);
                var time=formatTime(data.data[i].time,"Y-M-D h:m:s");
                var idea="<div style=\"padding: 15px;\" class=\"idea"+data.data[i].id+"\">\n" +
                    "                        <fieldset class=\"layui-elem-field\">\n" +
                    "                            <legend>"+data.data[i].title+"</legend>\n" +
                    "                            <div class=\"layui-field-box\">\n" +
                    "                                <p>"+subStringContent(data.data[i].content)+"...</p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\"><i data-type=\"viewOtherInfoLayui\" userId=\""+data.data[i].publisherId+"\" class=\"layui-icon layui-view-user-info\">&#xe770;"+data.data[i].publisher+"</i><i class=\"layui-icon\">&#xe637;"+time+"</i></p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\">\n" +
                    "                                    <a class='zan'><i class=\"layui-icon layui-icon-praise praise-topic topic-intro\" topicId='" + data.data[i].id + "'>顶一个</i></a>(<span class='likeNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].like+"</span>)\n" +
                    "                                   <a class='cai'><i class=\"layui-icon layui-icon-tread tread-topic topic-intro\" topicId='" + data.data[i].id + "'>踩一下</i></a> \n" +
                    "                                   <a><i class=\"layui-icon layui-icon-rate rate-topic topic-intro\" topicId='" + data.data[i].id + "'>收藏</i></a>(<span class='collectNum-topic topic-intro' topicId='"+data.data[i].id+"'>"+data.data[i].collect+"</span>)\n" +
                    "                                </p>\n" +
                    "                                <p style=\"text-align: right;cursor:pointer;\"><a viewClass=\"view"+data.data[i].id+"\" class=\"view\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></a></p>\n" +
                    "                            </div>\n" +
                    "                        </fieldset>\n" +
                    "                    </div>";
                $(".sortIdeaList").append(idea);

                var type = "topic";
                var className = "topic-intro";
                var processId = data.data[i].id;
                //console.log(user_id);
                precess(type, className, processId, user_id);
            }
        },
        error:function () {
            console.log("读取数据失败！");
        }
    });
}

function viewIdea() {
    // $(document).off('click','.view').on("click",".view",function () {
    //     //$("#viewIdea").empty();
    //     $(".ideaList").hide();
    //     $(".sortIdeaList").hide();
    //     $("#refreshIdeas").hide();
    //     var viewId=$(this).attr("viewClass"); //获取id属性值
    //     viewId=viewId.substring(4,);
    //     getIdeaInfo(viewId,appendDivName,ideaList);
    // });
    // $(document).off('click','#ret').on("click","#ret",function () {
    //     $(".ideaList").show();
    //     $(".sortIdeaList").show();
    //     $("#refreshIdeas").show();
    //     $(".viewIdea").empty();
    // });
    $(document).off('click','.view').on("click",".view",function () {
        var ideaId=$(this).attr("viewClass").substring(4,);
        window.open('/viewIdea?ideaId='+ encodeURI(encodeURI(ideaId)));;
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
    $(document).off('click','.refreshData1').on('click','.refreshData1',function () {
        refresh(pageSize);
        viewIdea();//查看创意详情
    });
    $(document).off('click','.refreshData2').on('click','.refreshData2',function () {
        getSortIdeas(pageSize+5);//热搜榜
        viewIdea();//查看创意详情
    });
    $(document).ready(function () {
        getIdeas(pageSize);
    });
    $(document).off("click","#refreshIdeas").on("click","#refreshIdeas",function (){
        getIdeas(pageSize);
    });
}