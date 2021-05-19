import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<T> todosOsColecionaveis;

    public Repositorio(String sufixoUrlImagens, int quantColecionaveis) {
        todosOsColecionaveis = new ArrayList<>(quantColecionaveis + 1);
        todosOsColecionaveis.add(null);
        for (int i = 1; i <= quantColecionaveis; i++) {
            T colecionavel = (T) new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsColecionaveis.add(colecionavel);
        }
    }

    public Repositorio(String sufixoUrlImagens, int quantColecionaveis, String tipoT) {
        todosOsColecionaveis = new ArrayList<>(quantColecionaveis + 1);
        todosOsColecionaveis.add(null);

        switch (tipoT) {
            case ("Figurinha"):
                for (int i = 1; i <= quantColecionaveis; i++) {
                    Figurinha colecionavel = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
                    todosOsColecionaveis.add((T) colecionavel);
                }
                break;
            case ("Selo"):
                for (int i = 1; i <= quantColecionaveis; i++) {
                    Selo colecionavel = new Selo(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
                    todosOsColecionaveis.add((T) colecionavel);

                }
                break;
        }

    }

    public int getTotalColecionaveis() {
        return this.todosOsColecionaveis.size() - 1;
    }

    public List<T> getTodosOsColecionaveis() {
        return todosOsColecionaveis;
    }

    public T getColecionavelDoRepositorio(int posicao) {
        return this.todosOsColecionaveis.get(posicao);
    }

}
