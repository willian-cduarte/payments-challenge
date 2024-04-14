package br.com.will.payments.domain.exceptions

import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.domain.exceptions.config.PaymentsChallengeException

open class NotFoundException() : PaymentsChallengeException(ErrorCodeConstants.NOT_FOUND)