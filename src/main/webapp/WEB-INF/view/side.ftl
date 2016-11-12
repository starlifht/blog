<#macro p flag  params>
  <#assign type=flag?string>
<div class="side">

    <div id="sidebar navbar"  class="sidebar">
        <ul>
            <li class="drdown" >
                <a href="#" data-toggle="drdown">最新电影<i class="icon-arrow"></i></a>
                <ul class="drdown-menu show">
                    <li>
                        <#if type == 'all'>
                            <a name="all" href="${params}/film/date/18/1">全部资源<<</a>
                          <#else >
                              <a name="all" href="${params}/film/date/18/1">全部资源>></a>


                        </#if>
                    </li>

                </ul>
            </li>
        <#--<%--<li class="drdown">--%>-->
        <#--<%--<a href="/article" data-toggle="drdown">文章列表<i class="icon-arrow"></i></a>--%>-->
        <#--<%--<ul class="drdown-menu">--%>-->
        <#--<%--<li><a href="/article">Home</a></li>--%>-->
        <#--<%--<li ><a  href="#">About Us</a></li>--%>-->
        <#--<%--<li><a href="#">Services</a></li>--%>-->
        <#--<%--<li><a href="#">Contact</a></li>--%>-->
        <#--<%--</ul>--%>-->
        <#--<%--</li>--%>-->


        </ul>

    </div>
    <div class="billboard ">
        <div style="text-align: center;background-color: #99CC66;color: honeydew;height: 25px;line-height: 25px;    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    border-radius:5px;">
            <label >豆瓣电影周排行榜</label></div>
        <table class="table table-hover" id="billboard"></table>

    </div>
</div>
</#macro>