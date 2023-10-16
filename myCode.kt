enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

open class Pessoa(nome: String, email: String)

class Usuario(val id: String, val nivel: Int, val senha: String): Pessoa(nome, email)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Matricula(val usuario: Usuario, val formacao: Formacao)

class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    private val matriculas = mutableListOf<Matricula>()

    fun matricular(usuario: Usuario) {
        if (!estaMatriculado(usuario)) {
            matriculas.add(Matricula(usuario, this))
            println("${usuario.nome} foi matriculado na formação $nome")
        } else {
            println("${usuario.nome} já está matriculado na formação $nome")
        }
    }

    fun desmatricular(usuario: Usuario) {
        val matricula = matriculas.find { it.usuario == usuario }
        if (matricula != null) {
            matriculas.remove(matricula)
            println("${usuario.nome} foi desmatriculado da formação $nome")
        } else {
            println("${usuario.nome} não está matriculado na formação $nome")
        }
    }

    fun estaMatriculado(usuario: Usuario): Boolean {
        return matriculas.any { it.usuario == usuario }
    }

    fun listarMatriculados() {
        println("Usuários matriculados na formação $nome:")
        matriculas.forEach { println(it.usuario.nome) }
    }
}

fun main() {
    val usuario1 = Usuario("João")
    val usuario2 = Usuario("Maria")

    val formacao1 = Formacao("Programação Básica", listOf(ConteudoEducacional("Introdução ao Kotlin"), ConteudoEducacional("Estruturas de Controle")))
    val formacao2 = Formacao("Programação Avançada", listOf(ConteudoEducacional("Programação Orientada a Objetos"), ConteudoEducacional("Design Patterns")))

    formacao1.matricular(usuario1)
    formacao2.matricular(usuario2)
    formacao1.matricular(usuario2)

    formacao1.listarMatriculados()
    formacao2.listarMatriculados()

    formacao1.desmatricular(usuario1)
    formacao2.desmatricular(usuario1)
}
