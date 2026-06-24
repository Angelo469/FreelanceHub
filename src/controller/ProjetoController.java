package controller;

import repository.ProjetoDAO;
import model.Projeto;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public class ProjetoController {

    private final ProjetoDAO dao = new ProjetoDAO();

    public void cadastrar(String nome, String cliente, LocalDate prazo, double valorDolar, Projeto.Status status, int freelancerId) throws SQLException {
        Projeto p = new Projeto(0, nome, cliente, prazo, valorDolar, status, freelancerId); // <--- CORREÇÃO AQUI
        dao.inserir(p);
    }
    public List<Projeto> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public void atualizar(Projeto p) throws SQLException {
        dao.atualizar(p);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}