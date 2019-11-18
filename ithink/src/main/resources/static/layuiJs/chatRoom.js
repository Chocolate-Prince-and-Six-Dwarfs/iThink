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

        this.width = "80%";
        this._openText = "+";
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
        container.css("right", "3px");
        container.css("display", "flex");
        container.css("flex-direction", "column");
        container.css("flex-wrap", "wrap");
        container.css("justify-content", "space-around");
        container.css("align-items", "flex-end");
        container.css("max-height", "40em");
        //container.css("overflow", "scroll");

        // 构建打开关闭的按钮
        let button = $("<button>");
        button.attr("id", "chat-room-button");
        button.css("border-radius", "34.0px");
        button.css("background", "#393D49");
        button.css("color", "rgba(255,255,255,.7)");
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
        button.on("click", function () {
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
        frame.css("flex-wrap", "wrap");
        frame.css("justify-content", "flex-start");
        frame.css("align-items", "flex-start");
        frame.css("background", "#c2c2c2");
        frame.css("color", "white");
        frame.css("padding", "1px");
        frame.css("display", "hidden");
        frame.css("margin", "0");
        frame.css("min-height", "10em");
        frame.css("max-height", "35em");
        frame.css("overflow", "scroll");
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

        let thisObject= this;

        // 初始化团组信息
        this._initGroup();

        // 创建SSE
        //console.log(this._userId);
        this._sse = new EventSource('/chat/connect?userId=' + this._userId);
        this._sse.onmessage = function(event)
        {
            thisObject._appendData(event.data.substring(3,));
        };

        // 设置关闭时自动缓存
        $(window).on("beforeunload", function () {
            thisObject._saveCookie();
        });
    }

    show()
    {
        $("#chat-room-frame").show();
    }

    hide()
    {
        $("#chat-room-frame").hide();
    }

    _loadCookie(id)
    {
        // 加载缓存
        let cache = $.cookie(id);
        cache = $(cache);
        //console.log(cache);
        $("#" + id).append(cache);
    }

    _saveCookie()
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
            $.cookie(id, cache, {path: "/"});
        }
    }

    _appendData(data)
    {
        let tmpData = $.parseJSON(data);
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

                $("#chat-room-group-chat-content-" + tmp[i].toId).append(this._createMessage(tmp[i], this._userId));
            }
        }
    }

    _createMessage(data, userId)
    {
        let message = $("<div>");
        message.attr("id", "chat-room-group-chat-content-message-" + data.id);
        message.css("display", "flex");
        message.css("flex-direction", "column");
        message.css("justify-content", "left");
        message.css("align-items", "center");
        message.css("border-radius", "9px");
        message.css("margin-top", "0.5em");
        message.css("background", "#c2c2c2");
        message.css("color", "white");
        message.css("min-width", "0px");
        message.css("max-width", "80%");
        message.css("padding", "0.2em");

        let user = $("<div>");
        user.attr("id", "chat-room-group-chat-content-message-user-" + data.id);
        //user.css("min-width", "0px");
        //user.css("max-width", "80%");
        user.css("margin-right", "inherit");
        user.css("margin-left", "inherit");
        user.text(data.fromName);

        let time = $("<div>");
        time.attr("id", "chat-room-group-chat-content-message-time-" + data.id);
        //time.css("min-width", "0px");
        //time.css("max-width", "80%");
        time.css("margin-right", "inherit");
        time.css("margin-left", "inherit");
        //处理时间
        let date = new Date(data.time);
        // let formatDate = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay() + " "
        //                 + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        let formatDate = date.format("yyyy-MM-dd HH:mm:ss");
        time.text(formatDate);

        let content = $("<div>");
        content.attr("id", "chat-room-group-chat-content-message-content-" + data.id);
        //content.css("min-width", "0px");
        //content.css("max-width", "80%");
        content.css("margin-right", "inherit");
        content.css("margin-left", "inherit");
        content.text(data.content);

        message.append(user);
        message.append(time);
        message.append(content);

        if(data.fromId === userId)
        {
            message.css("margin-left", "auto");
            message.css("margin-right", "0.2em");
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
                        // 加载缓存
                        thisObject._loadCookie("chat-room-group-chat-content-" + data[i].id);
                    }
                },
                error: function () {
                    console.log("发送失败");
                }
            }
        )

    }

    _createGroupElement(data, userId)
    {
        //console.log(id);
        let groupEle;
        groupEle = $("<div>");
        groupEle.attr("id", "chat-room-group-" + data.id);
        groupEle.css("width", "100%");
        groupEle.css("margin", "0.5em");
        groupEle.append(this._createGroupName(data.id, data.name));
        groupEle.append(this._createGroupClose(data.id));
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

    _createGroupClose(id)
    {
        let groupClose;
        groupClose = $("<button>");
        groupClose.attr("id", "chat-room-group-close-" + id);
        groupClose.css("border-radius", "34.0px");
        groupClose.css("background", "#393D49");
        groupClose.css("color", "rgba(255,255,255,.7)");
        groupClose.css("padding", "1px");
        groupClose.css("width", "2em");
        groupClose.css("height", "2em");
        groupClose.css("border", "0px");
        groupClose.css("transition", "transform 0.5s");
        groupClose.css("outline", "none");
        groupClose.css("font-size", "12px");
        groupClose.text("+");
        groupClose.on("click", function () {
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
            }else{
                node.hide(500);
            }
        });
        return groupClose;
    }

    _createGroupChat(id, userId)
    {
        //let thisObject = this;

        let groupChat;
        groupChat = $("<div>");
        groupChat.attr("id", "chat-room-group-chat-" + id);

        // 添加显示消息的框
        let groupChatContent = $("<div>");
        groupChatContent.attr("id", "chat-room-group-chat-content-" + id);
        groupChatContent.attr("cached", "true");
        groupChatContent.css("width", "80%");
        groupChatContent.css("border-radius", "6px");
        groupChatContent.css("display", "flex");
        groupChatContent.css("flex-direction", "column");
        groupChatContent.css("flex-wrap", "wrap");
        groupChatContent.css("justify-content", "flex-start");
        groupChatContent.css("align-items", "flex-start");
        groupChatContent.css("background", "#f2f2f2");
        groupChatContent.css("color", "black");
        groupChatContent.css("padding", "1px");
        groupChatContent.css("display", "hidden");
        groupChatContent.css("margin", "0");
        groupChatContent.css("min-height", "5em");

        // 添加输入组
        let groupChatInputGroup = $("<div>");
        groupChatInputGroup.css("width", "80%");
        // groupChatInputGroup.css("display", "flex");
        // groupChatInputGroup.css("flex-direction", "column");
        // groupChatInputGroup.css("flex-wrap", "wrap");
        // groupChatInputGroup.css("justify-content", "flex-start");
        // groupChatInputGroup.css("align-items", "flex-end");

        // 添加输入消息框
        let groupChatInput = $("<input>");
        groupChatInput.attr("id", "chat-room-group-chat-input-" + id);
        groupChatInput.css("width", "80%");
        groupChatInput.css("border", "0px");
        groupChatInput.css("margin", "0 auto");
        groupChatInput.attr("type", "text");

        // 添加发送按钮
        let groupChatSend = $("<button>");
        groupChatSend.attr("id", "chat-room-group-chat-send-" + id);
        groupChatSend.css("width", "20%");
        groupChatSend.css("border", "0px");
        groupChatSend.css("margin", "0 auto");
        //groupChatSend.css("margin-left", "auto");
        //groupChatSend.css("margin-right", "0.2em");
        groupChatSend.text("发送消息");
        groupChatSend.on("click", function ()
        {
            let message = $("#chat-room-group-chat-input-" + id).val();
            //console.log(message);
            if(message==null || message==="")
            {
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
        groupChatInput.on("keydown", function(event){
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

