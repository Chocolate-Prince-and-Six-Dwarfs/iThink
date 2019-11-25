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
    $(document).off('click','#userInfoForm').on('click','#userInfoForm',function () {
        if(($('#layui-form-item-username').val()!=='')&&($('#idcard').val()!=='')){
            layer.msg("申请成功");
        }
    });
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
            callUserParticipate(element);
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

