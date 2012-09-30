package com.secretescapes

import grails.test.*

class PaymentTests extends GrailsUnitTestCase {
    Account sourceAccount, targetAccount

    protected void setUp() {
        super.setUp()
        sourceAccount = new Account(name: "Source", balance: 200, email: "source@gmail.com")
        targetAccount = new Account(name: "Target", balance: 200, email: "target@gmail.com")
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {
        def payment1 = new Payment(amount: 200, sourceAccount:sourceAccount, targetAccount: targetAccount)
        mockForConstraintsTests(Payment, [ payment1 ])

        //This payment needs the arguments and cannot be created
        def payment2 = new Payment()
        assertFalse payment2.validate()
        assertEquals "nullable", payment2.errors["amount"]
        assertEquals "nullable", payment2.errors["sourceAccount"]
        assertEquals "nullable", payment2.errors["targetAccount"]

        //This payment cannot be done because amount is higher than the current balance
        payment2 = new Payment(amount: 300, sourceAccount:sourceAccount, targetAccount: targetAccount)
        assertFalse payment2.validate()
        assertEquals "validator", payment2.errors["amount"]

        //This payment cannot be done because amount is less than 0 and the source account is the same than the target account
        payment2 = new Payment(amount: -200, sourceAccount:sourceAccount, targetAccount: sourceAccount)
        assertFalse payment2.validate()
        assertEquals "validator", payment2.errors["amount"]
        assertEquals "validator", payment2.errors["sourceAccount"]

        //This payment is correct and it should be validated
        payment2 = new Payment(amount: 200, sourceAccount:sourceAccount, targetAccount: targetAccount)
        assertTrue payment2.validate()
    }
}
