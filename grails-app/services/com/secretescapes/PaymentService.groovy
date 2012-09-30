package com.secretescapes

import grails.util.Environment

class PaymentService {

    static transactional = true

    def mailService

    Payment transferMoney(params) {
        def paymentInstance = new Payment(sourceAccount: Account.get(params?.sourceAccountId), targetAccount: Account.get(params?.targetAccountId), amount: params?.amount)
        if (paymentInstance.validate()){
            try {
                paymentInstance.save()
                paymentInstance.sourceAccount.balance -= paymentInstance.amount
                paymentInstance.targetAccount.balance += paymentInstance.amount
                if (Environment.current!=Environment.TEST){
                    sendEmails(paymentInstance.sourceAccount.email, "admin@secretescapes.com", "Secret Escapes Payments System", "The transfer of ${paymentInstance.amount}€ you did to ${paymentInstance.targetAccount.name} was correctly processed")
                    sendEmails(paymentInstance.targetAccount.email, "admin@secretescapes.com", "Secret Escapes Payments System", "You have received a transfer of ${paymentInstance.amount}€ from ${paymentInstance.sourceAccount.name}")
                }
            }
            catch (Exception e){
                throw new RuntimeException("error saving payment")
            }
        }
        paymentInstance
    }

    def sendEmails(emailTo, emailFrom, emailSubject, emailBody) {
        mailService.sendMail {
            to emailTo
            from emailFrom
            subject emailSubject
            body emailBody
        }
    }
}
