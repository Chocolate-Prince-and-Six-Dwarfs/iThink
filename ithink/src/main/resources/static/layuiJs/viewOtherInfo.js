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