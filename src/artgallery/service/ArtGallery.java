package artgallery.service;

import java.io.*;
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
    private final String ARQUIVO_EXP = "exposicoes.dat";

    public ArtGallery(IRepositorioObra repositorio) {
        this.repositorio = repositorio;
        carregarExposicoes();
    }

    @SuppressWarnings("unchecked")
    private void carregarExposicoes() {
        File arquivo = new File(ARQUIVO_EXP);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                this.exposicoes = (Vector<Exposicao>) ois.readObject();
            } catch (Exception e) {
                this.exposicoes = new Vector<Exposicao>();
            }
        } else {
            this.exposicoes = new Vector<Exposicao>();
        }
    }

    private void salvarExposicoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_EXP))) {
            oos.writeObject(this.exposicoes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar exposições: " + e.getMessage());
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
        this.repositorio.atualizar(obra); // Grava a avaliação no ficheiro do repositório
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

    @Override
    public void criarExposicao(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.exposicoes.add(new Exposicao(nome));
            salvarExposicoes();
        }
    }

    @Override
    public void adicionarObraAExposicao(String nomeExposicao, String tituloObra) throws ObraNaoEncontradaException {
        Exposicao exposicaoEncontrada = null;
        for (Exposicao exp : this.exposicoes) {
            if (exp.getNome().equalsIgnoreCase(nomeExposicao)) {
                exposicaoEncontrada = exp;
                break;
            }
        }
        if (exposicaoEncontrada == null) {
            throw new ObraNaoEncontradaException("Exposição não encontrada: " + nomeExposicao);
        }

        Obra obra = this.repositorio.buscar(tituloObra);
        if (obra == null || !obra.isAtiva()) {
            throw new ObraNaoEncontradaException("A obra não existe ou está inativa.");
        }

        exposicaoEncontrada.adicionarObra(obra);
        salvarExposicoes();
    }

    @Override
    public Vector<String> listarNomesExposicoes() {
        Vector<String> nomes = new Vector<String>();
        for (Exposicao exp : this.exposicoes) {
            nomes.add(exp.getNome());
        }
        return nomes;
    }
}