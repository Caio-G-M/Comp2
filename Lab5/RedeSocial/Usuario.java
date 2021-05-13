import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Comparable<Usuario> {

    protected int id;

    protected List<Usuario> amigos = new ArrayList<>();

    protected CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id, CalculadorIntersecao calculador) {
        this.id = id;
        this.calculadorIntersecao = calculador;
        // instancia um calculador de interseção
    }

    public int getId() {
        return id;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {

        //Escrevi aqui o teste de performance para os diferentes calculadores
        CalculadorIntersecaoIngenuo calculadorIngenuo = new CalculadorIntersecaoIngenuo();
        CalculadorIntersecaoEsperto calculadorEsperto = new CalculadorIntersecaoEsperto();
        CalculadorIntersecaoViaBuscaBinaria calculadorBinario = new CalculadorIntersecaoViaBuscaBinaria();
        Usuario usuario1 = new Usuario(1, calculadorIngenuo);
        Usuario usuario2 = new Usuario(2, calculadorEsperto);
        Usuario usuario3 = new Usuario(3, calculadorBinario);

        int i;
        for (i = 4; i < 50_000; i++) {
            Usuario amigo = new Usuario(i, calculadorIngenuo);
            usuario1.amigos.add(amigo);
            usuario2.amigos.add(amigo);
            usuario3.amigos.add(amigo);
        }

        System.out.printf("Para uma lista de %d amigos:\n\n", i);

        long startTime = System.currentTimeMillis();
        usuario1.obterQuantidadeDeAmigosEmComum(usuario2);
        long finishTime = System.currentTimeMillis();
        System.out.println("calculadorIngenuo levou: " + ((finishTime - startTime) / 1000f) + " segundos");

        startTime = System.currentTimeMillis();
        usuario2.obterQuantidadeDeAmigosEmComum(usuario3);
        finishTime = System.currentTimeMillis();
        System.out.println("calculadorEsperto levou: " + ((finishTime - startTime) / 1000f) + " segundos");

        startTime = System.currentTimeMillis();
        usuario3.obterQuantidadeDeAmigosEmComum(usuario1);
        finishTime = System.currentTimeMillis();
        System.out.println("calculadorBinario levou: " + ((finishTime - startTime) / 1000f) + " segundos");
    }

}

