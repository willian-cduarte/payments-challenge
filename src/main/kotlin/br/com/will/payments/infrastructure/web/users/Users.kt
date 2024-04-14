package br.com.will.payments.infrastructure.web.users

data class UserRequest(
        val name: String?,
        val cpfOrCnpj: String?,
        val email: String?,
        val password: String?
)

class UserResponse private constructor(
        var id: Int? = null,
        var name: String? = null,
        var cpfOrCnpj: String? = null,
        var email: String? = null
) {
    data class Builder(
            var id: Int? = null,
            var name: String? = null,
            var cpfOrCnpj: String? = null,
            var email: String? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun cpfOrCnpj(cpfOrCnpj: String?) = apply { this.cpfOrCnpj = cpfOrCnpj }
        fun email(email: String?) = apply { this.email = email }
        fun build() = UserResponse(id, name, cpfOrCnpj, email)
    }
}
