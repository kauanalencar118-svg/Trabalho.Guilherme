package biblioteca;

public class Emprestimo {
    private static int contadorId = 1;

    private int id;
    private Aluno aluno;
    private Livro livro;
    private String dataEmprestimo;
    private String dataDevolucao;
    private boolean devolvido;

    public Emprestimo(Aluno aluno, Livro livro, String dataEmprestimo) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        if (dataEmprestimo == null || dataEmprestimo.trim().isEmpty()) {
            throw new IllegalArgumentException("Data do empréstimo é obrigatória.");
        }

        // Regra: só empresta se houver exemplar disponível
        livro.decrementarDisponivel();

        this.id             = contadorId++;
        this.aluno          = aluno;
        this.livro          = livro;
        this.dataEmprestimo = dataEmprestimo.trim();
        this.devolvido      = false;
    }

    public void registrarDevolucao(String dataDevolucao) {
        if (devolvido) {
            throw new IllegalStateException("Este empréstimo já foi devolvido.");
        }
        if (dataDevolucao == null || dataDevolucao.trim().isEmpty()) {
            throw new IllegalArgumentException("Data de devolução é obrigatória.");
        }
        this.dataDevolucao = dataDevolucao.trim();
        this.devolvido     = true;
        livro.incrementarDisponivel();
    }

    public int getId()               { return id; }
    public Aluno getAluno()          { return aluno; }
    public Livro getLivro()          { return livro; }
    public String getDataEmprestimo(){ return dataEmprestimo; }
    public String getDataDevolucao() { return dataDevolucao; }
    public boolean isDevolvido()     { return devolvido; }

    @Override
    public String toString() {
        return String.format("Emprestimo[id=%d, aluno=%s, livro=%s, emprestado=%s, %s]",
                id, aluno.getNome(), livro.getTitulo(), dataEmprestimo,
                devolvido ? "devolvido em " + dataDevolucao : "EM ABERTO");
    }
}
