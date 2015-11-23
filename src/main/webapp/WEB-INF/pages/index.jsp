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

       body{
            height: 100%;
            width: 100%;
            min-width: 1200px;
           /*min-height: 900px;*/
           /*overflow:scroll*/
        }
        li span{

        }
        li a{
            font-weight: bold;
            cursor:pointer;
       }
        tr{
            cursor: pointer;
        }
        tr:hover{
            color: darkorange;
            /*color:  #337ab7;*/
        }
    </style>
</head>
<body style="">
<nav class="navbar navbar-inverse" style="text-align: right">
    <span style="color: darkgray;font-weight: bold;font-size: 15px;line-height: 44px">
        Whatever is worth doing is worth doing well .</span>
</nav>


<div class="navbar" style="float: left; width: 17%;height: 100%; ">
    <ul class="nav nav-pills nav-stacked" >

        <li ><a class="active" target="home">首页</a></li>
        <li class="dropdown"><a  href="javascript:;" id="about">最新电影</a>
      </li>
            <ul class="nav  nav-pills nav-stacked" style="font-size: 11px;" id="ch">
                <li ><a target="film_list" onclick="parent.label='xixi';">&nbsp;&nbsp;西西影视</a></li>
                <li><a target="film_list" onclick="parent.label='gaoqing';">&nbsp;&nbsp;中国高清</a></li>
            </ul>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
</div>


<div  class="container" id="iframe" style="float: right;height: 100%;width: 81%" >

</div>
</body>
<footer>
    <script type="text/javascript" src="statics/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
<script>
    var id=1;
    var label='';
    $(document).ready(function() {
        $.get("home", function (data) { //初始將a.html include div#iframe
            $("#iframe").html(data);
        });
        $('#about').click(function(){
          //  alert('sdf');
            if ($('#ch').css('display')=='none'){
                $('#ch').css('display','');

            }else{
                $('#ch').css('display','none');
            }
        });
        $(function () {
            $('li').click(function () {
                $("li").attr('class','');
                // 找出 li 中的超链接 href(#id)
                $(this).attr('class','active');
                var $this = $(this),
                        _clickTab = $this.find('a').attr('target'); // 找到链接a中的targer的值
                if (typeof(_clickTab)!='undefined'){
                    $.get(_clickTab, function (data) {
                        $("#iframe").html(data);
                    });
                }


            });
        });
    });
</script>


</footer>
</html>
