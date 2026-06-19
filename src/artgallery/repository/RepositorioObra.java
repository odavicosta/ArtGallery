package artgallery.repository;

import java.io.*;
import java.util.Vector;

import artgallery.exception.ObraJaCadastradaException;
import artgallery.exception.ObraNaoEncontradaException;
import artgallery.model.Obra;

public class RepositorioObra implements IRepositorioObra {
    private static final String ARQUIVO = "obras.dat";
    private Vector<Obra> obras;

    public RepositorioObra() {
        this.obras = carregar();
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
        salvar();
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
        salvar();
    }

    @Override
    public void remover(String titulo) {
        Obra obra = buscar(titulo);
        if (obra != null) {
            obra.setAtiva(false);
            salvar();
        }
    }

    @Override
    public Vector<Obra> listar() {
        return this.obras;
    }

    @SuppressWarnings("unchecked")
    private Vector<Obra> carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new Vector<Obra>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Vector<Obra>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível carregar dados salvos: " + e.getMessage());
            return new Vector<Obra>();
        }
    }

    private void salvar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(this.obras);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os dados: " + e.getMessage());
        }
    }
}