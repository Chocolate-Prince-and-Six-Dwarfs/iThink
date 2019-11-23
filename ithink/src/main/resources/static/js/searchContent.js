layui.define(['element','jquery','layer'],function (exports) {
    var element=layui.element
        ,$=layui.jquery
        ,layer=layui.layer;
    $(".search_content_list").empty();
    var keyword=getOption();
    console.log(keyword);
    $(".keywordLegend").prepend("\""+keyword+"\"的");
    loadTitle(keyword,element);
    exports('searchContent', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function getOption() {
    var keyword=decodeURI(getQueryString("keyword"), "utf-8");
    return keyword;
}
function loadTitle(keyword,element){
    $.ajax({
        url:"idea/search",
        type:"post",
        contentType:false,
        dataType:false,
        data: keyword,
        //把用户搜索的keyword发送给后台
        //后台需要返回给我,针对于关键字的相关信息
        //返回帖子名称（title）,帖子内容（content），点赞数量（thumb），评论数量（comment）
        success:function (data) {
            console.log(data);
            if(data.count<=0){
                var isBlankContent="<div style=\"width: 80%;position: relative;left: 10%\">" +
                    "<div style=\"text-align: center\">" +
                    "<h1 style=\"font-weight: bold\">你的搜索空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                    "</div>" +
                    "</div>";
                $(".search_content_list").append(isBlankContent);
                return false;
            }else{
                for(var i=0;i<data.count;i++){
                    var searchRes="<div class=\"layui-colla-item\">\n" +
                        "                <h2 class=\"layui-colla-title\">创意标题："+data.data[i].title+"</h2>\n" +
                        "                <div class=\"layui-colla-content layui-show\">"+data.data[i].content+"\n" +
                        "                </div>\n" +
                        "            </div>";
                    $(".search_content_list").append(searchRes);
                    element.render("collapse");
                }
            }
        },
        error:function () {
            console.log("搜索出错。");
        }
    })
}