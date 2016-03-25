<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?ac9e03c4618198d8aff31e583d10f9fc";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

<link href="/statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/statics/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="/statics/css/nav.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="/statics/images/logo.ico" type="image/vnd.microsoft.icon">
<nav class="navbar navbar-default" id="navbar" >
 <span style="color: darkgray;font-weight: bold;font-size: 15px;width: 100%;line-height: 45px">
        Whatever is worth doing is worth doing well .</span>
</nav>
<div id="sidebar"  class="sidebar">
    <ul>
        <li class="drdown" >
            <a href="#" data-toggle="drdown">最新电影<i class="icon-arrow"></i></a>
            <ul class="drdown-menu show">
                <li>
                    <a name="all" href="/film/date/17/1">全部资源</a>
                </li>

            </ul>
        </li>
        <#--<%--<li class="drdown">--%>-->
        <#--<%--<a href="/article" data-toggle="drdown">文章列表<i class="icon-arrow"></i></a>--%>-->
        <#--<%--<ul class="drdown-menu">--%>-->
            <#--<%--<li><a href="/article">Home</a></li>--%>-->
            <#--<%--<li ><a  href="#">About Us</a></li>--%>-->
            <#--<%--<li><a href="#">Services</a></li>--%>-->
            <#--<%--<li><a href="#">Contact</a></li>--%>-->
            <#--<%--</ul>--%>-->
        <#--<%--</li>--%>-->


    </ul>
    <div class="input-group" >
        <input id="search" type="text" class="form-control" placeholder="电影名称" onkeypress="if(event.keyCode==13) {search();return false;}"/>
        <span class="input-group-btn">
            <button class="btn btn-info" type="button" onclick="search()" style="height: 34px">
                <span  aria-hidden="true">搜</span>
            </button>
        </span>
    </div>
</div>