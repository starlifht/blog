/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=20;
var label;
var t="film";
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
                getFilmList(1,"getAllByLimit");
                selectLabel(label);//导航条颜色
            }
        }
    });
});
function  getFilmList(page,type){

    $.ajax({
        type: 'GET',
      //  url: "film/getAllByLimit/"+label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
        url: "film/"+type+"/"+label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                var data_list=data.data;
                var content='<thead><td onclick=getFilmList(1,"getAllByLimit")>时间排序</td><td onclick=getFilmList(1,"getAllByLimitRating")>评分排序</td>' +
                    '<td>国家</td><td>类型</td><td>片名</td></thead>';
                // alert(data.count);
                for (var i=0;i<data_list.length;i++){
                    var data=data_list[i];
                    var time=data.datetime.time;
                    var   d=new   Date(time);
                    var country;
                    var genres;
                    if(data.douban.hasOwnProperty("countries")){
                        country=data.douban.countries;
                    }
                    if(data.douban.hasOwnProperty("genres")){
                        genres=data.douban.genres;
                    }
                    content=content+"<tr>" +
                        "<td><small>"+formatDate(d)+"</small></td>" +
                        //"<td style='display: none'><img  src="+data.douban.image+"></td>" +
                        "<td><a target='_blank' href='"+data.douban.url+"'><small>豆瓣"+(data.douban.rating).toFixed(1)+"</small></a></td>" +
                            "<td><small>"+country+"</small></td>"+
                            "<td><small>"+genres+"</small></td>"+
                        "<td><a target='_blank' href='content?t="+t+"&id="+data.id+"&l="+label+"'>"+data.title+"</a></td>" +
                            //"<td><img src='"+data.douban.images.medium+"'></td>"
                        "</tr>";
                }
                $('#film_list').html(content);
            }
        }
    });

}



