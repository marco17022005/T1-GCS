public class Funcionario extends Usuario {

    public Funcionario(String nome, int id) {
        super(nome, id);
    }

    @Override
    public String toString() {
        return "Funcionario: " + getNome();
    }
}
