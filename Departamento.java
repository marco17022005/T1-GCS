import java.util.ArrayList;
public class Departamento {
    private String departamento; // Financeiro, RH, Manutenção etc
    private double valorLimite; // 
    private ArrayList<Usuario> usuarios;

    public Departamento(String departamento, double valorLimite){
        this.departamento = departamento;
        setValorLimite(valorLimite);
        usuarios = new ArrayList<>();
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setValorLimite(double valor){
        this.valorLimite = valor;
    }

    public double getValorLimite(){
        return valorLimite;
    }

    public boolean cadastrarFuncionario(Usuario u){
        for(Usuario usuario : usuarios){
            if(usuario.getId() == u.getId()){
                return false;
            }
        }
        
        usuarios.add(u);
        return true;
    }
}

