package model;

public class Freelancer {
    private int id;
    private String nome;
    private String especialidade;
    private String pais;
    private double taxaPorHora;

    public Freelancer(int id, String nome, String especialidade, String pais, double taxaPorHora) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.pais = pais;
        this.taxaPorHora = taxaPorHora;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public String getPais() { return pais; }
    public double getTaxaPorHora() { return taxaPorHora; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public void setPais(String pais) { this.pais = pais; }
    public void setTaxaPorHora(double taxaPorHora) { this.taxaPorHora = taxaPorHora; }
}
