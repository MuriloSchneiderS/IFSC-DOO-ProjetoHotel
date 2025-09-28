package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.VagaEstacionamento;
import view.TelaBuscaVagaEstacionamento;

public class ControllerBuscaVagaEstacionamento implements ActionListener {

    TelaBuscaVagaEstacionamento telaBuscaVagaEstacionamento;

    public ControllerBuscaVagaEstacionamento(TelaBuscaVagaEstacionamento telaBuscaVagaEstacionamento) {

        this.telaBuscaVagaEstacionamento = telaBuscaVagaEstacionamento;

        this.telaBuscaVagaEstacionamento.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVagaEstacionamento.getjButtonFiltar().addActionListener(this);
        this.telaBuscaVagaEstacionamento.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonCarregar()) {
            if(this.telaBuscaVagaEstacionamento.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadVagaEstacionamento.codigo = (int) this.telaBuscaVagaEstacionamento.getjTableDados().getValueAt(this.telaBuscaVagaEstacionamento.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaVagaEstacionamento.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonFiltar()) {
            if (this.telaBuscaVagaEstacionamento.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaVagaEstacionamento.getjCBFiltro().getSelectedIndex() == 0) {//Ordenar por Id
                    //Criando objeto para receber o dado que virà do banco de dados
                    VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
                    //Carregando o registro do vagaEstacionamento na entidade para o objeto vagaEstacionamento
                    vagaEstacionamento = service.VagaEstacionamentoService.Carregar(Integer.parseInt(this.telaBuscaVagaEstacionamento.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o vagaEstacionamento da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableDados().getModel();
                    tabela.addRow(new Object[]{
                        vagaEstacionamento.getId(), 
                        vagaEstacionamento.getDescricao(), 
                        vagaEstacionamento.getObs(), 
                        vagaEstacionamento.getMetragemVaga(),
                        vagaEstacionamento.getStatus()
                    });
                } else if (this.telaBuscaVagaEstacionamento.getjCBFiltro().getSelectedIndex() == 1) {//Ordenar por Descricao
                    //Criando a lista para receber as vagaEstacionamentos
                    List<VagaEstacionamento> listaVagaEstacionamentos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o vagaEstacionamento da tabela a ele
                    listaVagaEstacionamentos = service.VagaEstacionamentoService.Carregar("nome", this.telaBuscaVagaEstacionamento.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVagaEstacionamento.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os vagaEstacionamentos na tabela
                    for(VagaEstacionamento vagaEstacionamentoAtualDaLista : listaVagaEstacionamentos){
                        tabela.addRow(new Object[]{
                            vagaEstacionamentoAtualDaLista.getId(), 
                            vagaEstacionamentoAtualDaLista.getDescricao(), 
                            vagaEstacionamentoAtualDaLista.getObs(), 
                            vagaEstacionamentoAtualDaLista.getMetragemVaga(),
                            vagaEstacionamentoAtualDaLista.getStatus()
                        });
                    }
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaVagaEstacionamento.getjButtonSair()) {
            this.telaBuscaVagaEstacionamento.dispose();
        }
    }
}
