<%@ page import="com.secretescapes.Account" %>

<html>
<head>
    <title>Pay Some Person</title>
    <meta name="layout" content="main" />
</head>
<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:hasErrors bean="${paymentInstance}">
    <div class="errors">
        <g:renderErrors bean="${paymentInstance}" as="list" />
    </div>
</g:hasErrors>
<div>
    <g:form action="doPayment">
        <h2><g:message code="com.secretescapes.pay" default="Pay"/></h2>
        <br/>
        <b><g:message code="com.secretescapes.from" default="From"/>:</b> <g:select from="${Account.list()}" id="sourceAccountId" name="sourceAccountId" optionValue="name" optionKey="id" value="${paymentInstance?.sourceAccount?.id}"/>
        <br/>
        <b><g:message code="com.secretescapes.to" default="To"/>: </b> <g:select from="${Account.list()}" id="targetAccountId" name="targetAccountId" optionValue="name" optionKey="id" value="${paymentInstance?.targetAccount?.id}"/>
        <br/>
        <b><g:message code="com.secretescapes.amount" default="Amount"/>:</b> <g:textField name="amount" id="amount" value="${paymentInstance?.amount}"/>
        <br/>
        <b><g:submitButton name="pay" value="Pay"/></b>
    </g:form>
</div>
</body>
</html>