package quadra.repository;

import quadra.model.Horario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HorarioRepository {

    private final List<Horario> horarios = new ArrayList<>();

    public void salvar(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> buscarTodos() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> buscarDisponiveis() {
        return horarios.stream()
                .filter(Horario::isDisponivel)
                .collect(Collectors.toList());
    }

    public Optional<Horario> buscarPorId(int id) {
        return horarios.stream()
                .filter(h -> h.getId() == id)
                .findFirst();
    }
}
