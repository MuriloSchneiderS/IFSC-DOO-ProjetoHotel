package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Check;
import model.CheckHospede;
import model.CheckQuarto;
import model.Hospede;
import model.Quarto;
import model.Reserva;
import view.TelaBuscaReserva;
import view.TelaCadastroReserva;

public class ControllerBuscaReserva implements ActionListener {

    TelaBuscaReserva telaBuscaReserva;
    ControllerCadReserva controllerCadReserva;
    TelaCadastroReserva telaCadastroReserva;

    public ControllerBuscaReserva(TelaBuscaReserva telaBuscaReserva) {

        this.telaBuscaReserva = telaBuscaReserva;

        this.telaBuscaReserva.getjButtonNovo().addActionListener(this);
        this.telaBuscaReserva.getjButtonEditar().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckin().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckout().addActionListener(this);
        this.telaBuscaReserva.getjButtonSair().addActionListener(this);

        telaCadastroReserva = new TelaCadastroReserva(null, true);
        telaCadastroReserva.setVisible(false);

        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaReserva.getjTableReservas().getModel();
        JTable jtableReservas = this.telaBuscaReserva.getjTableReservas();
        //sempre que o cadastro for fechado e esta janela voltar para o foco a tabela é reiniciada
        this.telaBuscaReserva.addWindowFocusListener(new java.awt.event.WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent we) {
                jtableReservas.setEnabled(true);
                //Criando a lista para receber os reservas
                tabela.setRowCount(0);//Reseta a tabela
                List<Reserva> listaReservas = service.ReservaService.Carregar("obs", "");//Armazena todas as reservas
                Check check;
                CheckHospede checkHospede;
                Hospede hospede;
                CheckQuarto checkQuarto;
                Quarto quarto;

                //Adicionando as reservas na tabela
                for (Reserva reservaAtualDaLista : listaReservas) {
                    //busca no banco de dados as linhas necessárias para popular os objetos usados na tabela de busca de reservas
                    check = service.CheckService.Carregar(reservaAtualDaLista.getCheck().get(0).getId());//reserva tem check_id
                    checkHospede = service.CheckHospedeService.Carregar("check_id", check.getId() + "").get(0);//check_hospede tem check_id
                    hospede = service.HospedeService.Carregar(checkHospede.getHospede().getId());//check_hospede tem hospede_id
                    checkQuarto = service.CheckQuartoService.Carregar(check.getCheckQuarto().getId());//check tem check_quarto_id
                    quarto = service.QuartoService.Carregar(checkQuarto.getQuarto().getId());//checkQuarto tem quarto_id
                    //Adiciona as informações às colunas da tabela
                    tabela.addRow(new Object[]{
                        reservaAtualDaLista.getId(),
                        hospede.getNome(),
                        quarto.getIdentificacao(),
                        reservaAtualDaLista.getDataPrevistaEntrada() + " - " + reservaAtualDaLista.getDataPrevistaSaida(),
                        check.getDataHoraEntrada() + "",
                        check.getDataHoraSaida() + ""
                    });
                }
            }

            @Override
            public void windowLostFocus(WindowEvent we) {
                jtableReservas.setEnabled(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaBuscaReserva.getjButtonNovo()) {
            controllerCadReserva = new ControllerCadReserva(telaCadastroReserva);
            ControllerCadReserva.codigo = 0;

            //Botão Editar
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonEditar()) {
            if (this.telaBuscaReserva.getjTableReservas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhuma reserva selecionada.");
            } else {
                controllerCadReserva = new ControllerCadReserva(telaCadastroReserva);
                ControllerCadReserva.codigo = (int) this.telaBuscaReserva.getjTableReservas().getValueAt(this.telaBuscaReserva.getjTableReservas().getSelectedRow(), 0);
                telaCadastroReserva.setVisible(true);
            }

            //Botão Check-in
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonCheckin()) {

            //Botão Check-out
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonCheckout()) {

            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonSair()) {
            this.telaBuscaReserva.dispose();
        }
    }
}
