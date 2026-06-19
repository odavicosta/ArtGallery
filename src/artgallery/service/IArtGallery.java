package artgallery.service;

import java.util.Vector;
import artgallery.model.Obra;
import artgallery.model.Avaliacao;
import artgallery.exception.ObraJaCadastradaException;
import artgallery.exception.ObraNaoEncontradaException;

public interface IArtGallery {
    void publicarObra(Obra obra) throws ObraJaCadastradaException;
    void removerObra(String titulo) throws ObraNaoEncontradaException;
    void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException;
    Vector<Obra> listarObras();
    Vector<Obra> buscarPorAutor(String autor);
    Vector<Obra> topObras();
    Vector<Obra> obrasExpostas(String nomeExposicao);
    
    void criarExposicao(String nome);
    void adicionarObraAExposicao(String nomeExposicao, String tituloObra) throws ObraNaoEncontradaException;
    Vector<String> listarNomesExposicoes();
}
