//Ideia: Classe catálogo de pedido e usuario, para a criação, remoção e visualização de pedidos;
import java.util.ArrayList;
public class Catalogo {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Usuario> usuarios;

    public Catalogo(){
        pedidos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    //Métodos referentes a pedidos: 

    public boolean registrarPedido(Pedido p, Usuario u){
        if(p.getDepartamento() != u.getDepartamento()){ //Se o departamento do pedido for diferente do departamento do usuário, retorna falso
            return false;  
        } else if(p.getDepartamento().getValorLimite() < p.getValorTotal()){ //Se o valor total do pedido for excedente ao valor limite do departamento, retorna falso
            return false;
        }
        pedidos.add(p);
        u.cadastrarPedido(p); 
        return true; // Pedido é cadastrado tanto para o usuário solicitante também
    }

    public void consultarListaDeItens(Pedido p){
        p.mostrarItensDoPedido();
    }

    
    //Métodos referentes à usuários:

    public boolean cadastrarUsuario(Usuario u){
        if(!usuarios.isEmpty()){
            if(consultarUsuario(u.getId()) != null){
                return false;
            }
        }
        usuarios.add(u);
        return true;
    }

    public Usuario consultarUsuario(int id){
        for(Usuario u : usuarios){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    
}
