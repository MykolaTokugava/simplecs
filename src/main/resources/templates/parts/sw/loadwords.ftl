<#import "../common.ftl" as c>
<#import "../sw/textarea.ftl" as ta>
<#import "../sw/filechoser.ftl" as f>

<@c.page>
<H1>Load Words Form!!!</H1>
<blockquote>${info}</blockquote>
    <@ta.textarea "zopa"></@ta.textarea>

    <@f.filechoser />

</@c.page>


