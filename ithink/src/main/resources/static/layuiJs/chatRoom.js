class ChatRoom
{


    constructor()
    {
        this.width = "80%";
        this._openText = "+";
        this._closeText = "x";
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

        // 构建打开关闭的按钮
        let button = $("<button>");
        button.attr("id", "chat-room-button");
        button.css("border-radius", "34.0px");
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
                $(this).css("transform", "rotate(" + nowDeg + "deg)");
            });

            // 更改frame可见性
            let node = $("#chat-room-frame");
            console.log(node);
            if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
                node.show(500);
            }else{
                node.hide(500);
            }
        });

        // 创建主体框
        let frame = $("<div>");
        frame.attr("id", "chat-room-frame");
        frame.css("width", "100%");
        frame.css("border-radius", "6px");
        frame.css("display", "flex");
        frame.css("flex-direction", "column");
        frame.css("flex-wrap", "wrap");
        frame.css("justify-content", "space-around");
        frame.css("align-items", "flex-start");
        frame.css("background", "black");
        frame.css("color", "white");
        frame.css("padding", "1px");
        let testContent = "测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>测试<br/>";
        frame.html(testContent);

        // 构建填充
        container.append(button);
        container.append(frame);

        return container;
    }

    getElement()
    {
        return this.element;
    }

}

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