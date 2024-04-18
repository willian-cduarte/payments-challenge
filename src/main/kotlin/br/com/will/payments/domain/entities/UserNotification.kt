package br.com.will.payments.domain.entities


class UserNotification(
        val name: String?,
        val email: String?
) {
    /*data class Builder(
            var name: String? = null,
            var email: String? = null
    ) {
        fun name(name: String?) = apply { this.name = name }
        fun email(email: String?) = apply { this.email = email }
        fun build() = UserNotification(name, email)
    }*/
}