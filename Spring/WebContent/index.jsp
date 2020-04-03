<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>WebChat | 聊天</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="static/plugins/amaze/css/amazeui.min.css">
    <link rel="stylesheet" href="static/plugins/amaze/css/admin.css">
    <link rel="stylesheet" href="static/plugins/contextjs/css/context.standalone.css">
    <script src="static/plugins/jquery/jquery-2.1.4.min.js"></script>  
    <script src="static/plugins/amaze/js/amazeui.min.js"></script>
    <script src="static/plugins/amaze/js/app.js"></script>
    <script src="static/plugins/layer/layer.js"></script>
    <script src="static/plugins/laypage/laypage.js"></script>
    <script src="static/plugins/contextjs/js/context.js"></script>    
	<script type="text/javascript">
	function showMessageDiv(){
		document.getElementById("cheackmessageDiv").style.display="block";
	}
	function  doCheckmessage(friendid,isAgree) {
		document.getElementById("cheackmessageDiv").style.display="none";
		location.href="/Spring/doCheakMessage?friendid="+friendid+"&isAgree="+isAgree;
	}
	//好友信息
	function findFriendList(){
		$.post("/Spring/doFriendList",null,function(data){
		document.getElementById("friendlisth").innerHTML="好友列表["+data.friendCount+"]";
		var friendlist = data.friendList;
		var friendul="";
		for(var i = 0;i<friendlist.length;i++){
			var friend = friendlist[i];
			if(friend.messageCount == 0){
			friendul+="<ul class='am-list am-list-static am-list-striped' id='list'>"+
            "<li>"+friend.nickname+
            "<button type='button' class='am-btn am-btn-xs am-btn-primary am-round' onclick='readChatMessage("+friend.id+");'>"+
            "<span class='am-icon-phone'><span> 私聊</button></li>"+
            "</ul>";
			}else{
			 friendul+="<ul class='am-list am-list-static am-list-striped' >"+
             "<li>"+friend.nickname+" <button class='am-btn am-btn-xs am-btn-danger' id='tuling' data-am-button onclick='readChatMessage("+friend.id+")'>"+
             friend.messageCount+"</button></li>"+
         	 "</ul>";
			}
		}
		//清空
		$("#friendListdiv ul").remove();
		//显示好友
		$("#friendListdiv").append(friendul);
		//document.getElementById("friendSpan").innerHTML=friendul;
			
			
		});
	}
	//用ajax读取消息
	function readChatMessage(friendid){
		//判断是否有聊天对象
		if(isNaN(friendid) == false){ 
			$("#friendid").val(friendid);
			}
		 //$("#friendid").val(data.friend.id);
		var temp_friend=$("#friendid").val();
		if(temp_friend==''){
			return;
		}
		$.post("/Spring/doReadChatMessage",{"friendid":temp_friend},function(data){
		//location.href="/Spring/doReadChatMessage?friendid="+friendid;
			
			$("#sendto").html(data.friend.nickname);
			$("#friendid").val(data.friend.id);
			
			//聊天内容
			var messageList = data.chatMessageList;
			var messageInfo="";
			for(var i=0;i<messageList.length;i++){
				var message =messageList[i];
				
				if(message.fromid == data.user.id){
				messageInfo+="<li class='am-comment am-comment-flip am-comment-primary'>"+
                "<a href='#link-to-user-home'><img width='48' height='48' class='am-comment-avatar' alt='' src='/Spring/headimages/"+data.user.profilehead +"'></a>"+"<div class='am-comment-main'>"+
                "<header class='am-comment-hd'>"+"<div class='am-comment-meta'> "+  
                "<a class='am-comment-author' href='#link-to-user'>"+data.user.nickname +"</a> 发表于"+
                "<time>"+message.time +"</time> 发送给:"+data.friend.nickname  +"</div></header><div class='am-comment-bd'> <p>"+message.content +"</p></div></div></li>"        
				}else{
				messageInfo+="<li class='am-comment am-comment-primary'>"+
                "<a href='#link-to-user-home'>"+
                	"<img width='48' height='48' class='am-comment-avatar' alt='' src='/Spring/headimages/"+data.friend.profilehead +"'></a>"+
                "<div class='am-comment-main'>"+
                "<header class='am-comment-hd'>"+
                "<div class='am-comment-meta'> "+  
                "<a class='am-comment-author' href='#link-to-user'>"+data.friend.nickname +"</a> 发表于"+
                "<time>"+message.time +"</time> 发送给:"+data.user.nickname+"</div>"+
                "</header>"+
                "<div class='am-comment-bd'> <p>"+message.content+"</p></div>"+
                "</div></li>";
				}
			}
			$("#chat").html(messageInfo);
		});
	}
	//发送聊天消息的函数
	function sendMessage(){
		var friendid = $("#friendid").val();
		var content = $("#message").val();
		if(friendid == '' || content==''){
			return;
		}
		//提交聊天信息
		$.post("/Spring/dochatCheakMessage",{"friendid":friendid,"content":content},function(data){
			$("#message").val("");
		});
	}
	setInterval(findFriendList,5000);
	setInterval(readChatMessage,5000);
	//$("#friendListdiv").remove();
	</script>
</head>
<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand">
        <i class="am-icon-weixin"></i> <strong>WebChat</strong> <small>网页聊天室</small>
    </div>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                   ${user.nickname }
                   	<c:if test="${not empty messageCount and messageCount !=0 }">
                   	(<span onclick="showMessageDiv()">${messageCount }</span>)</c:if>                             
                   <span class="am-icon-caret-down"></span>
                </a>
                <div id="cheackmessageDiv" style="display:none;
                width:200px ;background-color:white;
                boder:solid 1px gray;position:absolute;
                z-index: 10;left:-100px;padding:5px; ">
                <c:forEach var="message" items="${messagelist }">
                ${message.content }
                <a href="javascript:doCheckmessage(${message.fromid },0)">拒绝</a> | 
                <a href="javascript:doCheckmessage(${message.fromid },1)">同意</a><br>
                </c:forEach>
                </div>
            </li>
        </ul>
    </div>
</header>
<div class="am-cf admin-main">
<!-- sidebar start -->
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <li><a href="index.jsp"><span class="am-icon-commenting"></span> 聊天</a></li>
            <li><a href="/Spring/getUserInfo" class="am-cf"><span class="am-icon-book"></span> 个人信息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-cogs"></span> 设置 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                    <li><a href="/Spring/infoSetting"><span class="am-icon-group"></span> 个人设置</a></li>
                    <li><a href="/Spring/doReadSpaceList"><span class="am-icon-cog"></span>心情动态</a></li>                 
                </ul>
            </li>
           <li><a href="/Spring/dofriend"><span class="am-icon-globe"></span>好友动态</a></li>        
            <li><a href="log.html"><span class="am-icon-inbox"></span> 系统日志<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href="login.html"><span class="am-icon-sign-out"></span> 注销</a></li>
        </ul>
        <div class="am-panel am-panel-default admin-sidebar-panel">
            <div class="am-panel-bd">
                <p><span class="am-icon-tag"></span> Welcome</p>
                <p>欢迎使用WebChat聊天室~</p>
            </div>
        </div>
    </div>
</div>
<!-- sidebar end -->
    <!-- content start -->
    <div class="admin-content"  >
        <div class="" style="width: 80%;float:left;">
            <!-- 聊天区 -->
            <div class="am-scrollable-vertical" id="chat-view" style="height: 310px;">
                <ul class="am-comments-list am-comments-list-flip" id="chat">
                
                <li class="am-comment am-comment-flip am-comment-primary">
                <a href="#link-to-user-home"><img width="48" height="48" class="am-comment-avatar" alt="" src="/Spring/headimages/${user.profilehead }"></a><div class="am-comment-main">
                <header class="am-comment-hd"><div class="am-comment-meta">   
                <a class="am-comment-author" href="#link-to-user">${user.nickname }</a> 发表于<time>${message.time }</time> 发送给:${friend.nickname } </div></header><div class="am-comment-bd"> <p>${message.content }</p></div></div></li>    
               
                
                <li class="am-comment am-comment-primary">
                <a href="#link-to-user-home">
                	<img width="48" height="48" class="am-comment-avatar" alt="" src="/Spring/headimages/${user.profilehead }"></a>
                <div class="am-comment-main">
                <header class="am-comment-hd">
                <div class="am-comment-meta">   
                <a class="am-comment-author" href="#link-to-user">${friend.nickname }</a> 发表于
                <time>${message.time }</time> 发送给:${user.nickname } </div>
                </header>
                <div class="am-comment-bd"> <p>${message.content }</p></div>
                </div></li> 
               
                 
                               
                </ul>
            </div>
            <!-- 输入区 -->
            <div class="am-form-group am-form">
                <textarea class="" id="message" name="message" rows="5"  placeholder="这里输入你想发送的信息..."></textarea> 
            	<input type="hidden" name="friendid" id="friendid">
            </div>
            <!-- 接收者 -->
            <div class="" style="float: left">
                <p class="am-kai">发送给 : <span id="sendto">赵丽丽</span></p>
            </div>
            <!-- 按钮区 -->
            <div class="am-btn-group am-btn-group-xs" style="float:right;"> 
                <button class="am-btn am-btn-default" type="button" onClick="sendMessage()"><span class="am-icon-commenting"></span> 发送</button>
            </div>
        </div>
        <!-- 列表区 -->
        <div id="friendListdiv" class="am-panel am-panel-default" style="float:right;width: 20%;">
            <div class="am-panel-hd">
                <h3 id="friendlisth" class="am-panel-title">好友列表 [0]</h3>
            </div>
	
        </div>
    </div>
    <!-- content end -->
</div>
<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <span class="am-icon-btn am-icon-th-list"></span>
</a>
<footer style="text-align: center">
    <hr>
<p class="am-padding-left">© 2016 <a href="http://www.qianchengzhidao.com">oracle北京实训基地</a>. </p>
</footer>


</body>
</html>
