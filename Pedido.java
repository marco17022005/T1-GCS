import java.util.ArrayList;
public class Pedido {
    private Usuario funcionario;
    private Departamento departamento;
    private String data;
    private String dataDeConclusao;
    private Status status;
    private ArrayList<Item> itens;
    private double valorTotal;

    public Pedido(Usuario funcionario, Departamento departamento, String data){
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.data = data;
        status = Status.ABERTO;
        itens = new ArrayList<>();
        setValorTotal();
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

    public boolean setStatus(Usuario administrador, Status status, String data){
        if(this.getStatus() == Status.APROVADO || this.getStatus() == Status.REPROVADO || this.getStatus() == Status.CONCLUIDO){
            return false;
        }
        if(administrador instanceof Administrador) {
            if(status == Status.APROVADO){
                this.dataDeConclusao = data;
                this.status = status;
            }
        }
        return true;
    }

    public void mostrarItensDoPedido(){
        for(Item i : itens){
            System.out.println(i.getDescricao() + "," + i.getValor() + "," + i.getQuantidade() + "," + i.getValorTotal());
        }
    }

    public void setValorTotal(){
        for(Item i : itens){
            valorTotal += i.getValorTotal();
        }
    }

    public double getValorTotal(){
        return valorTotal;
    }

}
