public class Usuario {
    private String nome;
    private String iniciais;
    private String cargo; // "funcionario" ou "administrador"

    // Construtor
    public Usuario(String nome, String iniciais, String cargo) {
        this.nome = nome;
        this.iniciais = iniciais;
        this.cargo = cargo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método toString para exibir informações do usuário
    @Override
    public String toString() {
        return "Usuário: " + nome + " (" + iniciais + "), Cargo: " + cargo;
    }
}
