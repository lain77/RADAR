package radarOxcart;

import java.util.ArrayList;
import java.util.List;

public class Ceu {
    List<Inimigo> inimigos = new ArrayList<>();
    Player player;

    public Ceu(Player player) {
        this.player = player;
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }
    
    public class Player {
        Yasmin posicao;

        public Player(int x, int y) {
            posicao = new Yasmin(x, y);
        }
    }

    public class Inimigo {
        Yasmin posicao;

        public Inimigo(int x, int y) {
            posicao = new Yasmin(x, y);
        }
    }
}
