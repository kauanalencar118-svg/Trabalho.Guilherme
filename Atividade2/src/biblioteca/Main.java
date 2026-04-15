package biblioteca;

public class Main {
    public static void main(String[] args) {

        SistemaBiblioteca sistema = new SistemaBiblioteca();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE CONTROLE DE BIBLIOTECA       ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── 1. Cadastro de livros ──────────────────────────────────────────────
        System.out.println("► 1. CADASTRO DE LIVROS");
        Livro l1 = sistema.cadastrarLivro("Dom Casmurro",         "Machado de Assis", 3);
        Livro l2 = sistema.cadastrarLivro("O Cortiço",            "Aluísio Azevedo",  2);
        Livro l3 = sistema.cadastrarLivro("Vidas Secas",          "Graciliano Ramos", 1);
        Livro l4 = sistema.cadastrarLivro("A Moreninha",          "Joaquim Macedo",   2);
        sistema.listarLivros().forEach(l -> System.out.println("   " + l));

        // ── 2. Validação: título vazio ─────────────────────────────────────────
        System.out.println("\n► 2. VALIDAÇÃO: título vazio");
        try {
            sistema.cadastrarLivro("   ", "Autor X", 2);
        } catch (IllegalArgumentException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 3. Validação: quantidade negativa ──────────────────────────────────
        System.out.println("\n► 3. VALIDAÇÃO: quantidade negativa");
        try {
            sistema.cadastrarLivro("Livro Inválido", "Autor Y", -1);
        } catch (IllegalArgumentException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 4. Cadastro de alunos ──────────────────────────────────────────────
        System.out.println("\n► 4. CADASTRO DE ALUNOS");
        Aluno a1 = sistema.cadastrarAluno("Ana Lima",    "2024001");
        Aluno a2 = sistema.cadastrarAluno("Bruno Costa", "2024002", "bruno@escola.com");
        Aluno a3 = sistema.cadastrarAluno("Carla Dias",  "2024003");
        sistema.listarAlunos().forEach(a -> System.out.println("   " + a));

        // ── 5. Validação: nome de aluno vazio ──────────────────────────────────
        System.out.println("\n► 5. VALIDAÇÃO: nome de aluno vazio");
        try {
            sistema.cadastrarAluno("", "2024999");
        } catch (IllegalArgumentException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 6. Registro de empréstimos ─────────────────────────────────────────
        System.out.println("\n► 6. REGISTRO DE EMPRÉSTIMOS");
        Emprestimo e1 = sistema.registrarEmprestimo(a1, l1, "2024-06-10");
        Emprestimo e2 = sistema.registrarEmprestimo(a2, l1, "2024-06-10");
        Emprestimo e3 = sistema.registrarEmprestimo(a3, l3, "2024-06-11");
        Emprestimo e4 = sistema.registrarEmprestimo(a1, l2, "2024-06-11");
        System.out.println("   " + e1);
        System.out.println("   " + e2);
        System.out.println("   " + e3);
        System.out.println("   " + e4);

        // ── 7. Tentativa de emprestar livro sem exemplar disponível ───────────
        System.out.println("\n► 7. TENTATIVA DE EMPRESTAR SEM EXEMPLAR DISPONÍVEL");
        try {
            sistema.registrarEmprestimo(a2, l3, "2024-06-11"); // Vidas Secas tem apenas 1
        } catch (IllegalStateException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 8. Livros disponíveis após empréstimos ─────────────────────────────
        System.out.println("\n► 8. LIVROS APÓS EMPRÉSTIMOS");
        sistema.listarLivros().forEach(l -> System.out.println("   " + l));

        // ── 9. Empréstimos em aberto ───────────────────────────────────────────
        System.out.println("\n► 9. EMPRÉSTIMOS EM ABERTO");
        sistema.listarEmprestimosEmAberto().forEach(e -> System.out.println("   " + e));

        // ── 10. Alunos com empréstimos em aberto ──────────────────────────────
        System.out.println("\n► 10. ALUNOS COM EMPRÉSTIMOS EM ABERTO");
        sistema.listarAlunosComEmprestimoEmAberto().forEach(a -> System.out.println("   " + a));

        // ── 11. Registro de devolução ─────────────────────────────────────────
        System.out.println("\n► 11. REGISTRANDO DEVOLUÇÃO DO EMPRÉSTIMO #" + e1.getId());
        sistema.registrarDevolucao(e1.getId(), "2024-06-15");
        System.out.println("   " + e1);

        // ── 12. Tentativa de devolver empréstimo já devolvido ─────────────────
        System.out.println("\n► 12. TENTATIVA DE DEVOLVER EMPRÉSTIMO JÁ DEVOLVIDO");
        try {
            sistema.registrarDevolucao(e1.getId(), "2024-06-16");
        } catch (IllegalStateException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 13. Estado final ──────────────────────────────────────────────────
        System.out.println("\n► 13. EMPRÉSTIMOS EM ABERTO (após devolução)");
        sistema.listarEmprestimosEmAberto().forEach(e -> System.out.println("   " + e));

        System.out.println("\n► 14. LIVROS (estado final)");
        sistema.listarLivros().forEach(l -> System.out.println("   " + l));

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║              SIMULAÇÃO CONCLUÍDA             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
