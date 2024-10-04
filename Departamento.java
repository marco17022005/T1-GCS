public class Departamento {
    private String departamento; // Financeiro, RH, Manutenção etc
    private double valorLimite; // 

    public Departamento(String departamento, double valorLimite){
        this.departamento = departamento;
        setValorLimite(valorLimite);
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setValorLimite(double valor){
        this.valorLimite = valor;
    }
}

