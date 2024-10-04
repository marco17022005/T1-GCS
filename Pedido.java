import java.util.ArrayList;
public class Pedido {
    private Usuario funcionario;
    private Departamento departamento;
    private String data;
    private String dataDeConclusao;
    private Status status;
    private ArrayList<Item> itens; 

    public Pedido(Usuario funcionario, Departamento departamento, String data){
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.data = data;
        status = Status.ABERTO;
        itens = new ArrayList<>();
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Usuario administrador, Status status, String data){
        if(administrador instanceof Usuario) {
            if(status == Status.APROVADO){
                this.dataDeConclusao = data;
            }
            this.status = status;
        }//APÓS a implementação da classe administrador, mudar para instanceof Administrador
    }

    public void mostrarItensDoPedido(){
        for(Item i : itens){
            System.out.println(i.getDescricao());
        }
    }
}
