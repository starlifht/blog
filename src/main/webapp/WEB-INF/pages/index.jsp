<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-11-18
  Time: 下午3:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  lang="zh-CN">
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="statics/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
    <link href="statics/css/index.css" rel="stylesheet" type="text/css" />

<body>
<nav class="navbar navbar-inverse" style="text-align: right">
    <span style="color: darkgray;font-weight: bold;font-size: 15px;line-height: 44px">
        Whatever is worth doing is worth doing well .</span>
</nav>


<div id="sidebar"  class="navbar" style="float: left; width: 17%;height: 100%; font-size: 18px">
    <ul class="nav nav-pills nav-stacked" >

        <li ><a class="active" target="hello">首页</a></li>
        <li class="dropdown"><a  href="javascript:;" id="about">最新电影</a>
      </li>
            <ul class="nav  nav-pills nav-stacked" style="font-size: 11px;" id="ch">
                <li ><a target="film_list?sdfsd=878" onclick="parent.label='xixi';">&nbsp;&nbsp;&nbsp;&nbsp;西西影视</a></li>
                <li><a target="film_list" onclick="parent.label='gaoqing';">&nbsp;&nbsp;&nbsp;&nbsp;中国高清</a></li>
            </ul>
        <li><a target="about">留言</a></li>
    </ul>
</div>


<div  class="container" id="iframe" style="float: right;width: 81%;margin-bottom: 30px" >

</div>

<div style="clear: both;text-align: center;color: gray;font-size: 14px">
    <span>Copyright @ 2015  All Rights Reserved </span>
</div>



    <script type="text/javascript" src="statics/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="statics/js/jquery.goup.min.js"></script>
    <script type="text/javascript" src="statics/js/index.js"></script>
<script type="text/javascript">
    var duoshuoQuery = {short_name:"wxgasd"};
    (function() {
        var ds = document.createElement('script');
        ds.type = 'text/javascript';ds.async = true;
        ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
        ds.charset = 'UTF-8';
        (document.getElementsByTagName('head')[0]
        || document.getElementsByTagName('body')[0]).appendChild(ds);
    })();
</script>

</body>
</html>
