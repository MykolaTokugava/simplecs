
<h3>${fullName} - Client detail</h3>

<table width="100%" class="table table-striped">
    <thead class="thead-light">
    <tr>
        <th scope="col">General</th>
        <th scope="col">Tariff Info</th>
    </tr>
    </thead>
    <tr>
        <td width="60%" valign="top">
            <table class="table table-striped">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Parameter</th>
                    <th scope="col">Info</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>ID</td>
                    <td>${id}</td>
                </tr>
                <tr>
                    <td>Full Name</td>
                    <td>${fullName}</td>
                </tr>
                <tr>
                    <td>Dob</td>
                    <td>${dob}</td>
                </tr>
                <tr>
                    <td>Taxcode</td>
                    <td>${taxcode}</td>
                </tr>
                <tr>
                    <td>Passport</td>
                    <td>${passport}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${address}</td>
                </tr>
                <tr>
                    <td>Bank</td>
                    <td>${bank}</td>
                </tr>
                <tr>
                    <td>Bank Product</td>
                    <td>${bank_tp}</td>
                </tr>
                <tr>
                    <td>Paid From</td>
                    <td>${paidfrom}</td>
                </tr>
                <tr>
                    <td>Paid To</td>
                    <td>${paidto}</td>
                </tr>
                <tr>
                    <td>Paid Date</td>
                    <td>${paiddate}</td>
                </tr>
                <tr>
                    <td>Amount</td>
                    <td>${amount}</td>
                </tr>
                <tr>
                    <td>Premium</td>
                    <td>${premium}</td>
                </tr>
                </tbody>
            </table>
        </td>
        <td width="40%" valign="top">
        <b>[${tpCode}] ${tplan}</b>
            <table class="table table-striped">
                <thead class="thead-light">
                <tr>
                    <th scope="row">count</th>
                    <th scope="col">Product</th>
                    <th scope="col">Rate</th>
                </tr>
                </thead>
                <tbody>
                <#assign count = 0>
                <#list tarrifs as tar>
                    <#assign count = count + 1>
                    <tr>
                        <th scope="row">${count}</th>
                        <td>${tar.ipId.ipCode}</td>
                        <td>${tar.coefValue}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </td>
    </tr>
</table>
