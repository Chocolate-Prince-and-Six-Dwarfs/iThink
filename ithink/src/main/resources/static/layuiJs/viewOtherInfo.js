layui.define(['element','jquery','layer'],function (exports) {
    var element=layui.element
        ,$=layui.jquery
        ,layer=layui.layer;
    var userId=getUserId();
    getOtherUserInfo(userId);
    exports('viewOtherInfo', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
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
    $.ajax({
        url:"user/getById",
        type:"post",
        dataType: "json",
        data:{
            id:userId
        },
        success:function (data) {
            console.log(data);
        },
        error:function () {
            console.log("读取用户信息失败");
        }
    });
}