package Mode;

/**
 * Created by sssss on 2017/3/16.
 */

import java.awt.Point;

class Hero extends Stuff {
    private Point xy;
    private int dir;
    private int ykey;
    private int bkey;
    private int rkey;
    MyPanel mp;
    BookPane bp;
    Game game;
    private HeroShake hs;
    private DoorShake ds;

    Hero(int var1, int var2, int var3, Point var4) {
        this.hp = var1;
        this.atk = var2;
        this.def = var3;
        this.xy = var4;
    }

    int getMoney() {
        return this.money;
    }

    void setMoney(int var1) {
        this.money = var1;
    }

    int getExp() {
        return this.exp;
    }

    void setExp(int var1) {
        this.exp = var1;
    }

    int getYkey() {
        return this.ykey;
    }

    void setYkey(int var1) {
        this.ykey = var1;
    }

    int getBkey() {
        return this.bkey;
    }

    void setBkey(int var1) {
        this.bkey = var1;
    }

    int getRkey() {
        return this.rkey;
    }

    void setRkey(int var1) {
        this.rkey = var1;
    }

    int getDir() {
        return this.dir;
    }

    void setDir(int var1) {
        this.dir = var1;
    }

    int getHp() {
        return this.hp;
    }

    void setHp(int var1) {
        this.hp = var1;
    }

    int getAtk() {
        return this.atk;
    }

    void setAtk(int var1) {
        this.atk = var1;
    }

    int getDef() {
        return this.def;
    }

    void setDef(int var1) {
        this.def = var1;
    }

    Point getXY() {
        return this.xy;
    }

    int getX() {
        return this.xy.x;
    }

    int getY() {
        return this.xy.y;
    }

    void setXY(Point var1) {
        this.xy = var1;
    }

    void setX(int var1) {
        this.xy.x = var1;
    }

    void setY(int var1) {
        this.xy.y = var1;
    }

    void fight(int var1) {
        this.hp -= this.bp.judgeDamage(this, var1);
        Game var10002 = this.game;
        this.money += Game.MMONEY[var1 - 1];
        var10002 = this.game;
        this.exp += Game.MEXP[var1 - 1];
        if (this.hp <= 0) {
            this.game.lost();
        }

    }

    void getItem(int var1) {
        switch (var1) {
            case -29:
                this.def += 25;
                break;
            case -28:
                this.def += 20;
                break;
            case -27:
                this.def += 15;
                break;
            case -26:
                this.def += 10;
                break;
            case -25:
                this.def += 5;
                break;
            case -24:
                this.atk += 25;
                break;
            case -23:
                this.atk += 20;
                break;
            case -22:
                this.atk += 15;
                break;
            case -21:
                this.atk += 10;
                break;
            case -20:
                this.atk += 5;
            case -19:
            case -18:
            case -17:
            case -16:
            case -15:
            case -14:
            case -13:
            case -12:
            case -11:
            case -10:
            default:
                break;
            case -9:
                this.hp += 500;
                break;
            case -8:
                this.hp += 250;
                break;
            case -7:
                this.hp += 100;
                break;
            case -6:
                ++this.def;
                break;
            case -5:
                ++this.atk;
                break;
            case -4:
                ++this.rkey;
                break;
            case -3:
                ++this.bkey;
                break;
            case -2:
                ++this.ykey;
        }

    }

    boolean openDoor(int var1, Point var2) {
        boolean var3 = false;
        Game var10001 = this.game;
        if (var1 == -12 && this.ykey > 0) {
            --this.ykey;
            var3 = true;
        } else {
            var10001 = this.game;
            if (var1 == -13 && this.bkey > 0) {
                --this.bkey;
                var3 = true;
            } else {
                var10001 = this.game;
                if (var1 == -14 && this.rkey > 0) {
                    --this.rkey;
                    var3 = true;
                }
            }
        }

        if (var3) {
            this.openingDoor(var1);
        }

        return var3;
    }

    void openingDoor(int var1) {
        this.ds = new DoorShake(this.mp);
        this.ds.start();
        this.mp.isopeningdoor = true;
    }

    void openedDoor(Point var1) {
        this.ds.stop();
        this.mp.isopeningdoor = false;
        this.game.mt[this.game.curfloor - 1][var1.x][var1.y] = 0;
        this.mp.doorcnt = 0;
    }

    void moving() {
        this.hs = new HeroShake(this.mp);
        this.hs.start();
        this.mp.ismoving = true;
    }

    void moved() {
        this.hs.stop();
        this.mp.ismoving = false;
        this.setXY(this.mp.heroxy);
    }
}
