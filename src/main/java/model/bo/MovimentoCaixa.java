package model;

import java.security.Timestamp;

public class MovimentoCaixa {
    private int id;
    private Timestamp dataHoraMovimento;
    private float valor;
    private String descricao, obs;
    private char status;

    public MovimentoCaixa(int id, Timestamp dataHoraMovimento, float valor, String descricao, String obs, char status) {
        this.id = id;
        this.dataHoraMovimento = dataHoraMovimento;
        this.valor = valor;
        this.descricao = descricao;
        this.obs = obs;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    public void setDataHoraMovimento(Timestamp dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
}
