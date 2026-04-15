package biblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaBiblioteca {

    private final List<Livro>      livros      = new ArrayList<>();
    private final List<Aluno>      alunos      = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    // ── Livros ────────────────────────────────────────────────────────────────

    public Livro cadastrarLivro(String titulo, String autor, int quantidade) {
        Livro l = new Livro(titulo, autor, quantidade);
        livros.add(l);
        return l;
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    // ── Alunos ────────────────────────────────────────────────────────────────

    public Aluno cadastrarAluno(String nome, String matricula) {
        Aluno a = new Aluno(nome, matricula);
        alunos.add(a);
        return a;
    }

    public Aluno cadastrarAluno(String nome, String matricula, String email) {
        Aluno a = new Aluno(nome, matricula, email);
        alunos.add(a);
        return a;
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    // ── Empréstimos ───────────────────────────────────────────────────────────

    public Emprestimo registrarEmprestimo(Aluno aluno, Livro livro, String data) {
        Emprestimo e = new Emprestimo(aluno, livro, data);
        emprestimos.add(e);
        return e;
    }

    public void registrarDevolucao(int emprestimoId, String dataDevolucao) {
        emprestimos.stream()
                .filter(e -> e.getId() == emprestimoId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado: id=" + emprestimoId))
                .registrarDevolucao(dataDevolucao);
    }

    public List<Emprestimo> listarEmprestimosEmAberto() {
        return emprestimos.stream()
                .filter(e -> !e.isDevolvido())
                .collect(Collectors.toList());
    }

    public List<Aluno> listarAlunosComEmprestimoEmAberto() {
        return emprestimos.stream()
                .filter(e -> !e.isDevolvido())
                .map(Emprestimo::getAluno)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return new ArrayList<>(emprestimos);
    }
}

