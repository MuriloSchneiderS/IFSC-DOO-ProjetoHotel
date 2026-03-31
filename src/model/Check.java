package model;

public class Check {
    private int id;
    private String dataHoraCadastro, dataHoraEntrada, dataHoraSaida;
    private String obs;
    private char status;
    private CheckQuarto checkQuarto;
    
    public Check(){};
    public Check(int id, String dataHoraCadastro, String dataHoraEntrada, String dataHoraSaida, String obs, char status, CheckQuarto checkQuarto) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.obs = obs;
        this.status = status;
        this.checkQuarto = checkQuarto;
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

    public String getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(String dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(String dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
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

    public CheckQuarto getCheckQuarto() {
        return checkQuarto;
    }

    public void setCheckQuarto(CheckQuarto checkQuarto) {
        this.checkQuarto = checkQuarto;
    }
    
    
}
