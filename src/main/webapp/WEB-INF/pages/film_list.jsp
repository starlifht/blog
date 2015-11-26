<%--
  Created by IntelliJ IDEA.
  User: star
  Date: 15-11-18
  Time: 下午4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
    <style>
        html,body{
            width: 100%;
        }
        /*table tr:first-child{*/
            /*font-weight: 600;*/
        /*}*/
        table tr td:nth-child(2) {
            font-size: 15px;

            color: green;
        }
        table tr td:first-child{
            color: dimgray;
            font-size: 15px;
        }
        tr:hover{
            color: darkorange;

            /*font-weight: 600;*/
            /*color:  #337ab7;*/
        }
    </style>
</head>
<body>

<table class="table table-striped" id="film_list">


</table>
<nav style="text-align: center;margin: auto">
    <ul class="pagination" id="pages">

    </ul>
</nav>
<script type="text/javascript" src="statics/js/film_list.js"></script>

</body>

</html>
