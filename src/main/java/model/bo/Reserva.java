package model;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

public class Reserva {
    private int id;
    private Timestamp dataHoraReserva;
    private Date dataPrevista, dataPrevistaSaida;
    private String obs;
    private char status;
    private List<Check> cheques;

    public Reserva(int id, Timestamp dataHoraReserva, Date dataPrevista, Date dataPrevistaSaida, String obs, char status) {
        this.id = id;
        this.dataHoraReserva = dataHoraReserva;
        this.dataPrevista = dataPrevista;
        this.dataPrevistaSaida = dataPrevistaSaida;
        this.obs = obs;
        this.status = status;
    }
    public Reserva(int id, Timestamp dataHoraReserva, Date dataPrevista, Date dataPrevistaSaida, String obs, char status, List<Check> cheques) {
        this.id = id;
        this.dataHoraReserva = dataHoraReserva;
        this.dataPrevista = dataPrevista;
        this.dataPrevistaSaida = dataPrevistaSaida;
        this.obs = obs;
        this.status = status;
        this.cheques = cheques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(Timestamp dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataPrevistaSaida() {
        return dataPrevistaSaida;
    }

    public void setDataPrevistaSaida(Date dataPrevistaSaida) {
        this.dataPrevistaSaida = dataPrevistaSaida;
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

    public List<Check> getCheques() {
        return cheques;
    }

    public void setCheques(List<Check> cheques) {
        this.cheques = cheques;
    }
    
    
}
