package com.secretescapes

import grails.test.*

class PaymentServiceTests extends GroovyTestCase {
    Account sourceAccount, targetAccount
    PaymentService paymentService

    protected void setUp() {
        super.setUp()
        sourceAccount = new Account(name: "Source", balance: 200, email: "source@gmail.com").save()
        targetAccount = new Account(name: "Target", balance: 200, email: "target@gmail.com").save()

        paymentService = new PaymentService()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void test001TransferMoneyLessOrEqualsThanZero() {
        paymentService.transferMoney([sourceAccountId:sourceAccount.id, targetAccountId:targetAccount.id, amount:0])

        //After the payment, the test should check the balance of every account
        assertEquals 200.0, sourceAccount.balance
        assertEquals 200.0, targetAccount.balance

    }

    void test002TransferMoneyHigherThanCurrentBalanceSourceAccount() {
        paymentService.transferMoney([sourceAccountId:sourceAccount.id, targetAccountId:targetAccount.id, amount:300])

        //After the payment, the test should check the balance of every account
        assertEquals 200.0, sourceAccount.balance
        assertEquals 200.0, targetAccount.balance
    }

    void test003TransferMoneyOk() {
        paymentService.transferMoney([sourceAccountId:sourceAccount.id, targetAccountId:targetAccount.id, amount:200])

        //After the payment, the test should check the balance of every account
        assertEquals 0.0, sourceAccount.balance
        assertEquals 400.0, targetAccount.balance

    }
}
