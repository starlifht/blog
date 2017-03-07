<!DOCTYPE html>
<html lang="zh">
<head>
<#include  "*/nav.ftl">
<#import "*/side.ftl" as side>
<#import "*/page.ftl" as pager>
    <title>最新影视|电影|高清|蓝光|1080P|720P|迅雷|BT|磁力链接|下载</title>
    <meta name="description" content="每天关注提供720p高清、1080p高清、蓝光原盘高清、高清3d高清、高清mv最新热门bt种子磁力链迅雷下载网站，是下载了解高清电影天堂！"/>
    <meta name="keywords" content="电影天堂、高清电影下载、1080p高清电影下载、高清电影、720p、1080p、蓝光原盘、磁力链、电影下载"/>

</head>
<body>
<div class="iframe" id="iframe">
    <div style="font-size: smaller;margin-bottom: 20px">
    <ul class="nav nav-tabs filmlist-tab" role="tablist">
        <li role="presentation"  name="date" data-toggle="tab"><a onclick="window.location.href='${base}/film/date/18/1';" href="#">默认排序</a></li>
        <li role="presentation" name="rating" data-toggle="tab"><a onclick="window.location.href='${base}/film/rating/18/1';" href="#">评分排序</a></li>
        <li role="presentation" name="year" data-toggle="tab"><a onclick="window.location.href='${base}/film/year/18/1'" href="#">年份排序</a></li>
    </ul>
    </div>
<#--<table class="table table-striped" id="film_list">-->
    <#--<thead><td onclick="location.href='/film/date/17/1'">更新时间</td>-->
    <#--<td onclick="location.href='/film/rating/17/1'">豆瓣</td>-->
    <#--<td>年份</td>-->
    <#--<td>国家</td><td>电影名称</td><td>类型</td></thead>-->
<div style="margin: auto">
    <#list list as filmlist>
        <#--<tr >-->
            <#assign douban=filmlist.douBanInfo/>

        <#--&lt;#&ndash;<#assign json=filmlist.douban?eval />&ndash;&gt;-->
            <#--&lt;#&ndash;<#assign douban=filmlist.douBanInfo?eval/>&ndash;&gt;-->
            <#--<td>${filmlist.datetime?string("yyyy-MM-dd")}</td>-->

            <#--<td> <a  target="_blank" href="${douban.url}" > ${douban.rating?string("0.0")}</a></td>-->

            <#--<td>${douban.year}</td>-->
            <#--<td>${douban.country?split("/")[0]}</td>-->
            <#--<td>-->
                <div class="media " onclick="location.href='${base}/film/content/${douban.id}'" >
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object img-rounded" src="${douban.images?replace('lpst','spst')}" alt="...">
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${douban.subject}</h4>

                       <h6><span class="label  label-success ">豆瓣评分</span>&nbsp;&nbsp;<span  class="rating">${douban.rating?string("0.0")}</span></h6>
                        <h5><span>${douban.year}</span>&nbsp;${douban.country}</h5>

                       <#--<h6><label >导演&nbsp;</label>${douban.directors}</</h6>-->
                       <#--<h6><label >主演&nbsp;</label>${douban.casts}</h6>-->
                       <h6>${douban.genres}</h6>
                       <#--<h6><label >国家&nbsp</label>${douban.country}</h6>-->
                        <#--<span style="">主演：${douban.casts}</span><br>-->
                        <#--别名：${douban.aka}<br>-->
                    </div>
                </div>

                <#--<a target="_blank" href="/film/content/${filmlist.id}"> ${douban.subject}</a>-->
            <#--</td>-->
            <#--</td>-->
            <#--<td>${douban.genres}</td>-->

        <#--</tr>-->
    </#list>



</div>
    <div class="page">
    <@pager.p url="${base}/film/${label}" pageno=pageNo totalpage=totalpage pagesize=pageSize></@pager.p>

    </div>
</div>
<@side.p flag="${base}" params="${base}"></@side.p>



</body>
<#include "*/foot.ftl">
</html>