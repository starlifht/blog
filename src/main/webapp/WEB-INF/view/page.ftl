<#macro p url pageno totalpage pagesize  params=''>
    <#assign ipage=pageno?number>
    <#assign size=pagesize?number>
    <#assign  url=url?string>
<nav>
    <ul class="pagination">
            <li>
                <a href="${url}/${size}/1">
                    <span>首页</span>
                </a>
            </li>
            <#if ipage gt 1>
            <li>
                <a href="${url}/${size}/${ipage - 1}" aria-label=" Previous">
                <span aria-hidden="true">&laquo;</span> </a>
                </li>
            <#else>
            <li class="disabled">
                <span aria-hidden="true"> &laquo;</span>
            </li>
            </#if>


        <#if ipage-3 gte 1>
            <li><a href="${url}/${size}/${ipage - 3}">${ipage-3}</a></li>
        </#if>
        <#if ipage-2 gte 1>
            <li><a href="${url}/${size}/${ipage - 2}">${ipage-2}</a></li>
        </#if>
        <#if ipage-1 gte 1>
            <li><a href="${url}/${size}/${ipage - 1}">${ipage-1}</a></li>
        </#if>
        <li class="active"><a href="/${url}/${size}/${ipage }">${ipage}</a></li>
        <#if ipage+1 lte totalpage>
            <li><a href="${url}/${size}/${ipage +1}">${ipage+1}</a></li>
        </#if>
        <#if ipage+2 lte totalpage>
            <li><a href="/${url}/${size}/${ipage +2}">${ipage+2}</a></li>
        </#if>
        <#if ipage+3 lte totalpage>
            <li><a href="/${url}/${size}/${ipage +3}">${ipage+3}</a></li>
        </#if>

        <#if ipage lt totalpage>
        <li>
            <a href="${url}/${size}/${ipage + 1}" aria-label="Next" >
            <span aria-hidden="true">&raquo;</span> </a>
        <#else>
        <li class="disabled">
            <span aria-hidden="true"> &raquo;</span>
        </li>

        </#if>
        <li>
            <a href="${url}/${size}/${totalpage}">
                <span>尾页</span>
            </a>
        </li>
        <li  class="disabled"><a > 共<span>${totalpage}</span>页</a></li>
    </ul>
</nav>


</#macro>