package artgallery.view;

import artgallery.exception.ObraNaoEncontradaException;
import artgallery.model.Obra;
import artgallery.service.IArtGallery;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TelaExposicao {
    public TelaExposicao(JanelaPrincipal pai, IArtGallery artGallery) {
        JFrame frame = new JFrame("Exposições");
        frame.setSize(520, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1, 5, 5));

        //criar exposição
        JPanel painelCriar = new JPanel(new FlowLayout());
        JTextField campoNomeNova = new JTextField(15);
        JButton botaoCriar = new JButton("Criar Exposição");
        painelCriar.add(new JLabel("Nome da exposição:"));
        painelCriar.add(campoNomeNova);
        painelCriar.add(botaoCriar);

        botaoCriar.addActionListener(e -> {
            String nome = campoNomeNova.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Informe um nome para a exposição.");
                return;
            }
            artGallery.criarExposicao(nome);
            JOptionPane.showMessageDialog(frame, "Exposição criada com sucesso!");
            campoNomeNova.setText("");
        });

        //adicionar obra à exposição
        JPanel painelAdicionar = new JPanel(new FlowLayout());
        JTextField campoNomeExp = new JTextField(10);
        JTextField campoTituloObra = new JTextField(10);
        JButton botaoAdicionar = new JButton("Adicionar Obra");
        painelAdicionar.add(new JLabel("Exposição:"));
        painelAdicionar.add(campoNomeExp);
        painelAdicionar.add(new JLabel("Título da obra:"));
        painelAdicionar.add(campoTituloObra);
        painelAdicionar.add(botaoAdicionar);

        botaoAdicionar.addActionListener(e -> {
            try {
                artGallery.adicionarObraAExposicao(campoNomeExp.getText().trim(), campoTituloObra.getText().trim());
                JOptionPane.showMessageDialog(frame, "Obra adicionada à exposição!");
                campoNomeExp.setText(""); campoTituloObra.setText("");
            } catch (ObraNaoEncontradaException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        //ver obras de uma exposição
        JPanel painelVer = new JPanel(new BorderLayout());
        JPanel painelVerTopo = new JPanel(new FlowLayout());
        JTextField campoNomeVer = new JTextField(10);
        JButton botaoVer = new JButton("Ver Obras");
        painelVerTopo.add(new JLabel("Exposição:"));
        painelVerTopo.add(campoNomeVer);
        painelVerTopo.add(botaoVer);

        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        botaoVer.addActionListener(e -> {
            Vector<Obra> obras = artGallery.obrasExpostas(campoNomeVer.getText().trim());
            StringBuilder texto = new StringBuilder();

            if (obras.isEmpty()) {
                texto.append("Nenhuma obra encontrada para essa exposição.");
            } else {
                for (Obra obra : obras) {
                    texto.append(obra.exibirDetalhes());
                    texto.append("\n--------------------------\n");
                }
            }
            areaResultado.setText(texto.toString());
        });

        painelVer.add(painelVerTopo, BorderLayout.NORTH);
        painelVer.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        frame.add(painelCriar);
        frame.add(painelAdicionar);
        frame.add(painelVer);
        frame.setVisible(true);
    }
}