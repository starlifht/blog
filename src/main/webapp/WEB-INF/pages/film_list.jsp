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
    </style>
</head>
<body>

<%--<h1>Prototype</h1>--%>

<table class="table table-striped" id="film_list">


</table>
<nav style="text-align: center;margin: auto">
    <ul class="pagination" id="pages">

    </ul>
</nav>
<script>
    var MAX_LINE=18;
    $(document).ready(function() {
        getFilmList(this,0,MAX_LINE);
        $.ajax({
            type: 'GET',
            url: "film/getCount/"+parent.label,
            async: false,
            success: function (data, status) {
                this;
                if (status == "success") {
                    // alert(data.count);
                    var counts=Math.ceil(data.count/15);
                    //var content="<li class='asd'> <a href='javascript:void(0)'  aria-label='Previous'' disabled> <span aria-hidden='true'>&laquo;</span> </a> </li>";
                    var content="";
                    var i=1;
                    while (i<=counts){
                        content=content+"<li ><a  onclick=getFilmList(this,"+(i-1)*MAX_LINE+","+MAX_LINE+")>"+i+"</a></li>";
                        i=i+1;
                    }
                    $('#pages').html(content);

                        $('li').click(function () {
                            $("#pages li").attr('class','');
                            // 找出 li 中的超链接 href(#id)
                            $(this).attr('class','active');

                        });

                }
            }
        });
    });

    function getLocalTime(nS) {
        return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
    }
    function  getFilmList(obj,offset,limit){
        $.ajax({
            type: 'GET',
            url: "film/getAllByLimit/"+parent.label+"/"+offset+"/"+limit,
            // data: params,
            async: false,
            success: function (data, status) {
                this;
                if (status == "success") {
                    var data_list=data.data;
                   // var content='<tr><td>更新时间</td><td>豆瓣评分</td><td>电影名称</td></tr>';
                    var content='';
                    // alert(data.count);
                    for (var i=0;i<data_list.length;i++){
                        var time= data_list[i].datetime;
                        content=content+"<tr onclick=toContent("+data_list[i].id+")>" +
                                "<td>"+(time.year+1900)+"-"+(time.month+1)+"-"+time.date+" "+time.hours+":"+time.minutes+"</td>" +
                                "<td>豆瓣"+(data_list[i].rating).toFixed(1)+"</td>" +
                                "<td>"+data_list[i].title+"</td>" +
                                "</tr>";
                    }
                    $('#film_list').html(content);

                }
            }
        });
    }

    function toContent(id){
        parent.id=id;
        $.get('content', function (data) {
            $('#iframe',window.parent.document).html(data);
        });

    }

</script>
</body>

</html>
