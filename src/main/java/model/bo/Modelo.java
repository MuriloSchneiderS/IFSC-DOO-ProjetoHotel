package model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Modelo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descricao;
    @Column
    private char status;

    @JoinColumn
    @ManyToOne
    private Marca marca;

    public Modelo(){}
    public Modelo(int id, String descricao, Marca marca) {
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
        this.status = 'A';
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ((status == 'A') || (status == 'C') || (status == 'a') || (status == 'c')) {
            this.status = status;
        } else {
            this.status = 'A';
        }
    }

    @Override
    public String toString() {
        return "id     = " + this.getId()
                + "\nDescr. = " + this.getDescricao()
                + "\nMarca  = " + this.getMarca().getDescricao()
                + "\nStatus = " + this.getStatus();
    }

}