package artgallery.view;

import artgallery.model.Obra;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.util.Vector;

public class TelaTopObras {
    public TelaTopObras(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Top Obras");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);

        Vector<Obra> obras = artGallery.topObras();
        StringBuilder texto = new StringBuilder();

        if (obras.isEmpty()) {
            texto.append("Nenhuma obra cadastrada ainda.");
        } else {
            int posicao = 1;
            for (Obra obra : obras) {
                texto.append(posicao).append("º lugar - ").append(obra.getTitulo());
                texto.append(" (").append(obra.getAutor()).append(")");
                texto.append(" - Média: ").append(String.format("%.2f", obra.mediaAvaliacoes()));
                texto.append("\n");
                posicao++;
            }
        }

        JTextArea areaTexto = new JTextArea(texto.toString());
        areaTexto.setEditable(false);
        frame.add(new JScrollPane(areaTexto));
        frame.setVisible(true);
    }
}