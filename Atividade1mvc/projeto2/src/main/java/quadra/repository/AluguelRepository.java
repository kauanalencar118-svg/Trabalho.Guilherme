package quadra.repository;

import quadra.model.Aluguel;
import quadra.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class AluguelRepository {

    private final List<Aluguel> alugueis = new ArrayList<>();

    public void salvar(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public List<Aluguel> buscarTodos() {
        return new ArrayList<>(alugueis);
    }

    public List<Aluguel> buscarPorData(String data) {
        return alugueis.stream()
                .filter(a -> a.getData().equals(data))
                .collect(Collectors.toList());
    }

    public List<Aluguel> buscarPorClienteEData(Cliente cliente, String data) {
        return alugueis.stream()
                .filter(a -> a.getData().equals(data)
                          && a.getCliente().getId() == cliente.getId())
                .collect(Collectors.toList());
    }

    public Optional<Aluguel> buscarPorId(int id) {
        return alugueis.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }
}
