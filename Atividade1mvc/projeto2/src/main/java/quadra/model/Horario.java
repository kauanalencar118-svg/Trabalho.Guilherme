package quadra.model;

public class Horario {

    private static int contadorId = 1;

    private int id;
    private String horaInicio;
    private String horaFim;
    private double valor;
    private boolean disponivel;

    public Horario(String horaInicio, String horaFim, double valor) {
        if (horaInicio == null || horaInicio.trim().isEmpty()) {
            throw new IllegalArgumentException("Hora de início é obrigatória.");
        }
        if (horaFim == null || horaFim.trim().isEmpty()) {
            throw new IllegalArgumentException("Hora de fim é obrigatória.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("Valor do horário não pode ser negativo.");
        }
        this.id         = contadorId++;
        this.horaInicio = horaInicio.trim();
        this.horaFim    = horaFim.trim();
        this.valor      = valor;
        this.disponivel = true;
    }

    public int getId()            { return id; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFim()    { return horaFim; }
    public double getValor()      { return valor; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return String.format("Horario[id=%d, %s-%s, R$%.2f, %s]",
                id, horaInicio, horaFim, valor,
                disponivel ? "disponível" : "ocupado");
    }
}
