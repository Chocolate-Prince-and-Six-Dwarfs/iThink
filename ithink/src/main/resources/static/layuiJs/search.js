layui.use(['layer','form','jquery'],function () {
    var layer = layui.layer
        ,form = layui.form
        ,$=layui.jquery;

    search(layer);
});

function search(layer) {
    var active={
        searchInfo:function () {
            var value=$("#search-input").val();
            if(value!=null && value!=""){
                layer.msg('查询中，请稍候...');
                window.location.href='/search?keyword='+ encodeURI(encodeURI(value));
            }else{
                layer.msg("请输入搜索关键字！");
                return false;
            }
        }
    };
    $("#search").on('click',function () {
        var othis = $(this),type=$(this).data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
    //监听回车事件,扫描枪一扫描或者按下回车键就直接执行查询
    $("#search-input").bind("keyup", function (e) {
        if (e.keyCode == 13) {
            var type = "searchInfo";
            active[type] ? active[type].call(this) : '';
        }
    });
}