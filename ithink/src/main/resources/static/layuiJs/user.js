layui.define(['laypage','layer', 'form','jquery','element'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,laypage=layui.laypage
        ,$=layui.jquery
        ,element=layui.element;
    getUserId();//获取userId
    search(layer);//搜索
    getUserInfo(user_id,form);//获取用户数据
    saveUserInfo(form,layer);//保存个人信息
    $(document).off('click','#investorInfoBut').on('click','#investorInfoBut',function () {
        if(($('#investorName').val()!=='')&&($('#investorIdcard').val()!=='')){
            layer.msg("申请成功");
        }
    });
    deleteAndChangeIdea(layer);//个人创意修改，删除与创建团组
    releaseAndChangeCapsule(layer);//创意胶囊的发布与修改
    clickToViewOther();//粉丝界面点击头像跳转
    form.render();

    switch(location.hash) {
        case("#userDemo=userNotices"):
            callUserNotices(element);
            break;
        case("#userDemo=userReleaseIdea"):
            callUserReleaseIdea(element);
            break;
        case("#userDemo=userIdeas"):
            callUserIdeas(element,layer);
            break;
        case("#userDemo=userIdeasCapsules"):
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
        case("#userDemo=userFans"):
            callUserFans(element);
            break;
        case("#userDemo=userFocusOn"):
            callUserFocusOn(element);
            break;
        default:
            break;
    }
    exports('user', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
layui.use(['element','jquery','form'],function () {
    var element=layui.element
        ,$=layui.jquery
        ,form=layui.form
        ,layer=layui.layer;//导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){
        layer.msg(elem.text());
    });
    //触发动态操作tab事件
    var active={
        tabUserInfo:function () {
            if($(".layui-tab-title li[lay-id='userInfo']").length>0){//判断个人信息tab是否已经存在
                element.tabChange('userDemo','userInfo');
                getUserInfo(user_id,form);
                form.render();
            }else{
                element.tabAdd('userDemo',{
                    title: "个人信息"
                    ,content: "<div class=\"userInfoContent\"><fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 30px;\">\n" +
                        "                            <legend>个人信息</legend>\n" +
                        "                        </fieldset>\n" +
                        "                        <div id=\"table_and_page_div_id\" style=\"width: 80%; position: relative; left:20%;\">\n" +
                        "                            <div class=\"layui-form-item\">\n" +
                        "                                <label class=\"layui-form-label\">头像</label>\n" +
                        "                                <div class=\"layui-input-inline\">\n" +
                        "                                    <input type=\"text\" name=\"head\" lay-verify=\"required\" id=\"inputimgurl\" placeholder=\"图片地址\" value=\"/img/头像.png\" class=\"layui-input layui-hide\">\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-input-inline\">\n" +
                        "                                    <div class=\"layui-upload-list user-head\" style=\"margin:0\">\n" +
                        "                                        <img src=\"/img/头像.png\" id=\"srcimgurl\" class=\"layui-upload-img\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-input-inline layui-btn-container\" style=\"width: auto;\">\n" +
                        "                                    <button class=\"layui-btn layui-btn-radius layui-btn-warm\" id=\"editimg\">修改图片</button >\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-mid layui-word-aux\">头像的尺寸限定150x150px,大小在66kb以内</div>\n" +
                        "                            </div>\n" +
                        "\n" +
                        "                            <form class=\"layui-form\" action=\"\">\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">昵称:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"title\" required  lay-verify=\"required\" placeholder=\"请输入昵称\" autocomplete=\"off\" class=\"layui-input user_name\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">电话:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"tel\" required  lay-verify=\"required\" placeholder=\"***-****-****\" autocomplete=\"off\" class=\"layui-input user_tel\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">行业:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"major\" placeholder=\"行业领域\" autocomplete=\"off\" class=\"layui-input user_industry\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">学校:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"school\" placeholder=\"请输入学校\" autocomplete=\"off\" class=\"layui-input user_school\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">地区:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"text\" name=\"addr\" placeholder=\"请输入城市\" autocomplete=\"off\" class=\"layui-input user_addr\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <label class=\"layui-form-label\">性别:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <input type=\"radio\" name=\"sex\" value=\"男\" title=\"男\">\n" +
                        "                                        <input type=\"radio\" name=\"sex\" value=\"女\" title=\"女\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item layui-form-text\">\n" +
                        "                                    <label class=\"layui-form-label\">简介:</label>\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <textarea id=\"userEdit\" name=\"desc\" placeholder=\"请输入内容\" class=\"layui-textarea user_desc\"></textarea>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-form-item\">\n" +
                        "                                    <div class=\"layui-input-block\">\n" +
                        "                                        <button class=\"layui-btn layui-btn-normal\" id=\"userInfoForm\" style=\"background-color: #A4D3EE\"\n" +
                        "                                                lay-submit lay-filter=\"userForm\" type=\"submit\">保存信息</button>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </form>\n" +
                        "                        </div></div>"
                    ,id: "userInfo"
                });
                element.tabChange('userDemo','userInfo');
                getUserInfo(user_id,form);
                form.render();
            }
        }
        ,tabUserNotices:function () {
            callUserNotices(element);
        }
        ,tabUserReleaseIdea:function () {
            callUserReleaseIdea(element);
        }
        ,tabUserIdeas:function () {
            callUserIdeas(element,layer);
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
            //callUserParticipate(element);
        }
        ,tabUserProfitProjects:function () {
            callUserProfitProjects(element);
        },
        tabUserFans:function () {
            callUserFans(element);
        },
        tabUserFocusOn:function () {
            callUserFocusOn(element);
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

function addEdit(editId){
    layui.use('layedit', function(){
        var layedit = layui.layedit
            ,layer=layui.layer
            ,form=layui.form;
        var editIndex=layedit.build(editId,{tool: ['strong','italic','underline','del','|','left','center','right','link','unlink','face']}); //建立编辑器
        releaseIdeaInfo(form,layer,layedit,editIndex);
    });
}
var ideaId;//我的创意模块的修改创意时所需的创意id.
function deleteAndChangeIdea(layer){
    $(document).off('click','.deleteIdea').on('click','.deleteIdea',function () {
        var id=$(this).attr('ideaId');
        var loading=layer.load(2);
        $.ajax({
            url:"/idea/delete",
            type:"post",
            dataType: "json",
            data:{
                id:id,
            },
            success:function (data) {
                if(data==1){
                    layer.close(loading);
                    layer.msg("删除成功");
                    window.location.reload();
                }else{
                    layer.close(loading);
                    layer.msg("删除失败");
                    return false;
                }
            },
            error:function () {
                layer.close(loading);
                layer.msg("删除失败!");
            }
        });
    });
    $(document).off('click','.changeIdea').on('click','.changeIdea',function(){
        ideaId=$(this).attr('ideaId');
        layer.open({
            type: 2,
            area: ['70%', '90%'],
            title:"修改创意",
            fixed: false, //不固定
            maxmin: true,
            anim:5,
            isOutAnim:true,
            resize:false,
            btn:['修改创意','返回'],
            btnAlign: 'c',
            content: '/changeIdea',
            success:function(layero, index){
                //var body = layer.getChildFrame('body',index);
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var ideaId=parent.ideaId;
                //console.log(id);
                iframeWin.addIframeEdit(ideaId,1);
            },
            yes:function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var ideaId=parent.ideaId;
                var userId=parent.user_id;
                iframeWin.update(ideaId,userId,layer,index);
                //setTimeout(function(){ window.location.reload(); }, 1500);
            },
            btn2:function(index){
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            },
            cancel:function (index) {
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            }
        });
    });
    $(document).off('click','.createGroup').on('click','.createGroup',function(){
        var idea_id=$(this).attr('ideaId');
        var group_name=getNoticeTopicName(idea_id);
        group_name=group_name.substring(0,20)+"的聊天室";
        var idList=new Array();
        idList[0]=user_id;
        idList[1]=user_id;
        $.ajax({
            url:'/chat/addToGroup',
            type:'post',
            data: {
                topicId:idea_id,
                name:group_name,
                userIdList:idList,
                ownerId:user_id,
            },
            success:function (data) {
                if(data==0){
                    layer.msg("团组已创建");
                }else{
                    layer.msg("创建团组成功");
                }
            },
            error:function () {
                layer.msg("网络错误，创建团组失败");
            }
        });
    });
}

function releaseAndChangeCapsule(layer) {
    $(document).off('click','.addCapsule').on('click','.addCapsule',function () {
        layer.open({
            type: 2,
            area: ['70%', '90%'],
            title:"添加创意胶囊",
            fixed: false, //不固定
            maxmin: true,
            anim:5,
            isOutAnim:true,
            resize:false,
            btn:['添加创意胶囊','返回'],
            btnAlign: 'c',
            content: '/changeIdea',
            success:function(layero,index){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.addIframeEdit(-1,0);
            },
            yes:function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var userId=parent.user_id;
                iframeWin.addIframeEdit(userId,2);
                //setTimeout(function(){ window.location.reload(); }, 1500);
            },
            btn2:function(index){
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            },
            cancel:function (index) {
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            }
        });
    });
    $(document).off('click','.changeCapsule').on('click','.changeCapsule',function () {
        var capsuleId=$(this).attr('capsuleId');
        layer.open({
            type: 2,
            area: ['70%', '90%'],
            title:"修改创意胶囊",
            fixed: false, //不固定
            maxmin: true,
            anim:5,
            isOutAnim:true,
            resize:false,
            btn:['修改创意胶囊','返回'],
            btnAlign: 'c',
            content: '/changeIdea',
            success:function(layero,index){
                var iframeWin = window[layero.find('iframe')[0]['name']];
                //console.log("capsuleId:"+capsuleId);
                iframeWin.addIframeEdit(capsuleId,3);
            },
            yes:function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                iframeWin.addIframeEdit(capsuleId,4);
                //setTimeout(function(){ window.location.reload(); }, 1500);
            },
            btn2:function(index){
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            },
            cancel:function (index) {
                if(confirm('确定要关闭么?(如果关闭不会保存您的修改)')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index);
                }
                return false;
            }
        });
    });
    $(document).off('click','.releaseCapsule').on('click','.releaseCapsule',function () {
        var capsule_id=$(this).attr('capsuleId');
        var title=getCapsuleTitle(capsule_id);
        var capsuleContent=getCapsuleContent(capsule_id);
        $.ajax({
            url:'/idea/publish',
            type:'post',
            data: {
                topicTitle:title,
                capsuleId:capsule_id,
                userId:user_id,
                content:capsuleContent,
            },
            success:function (data) {
                if(data==null){
                    layer.msg("发布创意失败");
                }else{
                    layer.msg("发布创意成功");
                }
            },
            error:function () {
                layer.msg("网络错误,发布创意失败");
            }
        });
    });
}

function getCapsuleTitle(capsuleId) {
    var content;
    $.ajax({
        url:'capsule/getById',
        type:'post',
        data: {
            id:capsuleId,
        },
        async:false,
        success:function (data) {
            content=data.data.name;
        },
        error:function () {
            console.log("获取创意胶囊标题失败");
        }
    });
    return content;
}
function getCapsuleContent(capsuleId) {
    var content;
    $.ajax({
        url:'capsule/getById',
        type:'post',
        data: {
            id:capsuleId,
        },
        async:false,
        success:function (data) {
            content=data.data.content;
        },
        error:function () {
            console.log("获取创意胶囊内容失败");
        }
    });
    return content;
}

function releaseIdeaInfo(form,layer,layedit,editIndex){//发布创意
    $(document).off('click','#releaseIdea').on('click','#releaseIdea',function () {
        var fData=new FormData();
        var topicTitle=htmlEscape($("#topicTitle").val());
        if(topicTitle.length>=26){
            layer.msg("创意标题过长");
            return false;
        }
        fData.append('userId',user_id);
        fData.append('topicTitle',topicTitle);
        fData.append('content',layedit.getContent(editIndex));
        if($("#topicTitle").val()==null||$("#topicTitle").val()==""){
            layer.msg("创意标题不能为空");
            return false;
        }
        if(layedit.getContent(editIndex)==null||layedit.getContent(editIndex)==""){
            layer.msg("创意内容不能为空");
            return false;
        }
        $("#topicTitle").val("");
        layedit.setContent(editIndex, "",false);
        var loading=layer.load(2);
        $.ajax({
            url:"/idea/publish",
            type:"post",
            data:fData,
            processData: false,
            contentType: false,
            success:function (data) {
                layer.close(loading);
                layer.msg("发布成功");
                window.location.href="/viewIdea?ideaId="+data;
            },
            error:function () {
                layer.close(loading);
                layer.msg("发布创意失败");
            }
        });
    });
}

function clickToViewOther() {
    $(document).off('click','.clickUserFans').on('click','.clickUserFans',function () {
        var userId=$(this).attr('userId');
        window.location.href='/otherUser?userId='+userId;
    });
}