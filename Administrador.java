import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Administrador extends Usuario{

    public Administrador(String nome, int id){
        super(nome, id);
    }

    public void listarPedidosEntreDatas(String date1, String date2, List<Pedido> pedidos) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            //Converte as strings para objetos Date
            Date dataInicial = formato.parse(date1);
            Date dataFinal = formato.parse(date2);
            
            //Verifica cada pedido e imprime os que estão entre as duas datas
            for (Pedido pedido : pedidos) {
                Date dataPedido = formato.parse(pedido.getData()); 
                
                if ((dataPedido.equals(dataInicial) || dataPedido.after(dataInicial)) && 
                    (dataPedido.equals(dataFinal) || dataPedido.before(dataFinal))) {
                    System.out.println(pedido); 
                }
            }
        } catch (ParseException e) {
            System.out.println("Erro ao interpretar as datas: " + e.getMessage());
        }
    }

    public void listarPedidoPorFuncionario(Funcionario funcionario, List <Pedido> pedidos){
        for (Pedido pedido : pedidos) {
            if (pedido.getFuncionario().equals(funcionario)) {
                System.out.println(pedido);
            }
        }
    }

    public void buscarPedidoPorDescricao(String descricao, List<Pedido> pedidos){
        for (Pedido pedido : pedidos) {
            for (Item item : pedido.getItens()) {
                if (item.getDescricao().contains(descricao)) {
                    System.out.println(pedido);
                    break; // Para evitar imprimir o mesmo pedido várias vezes
                }
            }
        }
    }

    public void visualizarPedidosEmAberto(List<Pedido> pedidos){
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == Status.ABERTO) {
                System.out.println(pedido);
            }
        }
    }

    public void manipularStatusPedido(Pedido pedido, Status novoStatus){
        if (pedido.getStatus() == Status.ABERTO) {
            pedido.setStatus(novoStatus);
            System.out.println("Pedido atualizado para o status: " + novoStatus.getStatus());
        } else {
            System.out.println("O pedido já foi concluído e não pode ser alterado.");
        }
    }
}