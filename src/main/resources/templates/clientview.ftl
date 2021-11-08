<#import "parts/common.ftl" as c>
<#import "parts/ui.ftl" as ui/>
<@c.page>
    <h3>Credit Life Clients</h3>

    <div>
        <fieldset>
            <legend>Поиск!!!</legend>
            <form name="searchForm" action="/clients/" method="post">
                <div class="col-sm-6">
                <@ui.formInput id="t3" name="searchName"  label="Customer Name"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" class="btn btn-primary" value="Search"/>
            </form>
        </fieldset>
    </div>
    <br>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a <#if isLangList> class="nav-link active" <#else> class="nav-link" </#if> href="/clients">List</a>
        </li>
        <#--<li class="nav-item">-->
        <#--<a <#if isAddLang> class="nav-link active" <#else> class="nav-link" </#if> href="/clients/${action}">${action}</a>-->
        <#--</li>-->

    </ul>


    <br>
    <#if isLangList>
        <#include "parts/lang/langList.ftl" />
    </#if>

    <#if isAddLang>
        <#include "parts/lang/actionForm.ftl" />
    </#if>

</@c.page>
