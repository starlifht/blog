<!DOCTYPE html>
<html lang="zh">
<head>
<#include "*/nav.ftl">
<#import "*/side.ftl" as side>
<#assign douban=filminfo.douBanInfo/>
    <meta name="description" content="${douban.subject}/${douban.aka}" />
    <meta name="keywords" content="${douban.genres}" />
    <title>${douban.subject}</title>
    <style>
        .label{
            margin-right: 10px;
        }
        h1{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="iframe" >
    <#--<div >-->

<#--<p class="label label-default">更新时间 ${filminfo.datetime?string("yyyy-MM-dd HH:mm:ss")} </p>-->
    <#--</div>-->
    <div>
        <div style="float: left">
            <img src="${filminfo.douBanInfo.images?eval.large}">
        </div>
        <div style="float: left;margin-left: 50px; max-width: 64%">

            <p><span class="label label-danger">片名</span><span style="font-weight: bolder;font-size: 25px">${douban.subject}</span></p>
            <p><span class="label label-success">豆瓣</span>
                <a href="${douban.url}" target="_blank" style="font-weight: bolder;font-size: 23px">${douban.rating?string("0.0")}</a>
                （${douban.ratings_count}人评价）
            </p>

            <p><span class="label label-warning">导演</span>${douban.directors} </p>
            <p> <span class="label label-default">演员</span>${douban.casts}</p>
            <p><span class="label label-default">年份</span>${douban.year}</p>
            <p><span class="label label-default">国家</span>${douban.country}</p>
            <p><span class="label label-default">类型</span>${douban.genres}</p>
            <p><span class="label label-info">别名</span>${douban.aka}</p>


        </div>
        <div style="clear: both;padding-top:30px">
            <p><span class="label label-danger">内容简介</span></p>
       <p style="text-indent:2em ">
       ${douban.summary}
       </p>
        </div>
        <div style="padding-top:30px">
            <p  >
                <span class="label label-primary" style="margin-bottom: 30px">下载地址</span></p>
            <span style="text-indent:2em">${filminfo.content}</span>


        </div>
    </div>


    <#--<div>${filminfo.content}</div>-->
    <div class="ds-thread" data-thread-key="${filminfo.id}" data-alt="${douban.subject}" data-url="/film/content/${filminfo.id}"></div></div>
<@side.p flag="sdfs"></@side.p>

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