package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Quarto;
import view.TelaBuscaQuarto;

public class ControllerBuscaQuarto implements ActionListener {

    TelaBuscaQuarto telaBuscaQuarto;

    public ControllerBuscaQuarto(TelaBuscaQuarto telaBuscaQuarto) {

        this.telaBuscaQuarto = telaBuscaQuarto;

        this.telaBuscaQuarto.getjButtonCarregar().addActionListener(this);
        this.telaBuscaQuarto.getjButtonFiltar().addActionListener(this);
        this.telaBuscaQuarto.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaQuarto.getjButtonCarregar()) {
            if(this.telaBuscaQuarto.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadQuarto.codigo = (int) this.telaBuscaQuarto.getjTableDados().getValueAt(this.telaBuscaQuarto.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaQuarto.dispose();
            }
            
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaQuarto.getjButtonFiltar()) {
            if (this.telaBuscaQuarto.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                //Ordenar por Id
                if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 0) {
                    //Criando objeto para receber o dado que virà do banco de dados
                    Quarto quarto = new Quarto();
                    //Carregando o registro do quarto na entidade para o objeto quarto
                    quarto = service.QuartoService.Carregar(Integer.parseInt(this.telaBuscaQuarto.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.addRow(new Object[]{
                        quarto.getId(), 
                        quarto.getDescricao(),
                        quarto.getAndar(),
                        quarto.getCapacidadeHospedes(),
                        quarto.getFlagAnimais(),
                        quarto.getStatus()
                    });
                    
                //Ordenar por Descricao
                } else if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 1) {
                    //Criando a lista para receber as quartos
                    List<Quarto> listaQuartos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaQuartos = service.QuartoService.Carregar("descricao", this.telaBuscaQuarto.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os quartos na tabela
                    for(Quarto quartoAtualDaLista : listaQuartos){
                        tabela.addRow(new Object[]{
                            quartoAtualDaLista.getId(), 
                            quartoAtualDaLista.getDescricao(),
                            quartoAtualDaLista.getAndar(),
                            quartoAtualDaLista.getCapacidadeHospedes(),
                            quartoAtualDaLista.getFlagAnimais(),
                            quartoAtualDaLista.getStatus()
                        });
                    }
                    
                //Ordenar por Andar
                } else if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 2) {
                    //Criando a lista para receber as quartos
                    List<Quarto> listaQuartos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaQuartos = service.QuartoService.Carregar("andar", this.telaBuscaQuarto.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os quartos na tabela
                    for(Quarto quartoAtualDaLista : listaQuartos){
                        tabela.addRow(new Object[]{
                            quartoAtualDaLista.getId(), 
                            quartoAtualDaLista.getDescricao(),
                            quartoAtualDaLista.getAndar(),
                            quartoAtualDaLista.getCapacidadeHospedes(),
                            quartoAtualDaLista.getFlagAnimais(),
                            quartoAtualDaLista.getStatus()
                        });
                    }
                    
                //Ordenar por Capacidade De Hospedes
                } else if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 3) {
                    //Criando a lista para receber as quartos
                    List<Quarto> listaQuartos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaQuartos = service.QuartoService.Carregar("capacidade_hospedes", this.telaBuscaQuarto.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os quartos na tabela
                    for(Quarto quartoAtualDaLista : listaQuartos){
                        tabela.addRow(new Object[]{
                            quartoAtualDaLista.getId(), 
                            quartoAtualDaLista.getDescricao(),
                            quartoAtualDaLista.getAndar(),
                            quartoAtualDaLista.getCapacidadeHospedes(),
                            quartoAtualDaLista.getFlagAnimais(),
                            quartoAtualDaLista.getStatus()
                        });
                    }
                    
                //Ordenar por Flag Animais
                } else if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 4) {
                    //Criando a lista para receber as quartos
                    List<Quarto> listaQuartos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaQuartos = service.QuartoService.Carregar("flag_animais", this.telaBuscaQuarto.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os quartos na tabela
                    for(Quarto quartoAtualDaLista : listaQuartos){
                        tabela.addRow(new Object[]{
                            quartoAtualDaLista.getId(), 
                            quartoAtualDaLista.getDescricao(),
                            quartoAtualDaLista.getAndar(),
                            quartoAtualDaLista.getCapacidadeHospedes(),
                            quartoAtualDaLista.getFlagAnimais(),
                            quartoAtualDaLista.getStatus()
                        });
                    }
                    
                //Ordenar por Status
                } else if (this.telaBuscaQuarto.getjCBFiltro().getSelectedIndex() == 5) {
                    //Criando a lista para receber as quartos
                    List<Quarto> listaQuartos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaQuartos = service.QuartoService.Carregar("status", this.telaBuscaQuarto.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaQuarto.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os quartos na tabela
                    for(Quarto quartoAtualDaLista : listaQuartos){
                        tabela.addRow(new Object[]{
                            quartoAtualDaLista.getId(), 
                            quartoAtualDaLista.getDescricao(),
                            quartoAtualDaLista.getAndar(),
                            quartoAtualDaLista.getCapacidadeHospedes(),
                            quartoAtualDaLista.getFlagAnimais(),
                            quartoAtualDaLista.getStatus()
                        });
                    }
                }
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaQuarto.getjButtonSair()) {
            this.telaBuscaQuarto.dispose();
        }
    }
}
