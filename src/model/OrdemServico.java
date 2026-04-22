package model;

public class OrdemServico {
    private int id;
    private String dataHoraCadastro, dataHoraPrevistaInicio, dataHoraPrevistaTermino;
    private String obs;
    private char status;
    private Servico servico;
    private Quarto quarto;
    private Check check;

    public OrdemServico(){};
    public OrdemServico(int id, String dataHoraCadastro, String dataHoraPrevistaInicio, String dataHoraPrevistaTermino, String obs, char status, Servico servico, Check check) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataHoraPrevistaInicio = dataHoraPrevistaInicio;
        this.dataHoraPrevistaTermino = dataHoraPrevistaTermino;
        this.obs = obs;
        this.status = status;
        this.servico = servico;
        this.check = check;
    }
    public OrdemServico(int id, String dataHoraCadastro, String dataHoraPrevistaInicio, String dataHoraPrevistaTermino, String obs, char status, Servico servico, Quarto quarto, Check check) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataHoraPrevistaInicio = dataHoraPrevistaInicio;
        this.dataHoraPrevistaTermino = dataHoraPrevistaTermino;
        this.obs = obs;
        this.status = status;
        this.servico = servico;
        this.quarto = quarto;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(String dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getDataHoraPrevistaInicio() {
        return dataHoraPrevistaInicio;
    }

    public void setDataHoraPrevistaInicio(String dataHoraPrevistaInicio) {
        this.dataHoraPrevistaInicio = dataHoraPrevistaInicio;
    }

    public String getDataHoraPrevistaTermino() {
        return dataHoraPrevistaTermino;
    }

    public void setDataHoraPrevistaTermino(String dataHoraPrevistaTermino) {
        this.dataHoraPrevistaTermino = dataHoraPrevistaTermino;
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
    
    
}
