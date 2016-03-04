<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-11-30
  Time: 下午1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <link href="statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="statics/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
    <link href="statics/css/index.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="statics/images/logo.ico" type="image/vnd.microsoft.icon">
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
                    <a name="all" href="/film_list">全部资源</a>
                </li>
                <li><a name="gq" href="/film_list?label=gq&type=date">720P/1080P</a></li>
                <li>
                    <a name="ai" href="/film_list?label=ai&type=date">电影分享</a>
                </li>
            </ul>
        </li>
        <%--<li class="drdown">--%>
            <%--<a href="/article" data-toggle="drdown">文章列表<i class="icon-arrow"></i></a>--%>
            <%--<ul class="drdown-menu">--%>
                <%--<li><a href="/article">Home</a></li>--%>
                <%--<li ><a  href="#">About Us</a></li>--%>
                <%--<li><a href="#">Services</a></li>--%>
                <%--<li><a href="#">Contact</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>


    </ul>
    <div class="input-group" >
        <input id="search" type="text" class="form-control" placeholder="电影名称" onkeypress="if(event.keyCode==13) {search();return false;}"/>
        <span class="input-group-btn">
            <button class="btn btn-info" type="button" onclick="search()" style="height: 34px">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
        </span>
    </div>
    </div>

<script type="text/javascript" src="statics/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
<script type="text/javascript" src="statics/js/jquery.goup.min.js"></script>
<script type="text/javascript" src="statics/js/index.js"></script>