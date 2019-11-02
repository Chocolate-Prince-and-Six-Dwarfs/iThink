layui.define(['laypage','layer', 'form','jquery','element'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery
        ,element=layui.element;

    layer.msg("欢迎你！");
    getUserInfo();
    switch(location.hash) {
        case("#userDemo=userNotice"):
            callUserNotice(element);
            break;
        default:
            break;
    }
    exports('user', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use(['element','jquery'],function () {
    var element=layui.element
        ,$=layui.jquery;//导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
    //触发动态操作tab事件
    var active={
        tabUserInfo:function () {
            if($(".layui-tab-title li[lay-id='userInfo']").length>0){//判断个人信息tab是否已经存在
                element.tabChange('userDemo','userInfo');
                getUserInfo();
            }else{
                element.tabAdd('userDemo',{
                    title: "个人信息"
                    ,content: "<div class=\"userInfoContent\"><img src=\"/img/头像.png\" alt=\"\"></div>"
                    ,id: "userInfo"
                });
                element.tabChange('userDemo','userInfo');
                getUserInfo();
            }
        }
        ,tabUserNotice:function () {
            callUserNotice(element);
        }
    };
    $('.user-site-active').on('click', function(){
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#userDemo=/, '');
    element.tabChange('userDemo', layid);
    element.on('tab(userDemo)', function(elem){
        location.hash = 'userDemo='+ $(this).attr('lay-id');
    });

});

function callUserNotice(element){
    if($(".layui-tab-title li[lay-id='userNotice']").length>0){
        element.tabChange('userDemo','userNotice');
    }else{
        element.tabAdd('userDemo',{
            title: "我的通知"
            ,content: "<div class=\"userNoticeContent\">我的通知内容</div>"
            ,id: "userNotice"
        });
        element.tabChange('userDemo','userNotice');
    }
}

function getUserInfo() {
    $.ajax({
        url:"idea/detail",
        type:"post",
        dataType: "json",
        data:{
            id: 1,
        },
        success:function (data) {
            var a="<div>Hello!</div>";
            $(".userInfoContent").append(a);
        },
        error:function () {
            console.log("读取数据失败！");
        }
    });
}