package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Reserva;
import view.TelaBuscaReserva;
import view.TelaCadastroReserva;

public class ControllerBuscaReserva implements ActionListener {

    TelaBuscaReserva telaBuscaReserva;

    public ControllerBuscaReserva(TelaBuscaReserva telaBuscaReserva) {

        this.telaBuscaReserva = telaBuscaReserva;

        this.telaBuscaReserva.getjButtonNovo().addActionListener(this);
        this.telaBuscaReserva.getjButtonEditar().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckin().addActionListener(this);
        this.telaBuscaReserva.getjButtonCheckout().addActionListener(this);
        this.telaBuscaReserva.getjButtonSair().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaBuscaReserva.getjButtonNovo()) {
            ControllerCadReserva controllerCadReserva = new ControllerCadReserva(new TelaCadastroReserva(null,true));
            ControllerCadReserva.codigo = 0;
            telaCadastroReserva.setVisible(true);
            
        //Botão Editar
        }else if (evento.getSource() == this.telaBuscaReserva.getjButtonEditar()) {
            if(this.telaBuscaReserva.getjTableReservas().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhuma reserva selecionada.");
            }else{
                ControllerCadReserva.codigo = (int) this.telaBuscaReserva.getjTableReservas().getValueAt(this.telaBuscaReserva.getjTableReservas().getSelectedRow(), 0);
                telaCadastroReserva.setVisible(true);
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaReserva.getjButtonSair()) {
            this.telaBuscaReserva.dispose();
        }
    }
}
