package quadra.controller;

import quadra.model.Aluguel;
import quadra.model.Cliente;
import quadra.model.Horario;
import quadra.repository.AluguelRepository;

import java.util.List;

public class AluguelController {

    private final AluguelRepository repository;

    public AluguelController(AluguelRepository repository) {
        this.repository = repository;
    }


    public Aluguel registrar(Cliente cliente, Horario horario, String data) {
        Aluguel aluguel = new Aluguel(cliente, horario, data);
        repository.salvar(aluguel);
        return aluguel;
    }



    public void registrarPagamento(int aluguelId) {
        repository.buscarPorId(aluguelId)
                  .ifPresent(a -> a.setPago(true));
    }

    public List<Aluguel> listarTodos() {
        return repository.buscarTodos();
    }

    public List<Aluguel> listarPorData(String data) {
        return repository.buscarPorData(data);
    }

    public double calcularTotalDia(Cliente cliente, String data) {
        return repository.buscarPorClienteEData(cliente, data)
                .stream()
                .mapToDouble(Aluguel::getValorCobrado)
                .sum();
    }
}
