<#import "parts/common.ftl" as c>
<@c.page>
<h3>Languages</h3>
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a <#if isLangList> class="nav-link active" <#else> class="nav-link" </#if> href="/languages">List</a>
    </li>
    <li class="nav-item">

        <a <#if isAddLang> class="nav-link active" <#else> class="nav-link" </#if> href="/languages/${action}">${action}</a>
    </li>

</ul>
<br>
    <#if isLangList>
        <#include "parts/lang/langList.ftl" />
    </#if>

    <#if isAddLang>
        <#include "parts/lang/actionForm.ftl" />
    </#if>

</@c.page>
