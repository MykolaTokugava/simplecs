<table class="table table-striped">
    <thead class="thead-light">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Taxcode</th>
        <th scope="col">Paid: From-To</th>
        <th scope="col">Tariff: code</th>
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
        <td><a href="javascript:void(0)" onClick="javascript:window.open('/clients/clientview/${client.id}', 'okno', 'width=1200,height=1000, left=350, top=100, modal=true, status=no,toolbar=no, menubar=no,scrollbars=yes,resizable=yes');">${client.generalId.customerFullName}</a></td>
        <td>${client.generalId.taxcode}</td>
        <td>${client.getFormatedData(client.paidFrom)}-${client.getFormatedData(client.paidTo)}</td>
        <td>${client.generalId.currentTpId.tpCode}</td>
        <td>${client.generalId.claim}</td>
        <td><a class="btn btn-info" href="/clients/addclaim/${client.id}"/>Add Claim</a>&nbsp;<a class="btn btn-info" href="/clients/openclaim/${client.id}"/>Open Claim</a>&nbsp;<a class="btn btn-info" href="/clients/declineclaim/${client.id}"/>Decline</a></td>
    </tr>
    </#list>
    </tbody>
</table>
