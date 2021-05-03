public class Periodo {

    protected int ano;

    protected int semestre;  // 1 ou 2

    public Periodo(int ano, int semestre) {

        if (semestre < 1 || semestre > 2) {
            throw new RuntimeException("Semestre inválido!!!!");
        }

        this.ano = ano;
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }
}
