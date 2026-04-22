package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import view.TelaCadastroAlocacaoVagaEstacionamento;
import view.TelaCadastroReserva;
import view.TelaCadastroOrdemServico;

public class ControllerBuscaReserva implements ActionListener {

    TelaBuscaReserva telaBuscaReserva;
    private JTable jtableReservas;
    private JTable jtableReservasVaga;
    private JTable jtableReservasServico;
    private JTable tabelaSelecionada;
    private int linhaSelecionada;

    public ControllerBuscaReserva(TelaBuscaReserva telaBuscaReserva) {

        this.telaBuscaReserva = telaBuscaReserva;

        this.telaBuscaReserva.getjButtonNovo().addActionListener(this);
        this.telaBuscaReserva.getjButtonEditar().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckin().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckout().addActionListener(this);
        this.telaBuscaReserva.getjButtonSair().addActionListener(this);

        jtableReservas = this.telaBuscaReserva.getjTableReservas();
        jtableReservasVaga = this.telaBuscaReserva.getjTableReservasVaga();
        jtableReservasServico = this.telaBuscaReserva.getjTableReservasServico();
        tabelaSelecionada = jtableReservas;

        //FocusListener para tabela de Reservas
        jtableReservas.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                jtableReservas.setEnabled(true);
                atualizarTabela((DefaultTableModel) ControllerBuscaReserva.this.telaBuscaReserva.getjTableReservas().getModel());
                tabelaSelecionada = jtableReservas;
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                linhaSelecionada = tabelaSelecionada.getSelectedRow();
                jtableReservas.clearSelection();
            }
        });
        //FocusListener para tabela de Reservas de Vaga
        jtableReservasVaga.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                jtableReservasVaga.setEnabled(true);
                //atualizarTabela((DefaultTableModel) ControllerBuscaReserva.this.telaBuscaReserva.getjTableReservas().getModel());
                tabelaSelecionada = jtableReservasVaga;
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                linhaSelecionada = tabelaSelecionada.getSelectedRow();
                jtableReservasVaga.clearSelection();
            }
        });
        //FocusListener para tabela de Reservas de Servico
        jtableReservasServico.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                jtableReservasServico.setEnabled(true);
                //atualizarTabela((DefaultTableModel) ControllerBuscaReserva.this.telaBuscaReserva.getjTableReservas().getModel());
                tabelaSelecionada = jtableReservasServico;
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                linhaSelecionada = tabelaSelecionada.getSelectedRow();
                jtableReservasServico.clearSelection();
            }
        });

        //ListSelectionListener para capturar a linha selecionada
        jtableReservas.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    telaBuscaReserva.getjButtonEditar().setEnabled(true);
                    telaBuscaReserva.getjButtonCheckin().setEnabled(true);
                    telaBuscaReserva.getjButtonCheckout().setEnabled(true);
                }
            }
        });
    }

    private void atualizarTabela(DefaultTableModel tabela) {
        utilities.Utilities.ativaDesativa(this.telaBuscaReserva.getjPanelBotoes(), true);

        //Criando a lista para receber as reservas
        List<Reserva> listaReservas = service.ReservaService.Carregar("obs", "");//Armazena todas as reservas
        if (listaReservas.isEmpty()) {
            tabela.setRowCount(1);
            return;
        }
        tabela.setRowCount(0);//Reseta a tabela

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
                reservaAtualDaLista.getDataPrevistaEntrada() + "  -  " + reservaAtualDaLista.getDataPrevistaSaida(),
                check.getDataHoraEntrada() + "",
                check.getDataHoraSaida() + ""
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaBuscaReserva.getjButtonNovo()) {
            if (tabelaSelecionada.getName().equals(jtableReservas.getName())) {
                TelaCadastroReserva telaCadastroReserva = new TelaCadastroReserva(null, true);
                telaCadastroReserva.getjTextFieldId().setText(0 + "");
                ControllerCadReserva controllerCadReserva = new ControllerCadReserva(telaCadastroReserva);
                controllerCadReserva.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().requestFocus();
            } else if (tabelaSelecionada.getName().equals(jtableReservasVaga.getName())) {
                TelaCadastroAlocacaoVagaEstacionamento telaCadastroAlocacaoVagaEstacionamento = new TelaCadastroAlocacaoVagaEstacionamento(null, true);
                telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().setText(0 + "");
                ControllerCadAlocacaoVagaEstacionamento controllerCadAlocacaoVagaEstacionamento = new ControllerCadAlocacaoVagaEstacionamento(telaCadastroAlocacaoVagaEstacionamento);
                controllerCadAlocacaoVagaEstacionamento.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaEntrada().requestFocus();
            } else if (tabelaSelecionada.getName().equals(jtableReservasServico.getName())) {
                TelaCadastroOrdemServico telaCadastroOrdemServico = new TelaCadastroOrdemServico(null, true);
                telaCadastroOrdemServico.getjTextFieldId().setText(0 + "");
                ControllerCadOrdemServico controllerCadOrdemServico = new ControllerCadOrdemServico(telaCadastroOrdemServico);
                controllerCadOrdemServico.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma tabela selecionada.");
            }

            //Botão Editar
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonEditar()) {
            if (tabelaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Nenhuma tabela selecionada.");
            } else if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(null, "Nenhuma reserva selecionada.");
            } else {
                if (tabelaSelecionada.getName().equals(jtableReservas.getName())) {
                    int cod = (int) tabelaSelecionada.getValueAt(linhaSelecionada, 0);
                    TelaCadastroReserva telaCadastroReserva = new TelaCadastroReserva(null, true);
                    telaCadastroReserva.getjTextFieldId().setText(cod + "");
                    ControllerCadReserva controllerCadReserva = new ControllerCadReserva(telaCadastroReserva);
                } else if (tabelaSelecionada.getName().equals(jtableReservasVaga.getName())) {
                    
                } else if (tabelaSelecionada.getName().equals(jtableReservasServico.getName())) {

                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma reserva selecionada.");
                }
            }
            tabelaSelecionada.requestFocus();
            //Botão Check-in
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonCheckin()) {
            if (this.telaBuscaReserva.getjTableReservas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                try {
                    int linhaSelecionada = this.telaBuscaReserva.getjTableReservas().getSelectedRow();
                    int idReserva = (int) this.telaBuscaReserva.getjTableReservas().getValueAt(linhaSelecionada, 0);
                    Reserva reserva = service.ReservaService.Carregar(idReserva);
                    Check checkAtualiza = service.CheckService.Carregar(reserva.getCheck().get(0).getId());

                    java.util.Date dataAtual = new Date();
                    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String novaData = dataFormatada.format(dataAtual);
                    checkAtualiza.setDataHoraEntrada(novaData);

                    service.CheckService.Atualizar(checkAtualiza);
                    atualizarTabela((DefaultTableModel) ControllerBuscaReserva.this.telaBuscaReserva.getjTableReservas().getModel());
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar check: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            //Botão Check-out
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonCheckout()) {
            if (this.telaBuscaReserva.getjTableReservas().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                try {
                    int linhaSelecionada = this.telaBuscaReserva.getjTableReservas().getSelectedRow();
                    int idReserva = (int) this.telaBuscaReserva.getjTableReservas().getValueAt(linhaSelecionada, 0);
                    Reserva reserva = service.ReservaService.Carregar(idReserva);
                    Check checkAtualiza = service.CheckService.Carregar(reserva.getCheck().get(0).getId());

                    java.util.Date dataAtual = new Date();
                    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String novaData = dataFormatada.format(dataAtual);
                    checkAtualiza.setDataHoraSaida(novaData);

                    service.CheckService.Atualizar(checkAtualiza);
                    atualizarTabela((DefaultTableModel) ControllerBuscaReserva.this.telaBuscaReserva.getjTableReservas().getModel());
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar check: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonSair()) {
            this.telaBuscaReserva.dispose();
        }
    }
}
