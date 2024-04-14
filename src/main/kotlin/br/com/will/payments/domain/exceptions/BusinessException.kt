package br.com.will.payments.domain.exceptions

import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.domain.exceptions.config.PaymentsChallengeException

open class BusinessException(
        errorCodeConstants: ErrorCodeConstants,
        params: Array<String>? = null,
        originalErrorMessage: String? = null
) : PaymentsChallengeException(
        errorCodeConstants,
        params,
        originalErrorMessage
)