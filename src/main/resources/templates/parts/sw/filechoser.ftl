<#macro filechoser>
<form action="/loadwords" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Text :</label>
        <div class="col-sm-6">
            <input type="file" name="myFile">
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Parse</button>
</form>
</#macro>