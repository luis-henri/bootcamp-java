package repository;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    // Conexão em arquivo local para persistir dados mesmo após fechar o app
    private static final String URL = "jdbc:h2:./banco_social_dogs";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public UsuarioRepository() {
        criarTabela();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void criarTabela() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL
                )
                """;
        try (var conn = getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();

            // Recuperar o ID gerado automaticamente
            try (var generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                }
            }
            System.out.println("Usuário salvo: " + usuario.getNome());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (var conn = getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id); //aqui substitiu a ? pelo ID que o usuário irá escolher
            stmt.executeUpdate();
            System.out.println("Usuário deletado (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}