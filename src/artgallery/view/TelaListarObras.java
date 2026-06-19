package artgallery.view;

import artgallery.model.Obra;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.util.Vector;

public class TelaListarObras {
    public TelaListarObras(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Obras Ativas");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);

        Vector<Obra> obras = artGallery.listarObras();
        StringBuilder texto = new StringBuilder();

        if (obras.isEmpty()) {
            texto.append("Nenhuma obra ativa cadastrada.");
        } else {
            for (Obra obra : obras) {
                texto.append(obra.exibirDetalhes());
                texto.append("\nMédia de avaliações: ").append(String.format("%.2f", obra.mediaAvaliacoes()));
                texto.append("\n--------------------------\n");
            }
        }

        JTextArea areaTexto = new JTextArea(texto.toString());
        areaTexto.setEditable(false);
        frame.add(new JScrollPane(areaTexto));
        frame.setVisible(true);
    }
}