package com.secretescapes

import grails.test.*

class PaymentControllerTests extends GroovyTestCase {

    PaymentController pc
    Account sourceAccount, targetAccount

    protected void setUp() {
        super.setUp()
        sourceAccount = new Account(name: "Source", balance: 200, email: "source@gmail.com").save()
        targetAccount = new Account(name: "Target", balance: 200, email: "target@gmail.com").save()
        pc = new PaymentController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void test001DoPaymentAmountLessThanZero() {
        pc.params.amount = -200
        pc.params.sourceAccountId = sourceAccount.id
        pc.params.targetAccountId = targetAccount.id

        pc.doPayment()
        assertEquals pc.modelAndView.viewName, "/payment/pay"
        assertEquals pc.modelAndView.model.paymentInstance.amount, -200.0
        assertEquals pc.modelAndView.model.paymentInstance.sourceAccount.name, "Source"
        assertEquals pc.modelAndView.model.paymentInstance.targetAccount.balance, 200.0
    }

    void test002DoPaymentAmountHigherThanCurrentBalance() {
        pc.params.amount = 300
        pc.params.sourceAccountId = sourceAccount.id
        pc.params.targetAccountId = targetAccount.id

        pc.doPayment()
        assertEquals pc.modelAndView.viewName, "/payment/pay"
        assertEquals pc.modelAndView.model.paymentInstance.amount, 300.0
        assertEquals pc.modelAndView.model.paymentInstance.sourceAccount.balance, 200.0
        assertEquals pc.modelAndView.model.paymentInstance.targetAccount.name, "Target"
    }

    void test003DoPaymentOk() {
        pc.params.amount = 150
        pc.params.sourceAccountId = sourceAccount.id
        pc.params.targetAccountId = targetAccount.id

        pc.doPayment()
        assertEquals pc.response.redirectedUrl, "/payment/transactions?sourceAccountId=${sourceAccount.id}"
    }

    void test004TransactionsOk() {
        pc.params.amount = 150
        pc.params.sourceAccountId = sourceAccount.id
        pc.params.targetAccountId = targetAccount.id

        pc.doPayment()

        pc.params.sourceAccountId = sourceAccount.id

        pc.transactions()
        assertEquals pc.modelAndView.model.sourceAccount.name, sourceAccount.name
        assertEquals pc.modelAndView.model.sourceAccount.balance, 50.0
        assertEquals pc.modelAndView.model.transactionsInstanceList.size(), 1
        assertEquals pc.modelAndView.model.transactionsInstanceTotal, 1
        assertEquals pc.modelAndView.viewName, "/payment/transactions"

    }

}
