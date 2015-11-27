/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=18;
$(document).ready(function() {

    $.ajax({
        type: 'GET',
        url: "film/getCount/"+parent.label,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                // alert(data.count);
                var counts=Math.ceil(data.count/15);
                var content="";
                var i=1;
                while (i<counts){
                    content=content+"<li ><a name='"+i+"' onclick=getFilmList(this,"+i+")>"+i+"</a></li>";
                    i=i+1;
                }
                if (counts>1){
                    var content="<li> <a onclick='getPre()' >&laquo; </a> </li>"
                        +content
                        +"<li><a  onclick='getNext()'>&raquo;</a></li>";
                    $('#pages').html(content);
                }

               // getFilmList(this,1);
                if ( $("#pages li a[name=1]").length>0){
                    $("#pages li a[name=1]").click();
                }else{
                    getFilmList(this,1);
                }

            }
        }
    });
});
function  getPre(){
    var page=$('#pages li[class=active]>a').attr('name');
    page=page-1;
    $("#pages li a[name="+page+"]").click();
}
function  getNext(){

    var page=$('#pages li[class=active]>a').attr('name');
    page=parseInt(page)+1;
    $("#pages li a[name="+page+"]").click();
}
function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
}
function  getFilmList(obj,page){

    $.ajax({
        type: 'GET',
        url: "film/getAllByLimit/"+parent.label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
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
                $("#pages li ").attr('class','');
                // 找出 li 中的超链接 href(#id)
                $(obj).parent().attr('class','active');
            }
        }
    });

}

function toContent(id) {
    parent.id = id;
    $.get('content', function (data) {
        $('#iframe', window.parent.document).html(data);
    });
}