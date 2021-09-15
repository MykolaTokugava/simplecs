<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">SW</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/languages">Languages</a>
            </li>
        </ul>
    </div>

                <#if isLogin>
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Load Words
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

            <a class="dropdown-item" href="/logout">From File</a>
            <a class="dropdown-item" href="/login">TexArea</a>
        </div>
    </div>
                </#if>
    <p>&nbsp;</p>
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            System Action
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <#if isLogin>
                <a class="dropdown-item" href="/logout">LogOut</a>
                </#if>
                <#if isLogin==false>
                <a class="dropdown-item" href="/login">Login</a>
                </#if>
            <a class="dropdown-item" href="#">Clear</a>
        </div>
    </div>
    <p>&nbsp;</p>
    <div class="navbar-text mr-3"><b><#if user??>${name}<#else>Please, login</#if></b></div>

</nav>

