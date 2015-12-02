/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=18;
var label;
function GetUrl() {

    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
$(document).ready(function() {
    var request=GetUrl();
     label= request['label'];
    $.ajax({
        type: 'GET',
        url: "film/getCount/"+label,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
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

                if ( $("#pages li a[name=1]").length>0){
                    $("#pages li a[name=1]").click();
                }else{
                    getFilmList(this,1);
                }
                selectLabel(label);//导航条颜色
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

function  getFilmList(obj,page){

    $.ajax({
        type: 'GET',
        url: "film/getAllByLimit/"+label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
        // data: params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                var data_list=data.data;
                var content='';
                // alert(data.count);
                for (var i=0;i<data_list.length;i++){
                    var data=data_list[i];
                    var time=data.datetime.time;
                    var   d=new   Date(time);
                    content=content+"<tr>" +
                        "<td><small>"+formatDate(d)+"</small></td>" +
                        "<td><a target='_blank' href='"+data.douban.url+"'><small>豆瓣"+(data.douban.rating).toFixed(1)+"</small></a></td>" +
                        "<td><a target='_blank' href='content?id="+data.id+"&label="+label+"'>"+data.title+"</a></td>" +
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



