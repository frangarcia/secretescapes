<%@ page import="com.secretescapes.Account" %>


<html>
<head>
    <title>See transactions</title>
    <meta name="layout" content="main" />
</head>
<body>
<div>
    <h2>Pay</h2>
    <br/>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="transactions">
        <b>Person:</b> <g:select from="${Account.list()}" name="sourceAccountId" optionValue="name" optionKey="id" value="${params?.sourceAccountId}"/>
        <br/>
        <b><g:submitButton name="transactions" value="Show transactions"/></b>
    </g:form>
    <g:if test="${params?.sourceAccountId}">
        <g:if test="${transactionsInstanceList}">
            <div>Balance for <b>${sourceAccount.name}</b>: ${sourceAccount.balance}</div>
            <div>
                <table>
                    <tr>
                        <th><g:message code="com.secretescapes.sourceAccount" default="Source account"/></th>
                        <th><g:message code="com.secretescapes.targetAccount" default="Target account"/></th>
                        <th><g:message code="com.secretescapes.amount" default="Amount account"/></th>
                        <th><g:message code="com.secretescapes.date" default="Date"/></th>

                    </tr>
                    <g:each in="${transactionsInstanceList}" var="transactionsInstance">
                        <tr>
                            <td>${transactionsInstance.sourceAccount.name}</td>
                            <td>${transactionsInstance.targetAccount.name}</td>
                            <td>${transactionsInstance.amount}</td>
                            <td><g:formatDate date="${transactionsInstance.dateCreated}" format="dd/MM/yyyy hh:mm:ss"/> </td>
                        </tr>
                    </g:each>
                </table>
            </div>
            <g:paginate total="${transactionsInstanceTotal}"/>
        </g:if>
        <g:else>
            <p class="error">
                <g:message code="com.secretescapes.thereisnotransactionsfortheuser" default="There is no transactions for the user" args="[sourceAccount.name]"/>
            </p>
        </g:else>
    </g:if>
    <g:else>
        <p class="error">
            <g:message code="com.secretescapes.selectauser" default="Please, select a user"/>
        </p>
    </g:else>
</div>
</body>
</html>