<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/pageStyle.css">
    <script src="js/jquery.min.js"></script>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>parentid</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>
	<s:iterator value="category" var="category">
    	<ul class="list_goods_ul">
        	<li class="parentid"><s:property value="#category.parentid"/></li>
        	<li class="cname"><s:property value="#category.cname"/></li>
        	<li>
        		<a href="#" class="category_updata">
        			<input type="hidden" name="cid" value="<s:property value="#category.cid"/>"/>
        			<img class="img_icon" src="images/edit_icon.png" alt="">
        		</a>
        	</li>
        	<li><a href="#" class="category_delete"><img class="img_icon" src="images/delete_icon.png" alt=""></a></li>
    	</ul>
	</s:iterator>
</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="images/delete_icon.png" alt=""></div>
    <form action="${pageContext.request.contextPath}/category_add.action" method="post">
	    <div class="edit_content">
	        <div class="item1">
	            <div>
	                <span>添加分类：</span>
	            </div>
	        </div>
	        
	        <div class="item1">
	            <div>
	            	<span>parentid：</span>
	                <input type="text" class="am-form-field" id="parentid" name="parentid" >&nbsp;&nbsp;
	                <br/>
	                <span>分类名称：</span>
	                <input type="text" class="am-form-field" id="cname" name="cname">&nbsp;&nbsp;
	                <br/>
	            </div>
	        </div>
	         
	        <div class="item1">
	            <!-- <button class="am-btn am-btn-default" type="button" id="category">添加</button> -->
	           	<input type="submit" value="添加" style="height:35px;color:#000;border:1px solid #ccc"/>
	        </div>
	       
	    </div>
    </form>
</div>
<div id="modal_content1">
    <div id="close1"><img src="images/delete_icon.png" alt=""></div>
	    <div class="edit_content1">
	        <div class="item2">
	            <div>
	                <span>修改分类：</span>
	            </div>
	        </div>
	        
	        <div class="item2">
	            <div>
	            	<input type="hidden" class="" id="cid1" >
	            	<span>parentid：</span>
	                <input type="text" class="am-form-field" id="parentid1" name="parentid" >&nbsp;&nbsp;
	                <br/>
	                <span>分类名称：</span>
	                <input type="text" class="am-form-field" id="cname1" name="cname">&nbsp;&nbsp;
	                <br/>
	            </div>
	        </div>
	         
	        <div class="item2">
	            <!-- <button class="am-btn am-btn-default" type="button" id="category">添加</button> -->
	           	<input type="submit" value="修改" id="update" style="height:35px;color:#000;border:1px solid #ccc"/>
	        </div>
	       
	    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
        $("#close1").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content1").fadeOut();
        });
        $(".category_updata").on("click",function(){
        	$("#modal_view").fadeIn();
            $("#modal_content1").fadeIn();
            var cid = $(this).parents(".list_goods_ul").find("input[name=cid]").val();
            
            $.ajax({
        		url:"${pageContext.request.contextPath}/"+"category_categoryone.action",
        		type:"post",
        		data:{
        			"cid":cid
        		},
        		dataType:"json",
        		success:function(data){
        			/* console.log(data) */
        			if(data.length>0){
        				$("#parentid1").val(data[0].parentid);
        				$("#cname1").val(data[0].cname);
        				$("#cid1").val(data[0].cid);
        			}
        		},
        		error:function(data){
        			console.log(data)
        		}
        	})
        })
        
        //修改
        $("#update").on("click",function(){
        	var parentid = $("#parentid1").val();
        	var cname = $("#cname1").val();
        	var cid = $("#cid1").val();
        	
        	$(window).attr("location",'${pageContext.request.contextPath}/category_update.action?parentid='+parentid+'&cname='+encodeURI(encodeURI (cname))+'&cid='+cid)
        })
        //删除
        $(".category_delete").on("click",function(){
        	 var cid = $(this).parents(".list_goods_ul").find("input[name=cid]").val();
        	 var parentid = $(this).parents(".list_goods_ul").find(".parentid").text();
        	 var cname = $(this).parents(".list_goods_ul").find(".cname").text();
        	 //console.log("--"+cid+"="+parentid+"="+cname+"--")
        	$(window).attr("location",'${pageContext.request.contextPath}/category_delete.action?&cid='+cid)
        })
        
        
        
        
    });
    
</script>
</body>
</html>