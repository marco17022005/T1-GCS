import java.util.ArrayList;
import java.util.Comparator;

public class Usuario implements Comparator{
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
        return nome + " | ID - " + id;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Usuario usuario1, usuario2;
        
        usuario1 = (Usuario) o1;
        usuario2 = (Usuario) o2;

        if (usuario1.getId() < usuario2.getId())
            return -1;
        else if(usuario1.getId() > usuario2.getId())
            return +1;
        return 0;
    }
}
