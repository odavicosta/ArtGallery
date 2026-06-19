package artgallery.view;

import artgallery.repository.RepositorioObra;
import artgallery.service.ArtGallery;

public class Main {
	public static void main(String[] args) {
		RepositorioObra repositorio = new RepositorioObra();
		
		ArtGallery galeria = new ArtGallery(repositorio);
		
		JanelaPrincipal janela = new JanelaPrincipal(galeria);
	}
}
