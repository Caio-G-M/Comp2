import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {
        List<Usuario> listaDeIntersecao = new ArrayList<>();
        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
        for (Usuario u1 : lista1) {
            for (Usuario u2 : lista2) {
                if (u1.equals(u2)){
                    listaDeIntersecao.add(u1);
                }
            }
        }
        return listaDeIntersecao;
    }
}
