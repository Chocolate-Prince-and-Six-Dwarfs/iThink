function loadIdea(){
    $.ajax({
        url:"/example/ideaJson.json",
        type:"get",
        dataType:"json",
        success:function (data) {
            $("#ideaList").empty();
            sortLikenumInt(data);
            for(i in data.Ideas){
                var a="<div id=\"idea"+data.Ideas[i].ideaID+"\">\n" +
                    "                <h3>"+data.Ideas[i].ideaTitle+"</h3>\n" +
                    "                <p>"+data.Ideas[i].ideaContent+"</p>\n" +
                    "                <p>发布时间："+data.Ideas[i].ideaReleaseTime+"</p>\n" +
                    "                <p>发布者："+data.Ideas[i].ideaPublisher+"</p>\n" +
                    "                <p>点赞数："+data.Ideas[i].ideaLikenumInt+"</p>\n" +
                    "                <div class=\"more\">\n" +
                    "                    <span><img src=\"/img/支持.png\" alt=\"\">&nbsp支持</span>\n" +
                    "                    <span><img src=\"/img/评论.png\" alt=\"\">&nbsp评论</span>\n" +
                    "                    <span><img src=\"/img/收藏.png\" alt=\"\">&nbsp收藏</span>\n" +
                    "                </div>\n" +
                    "            </div>";
                $("#ideaList").append(a);
            }
            //loadIdea();
        },
        error:function () {
            console.log("读取数据失败！");
            //loadIdea();
        }
    })
}
function sortLikenumInt(data) {
    var tmp;
    for(var i=0;i<data.Ideas.length;i++){
        tmp=data.Ideas;
        for(var j=i+1;j<data.Ideas.length;j++){
            if(data.Ideas[i].ideaLikenumInt<data.Ideas[j].ideaLikenumInt){
                tmp=data.Ideas[i];
                data.Ideas[i]=data.Ideas[j];
                data.Ideas[j]=tmp;
            }
        }
    }
}
$(document).ready(function () {
    loadIdea();
});