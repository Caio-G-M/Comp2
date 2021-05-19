import java.util.Random;

public class Pacotinho<T extends Colecionavel> {

    public T[] colecionaveisDoPacote;

    public Repositorio repositorio;

    public Pacotinho(Repositorio<T> repo, int[] posicoesDesejadas) {
        this.repositorio = repo;
        int colecionaveisPorPacote = 0;
        for (int i : posicoesDesejadas) {
            colecionaveisPorPacote++;
        }
        T[] array = (T[]) new Colecionavel[colecionaveisPorPacote];
        int contador = 0;
        for (int i : posicoesDesejadas) {
            array[contador] = (T) this.repositorio.getColecionavelDoRepositorio(i);
            contador++;
        }
        this.colecionaveisDoPacote = array;
    }

    /**
     * Produz um pacotinho com quantColecionaveis sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantColecionaveis a quantidade de colecionaveis a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantColecionaveis) {
        this.repositorio = repo;
        T[] array = (T[]) new Colecionavel[quantColecionaveis];
        int contador = 0;

        while (contador < quantColecionaveis) {
            array[contador] = (T) this.repositorio.getColecionavelDoRepositorio(new Random().nextInt(this.repositorio.getTotalColecionaveis() - 1) + 1);
            contador++;
        }
        this.colecionaveisDoPacote = array;

    }

    public T[] getColecionaveisDoPacote() {
        return this.colecionaveisDoPacote;
    }
}