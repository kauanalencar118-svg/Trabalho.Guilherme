package biblioteca;

public class Livro {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String autor;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(String titulo, String autor, int quantidade) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título do livro não pode ser vazio.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor do livro não pode ser vazio.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        this.id                   = contadorId++;
        this.titulo               = titulo.trim();
        this.autor                = autor.trim();
        this.quantidadeTotal      = quantidade;
        this.quantidadeDisponivel = quantidade;
    }

    public void decrementarDisponivel() {
        if (quantidadeDisponivel <= 0) {
            throw new IllegalStateException("Livro \"" + titulo + "\" não possui exemplares disponíveis.");
        }
        quantidadeDisponivel--;
    }

    public void incrementarDisponivel() {
        if (quantidadeDisponivel >= quantidadeTotal) {
            throw new IllegalStateException("Todos os exemplares de \"" + titulo + "\" já estão disponíveis.");
        }
        quantidadeDisponivel++;
    }

    public int getId()                   { return id; }
    public String getTitulo()            { return titulo; }
    public String getAutor()             { return autor; }
    public int getQuantidadeTotal()      { return quantidadeTotal; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }

    @Override
    public String toString() {
        return String.format("Livro[id=%d, titulo=%s, autor=%s, disponivel=%d/%d]",
                id, titulo, autor, quantidadeDisponivel, quantidadeTotal);
    }
}
