layui.define(['element','jquery','layer'],function (exports) {
    var element=layui.element
        ,$=layui.jquery
        ,layer=layui.layer;

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