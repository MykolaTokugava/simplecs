<#macro textarea info>
<form action="/loadwords" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Text :</label>
        <div class="col-sm-6">
            <textarea cols="50" rows="3" name="words" class="form-control" placeholder="words import" >${info}</textarea>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Parse</button>
</form>
</#macro>

