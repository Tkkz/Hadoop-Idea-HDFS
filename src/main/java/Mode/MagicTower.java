package Mode;

/**
 * Created by sssss on 2017/3/16.
 */

import java.awt.Point;
import javax.swing.JFrame;

public class MagicTower extends JFrame {
    Hero hero;
    Game game;
    JFrame state;
    StatePane sp;
    JFrame book;
    BookPane bp;
    MyPanel mp;

    MagicTower() {
        this.initObject();
        this.initWindow();
        this.initMusic();
    }

    void initObject() {
        this.hero = new Hero(1000, 5, 5, new Point(5, 9));
        this.game = new Game(this.hero);
        this.sp = new StatePane(this.hero, this.game);
        this.bp = new BookPane(this.hero, this.game);
        this.book = new JFrame("怪物手册");
        this.state = new JFrame("人物信息");
        this.book.add(this.bp);
        this.state.add(this.sp);
        this.mp = new MyPanel(this.hero, this.game, this.sp, this.bp);
        this.add(this.mp);
        this.mp.mt = this;
        this.mp.book = this.book;
        this.mp.state = this.state;
        (new MonsterShake(this.mp)).start();
        this.hero.mp = this.mp;
        this.hero.bp = this.bp;
        this.hero.game = this.game;
    }

    void initWindow() {
        this.state.setUndecorated(true);
        this.state.setResizable(false);
        this.state.setSize(200, 320);
        this.book.setUndecorated(true);
        this.book.setResizable(false);
        this.book.setSize(300, 300);
        this.bp.showInfo();
        this.setLocation(500, 100);
        this.setResizable(false);
        this.addKeyListener(this.mp);
        Game var10001 = this.game;
        this.setTitle("魔塔J0X06型 BY 魔塔与我讨论");
        this.setSize(389, 409);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    void initMusic() {
        this.game.playMusic(1);
    }

    public static void main(String[] var0) {
        new MagicTower();
    }
}
