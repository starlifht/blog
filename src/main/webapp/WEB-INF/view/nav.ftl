<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#assign base=request.contextPath />
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?ac9e03c4618198d8aff31e583d10f9fc";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

<link href="${base}/statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/statics/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/statics/css/nav.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="${base}/statics/images/logo.ico" type="image/vnd.microsoft.icon">
<nav class="navbar navbar-default" >
    <div class="nav_label">
           <span style="c">
        Whatever is worth doing is worth doing well .<img id="search_img" src="${base}/statics/images/search.png"></span>
    </div>

    <div id="search_input" class="hidden" >


        <div class="input-group"  >
            <input id="search" type="text" class="form-control" placeholder="电影名称" onkeypress="if(event.keyCode==13) {search();return false;}"/>
        <span class="input-group-btn">
            <button class="btn btn-info" type="button" onclick="search()" style="height: 34px">
                <span  aria-hidden="true">SEARCH</span>

            </button>
        </span>
        </div>
</nav>
<base id="base" href="${base}">
