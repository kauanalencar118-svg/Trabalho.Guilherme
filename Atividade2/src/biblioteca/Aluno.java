package biblioteca;

public class Aluno {
    private static int contadorId = 1;

    private int id;
    private String nome;
    private String matricula;
    private String email;

    public Aluno(String nome, String matricula) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio.");
        }
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula do aluno é obrigatória.");
        }
        this.id        = contadorId++;
        this.nome      = nome.trim();
        this.matricula = matricula.trim();
    }

    public Aluno(String nome, String matricula, String email) {
        this(nome, matricula);
        this.email = email;
    }

    public int getId()          { return id; }
    public String getNome()     { return nome; }
    public String getMatricula(){ return matricula; }
    public String getEmail()    { return email; }

    @Override
    public String toString() {
        return String.format("Aluno[id=%d, nome=%s, matricula=%s]", id, nome, matricula);
    }
}
