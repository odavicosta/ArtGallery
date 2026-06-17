package artgallery.model;

public class Modelagem3D extends Obra {
	private int numeroPoligonos;
	private String engine;
	
	public Modelagem3D(String titulo, String autor, int numeroPoligonos, String engine) {
		super(titulo, autor);
		this.numeroPoligonos = numeroPoligonos;
		this.engine = engine;
	}
	
	//Eu poderia simplificar aqui retirando os getters, mas acredito que desse jeito possa ser melhor sla XD
	public int getNumeroPoligonos() {return numeroPoligonos;}
	public String getEngine() {return engine;}
	
	@Override
	public String exibirDetalhes() {
		return "Título: " + getTitulo() + "\n" +
	           "Autor: " + getAutor() + "\n" +
			   "Tipo: Modelagem 3D\n" +
	           "Polígonos: " + getNumeroPoligonos() + "\n" +
			   "Engine: " + getEngine() + "\n";
	}
}
