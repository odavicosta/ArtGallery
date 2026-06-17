package artgallery.service;

import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;

import artgallery.exception.ObraJaCadastradaException;
import artgallery.exception.ObraNaoEncontradaException;
import artgallery.model.Avaliacao;
import artgallery.model.Exposicao;
import artgallery.model.Obra;
import artgallery.repository.IRepositorioObra;

public class ArtGallery implements IArtGallery {
	private IRepositorioObra repositorio;
	private Vector<Exposicao> exposicoes;
	
	public ArtGallery(IRepositorioObra repositorio) {
		this.repositorio = repositorio;
		this.exposicoes = new Vector<Exposicao>();
	}

	public void criarExposicao(Exposicao exposicao) {
        if (exposicao != null) {
            this.exposicoes.add(exposicao);
        }
    }	
	
	@Override
	public void publicarObra(Obra obra) throws ObraJaCadastradaException {
		this.repositorio.cadastrar(obra);
	}

	@Override
	public void removerObra(String titulo) throws ObraNaoEncontradaException {
		Obra obra = this.repositorio.buscar(titulo);
		
		if (obra == null || !obra.isAtiva()) {
            throw new ObraNaoEncontradaException("A obra não existe ou já se encontra inativa.");
        }
        
        this.repositorio.remover(titulo);
	}

	@Override
    public void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException {
        Obra obra = this.repositorio.buscar(titulo);
        
        if (obra == null || !obra.isAtiva()) {
            throw new ObraNaoEncontradaException("Não é possível avaliar: a obra não existe ou está inativa.");
        }
        
        obra.adicionarAvaliacao(avaliacao);
    }

    @Override
    public Vector<Obra> listarObras() {
        Vector<Obra> obrasAtivas = new Vector<Obra>();
        
        for (Obra obra : this.repositorio.listar()) {
            if (obra.isAtiva()) { obrasAtivas.add(obra); }
        }
        return obrasAtivas;
    }

    @Override
    public Vector<Obra> buscarPorAutor(String autor) {
        Vector<Obra> obrasDoAutor = new Vector<Obra>();
        
        for (Obra obra : this.repositorio.listar()) {
            if (obra.getAutor().equalsIgnoreCase(autor)) {
                obrasDoAutor.add(obra);
            }
        }
        return obrasDoAutor;
    }

    @Override
    public Vector<Obra> topObras() {
        Vector<Obra> ordenadas = new Vector<Obra>(this.listarObras());
        
        Collections.sort(ordenadas, new Comparator<Obra>() {
            @Override
            public int compare(Obra o1, Obra o2) {
                return Double.compare(o2.mediaAvaliacoes(), o1.mediaAvaliacoes());
            }
        });
        
        return ordenadas;
    }

    @Override
    public Vector<Obra> obrasExpostas(String nomeExposicao) {
        for (Exposicao exposicao : this.exposicoes) {
            if (exposicao.getNome().equalsIgnoreCase(nomeExposicao)) {
                return exposicao.listarObras();
            }
        }
        return new Vector<Obra>();
    }
}
