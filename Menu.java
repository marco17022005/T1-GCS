import java.util.Scanner;

public class Menu {
    private Catalogo catalogo;
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioAtual;
    public Menu(){
        catalogo = new Catalogo();
        executar();
    }

    public void executar(){
        inicializarObjetos();
        menuInicial();
    }

    /*Menu Inicial: Funciona como "login", apenas irá mostrar os usuários disponíveis
    e "logar" como o usuario escolhido no menu principal */
    public void menuInicial(){
        System.out.println("Digite o usuario atual pelo seu número:   1 - 15");
        mostrarFuncionarios();
        usuarioAtual = catalogo.retornaUsuario(retornarValorScanner());
        menuPrincipal();
    }

    public void menuPrincipal(){
        System.out.println("Usuario atual: " + usuarioAtual.getNome());

        System.out.println("Digite a opção desejada: ");
        System.out.println("1- Registrar pedido de aquisição");
        System.out.println("2- Deletar pedido em aberto");
        System.out.println("3- Trocar usuário");
        if(usuarioAtual instanceof Administrador){
            System.out.println("4- Analisar estatísticas gerais");
            System.out.println("5- Visualizar pedidos em aberto");
        }

        switch (retornarValorScanner()) {
            case 1-> {
                registrarPedido();
            } 
            case 2 -> {
                deletarPedido();
            } case 3 -> {
                menuInicial();
            } case 4 -> {

            } case 5 -> {
                administrarPedidos();
            }
            default -> {
                System.out.println("Opção inválida.");
                menuPrincipal();
            }
        }

    }

    public void registrarPedido(){
        Item item = null;
        System.out.println("Digite a data atual: ");
        String data = scanner.nextLine();
        Pedido p = new Pedido(usuarioAtual, usuarioAtual.getDepartamento(), data);
        p.setStatus(Status.ABERTO);
        System.out.println("Deseja adicionar 1 item ao pedido? ");
        System.out.println("1- Sim     | 2- Não");
        while(retornarValorScanner() == 1){
            System.out.println("Digite a descrição do item: ");
            String descricao = scanner.nextLine();
            System.out.println("Digite o valor unitário: ");
            double valorUnitario = scanner.nextDouble();
            System.out.println("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();
            item = new Item(descricao, valorUnitario);
            item.setValorTotal(valorUnitario, quantidade);
            p.cadastrarItemNoPedido(item);

            System.out.println("Deseja adicionar mais 1 item ao pedido? ");
            System.out.println("1- Sim     | 2- Não");
            retornarValorScanner();
        }
        catalogo.registrarPedido(p, usuarioAtual);
        menuPrincipal();
    }   

    public void deletarPedido(){
        System.out.println("Digite o pedido que deseja deletar: ");
        mostrarPedidos();
        if(!catalogo.deletarPedido(catalogo.retornaPedido(retornarValorScanner()), usuarioAtual)){
            System.out.println("O usuário atual não possui permissão de deletar o pedido informado.\nPermissão delegada ao usuário " + catalogo.retornaPedido(retornarValorScanner()).getFuncionario());
            menuPrincipal();
        } else {
            System.out.println("Pedido deletado.");
        }
        menuPrincipal();
    }

    public void mostrarFuncionarios(){
        catalogo.imprimeFuncionarios();
    }

    public void mostrarPedidos(){
        catalogo.imprimePedidos();
    }

    public int retornarValorScanner(){
        int resposta = scanner.nextInt();
        scanner.nextLine();
        return resposta;
    }

    public void administrarPedidos(){
        System.out.println("1- Voltar ao menu");
        System.out.println("2- Avaliar pedido em aberto");
        
        switch (retornarValorScanner()) {
            case 1 -> {
                menuPrincipal();
            } case 2 -> {
                System.out.println("------------------------");
                System.out.println("Pedidos em aberto: ");
                System.out.println("Digite o número do pedido para atualizar o status.");
                catalogo.imprimePedidosEmAberto();
                int resposta = retornarValorScanner(); //Vai guardar o pedido escolhido
                System.out.println("Deseja: \n 1- Aprovar  | 2- Rejeitar");
                retornarValorScanner();
                scanner.nextLine();
                System.out.println("Digite a data:");
                String data = scanner.nextLine();
                if(resposta == 1){
                    catalogo.retornaPedido(resposta).setStatus(usuarioAtual, Status.APROVADO, data);
                    System.out.println("Status do pedido " + catalogo.retornaPedido(resposta) + " atualizado para " + catalogo.retornaPedido(resposta).getStatus());
                } else if (resposta == 2){
                    catalogo.retornaPedido(resposta).setStatus(usuarioAtual, Status.REPROVADO, data);
                    System.out.println("Status do pedido " + catalogo.retornaPedido(resposta) + " atualizado para " + catalogo.retornaPedido(resposta).getStatus());
                }
                administrarPedidos();
            }
        
            default -> {
                System.out.println("Opção inválida.");
                menuPrincipal();
            }
        }
    
    }









    public void inicializarObjetos(){
        //Criando Departamentos
        Departamento d1 = new Departamento("Financeiro", 100000);
        Departamento d2 = new Departamento("RH", 100000);
        Departamento d3 = new Departamento("Manutenção", 100000);    
        Departamento d4 = new Departamento("Engenharia", 100000);
        Departamento d5 = new Departamento("Logística", 100000);

        //Criando Usuários, dividos entre administradores e funciuonários
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
        
        
        //Cadastrando os usuários
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

        //Relacionando Usuários e Departamentos
        //d1- financeiro | d2 - RH | d3 - manutenção | d4 - engenharia | d5 - logística
        
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
