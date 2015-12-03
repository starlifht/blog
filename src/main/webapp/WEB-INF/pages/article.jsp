<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-12-2
  Time: 上午11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章列表</title>
</head>
<body>
<jsp:include page="/nav" flush="true"></jsp:include>
<div class="iframe">
    <table class="table table-striped" id="article_list">


    </table>
    <nav style="text-align: center;margin: auto">
        <ul class="pagination" id="pages">

        </ul>
    </nav>
</div>
<script type="text/javascript" src="statics/js/article.js"></script>

</body>
</html>
