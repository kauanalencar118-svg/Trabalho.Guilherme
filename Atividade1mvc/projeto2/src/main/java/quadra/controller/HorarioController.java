package quadra.controller;

import quadra.model.Horario;
import quadra.repository.HorarioRepository;

import java.util.List;
import java.util.Optional;

public class HorarioController {

    private final HorarioRepository repository;

    public HorarioController(HorarioRepository repository) {
        this.repository = repository;
    }

    public Horario cadastrar(String horaInicio, String horaFim, double valor) {
        Horario horario = new Horario(horaInicio, horaFim, valor);
        repository.salvar(horario);
        return horario;
    }

    public List<Horario> listarTodos() {
        return repository.buscarTodos();
    }

    public List<Horario> listarDisponiveis() {
        return repository.buscarDisponiveis();
    }

    public Optional<Horario> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }
}
