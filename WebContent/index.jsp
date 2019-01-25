<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
     
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="header.jsp" %>
<style>
    .content_item {
        height: auto;
        min-height:160px;
        position: relative;
    }

    .img-rounded {
        position: absolute;
        right: 10px;
        top: 10px;
        width: 250px;
        height: 140px;
    }

</style>
<!-- 内容区 -->
<section class="layout main-wrap  content">
    <section class='col-main'>

        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="tab">
                        <%--分类信息--%>
                        <div id="lk_blog_two" class="container">
                            <div class="row" id="categorylist">
                                
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <!--博客社区-->
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" class="container">
                            <div class="row">
                                <ul class="blog-list" id="content">
                                    
                                </ul>
                                <!--分页-->
    							<div id="page" class="page_div"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

</section>
<footer id="lk_footer">
    <div class="container">
        <div class="footer-top">
          <!--分页-->
        </div>
        <div class="footer-bottom col-sm-offset-2 hidden-sm hidden-xs">
            <ul>
                <li><a href="">学科报名</a></li>
                <li><a href="">师资团队</a></li>
                <li><a href="">线上公开课</a></li>
                <li><a href="">联络我们</a></li>
                <li><a href="">支持我们</a></li>
                <li><a href="">沪ICP备 18026591号-1</a></li>
            </ul>
        </div>
    </div>
</footer>



<script>
$(function(){
	
	function getParans(key){
		var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null){
			return unescape(r[2]);
		}
		return null;
	}
	var parentid = getParans("cid");
	if(parentid != null){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/web_getcategory.action",
			dataType:"json",
			data:{"parentid":parentid},
			success:function(data){
				//console.log(data)
				var html="";
				$.each(data,function(i,obj){
					html+='<button class="btn-tag categoryBtn" cid="'+obj.cid+'" >'+obj.cname+'</botton>'
				})
				$("#categorylist").html(html);
				
			},
			error:function(data){
				
			}
		})
	}
	
	$("body").on("click",".categoryBtn",function(){
		getPage(parentid,$(this).attr("cid"),1);
	})
	
	
	function getPage(parentid,cid,num){
    $.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/web_getarticle.action",
		dataType:"json",
		data:{
			"parentid":parentid,
			"cid":cid,
			"currPage":num
			},
		success:function(data){
			//console.log(data)
			var html="";
			$.each(data.list,function(i,obj){
				html+="<li class='content_item'>"
               		+	"<div class='blog-list-left' style='float:left;'>"
               		+		"<div class='main-title'><a href='detail.jsp?id="+obj.article_id+"'>"+obj.article_title+"</a></div>"
               		+ 		"<p class='sub-title'>"+obj.article_content+"</p>"
                	+		"<div class='meta'>"+new Date(obj.article_time).toLocaleString()+"</div>"
           			+ 	"</div>"
           			if(obj.article_pic != null && obj.article_pic != ""){
           				html+=	"<img src='${pageContext.request.contextPath}/upload/"+obj.article_pic+"'  class='img-rounded'>"
           			}
           			html+="</li>"
			})
			$("#content").html(html);
			//分页
			$("#page").paging({
		    	pageNo:data.currentPage,
		        totalPage:data.totalPage ,
		        totalSize: data.totalCount,
		        callback: function(num) {
		        	getPage(parentid,cid,num)
		        }
		    });
		},
		error:function(data){
			
		}
	})
	
	}
	getPage(parentid,null,1);
})
</script>

</body>
</html>