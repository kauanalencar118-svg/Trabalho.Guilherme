package quadra.view;

import quadra.model.Aluguel;
import quadra.model.Cliente;
import quadra.model.Horario;

import java.util.List;

/**
 * View responsável por exibir os dados ao usuário no console.
 * Não contém nenhuma lógica de negócio — apenas formata e imprime.
 */
public class QuadraView {

    public void exibirTitulo(String titulo) {
        String linha = "═".repeat(titulo.length() + 4);
        System.out.println("╔" + linha + "╗");
        System.out.println("║  " + titulo + "  ║");
        System.out.println("╚" + linha + "╝");
    }

    public void exibirSecao(String secao) {
        System.out.println("\n► " + secao);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println("   " + mensagem);
    }

    public void exibirErro(String mensagem) {
        System.out.println("   [ERRO] " + mensagem);
    }

    public void exibirClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("   Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(c -> System.out.println("   " + c));
    }

    public void exibirHorarios(List<Horario> horarios) {
        if (horarios.isEmpty()) {
            System.out.println("   Nenhum horário encontrado.");
            return;
        }
        horarios.forEach(h -> System.out.println("   " + h));
    }

    public void exibirAlugueis(List<Aluguel> alugueis) {
        if (alugueis.isEmpty()) {
            System.out.println("   Nenhum aluguel encontrado.");
            return;
        }
        alugueis.forEach(a -> System.out.println("   " + a));
    }

    public void exibirTotalDia(String nomeCliente, String data, double total) {
        System.out.printf("   %-15s → %s → R$ %.2f%n", nomeCliente, data, total);
    }
}
