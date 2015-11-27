/**
 * Created by star on 15-11-26.
 */
var id=1;
var label='';

$(document).ready(function() {
    $.goup({
        trigger: 100,
        bottomOffset: 20,
        locationOffset: 20,
        title: 'TOP',
        goupSpeed: 'fast',
        titleAsText: true
    });
    $.get("hello", function (data) { //初始將a.html include div#iframe
        $("#iframe").html(data);
    });
    $('#about').click(function(){
        //  alert('sdf');
        if ($('#ch').css('display')=='none'){
            $('#ch').css('display','');

        }else{
            $('#ch').css('display','none');
        }
    });
    $(function () {
        $('li').click(function () {
            window.scrollTo(0,0);
            $("li").attr('class','');
            // 找出 li 中的超链接 href(#id)
            $(this).attr('class','active');
            var $this = $(this),
                _clickTab = $this.find('a').attr('target'); // 找到链接a中的targer的值
            if (typeof(_clickTab)!='undefined'){
                $.get(_clickTab, function (data) {
                        $("#iframe").html(data);
                    }
                );
            }


        });
    });
});