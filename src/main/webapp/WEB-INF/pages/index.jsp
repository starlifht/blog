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

    <style>
        li{
            font-weight: bold;
        }
    </style>
</head>
<body style="min-width: 1200px;overflow:scroll">


<div class="navbar" style="float: left; width: 15%;height: 100%; background-image: url('statics/images/debut_dark_@2X.png');">
    <ul class="nav nav-pills nav-stacked" >
        <li class="active"><a target="home">Homesdfsfsdfdsfsdfsdfsfffffffffffff</a></li>
        <li><a target="hello">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
</div>


<div  class="container" id="iframe" style="float: right;height: 100%;width: 85%" >

</div>
</body>
<footer>
    <script type="text/javascript" src="statics/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
<script>

    $(document).ready(function() {
        $.get("home", function (data) { //初始將a.html include div#iframe
            $("#iframe").html(data);
        });
        $(function () {
            $('li').click(function () {
                $(".navbar li").attr('class','');
                // 找出 li 中的超链接 href(#id)
                $(this).attr('class','active');
                var $this = $(this),
                        _clickTab = $this.find('a').attr('target'); // 找到链接a中的targer的值
                $.get(_clickTab, function (data) {
                    $("#iframe").html(data);
                });
            });
        });
    });
</script>
</footer>
</html>
