package mgs.inventory.model

enum class StatusValidacao {
    PENDENTE, APROVADO, REPROVADO
}

data class ItemConferencia(
    val id: Int,
    val quantidade: String,
    val nomeItem: String,
    val status: StatusValidacao = StatusValidacao.PENDENTE
)
