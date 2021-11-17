<#import "parts/common.ftl" as c>
<#import "parts/ui.ftl" as ui/>
<@c.page>
    <h3>Credit Life Clients</h3>

    <div>
        <fieldset>
            <legend>Search</legend>
            <form name="searchForm" action="/clients/" method="post">
                <div >
                <@ui.formInput id="t3" name="taxcode"  label="Taxcode" value="${taxcode}" />
                <@ui.formInput id="t4" name="searchName"  label="Customer Name" value="${searchName}" />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" class="btn btn-primary" value="Do search..."/>
            </form>
        </fieldset>
    </div>
    <br>

    <#if isStatus>
        <h4><font color="green">${statusAction}</font></h4>
    </#if>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a <#if isClientList> class="nav-link active" <#else> class="nav-link" </#if> href="/clients">List</a>
        </li>
        <#--<li class="nav-item">-->
        <#--<a <#if isAddLang> class="nav-link active" <#else> class="nav-link" </#if> href="/clients/${action}">${action}</a>-->
        <#--</li>-->

    </ul>


    <br>
    <#if isClientList>
        <#include "parts/client/clientsList.ftl" />
    </#if>

    <#if isAddLang>
        <#include "parts/client/actionForm.ftl" />
    </#if>

</@c.page>
