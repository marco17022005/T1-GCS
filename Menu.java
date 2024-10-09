import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Catalogo catalogo;
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioAtual;

    public Menu() {
        catalogo = new Catalogo();
        executar();
    }

    public void executar() {
        inicializarObjetos();
        menuInicial();
    }

    public void menuInicial() {
        System.out.println("Digite o usuário atual pelo seu número:   1 - 15");
        mostrarFuncionarios();
        usuarioAtual = catalogo.retornaUsuario(validarUsuarioEscolhido());
        menuPrincipal();
    }

    public void menuPrincipal() {
        System.out.println("Usuário atual: " + usuarioAtual.getNome());
        boolean continuarNoMenu = true;
        while (continuarNoMenu == true) {
            System.out.println("Digite a opção desejada: ");
            System.out.println("0- Sair do sistema");
            System.out.println("1- Registrar pedido de aquisição");
            System.out.println("2- Deletar pedido em aberto");
            System.out.println("3- Trocar usuário");
            
            if (usuarioAtual instanceof Administrador) {
                System.out.println("4- Analisar estatísticas gerais");
                System.out.println("5- Visualizar pedidos em aberto");
            }

            switch (validarOpcaoMenu()) {
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    continuarNoMenu = false; 
                }
                case 1 -> registrarPedido();
                case 2 -> deletarPedido();
                case 3 -> menuInicial();
                case 4 -> analisarEstatisticas();
                case 5 -> administrarPedidos();
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void registrarPedido() {
        Item item = null;
        System.out.println("Digite a data atual: ");
        String data = scanner.nextLine();
        Pedido p = new Pedido(usuarioAtual, usuarioAtual.getDepartamento(), data);
        p.setStatus(Status.ABERTO);

        while (true) {
            System.out.println("Deseja adicionar 1 item ao pedido? ");
            System.out.println("1- Sim     | 2- Não");
            if (validarOpcaoMenu() == 2) break;

            System.out.println("Digite a descrição do item: ");
            String descricao = scanner.nextLine();

            System.out.println("Digite o valor unitário: ");
            double valorUnitario = validarValor();

            System.out.println("Digite a quantidade desejada: ");
            int quantidade = validarQuantidade();

            item = new Item(descricao, valorUnitario);
            item.setValorTotal(valorUnitario, quantidade);
            p.cadastrarItemNoPedido(item);
        }
        catalogo.registrarPedido(p, usuarioAtual);
        System.out.println("Pedido registrado com sucesso!");
    }   

    public void deletarPedido() {
        System.out.println("Digite o pedido que deseja deletar: ");
        mostrarPedidos();
        int pedidoId = validarPedidoEscolhido();

        if (!catalogo.deletarPedido(catalogo.retornaPedido(pedidoId), usuarioAtual)) {
            System.out.println("O usuário atual não possui permissão para deletar o pedido informado.");
        } else {
            System.out.println("Pedido deletado com sucesso.");
        }
    }

    public void mostrarFuncionarios() {
        catalogo.imprimeFuncionarios();
    }

    public void mostrarPedidos() {
        catalogo.imprimePedidos();
    }

    public int validarOpcaoMenu() {
        while (true) {
            try {
                System.out.print("Escolha uma opção: ");
                int resposta = scanner.nextInt();
                scanner.nextLine();
                return resposta;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    public int validarUsuarioEscolhido() {
        while (true) {
            int usuarioEscolhido = validarOpcaoMenu();
            if (usuarioEscolhido >= 1 && usuarioEscolhido <= 15) {
                return usuarioEscolhido;
            } else {
                System.out.println("Número de usuário inválido. Por favor, escolha um número entre 1 e 15.");
            }
        }
    }

    public int validarPedidoEscolhido() {
        while (true) {
            int pedidoEscolhido = validarOpcaoMenu();
            if (catalogo.existePedido(pedidoEscolhido)) {
                return pedidoEscolhido;
            } else {
                System.out.println("Número de pedido inválido. Por favor, escolha um pedido existente.");
            }
        }
    }

    public double validarValor() {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor < 0) {
                    throw new InputMismatchException();
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um valor válido.");
                scanner.nextLine();
            }
        }
    }

    public int validarQuantidade() {
        while (true) {
            try {
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                if (quantidade <= 0) {
                    throw new InputMismatchException();
                }
                return quantidade;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira uma quantidade válida.");
                scanner.nextLine();
            }
        }
    }

    public void analisarEstatisticas() {
        System.out.println("Analisando estatísticas gerais...");
        System.out.println("Total de pedidos: " + catalogo.contarPedidos());
        System.out.println("Total de pedidos abertos: " + catalogo.contarPedidosPorStatus(Status.ABERTO));
    }

    public void administrarPedidos() {
        while (true) {
            System.out.println("1- Voltar ao menu");
            System.out.println("2- Avaliar pedido em aberto");

            switch (validarOpcaoMenu()) {
                case 1 -> {
                    return;
                }
                case 2 -> {
                    System.out.println("------------------------");
                    System.out.println("Pedidos em aberto: ");
                    catalogo.imprimePedidosEmAberto();
                    int resposta = validarPedidoEscolhido();
                    System.out.println("Deseja: \n 1- Aprovar  | 2- Rejeitar");
                    int acao = validarOpcaoMenu();
                    scanner.nextLine();

                    System.out.println("Digite a data:");
                    String data = scanner.nextLine();

                    if (acao == 1) {
                        catalogo.retornaPedido(resposta).setStatus(usuarioAtual, Status.APROVADO, data);
                        System.out.println("Status do pedido " + catalogo.retornaPedido(resposta) + " atualizado para " + catalogo.retornaPedido(resposta).getStatus());
                    } else if (acao == 2) {
                        catalogo.retornaPedido(resposta).setStatus(usuarioAtual, Status.REPROVADO, data);
                        System.out.println("Status do pedido " + catalogo.retornaPedido(resposta) + " atualizado para " + catalogo.retornaPedido(resposta).getStatus());
                    }
                }
                default -> {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }

    public void inicializarObjetos() {
        Departamento d1 = new Departamento("Financeiro", 100000);
        Departamento d2 = new Departamento("RH", 100000);
        Departamento d3 = new Departamento("Manutenção", 100000);    
        Departamento d4 = new Departamento("Engenharia", 100000);
        Departamento d5 = new Departamento("Logística", 100000);

        Usuario usuario1 = new Administrador("Ana Costa", 11111);
        Usuario usuario2 = new Funcionario("Lucas Almeida", 22222);
        Usuario usuario3 = new Administrador("Mariana Silva", 33333);
        Usuario usuario4 = new Funcionario("Pedro Oliveira", 44444);
        Usuario usuario5 = new Administrador("Juliana Santos", 55555);
        Usuario usuario6 = new Funcionario("Felipe Pereira", 66666);
        Usuario usuario7 = new Administrador("Carla Lima", 77777);
        Usuario usuario8 = new Funcionario("Tiago Rocha", 88888);
        Usuario usuario9 = new Administrador("Fernanda Mendes", 99999);
        Usuario usuario10 = new Funcionario("Roberto Carvalho", 00100);
        Usuario usuario11 = new Administrador("Sofia Martins", 00200);
        Usuario usuario12 = new Funcionario("Diego Nascimento", 00300);
        Usuario usuario13 = new Administrador("Isabela Ferreira", 00400);
        Usuario usuario14 = new Funcionario("Gustavo Ribeiro", 00500);
        Usuario usuario15 = new Administrador("Camila Souza", 00600);

        catalogo.cadastrarUsuario(usuario1);
        catalogo.cadastrarUsuario(usuario2);
        catalogo.cadastrarUsuario(usuario3);
        catalogo.cadastrarUsuario(usuario4);
        catalogo.cadastrarUsuario(usuario5);
        catalogo.cadastrarUsuario(usuario6);
        catalogo.cadastrarUsuario(usuario7);
        catalogo.cadastrarUsuario(usuario8);
        catalogo.cadastrarUsuario(usuario9);
        catalogo.cadastrarUsuario(usuario10);
        catalogo.cadastrarUsuario(usuario11);
        catalogo.cadastrarUsuario(usuario12);
        catalogo.cadastrarUsuario(usuario13);
        catalogo.cadastrarUsuario(usuario14);
        catalogo.cadastrarUsuario(usuario15);

        usuario1.setDepartamento(d1);
        usuario2.setDepartamento(d2);
        usuario3.setDepartamento(d3);
        usuario4.setDepartamento(d4);
        usuario5.setDepartamento(d5);
        usuario6.setDepartamento(d1);
        usuario7.setDepartamento(d2);
        usuario8.setDepartamento(d3);
        usuario9.setDepartamento(d4);
        usuario10.setDepartamento(d5);
        usuario11.setDepartamento(d1);
        usuario12.setDepartamento(d2);
        usuario13.setDepartamento(d3);
        usuario14.setDepartamento(d4);
        usuario15.setDepartamento(d5);

        d1.cadastrarFuncionario(usuario1);
        d1.cadastrarFuncionario(usuario6);
        d1.cadastrarFuncionario(usuario11);

        d2.cadastrarFuncionario(usuario2);
        d2.cadastrarFuncionario(usuario7);
        d2.cadastrarFuncionario(usuario12);

        d3.cadastrarFuncionario(usuario3);
        d3.cadastrarFuncionario(usuario8);
        d3.cadastrarFuncionario(usuario13);

        d4.cadastrarFuncionario(usuario4);
        d4.cadastrarFuncionario(usuario9);
        d4.cadastrarFuncionario(usuario14);

        d5.cadastrarFuncionario(usuario5);
        d5.cadastrarFuncionario(usuario10);
        d5.cadastrarFuncionario(usuario15);
    }
}
