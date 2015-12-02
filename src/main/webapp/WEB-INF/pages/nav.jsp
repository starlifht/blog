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


<nav class="navbar navbar-inverse" style="text-align: right">
    <span style="color: darkgray;font-weight: bold;font-size: 15px;line-height: 44px">
        Whatever is worth doing is worth doing well .</span>
</nav>


<div id="sidebar"  class="sidebar">
    <ul>
        <li class="drdown">
            <a href="#" data-toggle="drdown">First Menu <i class="icon-arrow"></i></a>
            <ul class="drdown-menu">
                <li><a href="">Home</a></li>
                <li ><a  href="#">About Us</a></li>
                <li><a href="#">Services</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </li>
        <li class="drdown" >
            <a href="#" data-toggle="drdown">最新电影<i class="icon-arrow"></i></a>
            <ul class="drdown-menu show">
                <li><a name="gaoqing" href="/film_list?label=gaoqing">高清</a></li>
                <li><a name="xixi" href="/film_list?label=xixi">西西</a></li>
                <%--<li><a href="#">Services</a></li>--%>
                <%--<li><a href="#">Contact</a></li>--%>
            </ul>
        </li>
        <%--<li class="dropdown">--%>
            <%--<a href="#" data-toggle="dropdown">Third Menu <i class="icon-arrow"></i></a>--%>
            <%--<ul class="dropdown-menu">--%>
                <%--<li><a href="#">Home</a></li>--%>
                <%--<li><a href="#">About Us</a></li>--%>
                <%--<li><a href="#">Services</a></li>--%>
                <%--<li><a href="#">Contact</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    </ul>
    </div>
<script type="text/javascript" src="statics/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
<script type="text/javascript" src="statics/js/jquery.goup.min.js"></script>
<script type="text/javascript" src="statics/js/index.js"></script>

