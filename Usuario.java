public class Usuario {
    private String nome;
    private int id;
    private String cargo; // "funcionario" ou "administrador"

    // Construtor
    public Usuario(String nome, int id, String cargo) {
        this.nome = nome;
        this.id = id;
        this.cargo = cargo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Usuário: " + nome + " id (" + id + "), Cargo: " + cargo;
    }
}
