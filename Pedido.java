import java.util.ArrayList;
import java.util.List;
public class Pedido {
    private int idPedido; 
    private Usuario funcionario;
    private Departamento departamento;
    private String data;
    private String dataDeConclusao;
    private Status status;
    private ArrayList<Item> itens;
    private double valorTotal;

    public Pedido(int idPedido,Usuario funcionario, Departamento departamento, String data){
        this.idPedido = idPedido;
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.data = data;
        status = Status.ABERTO;
        itens = new ArrayList<>();
        setValorTotal();
    }

    public int getIdPedido(){
        return idPedido;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public List<Item> getItens() {
        return itens;
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

    public boolean setStatus(Usuario administrador, Status s, String data){
        if(this.getStatus() == Status.APROVADO || this.getStatus() == Status.REPROVADO || this.getStatus() == Status.CONCLUIDO){
            return false;
        }
        if(administrador instanceof Administrador) {
            if(s == Status.APROVADO){
                this.dataDeConclusao = data;
                this.status = s;
            }
        }
        return true;
    }

    public boolean setStatus(Status s){
        if(this.getStatus() == Status.APROVADO || this.getStatus() == Status.REPROVADO || this.getStatus() == Status.CONCLUIDO){
            return false;
        }
        if(s == Status.ABERTO){
            this.status = s;
            return true;
        }
        return false;
    }

    public void cadastrarItemNoPedido(Item i){
        itens.add(i);
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
