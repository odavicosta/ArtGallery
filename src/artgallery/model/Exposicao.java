package artgallery.model;

import java.io.Serializable;
import java.util.Vector;

public class Exposicao implements Serializable {
    private static final long serialVersionUID = 1L;
	private String nome;
	private Vector<Obra> obras;
	
	public Exposicao(String nome) {
		this.nome = nome;
		this.obras = new Vector<Obra>();
	}
	
	public String getNome() {return nome;}
	
	//MÉTODOS
	public void adicionarObra(Obra obra) {
		if (obra != null) {
			this.obras.add(obra);
		}
	}
	
	public Vector<Obra> listarObras() {
		return obras;
	}
}
