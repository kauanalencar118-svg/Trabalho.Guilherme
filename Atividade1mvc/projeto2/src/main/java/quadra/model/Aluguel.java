package quadra.model;

public class Aluguel {

    private static int contadorId = 1;

    private int id;
    private Cliente cliente;
    private Horario horario;
    private String data;
    private double valorCobrado;
    private boolean pago;

    public Aluguel(Cliente cliente, Horario horario, String data) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (horario == null) {
            throw new IllegalArgumentException("Horário não pode ser nulo.");
        }
        if (!horario.isDisponivel()) {
            throw new IllegalStateException(
                "Horário " + horario.getHoraInicio() + "-" + horario.getHoraFim()
                + " já está reservado.");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Data do aluguel é obrigatória.");
        }

        this.id           = contadorId++;
        this.cliente      = cliente;
        this.horario      = horario;
        this.data         = data.trim();
        this.valorCobrado = horario.getValor();
        this.pago         = false;

        horario.setDisponivel(false);
    }

    public int getId()               { return id; }
    public Cliente getCliente()      { return cliente; }
    public Horario getHorario()      { return horario; }
    public String getData()          { return data; }
    public double getValorCobrado()  { return valorCobrado; }
    public boolean isPago()          { return pago; }
    public void setPago(boolean pago){ this.pago = pago; }

    @Override
    public String toString() {
        return String.format("Aluguel[id=%d, cliente=%s, %s-%s, data=%s, R$%.2f, %s]",
                id, cliente.getNome(),
                horario.getHoraInicio(), horario.getHoraFim(),
                data, valorCobrado,
                pago ? "PAGO" : "PENDENTE");
    }
}
