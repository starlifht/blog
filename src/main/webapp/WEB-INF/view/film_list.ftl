<!DOCTYPE html>
<html lang="en">
<head>

<#include "*/nav.ftl">
<#import "*/page.ftl" as pager>

    <title>Title</title>
</head>
<body>
<div class="iframe" id="iframe">
<table class="table table-striped" id="film_list">
    <thead><td>时间</td><td>豆瓣评分</td><td>国家</td><td>类型</td><td>片名</td></thead>
    <#list list as filmlist>
        <tr>
            <#assign json=filmlist.douban?eval />
            <td><small>${filmlist.datetime?string("yyyy-MM-dd HH:mm:ss")}</small></td>
            <td><a target="_blank" href="${json.url}" >${filmlist.douban_rating}</a></td>
            <td><small> ${json.countries?first}</small></td>
            <td><small>${json.genres?first}</small></td>
            <td>${filmlist.title}</td>
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