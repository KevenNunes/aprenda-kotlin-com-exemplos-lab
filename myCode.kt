enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val idUser: Int, val nameUser: String, val nivel: Int, val senha: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Matricula(val usuario: Usuario, val formacao: Formacao)

class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    private val matriculas = mutableListOf<Matricula>()

    fun matricular(usuario: Usuario) {
        if (!estaMatriculado(usuario)) {
            matriculas.add(Matricula(usuario, this))
            println("${usuario.nameUser} foi matriculado na formação $nome")
        } else {
            println("${usuario.nameUser} já está matriculado na formação $nome")
        }
    }

    fun desmatricular(usuario: Usuario) {
        val matricula = matriculas.find { it.usuario == usuario }
        if (matricula != null) {
            matriculas.remove(matricula)
            println("${usuario.nameUser} foi desmatriculado da formação $nome")
        } else {
            println("${usuario.nameUser} não está matriculado na formação $nome")
        }
    }

    fun estaMatriculado(usuario: Usuario): Boolean {
        return matriculas.any { it.usuario == usuario }
    }

    fun listarMatriculados() {
        println("Usuários matriculados na formação $nome:")
        matriculas.forEach { println(it.usuario.nameUser) }
    }
}

fun main() {
    val usuario1 = Usuario(1, "João", 2, "Jo112233")
    val usuario2 = Usuario(2, "Maria", 10, "M@ria.123")

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