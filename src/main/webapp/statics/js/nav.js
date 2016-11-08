/**
 * Created by star on 16-3-22.
 */
function search(){
    var key=document.getElementById("search").value;
    location.href="/film/date/18/1?title="+encodeURI(key);
}
$(document).ready(function() {
    $('#search_img').bind('click',function(event){
      if(!$('#search_input').hasClass('show')) {
          $('#search_input').addClass('show');
          $('#search_input').removeClass('hidden');

          event.preventDefault();
      }
      else {
          $('#search_input').removeClass('show');
          $('#search_input').addClass('hidden');
          event.preventDefault();
      }
  });
    getBillboard();//  get  billboard  list
});

function getBillboard(){

    $.ajax({
        type: 'GET',
        url: "/billboard/get",
        // data:params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                 var list=data.data;
                var innerhtml="";
                for (var i=1;i<=list.length;i++){
                    var filmtitle='';
                    if (list[i-1].id!=0){
                            filmtitle=   "<td><b><a target='_blank' href='/film/content/"+list[i-1].id+"'>"+list[i-1].title+"</a></b>" ;
                    }else{
                            filmtitle=  "<td><a href='https://movie.douban.com/subject/"+list[i-1].doubanid+"' target='_blank'>"+list[i-1].title+"</a>";
                    }
                    innerhtml=innerhtml+"<tr><td><small>"+i+"</small></td>" +filmtitle
                        + "<span>"+list[i-1].rating.toFixed(1)+"</span></td></tr>";


                }
                $('#billboard').html(innerhtml);
            }}
    });
}