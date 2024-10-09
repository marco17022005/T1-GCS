//Ideia: Classe catálogo de pedido e usuario, para a criação, remoção e visualização de pedidos;
import java.util.ArrayList;

public class Catalogo{
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

    public boolean deletarPedido(Pedido p, Usuario u){
        if(u.compare(u, p.getFuncionario()) != 0){
            return false;
        }

        pedidos.remove(p);
        return true;
    }

    public void imprimePedidos(){
        int contagem = 1;
        for(Pedido p : pedidos){
            System.out.println(contagem + "- " + p.toString());
            contagem++;
        }   
    }

    public Pedido retornaPedido(int posicao){
        return pedidos.get(posicao - 1);
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

    public void imprimeFuncionarios(){
        int contagem = 1;
        for(Usuario u : usuarios){
            System.out.println(contagem + "- Nome: " + u.getNome() + " |  ID: " + u.getId());
            contagem++;
        }
    }

    public Usuario retornaUsuario(int posicao){
        return usuarios.get(posicao - 1);
    }
}
