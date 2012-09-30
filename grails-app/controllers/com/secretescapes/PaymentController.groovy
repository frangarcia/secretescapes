package com.secretescapes

class PaymentController {

    def paymentService

    def pay = {
        def paymentInstance = new Payment(params)
    }

    def doPayment = {
        Payment paymentInstance = paymentService.transferMoney(params)

        if (!paymentInstance.hasErrors()){
            flash.message = "The payment has been correctly processed"
            redirect(action: "transactions", params: [sourceAccountId:paymentInstance.sourceAccount.id])
        }
        else
            render(view: "pay", model: [paymentInstance:paymentInstance])
    }

    def transactions = {
        if (params.sourceAccountId){
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def sourceAccount = Account.get(params.sourceAccountId)
            render(view: "transactions", model:[sourceAccount: sourceAccount, transactionsInstanceList: Payment.findAllBySourceAccount(sourceAccount, params), transactionsInstanceTotal: Payment.countBySourceAccount(sourceAccount)])
        }
    }

    def checkAmount = {
        def sourceAccount = Account.get(1)
        if (params.amount>sourceAccount.balance)
            render "OK"
        else
            render message(code:"com.secretescapes.notenoughmoneyinsourceaccount", default:"The source account doesn't have enough money to do the payment")
    }
}
