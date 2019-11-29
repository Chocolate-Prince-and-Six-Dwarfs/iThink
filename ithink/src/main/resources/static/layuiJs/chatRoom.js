Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


class ChatRoom
{
    constructor(userId)
    {

        this._userId = userId;
        this._layer = null;

        this.width = "80%";
        this._openText = "+";
        this._onlineUpdateSSE = [];
        this.element = this._createChatRoom();

        return this;
    }

    _createChatRoom()
    {
        // 构建最外部容器
        let container = $("<div>");
        container.attr("id", "chat-room-container");
        container.css("width", this.width);
        container.css("position", "fixed");
        container.css("right", "0.2px");
        container.css("top", "10%");
        container.css("display", "flex");
        container.css("flex-direction", "column");
        container.css("flex-wrap", "wrap");
        container.css("justify-content", "space-around");
        container.css("align-items", "flex-end");
        container.css("max-height", "40em");
        container.css("transition", "height 0.5s");
        //container.css("overflow", "scroll");
        container.css("margin", "2em");
        //container.css("z-index", "100");

        // 构建打开关闭的按钮
        let button = $("<button>");
        button.attr("id", "chat-room-button");
        button.css("border-radius", "34.0px");
        button.css("background", "#393D49");
        button.css("color", "rgba(255,255,255,0.7)");
        button.css("padding", "1px");
        button.css("width", "2em");
        button.css("height", "2em");
        button.css("border", "0px");
        button.css("transition", "transform 0.5s");
        button.css("outline", "none");
        button.css("font-size", "20px");
        // 构建按钮文本
        // 填充
        //console.log(this.openText);
        button.text(this._openText);
        button.off("click").on("click", function () {
            //console.log("click");
            // 旋转按钮
            let rotateSign = false;
            $(this).animate({}, function () {
                let transform = $(this).css("transform");
                let nowDeg;
                if(transform!=null && transform!="" && transform!="none")
                {
                    nowDeg = eval('get'+transform) + 45;
                }
                else
                {
                    nowDeg = 45;
                }
                if(nowDeg>=90)
                {
                    nowDeg = nowDeg % 90;
                }
                if(nowDeg!=45 && nowDeg!=0)
                {
                    return;
                }
                $(this).css("transform", "rotate(" + nowDeg + "deg)");
                rotateSign = true;
            });

            if(rotateSign === false)
            {
                return;
            }

            // 更改frame可见性
            let node = $("#chat-room-frame");
            //console.log(node);
            if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
                node.show(500);
            }else{
                node.hide(500);
            }
        });

        // 创建主体框
        let frame = $("<div>");
        frame.attr("id", "chat-room-frame");
        frame.css("width", "80%");
        frame.css("border-radius", "6px");
        frame.css("display", "flex");
        frame.css("flex-direction", "column");
        frame.css("flex-wrap", "nowrap");
        frame.css("justify-content", "flex-start");
        frame.css("align-items", "flex-start");
        frame.css("background", "#FFFFFF");
        frame.css("color", "#555");
        frame.css("border", "1px solid #C9C9C9");
        frame.css("padding", "1px");
        frame.css("display", "hidden");
        frame.css("margin", "0");
        frame.css("min-height", "10em");
        frame.css("max-height", "35em");
        frame.css("overflow", "scroll");
        frame.css("overflow-x", "hidden");
        frame.css("transition", "height 0.5s");
        //let testContent = "测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>";
        //frame.html(testContent);
        frame.hide();



        // 构建填充
        container.append(button);
        container.append("<br/>");
        container.append(frame);

        return container;
    }

    getElement()
    {
        return this.element;
    }

    appendTo(parent)
    {
        $(parent).append(this.element);

        this._init();
    }

    prependTo(parent)
    {
        $(parent).prepend(this.element);

        this._init();

    }

    _startSSE()
    {
        let thisObject= this;
        // 创建SSE
        //console.log(this._userId);
        this._sse = new EventSource('/chat/connect?userId=' + this._userId);
        this._sse.onmessage = function(event)
        {
            //console.log("收到信息" + thisObject._userId + "，状态" + thisObject._sse.readyState);
            if(event.data === "{}")
            {
                return;
            }
            thisObject._appendData(event.data);
        };

        // 创建SSE
        //console.log(this._userId);
        let sse = new EventSource('/chat/getOnlineNum?userId=' + this._userId);
        sse.onmessage = function(event)
        {
            //console.log("收到信息" + thisObject._userId + "，状态" + thisObject._sse.readyState);
            if(event.data === "{}")
            {
                return;
            }
            let data = $.parseJSON(event.data);
            for(let i in data)
            {
                if($("#chat-room-group-online-" + i).length === 0)
                {
                    continue;
                }
                $("#chat-room-group-online-" + i).text("本群在线人数: " + data[i]);
            }
        };
    }

    _init()
    {
        let thisObject= this;

        // 初始化团组信息
        this._initGroup();

        this._auth();

        // 设置关闭时行为
        $(window).off("beforeunload").on("beforeunload", function () {
            thisObject._saveCache();
            //thisObject._sse.close();
            //console.log("关闭" + thisObject._userId + "，状态" + thisObject._sse.readyState);
            thisObject._close();
        });


        this._startSSE();
    }

    _close()
    {
        this._sse.close();
        // for(let i = 0; i<this._onlineUpdateSSE.length; ++i)
        // {
        //     this._onlineUpdateSSE[i].close();
        // }
        $.ajax(
            {
                url: "/chat/close",
                type: "post",
                async:false,
                data: {
                    "userId": this._userId,
                },
                success:function()
                {
                    console.log("关闭成功");
                },
                error: function () {
                    console.log("关闭失败");
                }
            }
        );
    }

    _auth()
    {
        $.ajax(
            {
                url: "/chat/auth",
                type: "post",
                data: {
                    "userId": this._userId,
                },
                success:function()
                {
                    console.log("连接成功");
                },
                error: function () {
                    console.log("连接");
                }
            }
        );
    }

    show()
    {
        // 更改frame可见性
        let node = $("#chat-room-frame");
        //console.log(node);
        if(node.is(':hidden')){　　//如果node是隐藏的则显示
            $("#chat-room-button").click();
        }
    }

    hide()
    {
        // 更改frame可见性
        let node = $("#chat-room-frame");
        //console.log(node);
        if(!node.is(':hidden')){　　//如果node是显示的则隐藏
            $("#chat-room-button").click();
        }
    }

    _loadCache(id)
    {
        // 加载缓存
        let cache = localStorage.getItem(id + ":userId=" + this._userId);
        cache = $(cache);
        // for(let i=0; i<cache.length; ++i)
        // {
        //     if(parseInt(cache.eq(i).attr("user-id")) === this._userId)
        //     {
        //         cache.eq(i).css("margin-left", "auto");
        //         cache.eq(i).css("margin-right", "0.2em");
        //     }
        //     else
        //     {
        //         cache.eq(i).css("margin-left", null);
        //         cache.eq(i).css("margin-right", null);
        //     }
        // }
        //console.log(cache);
        $("#" + id).append(cache);
        $("#" + id).animate({scrollTop:$("#" + id)[0].scrollHeight},'500');
    }

    _saveCache()
    {
        // // 获取计数
        // let count;
        // let record;
        // let countCookieName = "chat-room-group" + tmp[j].toId + "-count";
        // let recordCookieName = "chat-room-group" + tmp[j].toId + "-record";
        // if ($.cookie(countCookieName) === null) {
        //     count = 0;
        // } else
        // {
        //     count = parseInt($.cookie(countCookieName));
        // }
        // // 增加计数
        // count++;
        // // 设置新计数
        // $.cookie(countCookieName, count, {path: "/"});
        //
        // // 读取聊天记录缓存
        // if ($.cookie(recordCookieName) === null)
        // {
        //     record = "";
        // } else
        // {
        //     record = $.cookie(recordCookieName);
        // }
        // // 设置聊天记录缓存
        // $.cookie(recordCookieName, record + "," + data, {path: "/"});

        // 生成缓存
        let contentElements = $("[id*='chat-room-group-chat-content-'][cached='true']");
        //console.log(contentElements);
        for(let i=0; i<contentElements.length; ++i)
        {
            let id = contentElements.eq(i).attr("id");
            // let idReg = new RegExp("(?<=chat-room-group-chat-content-)[1-9]{1,}[0-9]{0,}");
            // let numId = parseInt(idReg.exec(id)[0]);
            let cache = contentElements.eq(i).html();
            //console.log("生成缓存: " + cache);
            localStorage.setItem(id + ":userId=" + this._userId, cache);
        }
    }

    refresh()
    {
        this._saveCache();
        this._close();
        $("#chat-room-frame").empty();
        this._init();
    }

    _appendData(data)
    {
        let tmpData = $.parseJSON(data);
        //console.log(tmpData);
        for(let i=0; i<tmpData.groupChatRecord.length; ++i)
        {
            let tmp = tmpData.groupChatRecord[i];
            for(let j=0; j<tmp.length; ++j)
            {
                // let selector = "#chat-room-group-" + tmp[j].toId;
                // let groupEle = $(selector);
                // //console.log(groupEle);
                // if (groupEle.length <= 0)
                // {
                //
                // }
                if($("#chat-room-group-chat-content-" + tmp[j].toId).length === 0)
                {
                    let tmpGroupInfo = new Object();
                    tmpGroupInfo.id = tmp[j].toId;
                    tmpGroupInfo.name = tmp[j].toName;
                    let groupEle = this._createGroupElement(tmpGroupInfo, this._userId);
                    $("#chat-room-frame").append(groupEle);
                    $("#chat-room-frame").animate({scrollTop:$("#chat-room-frame")[0].scrollHeight},'500');
                }
                $("#chat-room-group-chat-content-" + tmp[j].toId).append(this._createMessage(tmp[j], this._userId));
                $("#chat-room-group-chat-content-" + tmp[j].toId).animate({scrollTop:$("#chat-room-group-chat-content-" + tmp[j].toId)[0].scrollHeight},'500');
                $.ajax({
                    url:"/user/getById",
                    type:"post",
                    dataType: "json",
                    data:{
                        id: tmp[j].fromId,
                    },
                    success:function (data) {
                        $("img[fromId=" + data.id + "]" )
                            .attr("src", "data:image/png;base64," + data.head);
                    },
                    error:function () {
                        console.log("读取用户信息失败！");
                    }
                });
            }
        }
    }

    _appendRecord(data)
    {
        let tmpData = $.parseJSON(data);
        //console.log(tmpData);
        let tmp = tmpData.groupChatRecord[0];
        for(let j=0; j<tmp.length; ++j)
        {
            $("#chat-room-group-chat-content-" + tmp[j].toId).append(this._createRecordMessage(tmp[j], this._userId));
        }
        $("#chat-room-group-chat-content-" + tmp[0].toId).append(this._createRecordHr(tmp[0]));
        $("#chat-room-group-chat-content-" + tmp[0].toId).animate({scrollTop:$("#chat-room-group-chat-content-" + tmp[0].toId)[0].scrollHeight},'500');
    }

    _createRecordHr(data)
    {
        let hrGroup = $("<div>");
        hrGroup.attr("chat-room-group-chat-content-record-hr-" + data.id);
        hrGroup.css("width", "100%");
        hrGroup.css("display", "flex");
        hrGroup.css("flex-direction", "column");
        hrGroup.css("flex-wrap", "nowrap");
        hrGroup.css("justify-content", "flex-start");
        hrGroup.css("align-items", "center");

        let hr = $("<hr>");
        hr.css("width", "80%");
        hr.css("color", "#393D49");

        let msg = $("<div>");
        msg.text("以上为聊天记录");
        msg.css("color", "#393D49");

        hrGroup.append(hr);
        hrGroup.append(msg);

        return hrGroup;
    }

    _createRecordMessage(data, userId)
    {
        let message = $("<div>");
        message.attr("id", "chat-room-group-chat-content-record-message-" + data.id);
        //message.attr("user-id", userId);
        message.css("display", "flex");
        message.css("flex-direction", "column");
        message.css("justify-content", "left");
        message.css("align-items", "center");
        message.css("border-radius", "9px");
        message.css("margin-top", "0.5em");
        message.css("background", "#393D49");
        message.css("color", "rgba(255,255,255,0.7)");
        message.css("min-width", "0px");
        message.css("max-width", "80%");
        message.css("padding", "0.2em");

        let user = $("<div>");
        user.attr("id", "chat-room-group-chat-content-record-message-user-" + data.id);
        //user.css("min-width", "0px");
        //user.css("max-width", "80%");
        user.css("font-size", "0.75em");
        //user.css("margin-right", "inherit");
        //user.css("margin-left", "inherit");
        user.text(data.fromName);

        let time = $("<div>");
        time.attr("id", "chat-room-group-chat-content-record-message-time-" + data.id);
        //time.css("min-width", "0px");
        //time.css("max-width", "80%");
        time.css("font-size", "0.5em");
        //time.css("margin-right", "inherit");
        //time.css("margin-left", "inherit");
        //处理时间
        let date = new Date(data.time);
        // let formatDate = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay() + " "
        //                 + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        let formatDate = date.format("yyyy-MM-dd HH:mm:ss");
        time.text(formatDate);

        let hr = $("<hr>");
        hr.attr("id", "chat-room-group-chat-content-record-message-hr-" + data.id);
        //hr.css("filter", "alpha(opacity=100,finishopacity=0,style=3)");
        hr.css("width", "100%");

        let content = $("<div>");
        content.attr("id", "chat-room-group-chat-content-record-message-content-" + data.id);
        //content.css("min-width", "0px");
        //content.css("max-width", "80%");
        content.css("margin-right", "inherit");
        content.css("margin-left", "inherit");
        content.css("color", "white");
        content.text(data.content);

        message.append(user);
        message.append(time);
        message.append(hr);
        message.append(content);

        return message;
    }

    _createMessage(data, userId)
    {
        let message = $("<div>");
        message.attr("id", "chat-room-group-chat-content-message-" + data.id);
        //message.attr("user-id", userId);
        message.css("display", "flex");
        message.css("flex-direction", "column");
        message.css("justify-content", "left");
        message.css("align-items", "center");
        message.css("border-radius", "9px");
        message.css("margin-top", "0.5em");
        message.css("background", "#393D49");
        message.css("color", "rgba(255,255,255,0.7)");
        message.css("min-width", "0px");
        message.css("max-width", "80%");
        message.css("padding", "0.2em");

        let head = $("<img>");
        head.attr("id", "chat-room-group-chat-content-message-img-" + data.id);
        head.attr("fromId", data.fromId);
        //head.addClass("imgcss");
        head.css("height", "2em");
        head.css("width", "2em");

        let user = $("<div>");
        user.attr("id", "chat-room-group-chat-content-message-user-" + data.id);
        //user.css("min-width", "0px");
        //user.css("max-width", "80%");
        user.css("font-size", "0.75em");
        //user.css("margin-right", "inherit");
        //user.css("margin-left", "inherit");
        user.text(data.fromName);

        let time = $("<div>");
        time.attr("id", "chat-room-group-chat-content-message-time-" + data.id);
        //time.css("min-width", "0px");
        //time.css("max-width", "80%");
        time.css("font-size", "0.5em");
        //time.css("margin-right", "inherit");
        //time.css("margin-left", "inherit");
        //处理时间
        let date = new Date(data.time);
        // let formatDate = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay() + " "
        //                 + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        let formatDate = date.format("yyyy-MM-dd HH:mm:ss");
        time.text(formatDate);

        let hr = $("<hr>");
        hr.attr("id", "chat-room-group-chat-content-message-hr-" + data.id);
        //hr.css("filter", "alpha(opacity=100,finishopacity=0,style=3)");
        hr.css("width", "100%");

        let content = $("<div>");
        content.attr("id", "chat-room-group-chat-content-message-content-" + data.id);
        //content.css("min-width", "0px");
        //content.css("max-width", "80%");
        content.css("margin-right", "inherit");
        content.css("margin-left", "inherit");
        content.css("color", "white");
        content.text(data.content);

        message.append(head);
        message.append(user);
        message.append(time);
        message.append(hr);
        message.append(content);

        if(data.fromId === userId)
        {
            message.css("margin-left", "auto");
            message.css("margin-right", "0.2em");
        }
        else
        {
            message.css("margin-left", "0.2em");
            message.css("margin-right", "auto");
        }

        return message;
    }
//     var newBlock = document.createElement("div");
//     var newUserBlock = document.createElement("div");
//     var newTimeBlock = document.createElement("div");
//     var newMessageBlock = document.createElement("div");
//
//     newBlock.className = "messageblock";
//     newUserBlock.className = "userblock";
//     newTimeBlock.className = "timeblock";
//     newMessageBlock.className = "msgblock";
//
//     newUserBlock.appendChild(document.createTextNode(Data[i]["user"]));
//     newTimeBlock.appendChild(document.createTextNode(timestampToTime(parseInt(Data[i]["time"]))));
//     newMessageBlock.appendChild(document.createTextNode(Data[i]["msg"]));
//
//     newBlock.appendChild(newUserBlock);
//     newBlock.appendChild(newMessageBlock);
//     newBlock.appendChild(newTimeBlock);
//     if(Data[i]["user"]=="'. $_COOKIE["kowaine_user"] .'")
// {
//     newBlock.style.marginLeft = "auto";
//     newBlock.style.marginRight = "2px";
// }
// else
// {
//     newBlock.style.marginRight = "auto";
//     newBlock.style.marginLeft = "2px";
// }
// newBlock.style.fontSize = "0.5em";
// newBlock.style.marginTop = "2px";
// document.getElementById("chat").appendChild(newBlock);

    _initGroup()
    {
        let thisObject = this;
        $.ajax(
            {
                url: "/chat/getGroupListByUserId",
                type: "post",
                dataType: "json",
                data: {
                    "userId": this._userId,
                },
                success:function(data)
                {
                    for(let i=0; i<data.length; ++i)
                    {
                        // console.log($("#chat-room-group-" + data[i].id));
                        // if($("#chat-room-group-" + data[i].id).length!==0)
                        // {
                        //     console.log("跳过");
                        //     continue;
                        // }
                        let groupEle = thisObject._createGroupElement(data[i], thisObject._userId);
                        $("#chat-room-frame").append(groupEle);
                        $("#chat-room-frame").animate({scrollTop:$("#chat-room-frame")[0].scrollHeight},'500');
                        // 加载缓存
                        thisObject._loadCache("chat-room-group-chat-content-" + data[i].id);
                    }
                },
                error: function () {
                    console.log("发送失败");
                }
            }
        );

    }

    _createGroupElement(data, userId)
    {
        //console.log(id);
        let groupEle;
        groupEle = $("<div>");
        groupEle.attr("id", "chat-room-group-" + data.id);
        groupEle.css("width", "100%");
        groupEle.css("margin", "0.5em");
        groupEle.css("transition", "height 0.5s");

        let hr = $("<hr>");
        hr.attr("id", "chat-room-group-hr-" + data.id);
        //hr.css("filter", "alpha(opacity=100,finishopacity=0,style=3)");
        hr.css("width", "100%");

        groupEle.append(hr);
        groupEle.append(this._createGroupName(data.id, data.name));
        groupEle.append(this._createGroupOnlineNum(data.id));
        groupEle.append(this._createGroupClose(data.id));
        groupEle.append(this._createGroupSync(data.id));
        groupEle.append(this._createGroupChat(data.id, userId));

        return groupEle;
    }

    _createGroupName(id, name)
    {
        let groupName;
        groupName = $("<div>");
        groupName.attr("id", "chat-room-group-name-" + id);
        groupName.text(name);

        return groupName;
    }

    _createGroupOnlineNum(id)
    {
        let groupOnlineNum;
        groupOnlineNum = $("<div>");
        groupOnlineNum.attr("id", "chat-room-group-online-" + id);
        groupOnlineNum.css("font-size", "0.75em");
        groupOnlineNum.text("本群在线人数: 0");

        // // 创建SSE
        // //console.log(this._userId);
        // let sse = new EventSource('/chat/getOnlineNum?userId=' + this._userId + '&id=' + id);
        // sse.onmessage = function(event)
        // {
        //     //console.log("收到信息" + thisObject._userId + "，状态" + thisObject._sse.readyState);
        //     if(event.data === "{}")
        //     {
        //         return;
        //     }
        //     $("#chat-room-group-online-" + id).text("本群在线人数: " + event.data);
        // };

        // this._onlineUpdateSSE.push(sse);

        return groupOnlineNum;

    }

    _createGroupClose(id)
    {
        let groupClose;
        groupClose = $("<button>");
        groupClose.attr("id", "chat-room-group-close-" + id);
        groupClose.css("border-radius", "34.0px");
        groupClose.css("background", "#393D49");
        groupClose.css("color", "rgba(255,255,255,0.7)");
        groupClose.css("padding", "1px");
        groupClose.css("width", "2em");
        groupClose.css("height", "2em");
        groupClose.css("border", "0px");
        groupClose.css("transition", "transform 0.5s");
        groupClose.css("outline", "none");
        groupClose.css("font-size", "12px");
        groupClose.text("+");
        groupClose.off("click").on("click", function () {
            // 旋转按钮
            let rotateSign = false;
            $(this).animate({}, function () {
                let transform = $(this).css("transform");
                let nowDeg;
                if(transform!=null && transform!="" && transform!="none")
                {
                    nowDeg = eval('get'+transform) + 45;
                }
                else
                {
                    nowDeg = 45;
                }
                if(nowDeg>=90)
                {
                    nowDeg = nowDeg % 90;
                }
                if(nowDeg!=45 && nowDeg!=0)
                {
                    return;
                }
                $(this).css("transform", "rotate(" + nowDeg + "deg)");
                rotateSign = true;
            });

            if(rotateSign === false)
            {
                return;
            }

            // 更改group可见性
            let node = $("#chat-room-group-chat-" + id);
            //console.log(node);
            if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
                node.show(500);
                $("#chat-room-group-chat-content-" + id).animate({scrollTop:$("#chat-room-group-chat-content-" + id)[0].scrollHeight},'500');
            }else{
                node.hide(500);
            }
        });
        return groupClose;
    }

    _createGroupSync(id)
    {
        let thisObject= this;

        let groupSync;
        groupSync = $("<button>");
        groupSync.attr("id", "chat-room-group-sync-" + id);
        groupSync.css("border-radius", "34.0px");
        groupSync.css("background", "#393D49");
        groupSync.css("color", "rgba(255,255,255,0.7)");
        groupSync.css("padding", "0.5em");
        //groupSync.css("width", "2em");
        //groupSync.css("height", "2em");
        groupSync.css("border", "0px");
        groupSync.css("margin-left", "1em");
        groupSync.css("transition", "transform 0.5s");
        groupSync.css("outline", "none");
        groupSync.css("font-size", "12px");
        groupSync.text("聊天记录");
        groupSync.off("click").on("click", function () {

            // 更改group可见性
            let node = $("#chat-room-group-chat-" + id);
            //console.log(node);
            if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则不作操作
                node.show(500);
                $("#chat-room-group-chat-content-" + id).animate({scrollTop:$("#chat-room-group-chat-content-" + id)[0].scrollHeight},'500');
                // 旋转按钮
                $("#chat-room-group-close-" + id).animate({}, function () {
                    let transform = $("#chat-room-group-close-" + id).css("transform");
                    let nowDeg;
                    if(transform!=null && transform!="" && transform!="none")
                    {
                        nowDeg = eval('get'+transform) + 45;
                    }
                    else
                    {
                        nowDeg = 45;
                    }
                    if(nowDeg>=90)
                    {
                        nowDeg = nowDeg % 90;
                    }
                    if(nowDeg!=45 && nowDeg!=0)
                    {
                        return;
                    }
                    $("#chat-room-group-close-" + id).css("transform", "rotate(" + nowDeg + "deg)");
                });
            }else{
            }

            // 同步聊天记录
            node = $("#chat-room-group-chat-content-" + id);
            node.empty();
            $.ajax(
                {
                    url: "/chat/getGroupChatRecord",
                    type: "post",
                    dataType: "json",
                    data: {
                        "id": id,
                    },
                    success:function(data)
                    {
                        thisObject._appendRecord(JSON.stringify(data));
                    },
                    error: function () {
                        console.log("发送失败");
                    }
                }
            );

            //console.log(node);
        });
        return groupSync;
    }

    _createGroupChat(id, userId)
    {
        let thisObject = this;

        let groupChat;
        groupChat = $("<div>");
        groupChat.attr("id", "chat-room-group-chat-" + id);

        // 添加显示消息的框
        let groupChatContent = $("<div>");
        groupChatContent.attr("id", "chat-room-group-chat-content-" + id);
        groupChatContent.attr("cached", "true");
        groupChatContent.css("width", "80%");
        // groupChatContent.css("border-radius", "6px");
        groupChatContent.css("display", "flex");
        groupChatContent.css("flex-direction", "column");
        groupChatContent.css("flex-wrap", "nowrap");
        groupChatContent.css("justify-content", "flex-start");
        groupChatContent.css("align-items", "flex-start");
        groupChatContent.css("background", "#F2F3F5");
        groupChatContent.css("color", "white");
        groupChatContent.css("padding", "1px");
        groupChatContent.css("display", "hidden");
        groupChatContent.css("margin", "0");
        groupChatContent.css("min-height", "5em");
        groupChatContent.css("border", "1px solid #C9C9C9");
        groupChatContent.css("max-height", "20em");
        groupChatContent.css("overflow", "scroll");
        groupChatContent.css("overflow-x", "hidden");
        groupChatContent.css("transition", "height 0.5s");
        // 添加输入组
        let groupChatInputGroup = $("<div>");
        groupChatInputGroup.css("width", "80%");
        groupChatInputGroup.css("display", "flex");
        groupChatInputGroup.css("flex-direction", "row");
        groupChatInputGroup.css("flex-wrap", "nowrap");
        groupChatInputGroup.css("justify-content", "space-between");

        // 添加输入消息框
        let groupChatInput = $("<input>");
        groupChatInput.attr("id", "chat-room-group-chat-input-" + id);
        groupChatInput.css("width", "85%");
        //groupChatInput.css("border", "0px");
        //groupChatInput.css("margin", "auto");
        groupChatInput.attr("type", "text");
        //groupChatInput.css("background", "#c2c2c2");
        //groupChatInput.css("color", "white");
        groupChatInput.css("border", "1px solid #C9C9C9");

        // 添加发送按钮
        let groupChatSend = $("<button>");
        groupChatSend.attr("id", "chat-room-group-chat-send-" + id);
        groupChatSend.css("width", "15%");
        //groupChatSend.css("border", "0px");
        //groupChatSend.css("margin", "auto");
        //groupChatSend.css("margin-left", "auto");
        //groupChatSend.css("margin-right", "0.2em");
        groupChatSend.css("background", "#393D49");
        groupChatSend.css("color", "rgba(255,255,255,0.7)");
        groupChatSend.text("发送消息");

        groupChatSend.off("click").on("click", function ()
        {
            let message = $("#chat-room-group-chat-input-" + id).val();
            //console.log(message);
            if(message==null || message==="")
            {
                if(thisObject._layer!=null)
                {
                    thisObject._layer.msg("消息不能为空");
                }
                return;
            }
            else if(message.length>255)
            {
                if(thisObject._layer!=null)
                {
                    thisObject._layer.msg("消息字符个数不能大于255");
                }
                return;
            }

            $.ajax(
                {
                    url: "/chat/toGroup",
                    type: "post",
                    dataType: "json",
                    data: {
                        "userId": userId,
                        "groupId": id,
                        "content": message
                    },
                    success:function(data)
                    {
                        if(data === 1)
                        {
                            $("#chat-room-group-chat-input-" + id).val("");
                        }
                    },
                    error: function () {
                        console.log("发送失败");
                    }
                });
        });


        // 绑定回车
        groupChatInput.off("keydown").on("keydown", function(event){
            let code = event.keyCode;
            if(code === 13){ //这是键盘的enter监听事件
                //绑定焦点，有可能不成功，需要多试试一些标签
                $("#chat-room-group-chat-send-" + id).click();
            }
        });

        groupChat.append(groupChatContent);

        groupChatInputGroup.append(groupChatInput);
        groupChatInputGroup.append(groupChatSend);

        groupChat.append(groupChatInputGroup);

        groupChat.hide();

        return groupChat;
    }

    setLayuiLayer(layer)
    {
        this._layer = layer;
    }
}

//
// {
//     "groupChatRecord":
//     [
//         [
//             {"content":"又来测试","fromId":1,"id":12,"time":1573633316000,"toId":1}
//         ]
//     ],
//         "userId":1
// }

/*
    * 解析matrix矩阵，0°-360°，返回旋转角度
    * 当a=b||-a=b,0<=deg<=180
    * 当-a+b=180,180<=deg<=270
    * 当a+b=180,270<=deg<=360
    *
    * 当0<=deg<=180,deg=d;
    * 当180<deg<=270,deg=180+c;
    * 当270<deg<=360,deg=360-(c||d);
    * */
function getmatrix(a,b,c,d,e,f){
    var aa=Math.round(180*Math.asin(a)/ Math.PI);
    var bb=Math.round(180*Math.acos(b)/ Math.PI);
    var cc=Math.round(180*Math.asin(c)/ Math.PI);
    var dd=Math.round(180*Math.acos(d)/ Math.PI);
    var deg=0;
    if(aa==bb||-aa==bb){
        deg=dd;
    }else if(-aa+bb==180){
        deg=180+cc;
    }else if(aa+bb==180){
        deg=360-cc||360-dd;
    }
    return deg>=360?0:deg;
    //return (aa+','+bb+','+cc+','+dd);
}

