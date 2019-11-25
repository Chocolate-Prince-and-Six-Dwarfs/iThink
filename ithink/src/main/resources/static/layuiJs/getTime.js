function myTime(date){
    var arr=date.split("T");
    var d=arr[0];
    var darr = d.split('-');

    var t=arr[1];
    var tarr = t.split('.000');
    var marr = tarr[0].split(':');

    var dd = parseInt(darr[0])+"-"+addZero(parseInt(darr[1]))+"-"+addZero(parseInt(darr[2]))+" "+addZero(parseInt(marr[0])+8)+":"+addZero(parseInt(marr[1]))+":"+addZero(parseInt(marr[2]));
    return dd;
}

// 数字补0操作
function  addZero(num) {
    return num < 10 ? '0' + num : num;
}

function formatDateTime (date) {
    var time = new Date(Date.parse(date));
    time.setTime(time.setHours(time.getHours()));
    var Y = time.getFullYear() + '-';
    var  M = this.addZero(time.getMonth() + 1) + '-';
    var D = this.addZero(time.getDate()) + ' ';
    var h = this.addZero(time.getHours()) + ':';
    var m = this.addZero(time.getMinutes()) + ':';
    var  s = this.addZero(time.getSeconds());
    return Y + M + D + h + m + s;
}
