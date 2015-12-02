/**
 * Created by star on 15-11-26.
 */

var id;
$(document).ready(function () {
    var request=GetUrl();
    id=request['id'];
    label=request['label'];
    getContent(id);
    selectLabel(label);

});
function getContent(id) {
    $.ajax({
        type: 'GET',
        url: "film/getByPriKey/" + id,
        // data: params,
        async: false,
        success: function (data, status) {
            this;
            if (status == "success") {
                $('#title').html(data.data.title);
                $('#content').html(data.data.content);
               // alert(window.location.href);
                toggleDuoshuoComments('#comment-box',id,window.location.href);
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