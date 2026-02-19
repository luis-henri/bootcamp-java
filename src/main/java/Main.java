import service.UsuarioService;
import model.Usuario;
import org.h2.tools.Server; // Importante para ver no navegador

import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // 1. Inicia o H2 para eu ver o banco de dados'
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Console disponível em: http://localhost:8082");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("\n=== Sistema de Socialização de Cães (H2 Local) ===");

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Editar Usuário");
            System.out.println("4 - Excluir Usuário");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            // Tratamento de erro caso o usuário digite letra em vez de número
            if(scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcao = -1; // Força cair no default
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();

                    service.registrarUsuario(nome, email);
                    break;

                case 2:
                    System.out.println("\n--- Lista de Usuários ---");
                    for (Usuario u : service.buscarTodosUsuarios()) {
                        System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail());
                    }
                    break;

                case 3:
                    System.out.println("\n--- Editar Usuário---");

                    //Pedir o ID de quem quer alterar
                    System.out.println("Digite o ID do usuário que irá ser alterado: ");
                    Long idEditar = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Digite o novo nome: ");
                    String nomeEditar = scanner.nextLine();

                    System.out.println("Digite o novo email: ");
                    String emailEditar = scanner.nextLine();

                    service.editarUsuario(idEditar, nomeEditar, emailEditar);
                    break;

                case 4:
                    System.out.println("\n--- Excluir Usuário---");
                    System.out.println("Digite o ID do usuário que irá ser excluído: ");
                    Long idExcluir = scanner.nextLong();
                    scanner.nextLine();

                    service.removerUsuario(idExcluir);

                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}