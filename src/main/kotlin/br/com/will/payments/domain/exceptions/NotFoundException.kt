package br.com.will.payments.domain.exceptions

import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.domain.exceptions.config.PaymentsChallengeException

open class NotFoundException : PaymentsChallengeException {

    constructor() : super(ErrorCodeConstants.NOT_FOUND)
    constructor(errorCodeConstants: ErrorCodeConstants, params: Array<String>? = null, originalErrorMessage: String? = null) : super(errorCodeConstants, params, originalErrorMessage)

}
