<#import "parts/common-mini.ftl" as c>
<@c.page>
    <#if isData>
        <#include "parts/client/clientinfo.ftl" />
    </#if>
    <#if isClient>
        <h3>Client detail not exist</h3>
    </#if>
</@c.page>
