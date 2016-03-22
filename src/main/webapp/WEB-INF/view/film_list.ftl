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
    <thead><td>时间</td><td>豆瓣评分</td><td>国家</td><td>类型</td><td>片名</td></thead>
    <#list list as filmlist>
        <tr>
            <#--<#assign json=filmlist.douban?eval />-->
            <#--<#assign douban=filmlist.douBanInfo?eval/>-->
            <td><small>${filmlist.datetime?string("yyyy-MM-dd HH:mm:ss")}</small></td>
            <td><a target="_blank" href="${filmlist.douBanInfo.url}" >${filmlist.douBanInfo.rating}</a></td>
            <td><small> ${filmlist.douBanInfo.country?split(",")[0]}</small></td>
            <td><small>${filmlist.douBanInfo.genres}</small></td>
            <td><a target="_blank" href="/film/content/${filmlist.id}">${filmlist.title}</a></td>
        </tr>
    </#list>
</table>
    <div class="page">
    <@pager.p url='film'pageno=pageNo totalpage=totalpage pagesize=pageSize></@pager.p>

    </div>
</div>

</body>
<#include "*/foot.ftl">
</html>