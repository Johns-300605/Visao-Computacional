package mgs.inventory.auth

object AuthManager {
    data class User(
        val usuario: String,
        val senha: String,
        val nome: String = "")

    private  val usuarios = mutableListOf<User>()

    fun login(usuario: String, senha: String): Boolean{
        return usuarios.any{ it.usuario == usuario && it.senha == senha}
    }

    fun cadastrar(nome: String, usuario: String, senha: String): Boolean{
        if (usuarios.any { it.usuario == usuario }) return false

        usuarios.add(User(usuario, senha, nome))
        return true
    }
}
