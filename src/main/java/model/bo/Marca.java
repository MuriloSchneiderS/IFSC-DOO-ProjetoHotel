package model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String descricao;
    
    @Column
    private char status;
    
    public Marca(){}
    public Marca(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ( (status == 'A') || (status == 'C') || (status == 'a') || (status == 'c') ) {
            this.status = status;
        }else {
            this.status = 'A';
        }
    }

    @Override
    public String toString() {
        return    "id    = " + this.getId()
                + "Desc. = " + this.getDescricao()
                + "Status= " + this.getStatus();
    }

}
