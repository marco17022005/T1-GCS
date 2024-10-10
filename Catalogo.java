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

    public boolean existePedido(int idPedido){
        for(Pedido p : pedidos){
            if(p.getIdPedido() == idPedido){
                return true;
            }
        }
        return false;
    }

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
        if(u.compare(u, p.getFuncionario()) != 0){ //Se o funcionario que está solicitando o pedido não for o mesmo que criou o pedido, irá retornar falso
            return false;
        }

        pedidos.remove(p);
        return true;
    }

    public void imprimePedidos(){
        //Imprime todos os pedidos
        //A contagem serve para auxiliar a visualização e escolha do pedido no terminal 
        int contagem = 1;
        for(Pedido p : pedidos){
            System.out.printf("%d- %-20s %5d %5d%n", contagem, p.getFuncionario(), p.getDepartamento(), p.getData());
            contagem++;
        }   
    }

    public void imprimePedidosEmAberto(){
        //Imprime todos os pedidos EM ABERTO -> APENAS PARA ADMINISTRADORES
        //A contagem serve para auxiliar a visualização e escolha do pedido no terminal 
        int contagem = 1;
        for(Pedido p : pedidos){
            if(p.getStatus() == Status.ABERTO){
                System.out.printf("%2d- %s%n", contagem, p.toString());
                contagem++;
            }
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
        //Imprime todos os usuarios
        //A contagem serve para auxiliar a visualização e escolha do Usuario no terminal 
        int contagem = 1;
        for(Usuario u : usuarios){
            System.out.printf("%2d- Nome: %-20s | ID: %10d%n", contagem, u.getNome(), u.getId());
            contagem++;
        }
    }

    public Usuario retornaUsuario(int posicao){
        //Posição - 1 porque a visualização no terminal começa pelo 1, não pelo 0  
        return usuarios.get(posicao - 1);
    }
}
