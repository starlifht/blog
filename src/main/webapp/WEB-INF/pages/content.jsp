<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-11-19
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CoolLily.com</title>
    <style>
        .title{
            margin-bottom: 30px;
        }
        .title p{
            font-size: 30px;
        }

    </style>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?ac9e03c4618198d8aff31e583d10f9fc";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body>
<jsp:include page="/nav"></jsp:include>
    <div class="iframe" >
        <div class="title">
            <p id="title"></p>
            <small id="publish_time"></small>
        </div>

        <div id="content"></div>
        <div id="comment-box"></div>
    </div>
<script>var duoshuoQuery = {short_name:"wxgasd"};</script>
<script src="http://static.duoshuo.com/embed.js"></script>
<script type="text/javascript" src="statics/js/content.js"></script>


</body>
</html>
