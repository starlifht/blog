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
                    if (list[i-1].id!=0){
                        innerhtml=innerhtml+"<tr><td>"+i+"</td><td><a href='/film/content/"+list[i-1].id+"'>"+list[i-1].title+"</a></td></tr>";
                    }else{
                        innerhtml=innerhtml+"<tr><td>"+i+"</td><td>"+list[i-1].title+"</td></tr>";
                    }

                }
                $('#billboard').html(innerhtml);
                //document.getElementById('billboard').innerHTML=innerhtml;
            }}
    });
}