import java.util.ArrayList;

public class Usuario {
    private String nome;
    private int id;
    private ArrayList<Pedido> pedidos;

    // Construtor
    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        pedidos = new ArrayList<>();
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

    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }
    
    @Override
    public String toString() {
        return "Usuário: " + nome + " id (" + id + ")";
    }
}
