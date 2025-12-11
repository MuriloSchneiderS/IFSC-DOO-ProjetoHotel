package model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="produto_copa")
public class ProdutoCopa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descricao;
    @Column
    private String obs;
    @Column
    private float valor;
    @Column
    private char status;
    @JoinColumn
    @OneToMany
    private CopaQuarto copaQuarto;

    public ProdutoCopa(){}
    public ProdutoCopa(int id, String descricao, String obs, float valor, char status, CopaQuarto copaQuarto) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.valor = valor;
        this.status = status;
        this.copaQuarto = copaQuarto;
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

    public CopaQuarto getCopaQuarto() {
        return copaQuarto;
    }

    public void setCopaQuarto(CopaQuarto copaQuarto) {
        this.copaQuarto = copaQuarto;
    }
    
    
}
