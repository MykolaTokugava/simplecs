<table class="table table-striped">
    <thead class="thead-light">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Taxcode</th>
        <th scope="col">Paid: From-To</th>
        <th scope="col">Tariff</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <#assign count = 0>
    <#list clients as client>
        <#assign count = count + 1>
    <tr>
        <!--<th scope="row">${count}</th>-->
        <td>${client.id}</td>
        <td><a href="/clients/clientview/${client.id}" target=_blank/>${client.generalId.customerFullName}</a></td>
        <td>${client.generalId.taxcode}</td>
        <td>${client.getFormatedData(client.paidFrom)}-${client.getFormatedData(client.paidTo)}</td>
        <td>Tariff</td>
        <td>${client.generalId.claim}</td>
        <td><a class="btn btn-info" href="/clients/addclaim/${client.id}"/>Add Claim</a>&nbsp;<a class="btn btn-info" href="/clients/openclaim/${client.id}"/>Open Claim</a>&nbsp;<a class="btn btn-info" href="/clients/declineclaim/${client.id}"/>Decline</a></td>

    </tr>
    </#list>
    </tbody>
</table>
