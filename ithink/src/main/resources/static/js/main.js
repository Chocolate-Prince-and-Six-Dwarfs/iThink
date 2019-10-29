document.getElementById("idea").onclick=function () {
    window.location.href='search';
};
document.getElementById("out").onclick=function(){
    window.location.href='index';
};
document.getElementById("user").onclick=function(){
    window.location.href='user';
}
document.getElementById("search").onclick=function () {
    var value=$("#search-input").val();
    // console.log(value);
    if(!value){
        alert("请输入搜索内容！");
        return ;
    }
    window.location.href='search?keyword='+ encodeURI(encodeURI(value));
}