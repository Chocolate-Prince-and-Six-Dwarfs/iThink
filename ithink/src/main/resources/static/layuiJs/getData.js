var user_id;
function getUserId() {
    $.ajax({
        url:"/user/getLoginId",
        type:"post",
        async:false,
        success:function (data) {
            user_id=data;
        },
        error:function () {
            console.log("读取用户Id失败.")
        }
    });
}

function getUserInfo(userId,form) {
    $.ajax({
        url:"/user/getById",
        type:"post",
        dataType: "json",
        data:{
            id: userId,
        },
        success:function (data) {
            $("#userImg").attr("src","data:image/png;base64,"+data.head);
            $("#userName").text(data.name);
            $("#userIndustry").text(data.industry);
            $("#srcimgurl").attr("src","data:image/png;base64,"+data.head);
            $(".user_name").val(data.name);
            $(".user_tel").val(data.phone);
            $(".user_industry").val(data.industry);
            $(".user_school").val(data.school);
            checkGender(data.sex,form);
            $(".user_desc").val(data.introduction);
            $(".user_addr").val(data.address);
        },
        error:function () {
            console.log("读取用户信息失败！");
        }
    });
}

function saveUserInfo(form,layer){
    $(document).on('click','#userInfoForm',function () {
        var formData=new FormData();
        formData.append('id',user_id);
        formData.append('name',$(".user_name").val());
        formData.append('phone',$(".user_tel").val());
        formData.append('address',$(".user_addr").val());
        formData.append('industry',$(".user_industry").val());
        formData.append('school',$(".user_school").val());
        formData.append('introduction',$(".user_desc").val());
        formData.append('sex',$('input:radio[name="sex"]:checked').val());
        $.ajax({
            url:"user/updateInfo",
            type:"post",
            dataType: "json",
            data:formData,
            processData: false,
            contentType: false,
            success:function () {
                //layer.msg("修改信息成功");
                form.render();
            },
            error:function () {
                layer.msg("修改信息失败");
            }
        });
    })
}

function releaseIdeaInfo(form,layer,layedit,editIndex){
    $(document).on('click','#releaseIdea',function () {
        var fData=new FormData();
        fData.append('userId',user_id);
        fData.append('topicTitle',$("#topicTitle").val());
        fData.append('content',layedit.getContent(editIndex));
        $.ajax({
            url:"/idea/publish",
            type:"post",
            data:fData,
            processData: false,
            contentType: false,
            success:function () {
                layer.msg("发布成功");
                window.location.reload();
            },
            error:function () {
                layer.msg("发布创意失败");
            }
        });
    })
}

function checkGender(mValue,form){
    var genderRadio = document.getElementsByName("sex");
    for(var i=0;i<genderRadio.length;i++){
        if(mValue==genderRadio[i].value){
            genderRadio[i].checked=true;
        }else{
            genderRadio[i].checked=false;
        }
        form.render();
    }
}