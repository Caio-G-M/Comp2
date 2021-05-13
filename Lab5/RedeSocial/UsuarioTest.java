import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTest {

    CalculadorIntersecao calculador = new CalculadorIntersecaoViaBuscaBinaria();
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario amigo1;
    private Usuario amigo2;
    private Usuario amigo3;
    private Usuario amigo4;

    @Before
    public void setUp() {

        usuario1 = new Usuario(1234, calculador);
        usuario2 = new Usuario(1235, calculador);
        amigo1 = new Usuario(1236, calculador);
        amigo2 = new Usuario(1237, calculador);
        amigo3 = new Usuario(1238, calculador);
        amigo4 = new Usuario(1239, calculador);
    }

    @Test
    public void testarInclusaoListaDeAmigosIgual() {
        usuario1.amigos.add(amigo1);
        usuario1.amigos.add(amigo2);
        usuario2.amigos.add(amigo1);
        usuario2.amigos.add(amigo2);
        assertEquals("A lista de amigos é igual.",usuario1.obterQuantidadeDeAmigosEmComum(usuario2),2);
    }

    @Test
    public void testarInclusaoListaDeAmigosComUmAmigoIgual() {
        usuario1.amigos.add(amigo1);
        usuario1.amigos.add(amigo2);
        usuario2.amigos.add(amigo1);
        usuario2.amigos.add(amigo4);
        assertEquals("A lista de amigos tem uma inteseção.", usuario1.obterQuantidadeDeAmigosEmComum(usuario2),1);
    }

    @Test
    public void testarInclusaoListaDeAmigosVazia() {
        usuario1.amigos.add(amigo1);
        usuario1.amigos.add(amigo2);
        usuario2.amigos.add(amigo3);
        usuario2.amigos.add(amigo4);
        assertEquals("A lista de amigos tem nenhuma interseção.", usuario1.obterQuantidadeDeAmigosEmComum(usuario2),0);
    }




}