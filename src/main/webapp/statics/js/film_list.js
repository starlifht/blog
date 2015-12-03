/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=20;
var label;
var type="film";
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
                getkkpager(1,data.count);
                getFilmList(1);
                selectLabel(label);//导航条颜色
            }
        }
    });
});
function  getFilmList(page){

    $.ajax({
        type: 'GET',
        url: "film/getAllByLimit/"+label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
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
                        "<td><a target='_blank' href='content?t="+type+"&id="+data.id+"&l="+label+"'>"+data.title+"</a></td>" +
                        "</tr>";
                }
                $('#film_list').html(content);
            }
        }
    });

}



