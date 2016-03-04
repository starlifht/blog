/**
 * Created by star on 15-11-26.
 */
var MAX_LINE=19;
var label;
var title;
var type;
$(document).ready(function() {
    var request=GetUrl();
     label= request['label'];
    title=request['title'];
    type=request['type'];
    if(typeof (title)=='undefined'){
        title='';
    }
    if(typeof (label)=='undefined'){
        label='all';
    }
    if(typeof (type)=='undefined'){
        type='date';
    }
    var params={
        title:title
    }
    $.ajax({
        type: 'GET',
        url: "film/getCount/"+label,
        data:params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                getkkpager(1,data.count,type);
                getFilmList(1);
                selectLabel(label);//导航条颜色
            }
        }
    });
});


function  getFilmList(page){

    var params={
        title:title
    }
    $.ajax({
        type: 'GET',
        url: "film/"+type+"/"+label+"/"+(page-1)*MAX_LINE+"/"+MAX_LINE,
        data:params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                var data_list=data.data;
                var content='<thead><td onclick=getFilmList(1,"date")>时间</td><td onclick=getFilmList(1,"rating")>豆瓣评分</td>' +
                    '<td>国家/类型</td><td>片名</td></thead>';
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
                        "<td><a target='_blank' href='"+data.douban.url+"'><small>"+(data.douban.rating).toFixed(1)+"</small></a></td>" +
                            "<td><small>["+country+"]</small><br><small>"+genres+"</small></td>"+
                            //"<td><small>"+genres+"</small></td>"+
                        //"<td><img src='"+data.douban.images.small+"'></td>"+

                    "<td><a target='_blank' href='content?t=film&id="+data.id+"&l="+label+"'>"+data.title+"</a></td>" +
                        "</tr>";
                }
                $('#film_list').html(content);
            }
        }
    });

}



