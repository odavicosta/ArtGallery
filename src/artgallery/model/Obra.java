package artgallery.model;

import java.util.Vector;

public abstract class Obra {
    private String titulo;
    private String autor;
    private boolean ativa;
    private Vector<Avaliacao> avaliacoes;

    public Obra(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.avaliacoes = new Vector<Avaliacao>();
        this.ativa = true;
    }

    public String getTitulo() { return titulo; }

    public String getAutor() { return autor; }

    public boolean isAtiva() { return ativa; }

    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao != null) {
            this.avaliacoes.add(avaliacao);
        }
    }

    public double mediaAvaliacoes() {
        if (this.avaliacoes.isEmpty()) {
            return 0.0;
        }
        
        double soma = 0.0;
        for (Avaliacao avaliacao : this.avaliacoes) {
            soma += avaliacao.getNota();
        }
        
        return soma / this.avaliacoes.size();
    }

    public abstract String exibirDetalhes();
}