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
    <title>内容</title>
</head>
<body>
<div >

    <div>
        <h1 id="title"></h1>
    </div>

    <div id="content"></div>
</div>
</body>
<footer>
    <script>

        $(document).ready(function () {
           getContent(parent.id);
        });
        function  getContent(id){
            $.ajax({
                type: 'GET',
                url: "film/getByPriKey/"+id,
                // data: params,
                async: false,
                success: function (data, status) {
                    this;
                    if (status == "success") {
                        $('#title').html(data.data.title);
                        $('#content').html(data.data.content);
                    }
                }
            });
        }



    </script>
</footer>
</html>