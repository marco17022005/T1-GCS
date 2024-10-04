public class Item {
    private String descricao;
    private double valor;
    private int quantidade;
    private double valorTotal;
    
    public Item(String descricao, double valor){
        this.descricao = descricao;
        this.valor = valor;

    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valor * quantidade;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao + "\n Valor Unitário: " + valor + "\n Quantidade: " + quantidade + "\n Valor total: " + valorTotal;
    }
}
