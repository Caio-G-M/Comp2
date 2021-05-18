import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumTest {
    private Album albumColecionaveis;
    private Repositorio repositorioColecionaveis;
    private static final int TAMANHO_DO_ALBUM = 200;
    private static final int ITENS_POR_PACOTE = 3;

    @Before  // roda antes de cada teste
    public void setUp() {
        this.repositorioColecionaveis = new Repositorio("album_copa2014", TAMANHO_DO_ALBUM);
        this.albumColecionaveis = new Album(repositorioColecionaveis, ITENS_POR_PACOTE);
    }
    private void popularAlbum(int[] posicoesDesejadas) {
        Pacotinho pacote = new Pacotinho(this.repositorioColecionaveis, posicoesDesejadas);
        this.albumColecionaveis.receberNovoPacotinho(pacote);
    }

    @Test
    public void testarPossuiColecionavelParaColecionaveisExistentes() {
        popularAlbum(new int[] {1, 2, 3});
        for (int i = 1; i <= 3; i++) {
            assertTrue("Colecionaveis já inseridos devem ser encontrados",
                    this.albumColecionaveis.possuiItemColado(i));
        }
    }

    @Test
    public void testarPossuiColecionavelParaColecionaveisAusentes() {
        popularAlbum(new int[] {1, 2, 3});
        assertFalse("Não devemos encontrar no álbum Colecionaveis nunca inseridas",
                this.albumColecionaveis.possuiItemColado(4));
        assertFalse("Não devemos encontrar Colecionaveis maiores do que o tamanho",
                this.albumColecionaveis.possuiItemColado(TAMANHO_DO_ALBUM + 1));
        assertFalse("Não devemos encontrar Colecionaveis de posições não-positivas",
                this.albumColecionaveis.possuiItemColado(-390));
    }

    @Test
    public void testarPossuiRepetidaParaColecionavelRepetido() {
        popularAlbum(new int[] {1, 2, 3});
        assertFalse(this.albumColecionaveis.possuiItemRepetido(2));  // sanity check
        popularAlbum(new int[] {2, 8, 9});
        assertTrue(this.albumColecionaveis.possuiItemRepetido(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.albumColecionaveis.getTamanho());
    }

    @Test
    public void testarGetContColecionaveis() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(3, this.albumColecionaveis.getQuantItensColados());
        // vou agora abrir outro pacotinho com as mesmas figurinhas
        // e verificar que o álbum terá ainda 3 figurinhas
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(3, this.albumColecionaveis.getQuantItensColados());
    }

    @Test
    public void testarGetQuantasFaltam() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(TAMANHO_DO_ALBUM - 3,
                this.albumColecionaveis.getQuantItensFaltantes());
    }

    @Test
    public void testarAutoCompletar() {
        albumColecionaveis.autoCompletar();
        assertEquals("Não deve ser possível auto-completar um álbum que esteja vazio",
                TAMANHO_DO_ALBUM, albumColecionaveis.getQuantItensFaltantes());
        // agora vamos adicionar pacotinhos aleatórios até o álbum ficar quase cheio
        int minimoColecionaveisColadosParaAutoCompletar =
                (int) (TAMANHO_DO_ALBUM * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);
        while (albumColecionaveis.getQuantItensColados() < minimoColecionaveisColadosParaAutoCompletar) {
            Pacotinho novoPacotinho = new Pacotinho(
                    this.repositorioColecionaveis, ITENS_POR_PACOTE);  // aleatório
            albumColecionaveis.receberNovoPacotinho(novoPacotinho);
        }
        // sanity check
        assertTrue(albumColecionaveis.getQuantItensFaltantes() > 0);

        albumColecionaveis.autoCompletar();
        assertEquals("O álbum deve estar completo após uma chamada válida ao auto-completar " +
                        "(isto é, após o percentual mínimo de Colecionaveis já ter sido obtido)",
                0, albumColecionaveis.getQuantItensFaltantes());  // álbum completo!
    }

    @Test
    public void testarGetItemColado() {
        popularAlbum(new int[] {1, 2, 3});
        Colecionavel colecionavel = albumColecionaveis.getItemColado(2);
        assertNotNull(colecionavel);
        assertEquals(2, colecionavel.getPosicao());
        assertNull(albumColecionaveis.getItemColado(4));
    }
    @Test
    public void testarRejeicaoPacotinhosDeTamanhoErrado() {
        popularAlbum(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8});
        assertEquals("Pacotes de tamanho distinto do informado na construção " +
                        "do álbum devem ser rejeitados",
                0, albumColecionaveis.getQuantItensColados());
    }
}