package quadra.controller;

import quadra.model.Cliente;
import quadra.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;


public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(String nome, String telefone) {
        // Validações encapsuladas no próprio modelo
        Cliente cliente = new Cliente(nome, telefone);
        repository.salvar(cliente);
        return cliente;
    }

    public Cliente cadastrar(String nome, String telefone, String email) {
        Cliente cliente = new Cliente(nome, telefone, email);
        repository.salvar(cliente);
        return cliente;
    }

    public List<Cliente> listarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Cliente> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }
}
