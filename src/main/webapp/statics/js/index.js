/**
 * Created by star on 15-11-26.
 */


function GetUrl() {

    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
$(document).ready(function() {


    $.goup({
        trigger: 100,
        bottomOffset: 20,
        locationOffset: 10,
        title: 'TOP',
        goupSpeed: 'fast',
        titleAsText: true,

   });


});
function formatDate(d) {
    var D=['00','01','02','03','04','05','06','07','08','09']
    with (d || new Date) return [
        [getFullYear(), D[getMonth()+1]||getMonth()+1, D[getDate()]||getDate()].join('-'),
        [D[getHours()]||getHours(), D[getMinutes()]||getMinutes(), D[getSeconds()]||getSeconds()].join(':')
    ].join(' ');
}
function selectLabel(label){
    $("a[name='"+label+"']").css("background-color","#f6f6f6")//
        .css("color","#2c89c6")
        .text('>>'+ $("a[name='"+label+"']").text());
}
var dropdown = document.querySelectorAll('.drdown');
var dropdownArray = Array.prototype.slice.call(dropdown,0);
dropdownArray.forEach(function(el){
    var button = el.querySelector('a[data-toggle="drdown"]'),
        menu = el.querySelector('.drdown-menu'),
        arrow = button.querySelector('i.icon-arrow');

    button.onclick = function(event) {
        if(!menu.hasClass('show')) {
            menu.classList.add('show');
            menu.classList.remove('hide');
            arrow.classList.add('open');
            arrow.classList.remove('close');
            event.preventDefault();
        }
        else {
            menu.classList.remove('show');
            menu.classList.add('hide');
            arrow.classList.remove('open');
            arrow.classList.add('close');
            event.preventDefault();
        }
    };
})

Element.prototype.hasClass = function(className) {
    return this.className && new RegExp("(^|\\s)" + className + "(\\s|$)").test(this.className);
};