package Mode;

/**
 * Created by sssss on 2017/3/16.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class BookPane extends JPanel {
    Hero hero;
    Game game;
    static int cnt;
    static String[] info = new String[32];
    static int[] imgtmp = new int[32];
    static final String ATT = "名字            生命   攻击  防御  伤害";

    BookPane(Hero var1, Game var2) {
        this.hero = var1;
        this.game = var2;
    }

    int judgeDamage(Hero var1, int var2) {
        --var2;
        Monster var3 = new Monster(Game.MNAME[var2], Game.MHP[var2], Game.MATK[var2], Game.MDEF[var2], Game.MMONEY[var2], Game.MEXP[var2]);
        int var4 = var3.hp;
        int var5 = var3.atk;
        int var6 = var3.def;
        int var7 = var1.getHp();
        int var8 = var1.getAtk();
        int var9 = var1.getDef();
        boolean var10 = false;
        int var13;
        if (var8 <= var6) {
            var13 = 9999999;
        } else if (var5 <= var9) {
            var13 = 0;
        } else {
            while (true) {
                int var11 = var8 - var6;
                int var12 = var5 - var9;
                var4 -= var11;
                if (var4 <= 0) {
                    var13 = var1.getHp() - var7;
                    break;
                }

                var7 -= var12;
            }
        }

        return var13;
    }

    public void paint(Graphics var1) {
        super.paint(var1);
        Image var2 = (new ImageIcon("pic/bg.png")).getImage();
        var1.drawImage(var2, 0, 0, this);
        var1.setColor(Color.CYAN);
        var1.drawRect(0, 0, 299, 299);
        var1.setFont(new Font("TimesRoman", 0, 17));
        var1.drawString("名字            生命   攻击  防御  伤害", 0, 32);

        for (int var3 = 0; var3 < cnt; ++var3) {
            Image var4 = (new ImageIcon("pic/m" + (imgtmp[var3] + 3) / 4 + ".png")).getImage();
            byte var5 = 0;
            int var6 = (var3 + 1) * 32;
            byte var7 = 0;
            int var8 = (imgtmp[var3] - 1) % 4 * 32;
            var1.drawImage(var4, var5, var6, var5 + 32, var6 + 32, var7, var8, var7 + 32, var8 + 32, this);
            var1.drawString(info[var3], 32, 32 * (var3 + 2));
        }

    }

    public void showInfo() {
        HashSet var1 = new HashSet();

        for (int var2 = 0; var2 < 10; ++var2) {
            for (int var3 = 0; var3 < 10; ++var3) {
                if (this.game.mt[this.game.curfloor - 1][var2][var3] > 0) {
                    var1.add(Integer.valueOf(this.game.mt[this.game.curfloor - 1][var2][var3]));
                }
            }
        }

        cnt = 0;

        Integer var7;
        for (Iterator var6 = var1.iterator(); var6.hasNext(); imgtmp[cnt - 1] = var7.intValue()) {
            var7 = (Integer) var6.next();
            int var4 = this.judgeDamage(this.hero, var7.intValue());
            Object[] var10001 = new Object[5];
            Game var10004 = this.game;
            var10001[0] = Game.MNAME[var7.intValue() - 1];
            var10004 = this.game;
            var10001[1] = Integer.valueOf(Game.MHP[var7.intValue() - 1]);
            var10004 = this.game;
            var10001[2] = Integer.valueOf(Game.MATK[var7.intValue() - 1]);
            var10004 = this.game;
            var10001[3] = Integer.valueOf(Game.MDEF[var7.intValue() - 1]);
            var10001[4] = Integer.valueOf(var4);
            String var5 = String.format("%s%5s%5s%5s%5s", var10001);
            info[cnt++] = var5;
        }

        this.repaint();
    }
}
