package artgallery.view;

import artgallery.exception.ObraNaoEncontradaException;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.awt.*;

public class TelaDesativarObra {
    public TelaDesativarObra(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Desativar Obra");
        frame.setSize(350, 150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JTextField campoTitulo = new JTextField(15);
        JButton botaoDesativar = new JButton("Desativar");

        frame.add(new JLabel("Título da obra:"));
        frame.add(campoTitulo);
        frame.add(botaoDesativar);

        botaoDesativar.addActionListener(e -> {
            try {
                artGallery.removerObra(campoTitulo.getText().trim());
                JOptionPane.showMessageDialog(frame, "Obra desativada com sucesso!");
                campoTitulo.setText("");
            } catch (ObraNaoEncontradaException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}