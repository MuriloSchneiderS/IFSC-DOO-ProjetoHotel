package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Modelo;
import view.TelaBuscaModelo;

public class ControllerBuscaModelo implements ActionListener {

    TelaBuscaModelo telaBuscaModelo;

    public ControllerBuscaModelo(TelaBuscaModelo telaBuscaModelo) {

        this.telaBuscaModelo = telaBuscaModelo;

        this.telaBuscaModelo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaModelo.getjButtonFiltar().addActionListener(this);
        this.telaBuscaModelo.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaModelo.getjButtonCarregar()) {
            if(this.telaBuscaModelo.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadModelo.codigo = (int) this.telaBuscaModelo.getjTableDados().getValueAt(this.telaBuscaModelo.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaModelo.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltar()) {
            JOptionPane.showMessageDialog(null, "Botão Filtrar Pressionado...");
            if (this.telaBuscaModelo.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                JOptionPane.showMessageDialog(null, "Filtrando informações...");
                if (this.telaBuscaModelo.getjCBFiltro().getSelectedIndex() == 0) {//Ordenar por Id
                    //Criando objeto para receber o dado que virà do banco de dados
                    Modelo modelo = new Modelo();
                    //Carregando o registro do modelo na entidade para o objeto modelo
                    modelo = service.ModeloService.Carregar(Integer.parseInt(this.telaBuscaModelo.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                    tabela.addRow(new Object[]{modelo.getId(), modelo.getDescricao(), modelo.getStatus()});
                } else if (this.telaBuscaModelo.getjCBFiltro().getSelectedIndex() == 1) {//Ordenar por Descricao
                    //Criando a lista para receber as modelos
                    List<Modelo> listaModelos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaModelos = service.ModeloService.Carregar("nome", this.telaBuscaModelo.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os modelos na tabela
                    for(Modelo modeloAtualDaLista : listaModelos){
                        tabela.addRow(new Object[]{modeloAtualDaLista.getId(), modeloAtualDaLista.getDescricao(), modeloAtualDaLista.getStatus()});
                    }
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            this.telaBuscaModelo.dispose();
        }
    }
}
