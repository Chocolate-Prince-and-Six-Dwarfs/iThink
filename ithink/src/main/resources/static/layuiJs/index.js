layui.define(['layer', 'form'], function(exports){
    var layer = layui.layer
        ,form = layui.form;

    layer.msg('Welcome to iThink!');

    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use('element',function () {
    var element=layui.element;//导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });
});