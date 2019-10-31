layui.define(['laypage','layer', 'form','jquery'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery;

    layer.msg('Welcome to iThink!');
    viewIdea();
    cutPage(laypage,6);
    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
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
                    "                                <p>"+data.data[i].content+"</p>\n" +
                    "                                <p style=\"text-align: right\"><i class=\"layui-icon\">&#xe770;"+data.data[i].publisher+"</i><i class=\"layui-icon\">&#xe637;"+data.data[i].time.substring(0,10)+"</i></p>\n" +
                    "                                <p style=\"text-align: right\">\n" +
                    "                                    <i class=\"layui-icon\">　&#xe6c6;顶一个"+data.data[i].like+"</i>\n" +
                    "                                    <i class=\"layui-icon\">　&#xe6c5;踩一下</i>\n" +
                    "                                    <i class=\"layui-icon\">　&#xe600;记得收藏</i>\n" +
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

function viewIdea() {
    $(document).on("click",".view",function () {
        $("#viewIdea").empty();
        $("#ideaList").hide();
        $("#cutPage").hide();
        var viewId=$(this).attr("id"); //获取id属性值
        viewId=viewId.substring(4,);
        getIdeaInfo(viewId);
    });
    $(document).on("click","#ret",function () {
        $("#ideaList").show();
        $("#cutPage").show();
        $("#viewIdea").empty();
    });
}

function getIdeaInfo(id){
    $.ajax({
        url:"idea/detail",
        type:"post",
        dataType: "json",
        data:{
            id: id,
        },
        success:function (data) {
            var a="<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id=\"ret\"><i class=\"layui-icon\">&#xe65c;返回"+id.substring(4,)+"</i></button>";
            var b="<div style=\"padding: 15px;\">\n" +
                "                            <fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 30px;\">\n" +
                "                                <legend>创意详情</legend>\n" +
                "                            </fieldset>\n" +
                "                            <div class=\"layui-col-md12\">\n" +
                "                                <div class=\"layui-card\">\n" +
                "                                    <div class=\"layui-card-header\">"+data.topic.title+"</div>\n" +
                "                                    <div class=\"layui-card-body\">\n" +
                "                                        "+data.topic.content+"\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>";
            $("#viewIdea").append(a);
            $("#viewIdea").append(b);
        },
        error:function () {
            console.log("读取创意详情失败！");
        }
    })
}

function subStringContent(ideaContent){ //截取部分创意内容

}

function cutPage(laypage,pageSize) {
    getIdeas(pageSize);
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
                getIdeas(pageSize);
            }
        }
    });//分页
}

function sortLikenumInt(data) {
    var tmp;
    for(var i=0;i<data.data.length;i++){
        tmp=data.data;
        for(var j=i+1;j<data.data.length;j++){
            if(data.data[i].like<data.data[j].like){
                tmp=data.data[i];
                data.data[i]=data.data[j];
                data.data[j]=tmp;
            }
        }
    }
    return data;
}