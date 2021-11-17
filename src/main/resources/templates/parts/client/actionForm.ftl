<form method="post" action="/clients/${action}">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Name:</label>
        <div class="col-sm-6">
            <input type="text" name="name" class="form-control" placeholder="Name" value="${name!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Code:</label>
        <div class="col-sm-6">
            <input type="text" name="code" class="form-control" placeholder="Code" value="${code!''}" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="hidden" value="${id!''}" name="id" />
    <button class="btn btn-primary" type="submit">Save</button>
</form>