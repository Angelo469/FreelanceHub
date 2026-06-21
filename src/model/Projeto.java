package model;

import java.time.LocalDate;

public class Projeto {
    public enum Status { EM_ANDAMENTO, CONCLUIDO, ATRASADO }

    private int id;
    private String nome;
    private String cliente;
    private LocalDate prazo;
    private double valorDolar;
    private Status status;

    public Projeto(int id, String nome, String cliente, LocalDate prazo, double valorDolar, Status status) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.prazo = prazo;
        this.valorDolar = valorDolar;
        this.status = status;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCliente() { return cliente; }
    public LocalDate getPrazo() { return prazo; }
    public double getValorDolar() { return valorDolar; }
    public Status getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
    public void setValorDolar(double valorDolar) { this.valorDolar = valorDolar; }
    public void setStatus(Status status) { this.status = status; }
}
