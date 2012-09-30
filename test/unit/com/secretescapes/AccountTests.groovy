package com.secretescapes

import grails.test.*

class AccountTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {
        def account1 = new Account(name: "Name", balance: 200, email: "fgarciarico@gmail.com")
        mockForConstraintsTests(Account, [ account1 ])

        //This account needs the arguments and cannot be created
        def account2 = new Account()
        assertFalse account2.validate()
        assertEquals "nullable", account2.errors["name"]
        assertEquals "nullable", account2.errors["email"]

        //This account has a negative balance and a repeated email
        account2 = new Account(name: "Name", balance: -200, email: "fgarciarico@gmail.com")
        assertFalse account2.validate()
        assertEquals "unique", account2.errors["email"]
        assertEquals "validator", account2.errors["balance"]

        //This account cannot be created because the blank name and the invalid email
        account2 = new Account(name: "", balance: 300, email: "fgarciarico at gmail.com")
        assertFalse account2.validate()
        assertEquals "blank", account2.errors["name"]
        assertEquals "email", account2.errors["email"]

        //This account is correct
        account2 = new Account(name: "Name", balance: 300, email: "myemail@gmail.com")
        assertTrue account2.validate()
    }
}
