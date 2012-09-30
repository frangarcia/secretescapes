package com.secretescapes

class Payment {

    Float amount
    Date dateCreated
    Account sourceAccount
    Account targetAccount

    static constraints = {
        amount(blank:false, nullable: false, validator: {val, obj -> (val>0 && obj.sourceAccount.balance>=val)})
        sourceAccount(validator: {val, obj -> val!=obj.targetAccount})//source and target cannot be the same
        targetAccount()
    }
}
