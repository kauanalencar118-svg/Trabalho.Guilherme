package quadra.model;

public class Cliente {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String nome, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do cliente é obrigatório.");
        }
        this.id       = contadorId++;
        this.nome     = nome.trim();
        this.telefone = telefone.trim();
    }

    public Cliente(String nome, String telefone, String email) {
        this(nome, telefone);
        this.email = email;
    }

    public int getId()          { return id; }
    public String getNome()     { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail()    { return email; }

    @Override
    public String toString() {
        return String.format("Cliente[id=%d, nome=%s, telefone=%s]", id, nome, telefone);
    }
}
