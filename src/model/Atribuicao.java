package model;

public class Atribuicao {
    private int id;
    private Freelancer freelancer;
    private Projeto projeto;
    private int horasTrabalhadas;

    public Atribuicao(int id, Freelancer freelancer, Projeto projeto, int horasTrabalhadas) {
        this.id = id;
        this.freelancer = freelancer;
        this.projeto = projeto;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public int getId() { return id; }
    public Freelancer getFreelancer() { return freelancer; }
    public Projeto getProjeto() { return projeto; }
    public int getHorasTrabalhadas() { return horasTrabalhadas; }

    public void setId(int id) { this.id = id; }
    public void setFreelancer(Freelancer freelancer) { this.freelancer = freelancer; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }
    public void setHorasTrabalhadas(int horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    public double calcularValorTotal(double taxaUsdParaBrl) {
        return freelancer.getTaxaPorHora() * horasTrabalhadas * taxaUsdParaBrl;
    }
}
