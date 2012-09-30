package com.secretescapes

class Account {

    String name
    Float balance = 200
    String email

    static hasMany = [outcomes:Payment, incomes: Payment]

    static mappedBy = [outcomes:'sourceAccount', incomes:'targetAccount']

    static constraints = {
        name(blank:false)
        balance(nullable:false, validator: {it>=0})//Balance must be always higher or equals than 0
        email(nullable:false, blank:false, email:true, unique:true)
    }

    String toString() {
        "${name} (${email}) = ${balance}"
    }
}
