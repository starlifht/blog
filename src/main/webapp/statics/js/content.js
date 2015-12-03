/**
 * Created by star on 15-11-26.
 */

$(document).ready(function () {
    var request=GetUrl();
    var id=request['id'];
    var label=request['l'];
    var type=request['t'];
    getContent(type,id);
    selectLabel(label);

});
function getContent(type,id) {
    $.ajax({
        type: 'GET',
        url: type+"/getByPriKey/" + id,
        // data: params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                $('title').text(data.data.title);
                $('#title').html(data.data.title);
                $('#publish_time').text(formatDate(new  Date(data.data.datetime.time)));
                $('#content').html(data.data.content);
               // alert(window.location.href);
                toggleDuoshuoComments('#comment-box',type+id,window.location.href);
            }
        }
    });
}

function toggleDuoshuoComments(container,id,url){
    var el = document.createElement('div');//该div不需要设置class="ds-thread"
    el.setAttribute('data-thread-key', id);//必选参数
    el.setAttribute('data-url', url);//必选参数
    el.setAttribute('data-author-key', 'star');//可选参数
    DUOSHUO.EmbedThread(el);
    jQuery(container).append(el);
}