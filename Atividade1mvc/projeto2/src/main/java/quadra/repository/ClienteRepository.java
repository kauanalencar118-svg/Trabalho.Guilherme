package quadra.repository;

import quadra.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();

    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
