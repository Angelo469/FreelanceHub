package controller;

import repository.FreelancerDAO;
import model.Freelancer;
import java.sql.SQLException;
import java.util.List;

public class FreelancerController {
    private FreelancerDAO dao = new FreelancerDAO();


    public void cadastrar(String nome, String especialidade, String pais, double taxa) throws SQLException {

        Freelancer f = new Freelancer(0, nome, especialidade, pais, taxa);
        dao.inserir(f);

    }

    public List<Freelancer> listarTodos() throws SQLException {
        return dao.listarTodos();

    }

    public void atualizar(Freelancer f) throws SQLException {
        dao.atualizar(f);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }


}

