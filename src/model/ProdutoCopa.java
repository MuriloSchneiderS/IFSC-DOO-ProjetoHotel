package model;

public class ProdutoCopa {
    private int id;
    private String descricao, obs;
    private float valor;
    private char status;
    private int copaQuartoId;

    public ProdutoCopa(){}
    public ProdutoCopa(int id, String descricao, String obs, float valor, char status, int copaQuartoId) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.valor = valor;
        this.status = status;
        this.copaQuartoId = copaQuartoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getCopaQuartoId() {
        return copaQuartoId;
    }

    public void setCopaQuartoId(int copaQuartoId) {
        this.copaQuartoId = copaQuartoId;
    }
    
    
}
