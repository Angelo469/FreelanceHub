package controller;

import repository.AtribuicaoDAO;
import model.Atribuicao;
import model.Freelancer;
import model.Projeto;
import java.sql.SQLException;
import java.util.List;

public class AtribuicaoController {


    private AtribuicaoDAO dao = new AtribuicaoDAO();

public void Atribuir(Freelancer freelancer, Projeto projeto, int horas) throws SQLException {
    Atribuicao a = new Atribuicao(0, freelancer, projeto, horas);
    dao.inserir(a);
}

public List<Atribuicao>listarTodos() throws SQLException{
    return dao.listarTodos();
}

public void deletar(int id) throws SQLException {
    dao.deletar(id);
}

}
