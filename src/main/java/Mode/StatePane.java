package Mode;

/**
 * Created by sssss on 2017/3/16.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class StatePane extends JPanel {
    Hero hero;
    Game game;
    static final String[] ATT = new String[]{"楼层数:", "生命值:", "攻击力:", "防御力:", "金钱数:", "经验值:", "黄钥匙:", "蓝钥匙:", "红钥匙:", "最高层:"};

    StatePane(Hero var1, Game var2) {
        this.hero = var1;
        this.game = var2;
    }

    public void paint(Graphics var1) {
        super.paint(var1);
        Image var2 = (new ImageIcon("pic/bg.png")).getImage();
        var1.drawImage(var2, 0, 0, this);
        var1.setColor(Color.CYAN);
        var1.drawRect(0, 0, 199, 319);
        var1.setColor(Color.GREEN);
        var1.setFont(new Font("TimesRoman", 0, 27));
        int[] var3 = new int[]{this.game.curfloor, this.hero.getHp(), this.hero.getAtk(), this.hero.getDef(), this.hero.getMoney(), this.hero.getExp(), this.hero.getYkey(), this.hero.getBkey(), this.hero.getRkey(), this.game.maxfloor};

        for (int var4 = 0; var4 < 10; ++var4) {
            String var5 = ATT[var4] + var3[var4];
            var1.drawString(var5, 0, 32 * (var4 + 1) - 5);
        }

    }
}
