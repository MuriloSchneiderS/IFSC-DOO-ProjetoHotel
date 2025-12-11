package model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Hospede extends Pessoa{
    @Column(name="razao_social")
    private String razaoSocial;
    @Column
    private String cnpj;
    @Column(name="inscricao_estadual")
    private String inscricaoEstadual;
    @Column
    private String contato;
    @Column
    private String usuario;
    @Column
    private String senha;

    public Hospede() {
    }

    public Hospede( int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

    @Override
    public String toString() {
        return  super.toString() + 
                "\ncnpj   = " + this.getCnpj()+
                "\nCep    = " + this.getCnpj()+
                "\nCidade = " + this.getCidade()+
                "\nBairro = " + this.getBairro()+
                "\nLogradouro = " + this.getLogradouro()+
                "\nComplemento= " + this.getComplemento()+
                "\nContato    = " + this.getContato()+
                "\nStatus = " + this.getStatus();
    }
    
    
    
    
    
}
