layui.define(['layer','form','jquery'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,$=layui.jquery;
    layer.msg("userId:"+getUserId());
});

function getUserId() {
    var userId;
    $.ajax({
        url:"user/getLoginId",
        type:"post",
        success:function (data) {
            console.log(data);
        },
        error:function () {
            console.log("读取数据失败")
        }
    });
    return userId;
}