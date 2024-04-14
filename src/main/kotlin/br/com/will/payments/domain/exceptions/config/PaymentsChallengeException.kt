package br.com.will.payments.domain.exceptions.config

open class PaymentsChallengeException: RuntimeException {

    private val errorCode: ErrorCodeAbstract
    private val originalErrorMessage: String?

    constructor(
            errorCodeAbstract: ErrorCodeAbstract,
            params: Array<String>? = null,
            originalErrorMessage: String? = null
    ): super(errorCodeAbstract.value) {
        this.errorCode = errorCodeAbstract
        this.errorCode.parameters = params
        this.originalErrorMessage = originalErrorMessage
    }
}