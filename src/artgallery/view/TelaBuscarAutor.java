package artgallery.view;

import artgallery.model.Obra;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TelaBuscarAutor {
    public TelaBuscarAutor(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Buscar por Autor");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel painelBusca = new JPanel(new FlowLayout());
        JTextField campoAutor = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");
        painelBusca.add(new JLabel("Autor:"));
        painelBusca.add(campoAutor);
        painelBusca.add(botaoBuscar);

        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        botaoBuscar.addActionListener(e -> {
            String autor = campoAutor.getText().trim();
            Vector<Obra> resultado = artGallery.buscarPorAutor(autor);
            StringBuilder texto = new StringBuilder();

            if (resultado.isEmpty()) {
                texto.append("Nenhuma obra encontrada para esse autor.");
            } else {
                for (Obra obra : resultado) {
                    texto.append(obra.exibirDetalhes());
                    texto.append("\n--------------------------\n");
                }
            }
            areaResultado.setText(texto.toString());
        });

        frame.add(painelBusca, BorderLayout.NORTH);
        frame.add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}