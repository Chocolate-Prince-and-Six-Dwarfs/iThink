window.onload=function () {
    var keyword=getOption();
    console.log(keyword);
    loadTitle(keyword);
};
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function getOption() {
    var keyword=decodeURI(getQueryString("keyword"), "utf-8");
    // var typeValue=$("#type option:selected").text();
    // var stateValue=$("#state option:selected").val();
    return keyword;
}

function loadTitle(keyword){
    $.ajax({
        url:"idea/search",
        type:"post",
        contentType:"application/json",
        dataType:false,
        data: keyword,
        //把用户搜索的keyword发送给后台

        //后台需要返回给我,针对于关键字的相关信息
        //返回帖子名称（title）,帖子内容（content），点赞数量（thumb），评论数量（comment）
        success:function (res) {
            console.log(res);
            for (var index=0;index<res.data.length;index++){

                var title=res.data[index].title;
                var content=res.data[index].content;
                var thumb=res.data[index].like;
                //var comment=res.data[index].comment;


                if(title !== ''&&content !==''){
                    /*$(".content").append('<div class="line">' +
                        '<div class="title"></div>' +
                        '            <p class="detail"></p>' +
                        '            <div class="num">' +
                        '                <img src="../../static/img/支持.png"  alt=""><span class="thumb">点赞数</span>' +
                        '                <img src="../../static/img/评论.png" class="comment" alt=""><span class="comment">评论数</span>' +
                        '            </div></div>');*/
                    $(".content").append('<div class="line">' +
                        '<div class="title"></div>' +
                        '            <p class="detail"></p>' +
                        '            <div class="num">' +
                        '                <img src="img/支持.png"  alt=""><span class="thumb">点赞数</span>' +
                        '            </div></div>');

                    $(".line .title").html(title);
                    $(".line .detail").html(content);
                    $(".line .thumb").html(thumb);
                    //$(".line .comment").html(+comment);
                }

                // else if(title===obj.getTitle&&type===obj.typeValue){
                //     $(".line").append('<div class="part">' +
                //         '<img src="" alt="后端提供图片url"/>' +
                //         '<h3 class="title"></h3>' +
                //         '<span class="state"></span>' +
                //         '<span class="type"></span>' +
                //         '</div>')
                //     $(".part h3").html(title);
                //     $(".part .type").html("  |  "+type);
                //     $(".part .state").html(state);
                //     var url=res.Data[index].image;
                //     $(".line img").attr("src",url);
                // }
            }
        },
        error:function () {
            console.log("搜索出错。");
        }
    })
}
/*$(document).on("click","#search",function (){
    var value=$("input[name='search-input']").val();
    console.log(value);
    if(!value){
        alert("请输入搜索内容！");
        return ;
    }
    window.location.href='search?keyword='+ encodeURI(encodeURI(value));
});*/