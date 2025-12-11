package model.bo;

import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Receber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="data_hora_cadastro")
    private Timestamp dataHoraCadastro;
    @Column(name="valor_original")
    private float valorOriginal;
    @Column
    private float desconto;
    @Column
    private float acrescimo;
    @Column(name="valor_pago")
    private float valorpago;
    @Column
    private String obs;
    @Column
    private char status;
    @JoinColumn
    @OneToOne
    private Check check;
    @JoinColumn
    @OneToOne
    private MovimentoCaixa movimentoCaixa;

    public Receber(){}
    public Receber(int id, Timestamp dataHoraCadastro, float valorOriginal, float desconto, float acrescimo, float valorpago, String obs, char status, Check check) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.valorOriginal = valorOriginal;
        this.desconto = desconto;
        this.acrescimo = acrescimo;
        this.valorpago = valorpago;
        this.obs = obs;
        this.status = status;
        this.check = check;
    }
    public Receber(int id, Timestamp dataHoraCadastro, float valorOriginal, float desconto, float acrescimo, float valorpago, String obs, char status, Check check, MovimentoCaixa movimentoCaixa) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.valorOriginal = valorOriginal;
        this.desconto = desconto;
        this.acrescimo = acrescimo;
        this.valorpago = valorpago;
        this.obs = obs;
        this.status = status;
        this.check = check;
        this.movimentoCaixa = movimentoCaixa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public float getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(float valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(float acrescimo) {
        this.acrescimo = acrescimo;
    }

    public float getValorpago() {
        return valorpago;
    }

    public void setValorpago(float valorpago) {
        this.valorpago = valorpago;
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

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public MovimentoCaixa getMovimentoCaixa() {
        return movimentoCaixa;
    }

    public void setMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
        this.movimentoCaixa = movimentoCaixa;
    }
    
    
}
