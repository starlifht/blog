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
        /*table tr:first-child{*/
            /*font-weight: 600;*/
        /*}*/


        table tr td:last-child{
            cursor: pointer;
        }
       table tr td:last-child:hover{
            color: darkorange;

            /*font-weight: 600;*/
            /*color:  #337ab7;*/
        }
    </style>

</head>
<body>

<jsp:include page="/nav" flush="true"></jsp:include>
<div class="iframe">
    <table class="table table-striped" id="film_list">


    </table>
    <nav style="text-align: center;margin: auto">
        <ul class="pagination" id="pages">

        </ul>
    </nav>
</div>


<script type="text/javascript" src="statics/js/film_list.js"></script>

</body>

</html>
