import java.util.HashMap;

public class Turma {

    public Disciplina disciplina;

    public Periodo periodo;

    public Professor professor;

    public HashMap<Long, Aluno> alunosInscritosByDre = new HashMap<>();

    private HashMap<Long, Float> notaByDre = new HashMap<>();

    public Turma(Disciplina disciplina, Periodo periodo, Professor professor) {
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.professor = professor;
    }

    void inscreverAluno(Aluno aluno){
        this.alunosInscritosByDre.put(aluno.getDre(), aluno);
    }

    void atribuirMediaFinal(long dre, float nota){
        notaByDre.put(dre, nota);
    }

    float obterMediaFinal(long dre){
        return notaByDre.get(dre);
    }

    String listarAlunos(){
        String listaAlunos = "Lista de alunos: \n";
        for (Aluno aluno: alunosInscritosByDre.values()){
            listaAlunos += aluno.getNome() + "\n";
        }
        return listaAlunos;

    }


}
