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
                    "                                    <a class='zan'><i class=\"layui-icon\">&#xe6c6;顶一个</i></a>"+data.data[i].like+"\n" +
                    "                                   <a class='cai'><i class=\"layui-icon\">&#xe6c5;踩一下</i></a> \n" +
                    "                                   <a><i class=\"layui-icon layui-icon-rate\" id='star'></i>收藏</a>\n" +
                    "                                </p>\n" +
                    "                                <p style=\"text-align: right\"><a id=\"view"+data.data[i].id+"\" class=\"view\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></a></p>\n" +
                    "                            </div>\n" +
                    "                        </fieldset>\n" +
                    "                    </div>";
                $("#ideaList").append(idea);
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
            var a="<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id=\"ret\"><i class=\"layui-icon\">&#xe65c;返回"+id.substring(4,)+"</i></button>";
            var b="<div style=\"padding: 15px;\"></div>";
            $("#viewIdea").append(a);
            $("#viewIdea").append(b);
        },
        error:function () {
            console.log("读取创意详情失败！");
        }
    })
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
    $(document).on('click','.zan',function () {
        var color1=$(".zan").css('color')//赞的颜色
        var color2=$(".cai").css('color')//踩的颜色
        if(color1=== 'rgb(255, 0, 0)')
        {
            $(".zan").css("color",'rgb(0,0,0)');
        }
        else{
            $(".zan").css("color",'rgb(255,0,0)');
            $(".cai").css("color",'rgb(0,0,0)');
        }
    });

    $(document).on('click','.cai',function () {
        var color1=$(".zan").css('color')//赞的颜色
        var color2=$(".cai").css('color')//踩的颜色
        if(color2=== 'rgb(0, 0, 255)')
        {
            $(".cai").css("color",'rgb(0,0,0)');
        }
        else{
            $(".cai").css("color",'rgb(0,0,255)');
            $(".zan").css("color",'rgb(0,0,0)');
        }
    });

    $(document).on('click','#star',function () {
        //var span=$("#star").html();
        if($("#star").attr('class') === 'layui-icon layui-icon-rate-solid'){
            $("#star").empty();
            $("#star").attr('class','layui-icon layui-icon-rate');
        }
        else {
            $("#star").empty();
            $("#star").attr('class','layui-icon layui-icon-rate-solid');
        }

    });
}

