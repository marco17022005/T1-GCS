import java.util.ArrayList;

public class Usuario {
    private String nome;
    private int id;
    private Departamento departamento;
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

    public void cadastrarPedido(Pedido p){
        pedidos.add(p); //Cadastrar o pedido como feito do usuário
    }

    public void setDepartamento(Departamento d){
        this.departamento = d;
        //Um usuário também precisa de departamento, "O departamento solicitante (**deve ser o mesmo do funcionário no momento do cadastro**)"
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }
    
    @Override
    public String toString() {
        return "Usuário: " + nome + " id (" + id + ")";
    }
}
