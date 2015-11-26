/**
 * Created by star on 15-11-26.
 */


$(document).ready(function () {
    getContent(parent.id);

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
                //$('#SOHUCS').attr('sid', id);
                //getSohuc();
            }
        }
    });
}

function toggleDuoshuoComments(container){
    var el = document.createElement('div');//该div不需要设置class="ds-thread"
    el.setAttribute('data-thread-key', 'sdfs');//必选参数
    el.setAttribute('data-url', 'qw');//必选参数
    el.setAttribute('data-author-key', 'ds');//可选参数
    DUOSHUO.EmbedThread(el);
    jQuery(container).append(el);
}