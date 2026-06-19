package artgallery.view;

import artgallery.service.*;
import javax.swing.JFrame;
import javax.swing.JButton;

public class JanelaPrincipal {
	private IArtGallery artGallery;
	
	public JanelaPrincipal(IArtGallery artGallery) {
		this.artGallery = artGallery;
		
		// inicializando a janela principal
        JFrame frame = new JFrame("ArtGallery");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 
        
        int x = 80;
        int y = 70;
        int largura = 200;
        int altura = 30;
        int espacamento = 40;
        
        JButton botaoCadastrarObra = new JButton("Cadastrar obra");
        botaoCadastrarObra.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoAvaliarObra = new JButton("Avaliar Obra");
        botaoAvaliarObra.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoListarObras = new JButton("Listar Obras Ativas");
        botaoListarObras.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoBuscarAutor = new JButton("Buscar por Autor");
        botaoBuscarAutor.setBounds(x, y, largura, altura);

        y += espacamento; 
        JButton botaoTopObras = new JButton("Top Obras");
        botaoTopObras.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoCriarExposicao = new JButton("Criar Exposição");
        botaoCriarExposicao.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoVerObrasExposicao = new JButton("Ver Obras de Exposição");
        botaoVerObrasExposicao.setBounds(x, y, largura, altura); 

        y += espacamento; 
        JButton botaoDesativarObra = new JButton("Desativar Obra");
        botaoDesativarObra.setBounds(x, y, largura, altura); 
        
        
        botaoCadastrarObra.addActionListener(e -> new TelaCadastrarObra(this, artGallery));
        botaoAvaliarObra.addActionListener(e -> new TelaAvaliarObra(this, artGallery));
        botaoListarObras.addActionListener(e -> new TelaListarObras(this, artGallery));
        botaoBuscarAutor.addActionListener(e -> new TelaBuscarAutor(this, artGallery));
        botaoTopObras.addActionListener(e -> new TelaTopObras(this, artGallery));
        botaoCriarExposicao.addActionListener(e -> new TelaExposicao(this, artGallery));
        botaoVerObrasExposicao.addActionListener(e -> new TelaExposicao(this, artGallery));
        botaoDesativarObra.addActionListener(e -> new TelaDesativarObra(this, artGallery));
        
        frame.add(botaoCadastrarObra);
        frame.add(botaoAvaliarObra);
        frame.add(botaoListarObras);
        frame.add(botaoBuscarAutor);
        frame.add(botaoTopObras);
        frame.add(botaoCriarExposicao);
        frame.add(botaoVerObrasExposicao);
        frame.add(botaoDesativarObra);
        
        frame.setVisible(true);
	}
}
