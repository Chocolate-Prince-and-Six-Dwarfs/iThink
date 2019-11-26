layui.define(['element','jquery','layer'],function (exports) {
    var element=layui.element
        ,$=layui.jquery
        ,layer=layui.layer;
    getUserId();
    getUserInfo(user_id);
    var ideaId=getIdeaId();
    getIdeaInfo(ideaId,".viewIdea",".ideaList");
    comment(layer);//评论
    commentShow();
    viewOtherInfo();
    exports('viewIdea',{}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
function getIdeaIdQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function getIdeaId() {
    var ideaId=decodeURI(getIdeaIdQueryString("ideaId"), "utf-8");
    return ideaId;
}
var user_id;
function getUserId() {
    $.ajax({
        url:"/user/getLoginId",
        type:"post",
        async:false,
        success:function (data) {
            user_id=data;
        },
        error:function () {
            console.log("读取用户Id失败.")
        }
    });
}
function getUserInfo(userId) {
    $.ajax({
        url:"/user/getById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            $("#userImg").attr("src","data:image/png;base64,"+data.head);
            $("#userName").text(data.name);
        },
        error:function () {
            console.log("读取用户信息失败！");
        }
    });
}