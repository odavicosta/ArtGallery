package artgallery.view;

import javax.swing.JOptionPane;
import artgallery.service.IArtGallery;
import artgallery.model.Obra;
import artgallery.model.PinturaDigital;
import artgallery.model.Modelagem3D;
import artgallery.model.ArteGenerativa;

public class TelaCadastrarObra {
    
    public TelaCadastrarObra(JanelaPrincipal pai, IArtGallery galeria) {
        String[] tipos = {"Pintura Digital", "Modelagem 3D", "Arte Generativa"};
        String tipoEscolhido = (String) JOptionPane.showInputDialog(
            null, "Qual o tipo de obra digital?", "Cadastro de Obra",
            JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]
        );

        if (tipoEscolhido == null) return; 

        try {
            String titulo = JOptionPane.showInputDialog("Digite o título da obra:");
            if (titulo == null || titulo.trim().isEmpty()) return;

            String autor = JOptionPane.showInputDialog("Digite o nome do artista:");
            if (autor == null || autor.trim().isEmpty()) return;

            Obra novaObra = null;

            if (tipoEscolhido.equals("Pintura Digital")) {
                String resolucao = JOptionPane.showInputDialog("Resolução (Ex: 4K, 1080p):");
                String software = JOptionPane.showInputDialog("Software utilizado:");
                novaObra = new PinturaDigital(titulo, autor, resolucao, software);
                
            } else if (tipoEscolhido.equals("Modelagem 3D")) {
                int poligonos = Integer.parseInt(JOptionPane.showInputDialog("Número de Polígonos:"));
                String engine = JOptionPane.showInputDialog("Engine (Ex: Blender, Unreal):");
                novaObra = new Modelagem3D(titulo, autor, poligonos, engine);
                
            } else if (tipoEscolhido.equals("Arte Generativa")) {
                String algoritmo = JOptionPane.showInputDialog("Algoritmo utilizado:");
                long seed = Long.parseLong(JOptionPane.showInputDialog("Seed (Apenas números):"));
                novaObra = new ArteGenerativa(titulo, autor, algoritmo, seed);
            }

            // O método publicarObra já cuida da exceção de duplicidade
            galeria.publicarObra(novaObra);
            JOptionPane.showMessageDialog(null, "Obra cadastrada com sucesso!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Valores como polígonos ou seed devem ser numéricos.", "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
}