public class Funcionario extends Usuario {
    private String departamento;

    public Funcionario(String nome, int id, String departamento) {
        super(nome, id);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Funcionario: " + getNome() + ", Departamento: " + departamento;
    }
}
