public class Item {
    private String descricao;
    private double valor;
    private int quantidade;
    private double valorTotal;

    // Construtor
    public Item(String descricao, double valor, int quantidade) {
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valorTotal = valor * quantidade;  // Cálculo do valor total ao criar o item
    }

    // Getters e Setters
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
        this.valorTotal = this.valor * quantidade;  // Recalcular o valor total quando a quantidade mudar
    }

    public void setValorTotal(double valor, int quantidade) {
        this.valorTotal = valor * quantidade;
    }

    @Override
    public String toString() {
        return String.format("Descrição: %s | Quantidade: %d | Valor Unitário: R$ %.2f | Valor Total: R$ %.2f",
                descricao, quantidade, valor, valorTotal);
    }
}
