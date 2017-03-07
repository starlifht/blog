<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<#include "*/nav.ftl">
<#import "*/side.ftl" as side>
<#assign douban=filminfo.douBanInfo/>


    <meta name="description" content="${douban.subject}/${douban.aka}"/>
    <meta name="keywords" content="${douban.genres}"/>
    <title>${douban.subject}</title>
    <style>

        h1 {
            text-align: center;
        }

        .doubanInfo > div {
            margin-bottom: 10px;

        }
    </style>
</head>
<body>
<div class="iframe">
<#--<button type="button" class="btn btn-link" onclick="window.location.href='${base}'">返回首页</button>-->
<div >
    <div class="doubanInfo" style="float:left;width: 65%" >
        <div class="row">
            <div class="col-md-1">
                <span class="label label-danger">片名</span>
            </div>
            <div class="col-md-10">
                <span style="font-weight: bolder;font-size: 30px;color: #0f0f0f">${douban.subject}</span></div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-success">豆瓣</span>
            </div>
            <div class="col-md-10">
                <a href="${douban.url}" target="_blank"
                   style="    font-family: Georgia, serif;    color: #FF4500;
font-weight: bolder;font-size: 26px">${douban.rating?string("0.0")}</a>
                （${douban.ratingcount}人评价）
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-warning">导演</span>
            </div>
            <div class="col-md-10">
                <span>${douban.directors} </span></div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-default">演员</span>
            </div>
            <div class="col-md-10">
                <span>${douban.casts} </span>
            </div>
        </div>

        <div class="row">
            <div class="col-md-1">
                <span class="label label-primary">上映</span>
            </div>
            <div class="col-md-10">
                <span> ${douban.releasedate} </span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-default">时长</span>
            </div>
            <div class="col-md-10">
                <span>  ${douban.runtime}  </span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-default">国家</span>
            </div>
            <div class="col-md-10">
                <span> ${douban.country}   </span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <span class="label label-default">类型</span>
            </div>
            <div class="col-md-10">
                <span>   ${douban.genres} </span>
            </div>
        </div>
    <#if douban.aka!="">
        <div class="row">
            <div class="col-md-1">
                <span class="label label-info">别名</span>
            </div>
            <div class="col-md-10">
                <span>   ${douban.aka} </span>
            </div>
        </div>

    </#if>


        <div class="row">
            <div class="col-md-1">
                <span class="label label-danger">IMDB</span>
            </div>
            <div class="col-md-10">
                    <span><a target="_blank" href="http://www.imdb.com/title/${douban.imdb}">${douban.imdb}</a>
                    </span>
            </div>
        </div>


    </div>

    <div style="float: left;width: 15%;margin-left: 20px">
        <img class="media-object img-rounded" src="${filminfo.douBanInfo.images}">
    </div>
</div>

        <div style="clear: both;padding-top:30px">
            <p><span class="label label-danger">内容简介</span></p>
            <span style="text-indent:2em">

            ${douban.summary}
       </span>
        </div>
        <div style="padding-top:30px">
            <p>
                <span class="label label-primary" style="margin-bottom: 30px">下载地址</span>
            </p>
            <div style="text-indent:2em">${filminfo.content}</div>


        </div>


<div class="ds-thread" data-thread-key="${douban.id}" data-alt="${douban.subject}" data-url="${base}/film/content/${douban.id}"></div>

</div>
<@side.p flag="${base}" params="${base}"></@side.p>

</body>
<#include "*/foot.ftl">

<script type="text/javascript">
    var duoshuoQuery = {short_name: "coollily"};
    (function () {
        var ds = document.createElement('script');
        ds.type = 'text/javascript';
        ds.async = true;
        ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
        ds.charset = 'UTF-8';
        (document.getElementsByTagName('head')[0]
        || document.getElementsByTagName('body')[0]).appendChild(ds);
    })();
</script>
</html>