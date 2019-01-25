<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         <%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx }/css/amazeui.min.css" />
    <script src="${ctx }/js/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">编辑文章
        </strong><small></small></div>
    </div>
    <hr>
    <form id="blog_form" action="${ctx}/article_updata.action" method="post" enctype="multipart/form-data">
        <div class="edit_content">
            <div class="item1">
                <div>
                    <span>文章标题：</span>
                    <input type="text" class="am-form-field" name="article_title" value="<s:property value="article_title" />" style="width: 300px">&nbsp;&nbsp;
                </div>
            </div>

            <input type="text" name="article_desc" id="article_desc" value="<s:property value="article_desc" />">


            <div class="item1">
                <span>所属分类：</span>
                <select id="category_select" name="category.parentid" style="width: 150px">&nbsp;&nbsp;
                </select>

                <select id="skill_select" name="category.cid" style="width: 150px">&nbsp;&nbsp;
                </select>
            </div>

            <div class="item1 update_pic" >
                <span>摘要图片：</span>
                <img src="${ctx}/upload/<s:property value="article_pic" />" id="imageview" class="item1_img">
                <label for="fileupload" id="label_file">上传文件</label>
                <input type="file" name="upload" id="fileupload"/>
            </div>

            <div id="editor" name="article_content" style="width:900px;height:400px;"></div>
            <input type="hidden" id="resContent" value="<s:property value="article_content" />"/>
            <input type="hidden" name="article_id" value="<s:property value="article_id" />"/>
            <input type="hidden" name="article_pic" value="<s:property value="article_pic" />"/>
            <button class="am-btn am-btn-default" type="button" id="send" style="margin-top: 10px;">保存</button>
        </div>

    </form>

</div>
<script>
	$(function(){
			
			//加载分类
			$.ajax({
				type:"post",
				url:"${ctx}/category_getAllCategory.action",
				dataType:"json",
				data:{"parentid":"0"},
				success:function(data){
					var html= "";
					$.each(data,function(i,obj){
						html+="<option value='"+obj.cid+"'>"+obj.cname+"</option>"
					})
					$("#category_select").html(html);
					$("#category_select").val(<s:property value="category.parentid"/>)
				},
				error:function(data){
					
				}
			})
			
			$.ajax({
				type:"post",
				url:"${ctx}/category_getAllCategory.action",
				dataType:"json",
				data:{"parentid":<s:property value="category.parentid"/>},
				success:function(data){
					var html= "";
					$.each(data,function(i,obj){
						html+="<option value='"+obj.cid+"'>"+obj.cname+"</option>"
					})
					$("#skill_select").html(html);
					$("#skill_select").val(<s:property value="category.cid"/>)
				},
				error:function(data){
					
				}
			})
			//加载二级分类
			$("#category_select").on("change",function(){
				var cid = $(this).val();
				$.ajax({
					type:"post",
					url:"${ctx}/category_getAllCategory.action",
					dataType:"json",
					data:{"parentid":cid},
					success:function(data){
						var html= "";
						$.each(data,function(i,obj){
							html+="<option value='"+obj.cid+"'>"+obj.cname+"</option>"
						})
						$("#skill_select").html(html);
					},
					error:function(data){
						
					}
				})
			})
		
		$("#fileupload").change(function(){
				var URL = window.URL || window.webkitURL;
				var file = URL.createObjectURL($(this)[0].files[0]);
				$("#imageview").attr("src",file);
				$("#imageview").css({display:"block"})
			})
		//初始化富文本编辑器
		var ue = UE.getEditor('editor');
		ue.ready(function(){
			ue.setContent($("#resContent").val());
		})
		
		$("#send").click(function(){
			var txt = ue.getContentTxt();
			$("#article_desc").val(txt.substring(0,20)+"...");
			$("#blog_form").submit();
		})
		
		
		
	})
	

</script>
</body>
</html>