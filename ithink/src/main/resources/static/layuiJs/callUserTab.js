function callUserNotices(element){
    if($(".layui-tab-title li[lay-id='userNotices']").length>0){
        element.tabChange('userDemo','userNotices');
        //getUserNotice(user_id);
    }else{
        element.tabAdd('userDemo',{
            title: "我的通知"
            ,content: "<div class=\"userNoticesContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "<div style=\"text-align: center\">" +
                "                <h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "                </div></div>"
            ,id: "userNotices"
        });
        element.tabChange('userDemo','userNotices');
        //getUserNotice(user_id);
    }
}
function callUserReleaseIdea(element){
    if($(".layui-tab-title li[lay-id='userReleaseIdea']").length>0){
        element.tabChange('userDemo','userReleaseIdea');
    }else{
        element.tabAdd('userDemo',{
            title: "发布创意"
            ,content: "<div class=\"userReleaseIdeaContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"put\">\n" +
                "    <input lay-verify=\"required\" id=\"topicTitle\" type=\"text\" placeholder=\"请输入标题\">\n" +
                "    <textarea lay-verify=\"required\" id=\"ideaEdit\" style=\"display: none;\"></textarea>\n" +
                "</div>\n" +
                "<button class=\"put-button\" type=\"button\" id=\"releaseIdea\">发布创意</button></div>"
            ,id: "userReleaseIdea"
        });
        element.tabChange('userDemo','userReleaseIdea');
        addEdit("ideaEdit");
    }
}
function callUserIdeas(element,layer) {
    if($(".layui-tab-title li[lay-id='userIdeas']").length>0){
        element.tabChange('userDemo','userIdeas');
        getUserIdeas(layer,user_id);//获取个人创意信息
    }else{
        element.tabAdd('userDemo',{
            title: "我的创意"
            ,content: "<div class=\"userIdeasContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "<div style=\"text-align: center\">" +
                "<h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "</div>" +
                "</div>"
            ,id: "userIdeas"
        });
        element.tabChange('userDemo','userIdeas');
        getUserIdeas(layer,user_id);//获取个人创意信息
    }
}
function callUserIdeasCapsules(element) {
    if($(".layui-tab-title li[lay-id='userIdeasCapsules']").length>0){
        element.tabChange('userDemo','userIdeasCapsules');
    }else{
        element.tabAdd('userDemo',{
            title: "创意胶囊"
            ,content: "<div class=\"userIdeasCapsulesContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"idea\">\n" +
                "    <h3>创意胶囊</h3>\n" +
                "    <hr>\n" +
                "    <a href=\"Put.html\"><button id=\"add\" data-src=\"Put.html\">添加胶囊</button></a>\n" +
                "    <div>\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <p class=\"content\">内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>\n" +
                "                <div class=\"foot\">\n" +
                "                    <span class=\"time\">2019-12-12</span>\n" +
                "                    <a href=\"Put.html\"> <button class=\"revise\">修改胶囊</button><a/>\n" +
                "                </div>\n" +
                "\n" +
                "            </li>\n" +
                "            <li>\n" +
                "                <p class=\"content\">内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>\n" +
                "                <div class=\"foot\">\n" +
                "                    <span class=\"time\">2019-12-12</span>\n" +
                "                    <a href=\"Put.html\"><button class=\"revise\">修改胶囊</button></a>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <li>\n" +
                "                <p class=\"content\">内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n" +
                "                    内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>\n" +
                "                <div class=\"foot\">\n" +
                "                    <span class=\"time\">2019-12-12</span>\n" +
                "                    <a href=\"Put.html\"><button class=\"revise\">修改胶囊</button></a>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n</div>"
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
            ,content: "<div class=\"userGroupsContent\"><div class=\"team\">\n" +
                "<div class=\"bgc\">\n" +
                "    <h3>我的团组</h3>\n" +
                "    <hr>\n" +
                "    <p>未完待续....</p>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n</div>"
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
            ,content: "<div class=\"userCollectionsContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"collection\">\n" +
                "    <h3>我的收藏</h3>\n" +
                "    <hr>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <img src=\"/img/头像small.png\" alt=\"\">\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100点赞</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100收藏</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100评论</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <img src=\"/img/头像small.png\" alt=\"\">\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100点赞</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100收藏</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100评论</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <img src=\"/img/头像small.png\" alt=\"\">\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100点赞</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100收藏</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100评论</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n</div>"
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
            ,content: "<div class=\"userParticipateContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"right\">\n" +
                "    <div class=\"layui-tab layui-tab-card\">\n" +
                "        <ul class=\"layui-tab-title layui-bg-cyan\">\n" +
                "            <li class=\"layui-this\">我的项目</li>\n" +
                "            <li>别人的项目</li>\n" +
                "\n" +
                "        </ul>\n" +
                "        <div class=\"layui-tab-content \">\n" +
                "            <div class=\"layui-tab-item layui-show\">\n" +
                "                <div class=\"collection\">\n" +
                "                    <ul style=\"border: 1px solid #c0c0c0;\">\n" +
                "                        <li >\n" +
                "                            <div class=\"title\">&nbsp;&nbsp;&nbsp;标题 </div>\n" +
                "                            <div>\n" +
                "                                <p>\n" +
                "                                    <span class=\"state\">正在进行中...</span>\n" +
                "                                    <span>&nbsp;&nbsp;&nbsp;100参与中</span>\n" +
                "                                    <button>进入团组</button>\n" +
                "                                </p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                        <hr>\n" +
                "                        <li >\n" +
                "                            <div class=\"title\">&nbsp;&nbsp;&nbsp;标题 </div>\n" +
                "                            <div>\n" +
                "                                <p>\n" +
                "                                    <span class=\"state\">已结束！！！</span>\n" +
                "                                    <span>&nbsp;&nbsp;&nbsp;100参与中</span>\n" +
                "                                </p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"layui-tab-item\">\n" +
                "                <div class=\"collection\">\n" +
                "                    <ul style=\"border: 1px solid #c0c0c0;\">\n" +
                "                        <li >\n" +
                "                            <div class=\"title\">&nbsp;&nbsp;&nbsp;标题 </div>\n" +
                "                            <div>\n" +
                "                                <p>\n" +
                "                                    <img src=\"/img/头像small.png\" alt=\"\">\n" +
                "                                    <span class=\"state\">正在进行中...</span>\n" +
                "                                    <span>&nbsp;&nbsp;&nbsp;100参与中</span>\n" +
                "                                    <button>进入团组</button>\n" +
                "                                </p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                        <hr>\n" +
                "                        <li >\n" +
                "                            <div class=\"title\">&nbsp;&nbsp;&nbsp;标题 </div>\n" +
                "                            <div>\n" +
                "                                <p>\n" +
                "                                    <img src=\"/img/头像small.png\" alt=\"\">\n" +
                "                                    <span class=\"state\">已结束！！！</span>\n" +
                "                                    <span>&nbsp;&nbsp;&nbsp;100参与中</span>\n" +
                "                                </p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n</div>"
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
            title: "投资人认证"
            ,content: "                    <div class=\"UserProfitProjects\" style=\"width: 60%; position: relative; left:20%;right: 20%\">\n" +
                "                        <fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 30px;\">\n" +
                "                            <legend>投资人认证</legend>\n" +
                "                        </fieldset>\n" +
                "                            <form class=\"layui-form\">\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">真实姓名:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" id='layui-form-item-username' name=\"title\" required  lay-verify=\"required\" placeholder=\"请输入真实姓名\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">身份证号码:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" id='idcard' name=\"tel\" required  lay-verify=\"required\" placeholder=\"身份证号码\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">联系电话:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" name=\"major\" placeholder=\"联系方式\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">所在公司:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" name=\"school\" placeholder=\"请输入公司\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">职位:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" name=\"addr\" placeholder=\"请输入职位名\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item layui-form-text\">\n" +
                "                                    <label class=\"layui-form-label\">自我描述:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <textarea id=\"userEdit\" name=\"desc\" placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <button class=\"layui-btn layui-btn-normal\" id=\"userInfoForm\" style=\"background-color: #A4D3EE\"\n" +
                "                                                lay-submit lay-filter=\"userForm\">提交</button>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </form>\n" +
                "                        </div>\n" +
                "                </div>\n"
            ,id: "userProfitProjects"
        });
        element.tabChange('userDemo','userProfitProjects');
    }
}
function callUserFans(element) {
    if($(".layui-tab-title li[lay-id='userFans']").length>0){
        element.tabChange('userDemo','userFans');
        getUserFans(user_id);
    }else{
        element.tabAdd('userDemo',{
            title: "我的粉丝"
            ,content: "<div class=\"userFansContent\" style=\"width: 60%; position: relative; left:20%;\">\n" +
                "                                <div style=\"text-align: center\">\n" +
                "                                <h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>\n" +
                "                                </div></div>"
            ,id: "userFans"
        });
        element.tabChange('userDemo','userFans');
        getUserFans(user_id);
    }
}
function callUserFocusOn(element) {
    if($(".layui-tab-title li[lay-id='userFocusOn']").length>0){
        element.tabChange('userDemo','userFocusOn');
    }else{
        element.tabAdd('userDemo',{
            title: "我的关注"
            ,content: "<div class=\"userFocusOnContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">我的关注</div>"
            ,id: "userFocusOn"
        });
        element.tabChange('userDemo','userFocusOn');

    }
}

