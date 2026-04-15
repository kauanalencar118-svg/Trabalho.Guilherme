package quadra;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class SistemaQuadra {
    private final List<Cliente>  clientes  = new ArrayList<>();
    private final List<Horario>  horarios  = new ArrayList<>();
    private final List<Aluguel>  alugueis  = new ArrayList<>();

    // ── Clientes ──────────────────────────────────────────────────────────────

    public Cliente cadastrarCliente(String nome, String telefone) {
        Cliente c = new Cliente(nome, telefone);
        clientes.add(c);
        return c;
    }

    public Cliente cadastrarCliente(String nome, String telefone, String email) {
        Cliente c = new Cliente(nome, telefone, email);
        clientes.add(c);
        return c;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    // ── Horários ──────────────────────────────────────────────────────────────

    public Horario cadastrarHorario(String inicio, String fim, double valor) {
        Horario h = new Horario(inicio, fim, valor);
        horarios.add(h);
        return h;
    }

    public List<Horario> listarHorarios() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> listarHorariosDisponiveis() {
        return horarios.stream()
                .filter(Horario::isDisponivel)
                .collect(Collectors.toList());
    }

    // ── Aluguéis ──────────────────────────────────────────────────────────────

    public Aluguel registrarAluguel(Cliente cliente, Horario horario, String data) {
        // Validação de disponibilidade encapsulada no construtor de Aluguel
        Aluguel a = new Aluguel(cliente, horario, data);
        alugueis.add(a);
        return a;
    }

    public void registrarPagamento(int aluguelId) {
        alugueis.stream()
                .filter(a -> a.getId() == aluguelId)
                .findFirst()
                .ifPresent(a -> a.setPago(true));
    }

    public List<Aluguel> consultarPorData(String data) {
        return alugueis.stream()
                .filter(a -> a.getData().equals(data))
                .collect(Collectors.toList());
    }

    public double calcularTotalDia(Cliente cliente, String data) {
        return alugueis.stream()
                .filter(a -> a.getData().equals(data)
                        && a.getCliente().getId() == cliente.getId())
                .mapToDouble(Aluguel::getValorCobrado)
                .sum();
    }

    public List<Aluguel> listarAlugueis() {
        return new ArrayList<>(alugueis);
    }
    }
