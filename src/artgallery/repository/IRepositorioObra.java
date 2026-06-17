package artgallery.repository;

import java.util.Vector;
import artgallery.model.Obra;
import artgallery.exception.ObraJaCadastradaException;
import artgallery.exception.ObraNaoEncontradaException;

public interface IRepositorioObra {
    void cadastrar(Obra obra) throws ObraJaCadastradaException;
    Obra buscar(String titulo);
    void atualizar(Obra obra) throws ObraNaoEncontradaException;
    void remover(String titulo);
    Vector<Obra> listar();
}