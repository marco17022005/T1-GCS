public class Usuario {
    private String nome;
    private int id;
    

    // Construtor
    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
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

    // Método toString para exibir informações do usuário
    @Override
    public String toString() {
        return "Usuário: " + nome + " id (" + id + ")";
    }
}
