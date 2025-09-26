package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import view.TelaBuscaMarca;

public class ControllerBuscaMarca implements ActionListener {

    TelaBuscaMarca telaBuscaMarca;

    public ControllerBuscaMarca(TelaBuscaMarca telaBuscaMarca) {

        this.telaBuscaMarca = telaBuscaMarca;

        this.telaBuscaMarca.getjButtonCarregar().addActionListener(this);
        this.telaBuscaMarca.getjButtonFiltar().addActionListener(this);
        this.telaBuscaMarca.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaMarca.getjButtonCarregar()) {
            if(this.telaBuscaMarca.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadMarca.codigo = (int) this.telaBuscaMarca.getjTableDados().getValueAt(this.telaBuscaMarca.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaMarca.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonFiltar()) {
            JOptionPane.showMessageDialog(null, "Botão Filtrar Pressionado...");
            if (this.telaBuscaMarca.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                JOptionPane.showMessageDialog(null, "Filtrando informações...");
                if (this.telaBuscaMarca.getjCBFiltro().getSelectedIndex() == 0) {//Ordenar por Id
                    //Criando objeto para receber o dado que virà do banco de dados
                    Marca marca = new Marca();
                    //Carregando o registro do marca na entidade para o objeto marca
                    marca = service.MarcaService.Carregar(Integer.parseInt(this.telaBuscaMarca.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableDados().getModel();
                    tabela.addRow(new Object[]{marca.getId(), marca.getDescricao(), marca.getStatus()});
                } else if (this.telaBuscaMarca.getjCBFiltro().getSelectedIndex() == 1) {//Ordenar por Descricao
                    //Criando a lista para receber as marcas
                    List<Marca> listaMarcas = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaMarcas = service.MarcaService.Carregar("nome", this.telaBuscaMarca.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaMarca.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os marcas na tabela
                    for(Marca marcaAtualDaLista : listaMarcas){
                        tabela.addRow(new Object[]{marcaAtualDaLista.getId(), marcaAtualDaLista.getDescricao(), marcaAtualDaLista.getStatus()});
                    }
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaMarca.getjButtonSair()) {
            this.telaBuscaMarca.dispose();
        }
    }
}
