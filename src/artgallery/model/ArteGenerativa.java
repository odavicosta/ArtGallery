package artgallery.model;

public class ArteGenerativa extends Obra {
	private String algoritmo;
	private long seed;
	
	public ArteGenerativa(String titulo, String autor, String algoritmo, long seed){
		super(titulo, autor);
		this.algoritmo = algoritmo;
		this.seed = seed;
	}
	
	//Eu poderia simplificar aqui retirando os getters, mas acredito que desse jeito possa ser melhor sla XD
	public String getAlgoritmo() {return algoritmo;}
	public long getSeed() {return seed;}
	
	@Override
	public String exibirDetalhes() {
		return "Título: " + getTitulo() + "\n" +
		       "Autor: " + getAutor() + "\n" +
			   "Tipo: Arte Generativa\n" +
		       "Algoritmo: " + getAlgoritmo() + "\n" +
			   "Seed: " + getSeed() + "\n";		
	}
} 
