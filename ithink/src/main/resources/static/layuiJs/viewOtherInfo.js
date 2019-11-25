layui.define(['element','jquery','layer'],function (exports) {
    var element=layui.element
        ,$=layui.jquery
        ,layer=layui.layer;
    var userId=getUserId();
    //console.log(userId);
    getOtherUserInfo(userId);
    exports('viewOtherInfo',{}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
function getUserIdQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function getUserId() {
    var userId=decodeURI(getUserIdQueryString("userId"), "utf-8");
    return userId;
}
function getOtherUserInfo(userId) {
    //console.log(userId);
    $.ajax({
        url:"user/getById",
        type:"post",
        data:{
            id:userId,
        },
        success:function (data) {
            //console.log(data);
            $(".user-other-img").attr("src","data:image/png;base64,"+data.head);
            $(".user-other-name").append(data.name);
            if(data.introduction==null||data.introduction==""){
                $(".user-intro-info").append("这个用户啥也没填呢<i class=\"layui-icon layui-icon-face-cry\" style=\"color: #1E9FFF;\"></i>");
            }else{
                $(".user-intro-info").append(data.introduction);
            }
            $(".sex-info").append(data.sex);
            if(data.school==null||data.school==""){
                $(".school-info").append("无");
            }else{
                $(".school-info").append(data.school);
            }
            $(".birthday-info").append(data.birthday);
            if(data.address==null||data.address==""){
                $(".addr-info").append("无");
            }else{
                $(".addr-info").append(data.address);
            }
            $(".email-info").append(data.email);
            $(".credit-info").append(data.credit);
            if(data.industry==null||data.industry==""){
                $(".industry-info").append("无");
            }else{
                $(".industry-info").append(data.industry);
            }
            $(".tel-info").append(data.phone);
        },
        error:function () {
            console.log("读取用户信息失败");
        }
    });
}

layui.use(['laypage', 'layer', 'table'], function(){
    var layer = layui.layer //弹层
        ,table = layui.table; //表格

    //向世界问个好
    layer.msg('Hello World');

    //执行一个 table 实例
    table.render({
        elem: '#demo'
        ,height: 420
        ,url: '/example/user.json' //数据接口
        ,title: '用户表'
        ,page: false //开启分页
        ,toolbar: true //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
            ,{field: 'username', title: '用户名', width:80}
            ,{field: 'experience', title: '积分', width: 90, sort: true, totalRow: true}
            ,{field: 'sex', title: '性别', width:80, sort: true}
            ,{field: 'score', title: '评分', width: 80, sort: true, totalRow: true}
            ,{field: 'city', title: '城市', width:150}
            ,{field: 'sign', title: '签名', width: 200}
            ,{field: 'classify', title: '职业', width: 100}
            ,{field: 'wealth', title: '财富', width: 135, sort: true, totalRow: true}
            ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            layer.msg('编辑操作');
        }
    });

});