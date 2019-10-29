layui.define(['laypage','layer', 'form'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage;

    layer.msg('Welcome to iThink!');

    laypage.render({
        elem: 'cutPage'
        ,count: 50
        ,theme: '#1E9FFF'
        ,jump:function (obj,first) {
            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            console.log(obj.limit); //得到每页显示的条数
            getIdeas();
            //首次不执行
            if(!first){

            }
        }
    });//分页



    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use('element',function () {
    var element=layui.element;//导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
});

function getIdeas(){
    var $=layui.jquery;

    $.get("/example/ideaJson.json",function (data) {
        console.log(data[0].ideaContent);
        $("#ideaList").empty();
        for(var i=0;i<10;i++){
            var ideaDiv="<div style=\"padding: 15px;\" id=\"idea\""+i+">\n" +
                "                        <fieldset class=\"layui-elem-field\">\n" +
                "                            <legend>"+data[0].ideaTitle+"</legend>\n" +
                "                            <div class=\"layui-field-box\">\n" +
                "                                <p>"+data[0].ideaContent+"</p>\n" +
                "                                <p style=\"text-align: right\"><i class=\"layui-icon\">&#xe770;"+data[0].ideaPublisher+"</i><i class=\"layui-icon\">&#xe637;"+data[0].ideaReleaseTime+"</i></p>\n" +
                "                                <p style=\"text-align: right\">\n" +
                "                                    <i class=\"layui-icon\">　&#xe6c6;顶一个"+data[0].ideaCollectNumber+"</i>\n" +
                "                                    <i class=\"layui-icon\">　&#xe6c5;踩一下</i>\n" +
                "                                    <i class=\"layui-icon\">　&#xe600;记得收藏</i>\n" +
                "                                </p>\n" +
                "                                <p style=\"text-align: right\"><i class=\"layui-icon\">查看详情 &#xe65b;</i></p>\n" +
                "                            </div>\n" +
                "                        </fieldset>\n" +
                "                    </div>";
            $("#ideaList").append(ideaDiv);
        }
    });
}