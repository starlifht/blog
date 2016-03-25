/**
 * Created by star on 16-3-22.
 */
function search(){
    var key=document.getElementById("search").value;
    location.href="/film/date/17/1?title="+encodeURI(key);
}