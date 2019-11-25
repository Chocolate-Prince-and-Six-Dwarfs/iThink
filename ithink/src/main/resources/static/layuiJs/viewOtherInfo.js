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


    var userId=getUserId();
    //执行一个 table 实例
    table.render({
        elem: '#demo'
        ,height: 420
        ,url:'user/getInfoByUserId' //数据接口
        ,where:{ id:userId ,opinion:'topic' }
        ,method:'post'
        ,response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            ,statusCode: 0 //规定成功的状态码，默认：0
            ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
            ,countName: 'count' //规定数据总数的字段名称，默认：count
            ,dataName: 'data' //规定数据列表的字段名称，默认：data
        }
        ,title: '用户表'
        ,page: false //开启分页
        ,style: "color: block;"
        ,cols: [[ //表头
            {field: 'title', title: '创意名称', width:'15%', fixed: 'left', style: 'color:black;font-weight:bold'}
            ,{field: 'content', title: '创意详情', width:'25%', style: 'color:black;font-weight:bold'}
            ,{field: 'experience', title: '发布者', width: '10%', style: 'color:black;font-weight:bold'}
            ,{field: 'like', title: '点赞', width:'10%', sort: true, style: 'color:black;font-weight:bold'}
            ,{field: 'collect', title: '收藏', width: '10%', sort: true, totalRow: true, style: 'color:black;font-weight:bold'}
            ,{field: 'time', title: '发布时间', width:'20%', style: 'color:black;font-weight:bold'}
            ,{fixed: 'right', width: '10%', align:'center', toolbar: '#barDemo', style: 'color:black;font-weight:bold'}
        ]]
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
            console(obj.data);
        }else{
            layer.msg('非法的操作');
            return false;
        }
    });

    $.ajax({
        url:"user/getInfoByUserId",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
            opinion:"topic",
        },
        success:function (data) {
            console.log(data);
        },
        error:function () {
            layer.msg("读取个人创意信息失败");
        }
    })

});