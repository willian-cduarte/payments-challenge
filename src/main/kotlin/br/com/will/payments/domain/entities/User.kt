package br.com.will.payments.domain.entities

import br.com.will.payments.domain.exceptions.ValidationException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants

data class User (
        val id: Int? = null,
        val name: String?,
        val cpfOrCnpj: String?,
        val email: String?,
        val password: String?
) {

    data class Builder(
            var id: Int? = null,
            var name: String? = null,
            var cpfOrCnpj: String? = null,
            var email: String? = null,
            var password: String? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun cpfOrCnpj(cpfOrCnpj: String?) = apply { this.cpfOrCnpj = cpfOrCnpj }
        fun email(email: String?) = apply { this.email = email }
        fun password(password: String?) = apply { this.password = password }
        fun build() = User(id, name, clearCpfOrCnpj(cpfOrCnpj), email, password)

        private fun clearCpfOrCnpj(cpfOrCnpj: String?): String? {
            return cpfOrCnpj
                    ?.replace(".", "")
                    ?.replace("-", "")
                    ?.replace("/", "")
                    ?.trim()
        }
    }


    fun isValidEmail() {
        val emailRegex = "^(?=.{1,64}@)[A-Za-z0-9+_-]+(.[A-Za-z0-9+_-]+)*@[^-][A-Za-z0-9+-]+(.[A-Za-z0-9+-]+)*(.[A-Za-z]{2,})$";
        if (email == null)
            throw ValidationException(this::class.simpleName!!, "email", ErrorCodeConstants.FIELD_VALUE_IS_NULL)
        if (!email.matches(emailRegex.toRegex()))
            throw ValidationException(this::class.simpleName!!, "email", ErrorCodeConstants.FIELD_VALUE_INVALID)
    }

    fun isValidCpfOrCnpj() {
        if (cpfOrCnpj == null)
            throw ValidationException(this::class.simpleName!!, "cpfOrCnpj", ErrorCodeConstants.FIELD_VALUE_IS_NULL)
        if (cpfOrCnpj.length != 11 && cpfOrCnpj.length != 14)
            throw ValidationException(this::class.simpleName!!, "cpfOrCnpj", ErrorCodeConstants.FIELD_VALUE_INVALID)
    }

}

