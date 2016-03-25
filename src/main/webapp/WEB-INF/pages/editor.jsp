<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-12-1
  Time: 下午3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <alt>Editor</alt>
    <link href="statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />


    <%--<link href="statics/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>--%>
    <%--<link href="statics/css/summernote-bs3.css" rel="stylesheet" type="text/css"/>--%>
    <style>

        /*@import url('http://cdn.gbtags.com/twitter-bootstrap/3.2.0/css/bootstrap.css');*/
        @import url('http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css');
        @import url('http://cdn.gbtags.com/summernote/0.5.2/summernote.css');

        /*.panel{*/
            /*height: 50%;*/
        /*}*/
    </style>
</head>
<body>
<div>
    <input type="text" class="form-control" id="alt" >
</div>
<div id="editor">
    输入内容&hellip;
</div>
<button onclick="addArticle()">保存</button>
<script type="text/javascript" src="statics/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="statics/js/bootstrap.min.js"></script>
<script type="text/javascript" src="statics/js/summernote.min.js"></script>
<script>
    $('#editor').summernote();
     function addArticle(){
         $.ajax({
             type: 'POST',
             url: "article/add",
             contentType: "application/json", //必须有
             dataType: "json", //表示返回值类型，不必须
             data: JSON.stringify({ 'alt': $('#alt').val(), 'content': $(".panel-body").html() }),
             async: false,
             success: function (jsonResult) {

             },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
                 alert(XMLHttpRequest.status);
                 alert(XMLHttpRequest.readyState);
                 alert(textStatus);
             }
         });
     }
</script>
</body>

</html>
