function loadIdea(){
    $.ajax({
        url:"idea/load",
        type:"post",
        dataType: "json",
        success:function (data) {
            $("#ideaList").empty();
            console.log(data);
            //sortLikenumInt(data);
            for(i in data.data){
                var a="<div id=\"idea"+data.data[i].id+"\">\n" +
                    "                <h3>"+data.data[i].title+"</h3>\n" +
                    "                <p>"+data.data[i].content+"</p>\n" +
                    "                <p>发布时间："+data.data[i].time+"</p>\n" +
                    "                <p>发布者："+data.data[i].publisher+"</p>\n" +
                    "                <p>点赞数："+data.data[i].like+"</p>\n" +
                    "                <div class=\"more\">\n" +
                    "                    <span><img src=\"img/支持.png\" alt=\"\">&nbsp支持</span>\n" +
                    "                    <span><img src=\"img/评论.png\" alt=\"\">&nbsp评论</span>\n" +
                    "                    <span><img src=\"img/收藏.png\" alt=\"\">&nbsp收藏</span>\n" +
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
    for(var i=0;i<data.data.length;i++){
        tmp=data.data;
        for(var j=i+1;j<data.data.length;j++){
            if(data.data[i].like<data.data[j].like){
                tmp=data.data[i];
                data.data[i]=data.data[j];
                data.data[j]=tmp;
            }
        }
    }
}
$(document).ready(function () {
    loadIdea();
})