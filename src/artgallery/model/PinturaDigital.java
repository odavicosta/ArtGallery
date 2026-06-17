package artgallery.model;

public class PinturaDigital extends Obra {
	private String resolucao;
	private String softwareUtilizado;
	
	public PinturaDigital(String titulo, String autor, String resolucao, String softwareUtilizado) {
		super(titulo, autor);
		this.resolucao = resolucao;
		this.softwareUtilizado = softwareUtilizado;
	}
	
	//Eu poderia simplificar aqui retirando os getters, mas acredito que desse jeito possa ser melhor sla XD
	public String getResolucao() {return resolucao;}
	public String getSoftwareUtilizado() {return softwareUtilizado;}
	
	@Override
    public String exibirDetalhes() {
		return "Título: " + getTitulo() + "\n" +
               "Autor: " + getAutor() + "\n" +
			   "Tipo: Pintura Digital\n" +
               "Resolução: " + getResolucao() + "\n" +
			   "Software: " + getSoftwareUtilizado() + "\n";
	}
}
