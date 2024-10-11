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

    public Pedido(int idPedido, Usuario funcionario, Departamento departamento, String data) {
        this.idPedido = idPedido;
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.data = data;
        this.status = Status.ABERTO;
        this.itens = new ArrayList<>();
    }

    // Getters
    public int getIdPedido() {
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

    public String getDataDeConclusao() {
        return dataDeConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Métodos de atualização de status
    public boolean setStatus(Usuario administrador, Status s, String data) {
        if (this.getStatus() == Status.APROVADO || this.getStatus() == Status.REPROVADO) {
            return false;
        }
        if (administrador instanceof Administrador) {
            if (s == Status.APROVADO) {
                this.dataDeConclusao = data;
                this.status = s;
            }
        }
        return true;
    }

    public boolean setStatus(Status s) {
        if (this.getStatus() == Status.APROVADO || this.getStatus() == Status.REPROVADO) {
            return false;
        }
        if (s == Status.ABERTO) {
            this.status = s;
            return true;
        }
        return false;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        atualizarValorTotal();
    }

    public void cadastrarItemNoPedido(Item i) {
        itens.add(i);
        atualizarValorTotal(); // Atualiza o valor total após adicionar o item
    }

    public void mostrarItensDoPedido() {
        for (Item i : itens) {
            System.out.printf("Descrição: %s | Valor Unitário: R$ %.2f | Quantidade: %d | Valor Total: R$ %.2f%n",
                    i.getDescricao(), i.getValor(), i.getQuantidade(), i.getValorTotal());
        }
    }


    private void atualizarValorTotal() {
        valorTotal = 0;
        for (Item i : itens) {
            valorTotal += i.getValorTotal();
        }
    }
}
