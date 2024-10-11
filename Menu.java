import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Catalogo catalogo;
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioAtual;

    public Menu() {
        catalogo = new Catalogo();
        inicializarObjetos();
        executar();
    }

    public void executar() {
        menuInicial();
    }

    public void menuInicial() {
        System.out.println("Selecione o usuário atual pelo número (1-15):");
        mostrarFuncionarios();
        usuarioAtual = catalogo.retornaUsuario(validarUsuarioEscolhido());
        menuPrincipal();
    }

    public void menuPrincipal() {
        boolean continuarNoMenu = true;
        while (continuarNoMenu) {
            System.out.println("============================================");
            System.out.println("Usuário atual: " + usuarioAtual.getNome() + " (" + (usuarioAtual instanceof Administrador ? "Administrador" : "Funcionário") + ")");
            System.out.println("============================================");
            System.out.println("Escolha uma opção:");
            System.out.println("0- Sair");
            System.out.println("1- Registrar pedido");
            System.out.println("2- Deletar pedido");
            System.out.println("3- Trocar usuário");
            System.out.println("4- Listar todos os pedidos");

            if (usuarioAtual instanceof Administrador) {
                System.out.println("5- Analisar estatísticas gerais");
                System.out.println("6- Visualizar pedidos em aberto");
            }

            switch (validarOpcaoMenu()) {
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    continuarNoMenu = false;
                }
                case 1 -> registrarPedido();
                case 2 -> deletarPedido();
                case 3 -> menuInicial();
                case 4 -> listarTodosPedidos();
                case 5 -> {
                    if (usuarioAtual instanceof Administrador) {
                        analisarEstatisticas();
                    } else {
                        System.out.println("Acesso negado: Somente administradores podem acessar esta função.");
                    }
                }
                case 6 -> {
                    if (usuarioAtual instanceof Administrador) {
                        visualizarPedidosEmAberto();
                    } else {
                        System.out.println("Acesso negado: Somente administradores podem acessar esta função.");
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void registrarPedido() {
        System.out.println("Digite a data do pedido (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.println("Digite o ID do pedido: ");
        int idPedido = scanner.nextInt();
        Pedido p = new Pedido(idPedido, usuarioAtual, usuarioAtual.getDepartamento(), data);
        p.setStatus(Status.ABERTO);

        while (true) {
            System.out.println("Deseja adicionar um item ao pedido?");
            System.out.println("1- Sim | 2- Não");
            if (validarOpcaoMenu() == 2) break;

            System.out.println("Digite a descrição do item: ");
            String descricao = scanner.nextLine();

            System.out.println("Digite o valor unitário do item: ");
            double valorUnitario = validarValor();

            System.out.println("Digite a quantidade desejada: ");
            int quantidade = validarQuantidade();

            Item item = new Item(descricao, valorUnitario, quantidade);
            p.adicionarItem(item);
        }
        catalogo.registrarPedido(p, usuarioAtual);
        System.out.println("Pedido registrado com sucesso!");
    }

    public void deletarPedido() {
        System.out.println("Digite o ID do pedido que deseja deletar: ");
        mostrarPedidos();
        int pedidoId = validarPedidoEscolhido();

        if (!catalogo.deletarPedido(catalogo.retornaPedido(pedidoId), usuarioAtual)) {
            System.out.println("O usuário atual não possui permissão para deletar o pedido informado.");
        } else {
            System.out.println("Pedido deletado com sucesso.");
        }
    }

    public void listarTodosPedidos() {
        catalogo.imprimePedidos();
    }

    public void visualizarPedidosEmAberto() {
        catalogo.imprimePedidosEmAberto();
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
                scanner.nextLine(); // Limpar o buffer
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
        catalogo.gerarEstatisticas();
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
