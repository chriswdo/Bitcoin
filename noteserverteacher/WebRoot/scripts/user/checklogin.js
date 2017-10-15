//检查用户是否登录
var token = getCookie("token");
if(token==null){//未登录
   window.location.href="log_in.html";
}
