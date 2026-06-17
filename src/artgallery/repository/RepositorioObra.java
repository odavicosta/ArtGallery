package artgallery.repository;

import java.util.Vector;
import artgallery.model.Obra;
import artgallery.exception.ObraJaCadastradaException;
import artgallery.exception.ObraNaoEncontradaException;

public class RepositorioObra implements IRepositorioObra {
	private Vector<Obra> obras;
	
	public RepositorioObra() {
		this.obras = new Vector<Obra>();
	}
	
	@Override
	public void cadastrar(Obra obra) throws ObraJaCadastradaException {
		for (Obra obraCadastrada : this.obras) {
            if (obraCadastrada.getTitulo().equalsIgnoreCase(obra.getTitulo()) && 
                obraCadastrada.getAutor().equalsIgnoreCase(obra.getAutor())) {
                throw new ObraJaCadastradaException("Já existe uma obra cadastrada com este título e autor.");
            }
        }
        this.obras.add(obra);
	}
	
	@Override
    public Obra buscar(String titulo) {
        for (Obra o : this.obras) {
            if (o.getTitulo().equalsIgnoreCase(titulo)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Obra obra) throws ObraNaoEncontradaException {
        Obra obraExistente = buscar(obra.getTitulo());
        
        if (obraExistente == null) {
            throw new ObraNaoEncontradaException("Obra não encontrada para atualização.");
        }
        
        int index = this.obras.indexOf(obraExistente);
        this.obras.set(index, obra);
    }

    @Override
    public void remover(String titulo) {
        Obra obra = buscar(titulo);
        if (obra != null) {
            obra.setAtiva(false);
        }
    }

    @Override
    public Vector<Obra> listar() {
        return this.obras;
    }
}