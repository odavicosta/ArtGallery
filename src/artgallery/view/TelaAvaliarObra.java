package artgallery.view;

import artgallery.exception.NotaInvalidaException;
import artgallery.exception.ObraNaoEncontradaException;
import artgallery.model.Avaliacao;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.awt.*;

public class TelaAvaliarObra {
    public TelaAvaliarObra(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Avaliar Obra");
        frame.setSize(400, 280);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField campoTitulo = new JTextField();
        JTextField campoUsuario = new JTextField();
        JTextField campoNota = new JTextField();
        JTextField campoComentario = new JTextField();
        JButton botaoAvaliar = new JButton("Avaliar");

        painel.add(new JLabel("Título da obra:")); painel.add(campoTitulo);
        painel.add(new JLabel("Usuário:"));         painel.add(campoUsuario);
        painel.add(new JLabel("Nota (0-10):"));     painel.add(campoNota);
        painel.add(new JLabel("Comentário:"));      painel.add(campoComentario);
        painel.add(new JLabel(""));                  painel.add(botaoAvaliar);

        botaoAvaliar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            String usuario = campoUsuario.getText().trim();

            try {
                int nota = Integer.parseInt(campoNota.getText().trim());
                Avaliacao avaliacao = new Avaliacao(usuario, nota, campoComentario.getText().trim());
                artGallery.avaliarObra(titulo, avaliacao);
                JOptionPane.showMessageDialog(frame, "Avaliação registrada com sucesso!");
                campoTitulo.setText(""); campoUsuario.setText("");
                campoNota.setText(""); campoComentario.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "A nota deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NotaInvalidaException | ObraNaoEncontradaException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(painel);
        frame.setVisible(true);
    }
}