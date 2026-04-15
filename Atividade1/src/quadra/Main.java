package quadra;

import java.util.List;
public class Main {
    public static void main(String[] args) {

        SistemaQuadra sistema = new SistemaQuadra();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE ALUGUEL DE QUADRA ESPORTIVA     ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── 1. Cadastro de clientes ────────────────────────────────────────────
        System.out.println("► 1. CADASTRO DE CLIENTES");
        Cliente joao  = sistema.cadastrarCliente("João Silva",  "(44) 99999-1111");
        Cliente maria = sistema.cadastrarCliente("Maria Souza", "(44) 98888-2222", "maria@email.com");
        Cliente pedro = sistema.cadastrarCliente("Pedro Lima",  "(44) 97777-3333");
        sistema.listarClientes().forEach(c -> System.out.println("   " + c));

        // ── 2. Validação: nome vazio ───────────────────────────────────────────
        System.out.println("\n► 2. VALIDAÇÃO: nome vazio");
        try {
            sistema.cadastrarCliente("  ", "(44) 90000-0000");
        } catch (IllegalArgumentException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 3. Cadastro de horários ────────────────────────────────────────────
        System.out.println("\n► 3. CADASTRO DE HORÁRIOS");
        Horario h1 = sistema.cadastrarHorario("08:00", "09:00",  80.00);
        Horario h2 = sistema.cadastrarHorario("09:00", "10:00",  80.00);
        Horario h3 = sistema.cadastrarHorario("10:00", "11:00",  80.00);
        Horario h4 = sistema.cadastrarHorario("19:00", "20:00", 120.00);
        Horario h5 = sistema.cadastrarHorario("20:00", "21:00", 120.00);
        sistema.listarHorarios().forEach(h -> System.out.println("   " + h));

        // ── 4. Validação: valor negativo ───────────────────────────────────────
        System.out.println("\n► 4. VALIDAÇÃO: valor negativo");
        try {
            sistema.cadastrarHorario("22:00", "23:00", -50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 5. Horários disponíveis antes das reservas ────────────────────────
        System.out.println("\n► 5. HORÁRIOS DISPONÍVEIS (antes das reservas)");
        sistema.listarHorariosDisponiveis().forEach(h -> System.out.println("   " + h));

        // ── 6. Registro de aluguéis ────────────────────────────────────────────
        System.out.println("\n► 6. REGISTRO DE ALUGUÉIS");
        Aluguel a1 = sistema.registrarAluguel(joao,  h1, "2024-06-10");
        Aluguel a2 = sistema.registrarAluguel(joao,  h2, "2024-06-10");
        Aluguel a3 = sistema.registrarAluguel(maria, h4, "2024-06-10");
        Aluguel a4 = sistema.registrarAluguel(pedro, h3, "2024-06-10");
        System.out.println("   " + a1);
        System.out.println("   " + a2);
        System.out.println("   " + a3);
        System.out.println("   " + a4);

        // ── 7. Tentativa de reservar horário já ocupado ────────────────────────
        System.out.println("\n► 7. TENTATIVA DE RESERVAR HORÁRIO JÁ OCUPADO");
        try {
            sistema.registrarAluguel(pedro, h1, "2024-06-10");
        } catch (IllegalStateException e) {
            System.out.println("   ERRO ESPERADO: " + e.getMessage());
        }

        // ── 8. Horários disponíveis após reservas ──────────────────────────────
        System.out.println("\n► 8. HORÁRIOS DISPONÍVEIS (após reservas)");
        List<Horario> disponiveis = sistema.listarHorariosDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("   Nenhum horário disponível.");
        } else {
            disponiveis.forEach(h -> System.out.println("   " + h));
        }

        // ── 9. Consulta por data ───────────────────────────────────────────────
        System.out.println("\n► 9. CONSULTA DE ALUGUÉIS EM 2024-06-10");
        sistema.consultarPorData("2024-06-10").forEach(a -> System.out.println("   " + a));

        // ── 10. Total do dia por cliente ───────────────────────────────────────
        System.out.println("\n► 10. TOTAL DO DIA EM 2024-06-10");
        for (Cliente c : sistema.listarClientes()) {
            double total = sistema.calcularTotalDia(c, "2024-06-10");
            if (total > 0) {
                System.out.printf("   %-15s → R$ %.2f%n", c.getNome(), total);
            }
        }

        // ── 11. Registro de pagamento ──────────────────────────────────────────
        System.out.println("\n► 11. REGISTRANDO PAGAMENTO DO ALUGUEL #" + a1.getId());
        sistema.registrarPagamento(a1.getId());
        sistema.consultarPorData("2024-06-10").forEach(a -> System.out.println("   " + a));

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║              SIMULAÇÃO CONCLUÍDA             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}

