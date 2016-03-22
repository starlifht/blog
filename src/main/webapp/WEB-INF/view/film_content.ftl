<!DOCTYPE html>
<html lang="zh">
<head>
<#include "*/nav.ftl">

    <title>${title}</title>
</head>
<body>
<div class="iframe" >
    <div >
        <h1 >${title}</h1>
        <small ><span class="label label-default">发布时间 ${datetime?string("yyyy-MM-dd HH:mm:ss")}</span></small>
    </div>

    <div>${content}</div>
    <div class="ds-thread" data-thread-key="${id}" data-title="${title}" data-url="/film/content/${id}"></div></div>
</body>
<#include "*/foot.ftl">

<script type="text/javascript">
    var duoshuoQuery = {short_name:"coollily"};
    (function() {
        var ds = document.createElement('script');
        ds.type = 'text/javascript';ds.async = true;
        ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
        ds.charset = 'UTF-8';
        (document.getElementsByTagName('head')[0]
        || document.getElementsByTagName('body')[0]).appendChild(ds);
    })();
</script>
</html>