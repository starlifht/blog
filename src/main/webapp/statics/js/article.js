/**
 * Created by star on 15-12-2.
 */
/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=18;
var type="article";
$(document).ready(function() {

    $.ajax({
        type: 'GET',
        url: "article/getCount",
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                var counts=Math.ceil(data.count/15);
                var content="";
                var i=1;
                while (i<counts){
                    content=content+"<li ><a name='"+i+"' onclick=getList(this,"+i+")>"+i+"</a></li>";
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
                    getList(this,1);
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

function  getList(obj,page){

    $.ajax({
        type: 'GET',
        url: "article/get/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                var data_list=data.data;
                var content='';
                for (var i=0;i<data_list.length;i++){
                    var data=data_list[i];
                    var time=data.datetime.time;
                    var   d=new   Date(time);
                    content=content+"<tr>" +
                        "<td><small>"+formatDate(d)+"</small></td>" +
                        "<td><a target='_blank' href='content?t="+type+"&id="+data.id+"'>"+data.title+"</a></td>" +
                        "</tr>";
                }
                $('#article_list').html(content);
                $("#pages li ").attr('class','');
                // 找出 li 中的超链接 href(#id)
                $(obj).parent().attr('class','active');
            }
        }
    });

}



