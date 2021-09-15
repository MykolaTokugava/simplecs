<table class="table table-striped">
    <thead class="thead-light">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Code</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <#assign count = 0>
    <#list languages as lang>
        <#assign count = count + 1>
    <tr>
        <th scope="row">${count}</th>
        <td>${lang.name}</td>
        <td>${lang.code}</td>
        <td><a class="btn btn-info" href="/languages/edit/${lang.id}"/>Edit</a>&nbsp;<a class="btn btn-info" href="/languages/delete/${lang.id}"/>Delete</a></td>

    </tr>
    </#list>
    </tbody>
</table>
