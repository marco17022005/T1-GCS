import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Catalogo {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Usuario> usuarios;

    public Catalogo() {
        pedidos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void gerarEstatisticas() {
        int totalPedidos = pedidos.size();
        int aprovados = 0, reprovados = 0;
        double valorTotalUltimos30Dias = 0;
        int pedidosUltimos30Dias = 0;
        double maiorValorAberto = 0;
        Pedido pedidoMaiorValorAberto = null;
        Date hoje = new Date();

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        for (Pedido pedido : pedidos) {
            // Contagem de pedidos aprovados e reprovados
            if (pedido.getStatus() == Status.APROVADO) {
                aprovados++;
            } else if (pedido.getStatus() == Status.REPROVADO) {
                reprovados++;
            }

            // Contagem de pedidos nos últimos 30 dias
            try {
                Date dataPedido = formatoData.parse(pedido.getData());
                long diferencaEmMilissegundos = hoje.getTime() - dataPedido.getTime();
                long diasDiferenca = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);

                if (diasDiferenca <= 30) {
                    pedidosUltimos30Dias++;
                    valorTotalUltimos30Dias += pedido.getValorTotal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Detalhes do pedido de maior valor ainda aberto
            if (pedido.getStatus() == Status.ABERTO && pedido.getValorTotal() > maiorValorAberto) {
                maiorValorAberto = pedido.getValorTotal();
                pedidoMaiorValorAberto = pedido;
            }
        }

        // Calcular percentuais
        double percentualAprovados = totalPedidos > 0 ? (double) aprovados / totalPedidos * 100 : 0;
        double percentualReprovados = totalPedidos > 0 ? (double) reprovados / totalPedidos * 100 : 0;

        // Valor médio dos pedidos nos últimos 30 dias
        double valorMedioUltimos30Dias = pedidosUltimos30Dias > 0 ? valorTotalUltimos30Dias / pedidosUltimos30Dias : 0;

        // Exibir estatísticas
        System.out.println("========== Estatísticas Gerais ==========");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.printf("Aprovados: %d (%.2f%%)%n", aprovados, percentualAprovados);
        System.out.printf("Reprovados: %d (%.2f%%)%n", reprovados, percentualReprovados);
        System.out.println("-----------------------------------------");
        System.out.println("Pedidos nos últimos 30 dias: " + pedidosUltimos30Dias);
        System.out.printf("Valor médio dos pedidos nos últimos 30 dias: R$ %.2f%n", valorMedioUltimos30Dias);
        System.out.println("-----------------------------------------");
        System.out.println("Valor total dos pedidos nos últimos 30 dias: R$ " + valorTotalUltimos30Dias);
        System.out.println("-----------------------------------------");

        if (pedidoMaiorValorAberto != null) {
            System.out.println("Pedido de maior valor ainda em aberto:");
            System.out.println(pedidoMaiorValorAberto);
        } else {
            System.out.println("Nenhum pedido aberto com valor.");
        }
        System.out.println("=========================================");
    }

    // Métodos referentes a pedidos:

    public boolean existePedido(int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getIdPedido() == idPedido) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarPedido(Pedido p, Usuario u) {
        if (p.getDepartamento() != u.getDepartamento()) {
            return false;
        } else if (p.getDepartamento().getValorLimite() < p.getValorTotal()) {
            return false;
        }
        pedidos.add(p);
        u.cadastrarPedido(p);
        return true;
    }

    public void consultarListaDeItens(Pedido p) {
        p.mostrarItensDoPedido();
    }

    public boolean deletarPedido(Pedido p, Usuario u) {
        if (u.compare(u, p.getFuncionario()) != 0) {
            return false;
        }
        pedidos.remove(p);
        return true;
    }

    public void imprimePedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos registrados.");
            return;
        }

        int contagem = 1;
        for (Pedido p : pedidos) {
            String cargo = (p.getFuncionario() instanceof Administrador) ? "Administrador" : "Funcionário";
            System.out.println("=========================================");
            System.out.printf("%d. Funcionario que fez o pedido: %s | Cargo: %s%n", contagem, p.getFuncionario().getNome(), cargo);
            System.out.println("=========================================");
            System.out.printf("ID: %d%n", p.getIdPedido());
            System.out.printf("Data: %s%n", p.getData());
            System.out.println("Itens do pedido:");
            for (Item item : p.getItens()) {
                System.out.printf("Descrição: %s | Quantidade: %d | Valor Unitário: R$ %.2f | Valor Total: R$ %.2f%n",
                        item.getDescricao(), item.getQuantidade(), item.getValor(), item.getValorTotal());
            }
            System.out.printf("Valor Total do Pedido: R$ %.2f%n", p.getValorTotal());
            System.out.println("=========================================");
            contagem++;
        }
    }

    public void imprimePedidosEmAberto() {
        int contagem = 1;
        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.ABERTO) {
                System.out.printf("%2d- %s%n", contagem, p.toString());
                contagem++;
            }
        }
    }

    public Pedido retornaPedido(int posicao) {
        return pedidos.get(posicao - 1);
    }

    // Métodos referentes a usuários:

    public boolean cadastrarUsuario(Usuario u) {
        if (!usuarios.isEmpty()) {
            if (consultarUsuario(u.getId()) != null) {
                return false;
            }
        }
        usuarios.add(u);
        return true;
    }

    public Usuario consultarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public void imprimeFuncionarios() {
        int contagem = 1;
        for (Usuario u : usuarios) {
            System.out.printf("%2d- Nome: %-20s | ID: %10d%n", contagem, u.getNome(), u.getId());
            contagem++;
        }
    }

    public Usuario retornaUsuario(int posicao) {
        return usuarios.get(posicao - 1);
    }

    public String contarPedidos() {
        int totalPedidos = pedidos.size();
        int aprovados = 0;
        int reprovados = 0;
        int abertos = 0;

        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.APROVADO) {
                aprovados++;
            } else if (p.getStatus() == Status.REPROVADO) {
                reprovados++;
            } else if (p.getStatus() == Status.ABERTO) {
                abertos++;
            }
        }

        return String.format("Total de pedidos: %d%nAprovados: %d%nReprovados: %d%nAbertos: %d%n", totalPedidos, aprovados, reprovados, abertos);
    }

    public String contarPedidosPorStatus(Status status) {
        int contagem = 0;

        for (Pedido p : pedidos) {
            if (p.getStatus() == status) {
                contagem++;
            }
        }

        return String.format("Total de pedidos com status %s: %d", status, contagem);
    }

}
