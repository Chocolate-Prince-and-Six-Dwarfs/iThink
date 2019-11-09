layui.define(['laypage','layer', 'form','jquery','element'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery
        ,element=layui.element;

    getUserInfo();
    form.render();

    switch(location.hash) {
        case("#userDemo=userNotices"):
            callUserNotices(element);
            break;
        case("#userDemo=userReleaseIdea"):
            callUserReleaseIdea(element);
            break;
        case("#userDemo=userIdeas"):
            callUserIdeas(element);
            break;
        case("#userDemo=userIdeasCapsule"):
            callUserIdeasCapsules(element);
            break;
        case("#userDemo=userGroups"):
            callUserGroups(element);
            break;
        case("#userDemo=userCollections"):
            callUserCollections(element);
            break;
        case("#userDemo=userParticipate"):
            callUserParticipate(element);
            break;
        case("#userDemo=userProfitProjects"):
            callUserProfitProjects(element);
            break;
        default:
            break;
    }
    exports('user', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use(['element','jquery','form'],function () {
    var element=layui.element
        ,$=layui.jquery
        ,form=layui.form;//导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
    form.render();
    //触发动态操作tab事件
    var active={
        tabUserInfo:function () {
            if($(".layui-tab-title li[lay-id='userInfo']").length>0){//判断个人信息tab是否已经存在
                element.tabChange('userDemo','userInfo');
                getUserInfo();
            }else{
                element.tabAdd('userDemo',{
                    title: "个人信息"
                    ,content: "<div class=\"userInfoContent\"><fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 30px;\">\n" +
                        "                            <legend>个人信息</legend>\n" +
                        "                        <div id=\"table_and_page_div_id\" style=\"width: 80%; position: relative; left:20%;\">\n" +
                        "                            <form class=\"layui-form\" action=\"\" >\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">头像</label>\n" +
                        "                                    <div class=\"layui-input-inline\">\n" +
                        "                                        <input type=\"text\" name=\"head\" lay-verify=\"required\" id=\"inputimgurl\" placeholder=\"图片地址\" value=\"/img/头像.png\" class=\"layui-input layui-hide\">\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"layui-input-inline\">\n" +
                        "                                        <div class=\"layui-upload-list\" style=\"margin:0\">\n" +
                        "                                            <img src=\"/img/头像.png\" id=\"srcimgurl\" class=\"layui-upload-img\">\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"layui-input-inline layui-btn-container\" style=\"width: auto;\">\n" +
                        "                                        <button class=\"layui-btn layui-btn-primary\" id=\"editimg\">修改图片</button >\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"layui-form-mid layui-word-aux\">头像的尺寸限定150x150px,大小在50kb以内</div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">昵称:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"title\" required  lay-verify=\"required\" placeholder=\"请输入昵称\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">电话:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"tel\" required  lay-verify=\"required\" placeholder=\"123456\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">行业:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"major\" required  lay-verify=\"required\" placeholder=\"行业领域\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">学校:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"school\" required  lay-verify=\"required\" placeholder=\"请输入学校\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">地区:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <select name=\"city\" lay-verify=\"required\">\n" +
                        "                                            <option value=\"\">国家</option>\n" +
                        "                                            <option value=\"0\">中国</option>\n" +
                        "                                            <option value=\"1\">美国</option>\n" +
                        "                                            <option value=\"2\">俄罗斯</option>\n" +
                        "                                            <option value=\"3\">日本</option>\n" +
                        "                                        </select>\n" +
                        "                                        <select name=\"city\" lay-verify=\"\">\n" +
                        "                                            <option value=\"\">省份</option>\n" +
                        "                                            <option value=\"010\">四川</option>\n" +
                        "                                            <option value=\"021\">上海</option>\n" +
                        "                                            <option value=\"0571\">香港</option>\n" +
                        "                                        </select>\n" +
                        "                                        <select name=\"city\" lay-verify=\"\">\n" +
                        "                                            <option value=\"\">城市</option>\n" +
                        "                                            <option value=\"010\">成都</option>\n" +
                        "                                            <option value=\"021\">武汉</option>\n" +
                        "                                            <option value=\"0571\">杭州</option>\n" +
                        "                                        </select>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">性别:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"radio\" name=\"sex\" value=\"男\" title=\"男\">\n" +
                        "                                        <input type=\"radio\" name=\"sex\" value=\"女\" title=\"女\" checked>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item layui-form-text\">\n" +
                        "                                    <label class=\"layui-form-label\">简介:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <textarea id=\"userEdit\" name=\"desc\" placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <button class=\"layui-btn layui-btn-normal\" style=\"background-color: #A4D3EE\"\n" +
                        "                                                lay-submit lay-filter=\"userForm\">立即提交</button>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </form>\n" +
                        "                        </div></div>"
                    ,id: "userInfo"
                });
                element.tabChange('userDemo','userInfo');
                form.render();
                getUserInfo();
                addEdit("userEdit");
            }
        }
        ,tabUserNotices:function () {
            callUserNotices(element);
        }
        ,tabUserReleaseIdea:function () {
            callUserReleaseIdea(element);
        }
        ,tabUserIdeas:function () {
            callUserIdeas(element);
        }
        ,tabUserIdeasCapsules:function () {
            callUserIdeasCapsules(element);
        }
        ,tabUserGroups:function () {
            callUserGroups(element);
        }
        ,tabUserCollections:function () {
            callUserCollections(element);
        }
        ,tabUserParticipate:function () {
            callUserParticipate(element);
        }
        ,tabUserProfitProjects:function () {
            callUserProfitProjects(element);
        }
    };
    $('.user-site-active').on('click', function(){
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#userDemo=/, '');
    element.tabChange('userDemo', layid);
    element.on('tab(userDemo)', function(elem){
        location.hash = 'userDemo='+ $(this).attr('lay-id');
    });
});

/*layui.use('upload',function () {//图片上传
    var $=layui.jquery
        ,upload=layui.upload;
    var uploadImg=upload.render({
        elem:"#uploadImg"
        ,url:"upload"
        ,size: 100
        ,accept:"images"
        ,before:function (obj) {
            obj.preview(function (index,file,result) {
                $("#userImg").attr('src',result);//图片链接（base64）
            });
        }
        ,done:function (res) {
            //如果上传失败
            if(res.code>0){
                return layer.msg('上传图片失败');
            }else{
                return layer.msg('上传成功');
            }
        }
        ,error:function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadImg.upload();
            });
        }
    });
});*/

function callUserNotices(element){
    if($(".layui-tab-title li[lay-id='userNotices']").length>0){
        element.tabChange('userDemo','userNotices');
    }else{
        element.tabAdd('userDemo',{
            title: "我的通知"
            ,content: "<div class=\"userNoticesContent\"><div style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"layui-tab-content\">\n" +
                "        <ul>\n" +
                "            <li >\n" +
                "                <span class=\"done\">用户七个小矮人关注了你的创意</span>\n" +
                "                <span class=\"time\">&nbsp;&nbsp;&nbsp;2019-12-12</span>\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题</div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <span class=\"done\">用户七个小矮人收藏了你的实现</span>\n" +
                "                <span class=\"time\">&nbsp;&nbsp;&nbsp;2019-12-12</span>\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题</div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <span class=\"done\">用户七个小矮人邀请你加入团组</span>\n" +
                "                <span class=\"time\">&nbsp;&nbsp;&nbsp;2019-12-12</span>\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题</div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "        </ul>\n" +
                "    </div></div></div>"
            ,id: "userNotices"
        });
        element.tabChange('userDemo','userNotices');
    }
}
function callUserReleaseIdea(element){
    if($(".layui-tab-title li[lay-id='userReleaseIdea']").length>0){
        element.tabChange('userDemo','userReleaseIdea');
    }else{
        element.tabAdd('userDemo',{
            title: "发布创意"
            ,content: "<div class=\"userReleaseIdeaContent\">发布创意内容</div>"
            ,id: "userReleaseIdea"
        });
        element.tabChange('userDemo','userReleaseIdea');
        addEdit("ideaEdit");
    }
}
function callUserIdeas(element) {
    if($(".layui-tab-title li[lay-id='userIdeas']").length>0){
        element.tabChange('userDemo','userIdeas');
    }else{
        element.tabAdd('userDemo',{
            title: "我的创意"
            ,content: "<div class=\"userIdeasContent\">我的创意内容</div>"
            ,id: "userIdeas"
        });
        element.tabChange('userDemo','userIdeas');
    }
}
function callUserIdeasCapsules(element) {
    if($(".layui-tab-title li[lay-id='userIdeasCapsules']").length>0){
        element.tabChange('userDemo','userIdeasCapsules');
    }else{
        element.tabAdd('userDemo',{
            title: "创意胶囊"
            ,content: "<div class=\"userIdeasCapsulesContent\">我的创意胶囊</div>"
            ,id: "userIdeasCapsules"
        });
        element.tabChange('userDemo','userIdeasCapsules');
    }
}
function callUserGroups(element) {
    if($(".layui-tab-title li[lay-id='userGroups']").length>0){
        element.tabChange('userDemo','userGroups');
    }else{
        element.tabAdd('userDemo',{
            title: "我的团组"
            ,content: "<div class=\"userGroupsContent\">我的团组内容</div>"
            ,id: "userGroups"
        });
        element.tabChange('userDemo','userGroups');
    }
}
function callUserCollections(element) {
    if($(".layui-tab-title li[lay-id='userCollections']").length>0){
        element.tabChange('userDemo','userCollections');
    }else{
        element.tabAdd('userDemo',{
            title: "我的收藏"
            ,content: "<div class=\"userCollectionsContent\">我的收藏内容</div>"
            ,id: "userCollections"
        });
        element.tabChange('userDemo','userCollections');
    }
}
function callUserParticipate(element) {
    if($(".layui-tab-title li[lay-id='userParticipate']").length>0){
        element.tabChange('userDemo','userParticipate');
    }else{
        element.tabAdd('userDemo',{
            title: "我的参与"
            ,content: "<div class=\"userParticipateContent\">我的参与内容</div>"
            ,id: "userParticipate"
        });
        element.tabChange('userDemo','userParticipate');
    }
}
function callUserProfitProjects(element) {
    if($(".layui-tab-title li[lay-id='userProfitProjects']").length>0){
        element.tabChange('userDemo','userProfitProjects');
    }else{
        element.tabAdd('userDemo',{
            title: "获利项目"
            ,content: "<div class=\"userProfitProjectsContent\">获利项目内容</div>"
            ,id: "userProfitProjects"
        });
        element.tabChange('userDemo','userProfitProjects');
    }
}

function getUserInfo() {
    $.ajax({
        url:"idea/detail",
        type:"post",
        dataType: "json",
        data:{
            id: 1,
        },
        success:function (data) {
            var a="<div>Hello!</div>";
            $(".userInfoContent").append(a);
        },
        error:function () {
            console.log("读取数据失败！");
        }
    });
}

function addEdit(editId){
    layui.use('layedit', function(){
        var layedit = layui.layedit;
        layedit.build(editId); //建立编辑器
    });
}

