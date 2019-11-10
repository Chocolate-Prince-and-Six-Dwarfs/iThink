function callUserNotices(element){
    if($(".layui-tab-title li[lay-id='userNotices']").length>0){
        element.tabChange('userDemo','userNotices');
    }else{
        element.tabAdd('userDemo',{
            title: "我的通知"
            ,content: "<div class=\"userNoticesContent\"  style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"notice\">\n" +
                "    <h3>我的通知</h3>\n" +
                "    <hr>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul class='list'>\n" +
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
                "    </div>\n" +
                "</div></div>"
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
            ,content: "<div class=\"userReleaseIdeaContent\"><div class=\"put\">\n" +
                "    <input type=\"text\" placeholder=\"请输入标题，最多10字\">\n" +
                "    <textarea id=\"demo\" style=\"display: none;\"></textarea>\n" +
                "</div>\n" +
                "<button class='put-button'>发布创意</button></div>"
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
            ,content: "<div class=\"userIdeasContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"collection\">\n" +
                "    <h3>我的创意</h3>\n" +
                "    <hr>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100喜欢</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100关注</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100伤实现</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100喜欢</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100关注</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100伤实现</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100喜欢</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100关注</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;100伤实现</span>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n</div>"
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
            title: "获利项目"
            ,content: "<div class=\"userProfitProjectsContent\" style=\"width: 60%; position: relative; left:20%;right: 20%\"><div class=\"collection\">\n" +
                "    <h3>我的获利</h3>\n" +
                "    <hr>\n" +
                "    <div class=\"layui-tab-content\">\n" +
                "        <ul>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12结束</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;获利：100 元</span>\n" +
                "\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12结束</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;获利：100 元</span>\n" +
                "\n" +
                "                </div>\n" +
                "            </li>\n" +
                "            <hr>\n" +
                "            <li >\n" +
                "                <div class=\"title\">&nbsp;&nbsp;&nbsp;标题标题标题标题</div>\n" +
                "                <div>\n" +
                "                    <span>2019-12-12结束</span>\n" +
                "                    <span>&nbsp;&nbsp;&nbsp;获利：100 元</span>\n" +
                "\n" +
                "                </div>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n</div>"
            ,id: "userProfitProjects"
        });
        element.tabChange('userDemo','userProfitProjects');
    }
}

function callUserFans() {

}