function callUserNotices(element){
    if($(".layui-tab-title li[lay-id='userNotices']").length>0){
        element.tabChange('userDemo','userNotices');
        //getUserNotice(user_id);
    }else{
        element.tabAdd('userDemo',{
            title: "我的通知"
            ,content: "<div class=\"userNoticesContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "                <div style=\"text-align: center\">" +
                "                <h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "                </div></div>"
            ,id: "userNotices"
        });
        element.tabChange('userDemo','userNotices');
        getUserNotice(user_id);
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
            ,content: "<div class=\"userIdeasCapsulesContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "    <div style=\"text-align: center\"><h2 style=\"font-weight: bold;\">创意胶囊</h2></div>" +
                "    <hr style=\"margin-top: 1em\">" +
                "    <div style=\"text-align: right\"><button class=\"layui-btn layui-btn-radius layui-btn-sm layui-btn-primary addCapsule\">添加胶囊</button></div>" +
                "    <div class=\"capsuleList\">" +
                "    </div>" +
                "</div>"
            ,id: "userIdeasCapsules"
        });
        element.tabChange('userDemo','userIdeasCapsules');
        getUserCapsule(user_id);
    }
}
function callUserGroups(element) {
    if($(".layui-tab-title li[lay-id='userGroups']").length>0){
        element.tabChange('userDemo','userGroups');
    }else{
        element.tabAdd('userDemo',{
            title: "我的团组"
            ,content: "<div class=\"userGroupsContent\"><div class=\"team\">" +
                "                <div style=\"text-align: center\">" +
                "                <h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "                </div></div>"
            ,id: "userGroups"
        });
        element.tabChange('userDemo','userGroups');
        getUserGroups(user_id);
    }
}
function callUserCollections(element) {
    if($(".layui-tab-title li[lay-id='userCollections']").length>0){
        element.tabChange('userDemo','userCollections');
    }else{
        element.tabAdd('userDemo',{
            title: "我的收藏"
            ,content: "<div class=\"userCollectionsContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "<div style=\"text-align: center\">" +
                "<h1 style=\"font-weight: bold\">空空如也<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "</div>" +
                "</div>"
            ,id: "userCollections"
        });
        element.tabChange('userDemo','userCollections');
        getUserCollect(user_id);
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
                "                                        <input type=\"text\" id=\"investorName\" name=\"title\" required  lay-verify=\"required\" placeholder=\"请输入真实姓名\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <label class=\"layui-form-label\">身份证号码:</label>\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <input type=\"text\" id=\"investorIdcard\" name=\"tel\" required  lay-verify=\"required\" placeholder=\"身份证号码\" autocomplete=\"off\" class=\"layui-input\">\n" +
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
                "                                        <textarea name=\"desc\" placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-form-item\">\n" +
                "                                    <div class=\"layui-input-block\">\n" +
                "                                        <button class=\"layui-btn layui-btn-normal investorInfoBut\" style=\"background-color: #A4D3EE\"\n" +
                "                                                lay-submit>提交</button>\n" +
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
            ,content: "<div class=\"userFocusOnContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\">" +
                "<div style=\"text-align: center\">" +
                "<h1 style=\"font-weight: bold\">空空如也（该模块未加载）<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 50px; color: #696969;\"></i></h1>" +
                "</div>" +
                "</div>"
            ,id: "userFocusOn"
        });
        element.tabChange('userDemo','userFocusOn');
    }
}

