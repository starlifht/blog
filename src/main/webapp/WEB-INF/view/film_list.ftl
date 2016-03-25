<!DOCTYPE html>
<html lang="zh">
<head>

<#include "*/nav.ftl">
<#import "*/page.ftl" as pager>

    <title>最新影视</title>
</head>
<body>
<div class="iframe" id="iframe">
<table class="table table-striped" id="film_list">
    <thead><td onclick="location.href='/film/date/17/1'">发布时间</td>
    <td onclick="location.href='/film/rating/17/1'">豆瓣</td>
    <td>国家</td><td>类型</td><td>片名</td></thead>
    <#list list as filmlist>
        <tr>
            <#assign douban=filmlist.douBanInfo/>

        <#--<#assign json=filmlist.douban?eval />-->
            <#--<#assign douban=filmlist.douBanInfo?eval/>-->
            <td><small>${filmlist.datetime?string("yyyy-MM-dd HH:mm")}</small></td>
            <td><a target="_blank" href="${douban.url}" >${douban.rating?string("0.0")}</a></td>
            <td><small> ${douban.country?split("/")[0]}</small></td>
            <td><small>${douban.genres}</small></td>
            <td><a target="_blank" href="/film/content/${filmlist.id}">${douban.subject}</a></td>
        </tr>
    </#list>
</table>
    <div class="page">
    <@pager.p url='film/${label}' pageno=pageNo totalpage=totalpage pagesize=pageSize></@pager.p>

    </div>
</div>

</body>
<#include "*/foot.ftl">
</html>