package quadra;

import quadra.controller.AluguelController;
import quadra.controller.ClienteController;
import quadra.controller.HorarioController;
import quadra.model.Aluguel;
import quadra.model.Cliente;
import quadra.model.Horario;
import quadra.repository.AluguelRepository;
import quadra.repository.ClienteRepository;
import quadra.repository.HorarioRepository;
import quadra.view.QuadraView;

public class Main {

    public static void main(String[] args) {

        // ── Injeção de dependências manual (sem framework) ─────────────────────
        ClienteRepository  clienteRepo  = new ClienteRepository();
        HorarioRepository  horarioRepo  = new HorarioRepository();
        AluguelRepository  aluguelRepo  = new AluguelRepository();

        ClienteController  clienteCtrl  = new ClienteController(clienteRepo);
        HorarioController  horarioCtrl  = new HorarioController(horarioRepo);
        AluguelController  aluguelCtrl  = new AluguelController(aluguelRepo);

        QuadraView view = new QuadraView();

        // ── Início da simulação ────────────────────────────────────────────────
        view.exibirTitulo("SISTEMA DE ALUGUEL DE QUADRA ESPORTIVA — MVC");

        // ── 1. Cadastro de clientes ────────────────────────────────────────────
        view.exibirSecao("1. CADASTRO DE CLIENTES");
        Cliente joao  = clienteCtrl.cadastrar("João Silva",  "(44) 99999-1111");
        Cliente maria = clienteCtrl.cadastrar("Maria Souza", "(44) 98888-2222", "maria@email.com");
        Cliente pedro = clienteCtrl.cadastrar("Pedro Lima",  "(44) 97777-3333");
        view.exibirClientes(clienteCtrl.listarTodos());

        // ── 2. Validação: nome vazio ───────────────────────────────────────────
        view.exibirSecao("2. VALIDAÇÃO: nome vazio");
        try {
            clienteCtrl.cadastrar("   ", "(44) 90000-0000");
        } catch (IllegalArgumentException e) {
            view.exibirErro(e.getMessage());
        }

        // ── 3. Cadastro de horários ────────────────────────────────────────────
        view.exibirSecao("3. CADASTRO DE HORÁRIOS");
        Horario h1 = horarioCtrl.cadastrar("08:00", "09:00",  80.00);
        Horario h2 = horarioCtrl.cadastrar("09:00", "10:00",  80.00);
        Horario h3 = horarioCtrl.cadastrar("10:00", "11:00",  80.00);
        Horario h4 = horarioCtrl.cadastrar("19:00", "20:00", 120.00);
        Horario h5 = horarioCtrl.cadastrar("20:00", "21:00", 120.00);
        view.exibirHorarios(horarioCtrl.listarTodos());

        // ── 4. Validação: valor negativo ───────────────────────────────────────
        view.exibirSecao("4. VALIDAÇÃO: valor negativo");
        try {
            horarioCtrl.cadastrar("22:00", "23:00", -50.00);
        } catch (IllegalArgumentException e) {
            view.exibirErro(e.getMessage());
        }

        // ── 5. Horários disponíveis antes das reservas ────────────────────────
        view.exibirSecao("5. HORÁRIOS DISPONÍVEIS (antes das reservas)");
        view.exibirHorarios(horarioCtrl.listarDisponiveis());

        // ── 6. Registro de aluguéis ────────────────────────────────────────────
        view.exibirSecao("6. REGISTRO DE ALUGUÉIS");
        Aluguel a1 = aluguelCtrl.registrar(joao,  h1, "2024-06-10");
        Aluguel a2 = aluguelCtrl.registrar(joao,  h2, "2024-06-10");
        Aluguel a3 = aluguelCtrl.registrar(maria, h4, "2024-06-10");
        Aluguel a4 = aluguelCtrl.registrar(pedro, h3, "2024-06-10");
        view.exibirAlugueis(aluguelCtrl.listarTodos());

        // ── 7. Tentativa de reservar horário já ocupado ────────────────────────
        view.exibirSecao("7. TENTATIVA DE RESERVAR HORÁRIO JÁ OCUPADO");
        try {
            aluguelCtrl.registrar(pedro, h1, "2024-06-10");
        } catch (IllegalStateException e) {
            view.exibirErro(e.getMessage());
        }

        // ── 8. Horários disponíveis após reservas ──────────────────────────────
        view.exibirSecao("8. HORÁRIOS DISPONÍVEIS (após reservas)");
        view.exibirHorarios(horarioCtrl.listarDisponiveis());

        // ── 9. Consulta por data ───────────────────────────────────────────────
        view.exibirSecao("9. CONSULTA DE ALUGUÉIS EM 2024-06-10");
        view.exibirAlugueis(aluguelCtrl.listarPorData("2024-06-10"));

        // ── 10. Total do dia por cliente ───────────────────────────────────────
        view.exibirSecao("10. TOTAL DO DIA EM 2024-06-10");
        for (Cliente c : clienteCtrl.listarTodos()) {
            double total = aluguelCtrl.calcularTotalDia(c, "2024-06-10");
            if (total > 0) {
                view.exibirTotalDia(c.getNome(), "2024-06-10", total);
            }
        }

        // ── 11. Registro de pagamento ──────────────────────────────────────────
        view.exibirSecao("11. REGISTRANDO PAGAMENTO DO ALUGUEL #" + a1.getId());
        aluguelCtrl.registrarPagamento(a1.getId());
        view.exibirAlugueis(aluguelCtrl.listarPorData("2024-06-10"));

        view.exibirTitulo("SIMULAÇÃO MVC CONCLUÍDA");
    }
}
