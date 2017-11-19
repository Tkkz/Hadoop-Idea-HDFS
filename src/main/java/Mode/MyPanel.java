package Mode;

/**
 * Created by sssss on 2017/3/16.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements KeyListener {
    Game game;
    boolean musicflag2;
    boolean musicflag3;
    boolean musicflag4;
    boolean musicflag5;
    boolean musicflag6;
    Hero hero; //英雄
    StatePane sp;
    BookPane bp;
    int monstercnt; //怪物cnt
    int doorcnt;    //门
    int herocnt;
    Point doorxy;
    Point heroxy;
    boolean isopeningdoor; //打开的门
    boolean ismoving;       //移除
    boolean cheath;
    boolean cheate;
    boolean cheatr;
    boolean cheato;
    boolean usingbook;     //使用的门
    boolean usingstate;     //使用规则或者状态
    MagicTower mt;         //mt 魔塔
    JFrame book;
    JFrame state;

    MyPanel(Hero var1, Game var2, StatePane var3, BookPane var4) {
        this.hero = var1;
        this.game = var2;
        this.sp = var3;
        this.bp = var4;
    }
        //颜色
    public void paint(Graphics var1) {
        super.paint(var1);
        if (this.doorcnt >= 4) {
            this.hero.openedDoor(this.doorxy);
        }

        this.drawEdge(var1);
        this.drawLand(var1);
        this.drawMap(var1);
        this.drawHero(var1);
        if (!this.ismoving) {
            this.herocnt = 0;
        }

    }
        //钥匙
    public void keyPressed(KeyEvent var1) {
        if (!this.isopeningdoor && !this.ismoving) {
            Point var2 = new Point(this.hero.getX(), this.hero.getY());

            int var3 = var1.getKeyCode();
            int var5;
            int var6;
            Point var7;
            if (var3 == 90) {
                var7 = this.mt.getLocation();
                var5 = var7.x - 200;
                var6 = var7.y + 50;
                if (var5 < 0) {
                    var5 = 0;
                }

                this.state.setLocation(var5, var6);
                this.state.setVisible(!this.usingstate);
                this.usingstate = !this.usingstate;
                this.mt.requestFocus();
            } else if (var3 == 88) {
                var7 = this.mt.getLocation();
                var5 = var7.x + 400;
                var6 = var7.y + 60;
                if (var5 < 0) {
                    var5 = 0;
                }

                this.book.setLocation(var5, var6);
                this.book.setVisible(!this.usingbook);
                this.usingbook = !this.usingbook;
                this.mt.requestFocus();
            } else {
                if (this.isDir(var3)) {
                    switch (var3) {
                        case 37:
                            this.hero.setDir(1);
                            --var2.x;
                            break;
                        case 38:
                            this.hero.setDir(3);
                            --var2.y;
                            break;
                        case 39:
                            this.hero.setDir(2);
                            ++var2.x;
                            break;
                        case 40:
                            this.hero.setDir(0);
                            ++var2.y;
                    }

                    if (this.isOutBound(var2)) {
                        this.updataInfo();
                        return;
                    }

                    int var4 = this.game.mt[this.game.curfloor - 1][var2.x][var2.y];
                    if (this.isWall(var4)) {
                        this.updataInfo();
                        return;
                    }

                    if (this.isLand(var4)) {
                        this.heroMove(var2);
                        this.updataInfo();
                        return;
                    }

                    if (this.isItem(var4)) {
                        this.hero.getItem(var4);
                        this.heroMove(var2);
                        this.game.playOgg("get");
                        this.updataInfo();
                        return;
                    }

                    if (this.isDoor(var4)) {
                        this.doorxy = new Point(var2.x, var2.y);
                        if (this.hero.openDoor(var4, this.doorxy)) {
                            this.game.playOgg("door");
                        }

                        this.updataInfo();
                        return;
                    }

                    if (this.isStair(var4)) {
                        this.hero.setDir(0);
                        Game var10003 = this.game;
                        this.game.curfloor += var4 == -10 ? 1 : -1;
                        if (this.game.curfloor > this.game.maxfloor) {
                            this.game.maxfloor = this.game.curfloor;
                        }

                        if (this.game.curfloor <= 0) {
                            ++this.game.curfloor;
                            return;
                        }

                        if (this.game.maxfloor >= 6 && !this.musicflag2) {
                            this.game.stopMusic();
                            this.game.playMusic(2);
                            this.musicflag2 = true;
                        } else if (this.game.maxfloor >= 11 && this.game.maxfloor <= 15 && !this.musicflag3) {
                            this.game.stopMusic();
                            this.game.playMusic(3);
                            this.musicflag3 = true;
                        } else if (this.game.maxfloor >= 16 && this.game.maxfloor <= 20 && !this.musicflag4) {
                            this.game.stopMusic();
                            this.game.playMusic(4);
                            this.musicflag4 = true;
                        } else if (this.game.maxfloor >= 21 && this.game.maxfloor <= 25 && !this.musicflag5) {
                            this.game.stopMusic();
                            this.game.playMusic(5);
                            this.musicflag5 = true;
                        } else if (this.game.maxfloor >= 26 && this.game.maxfloor <= 27 && !this.musicflag6) {
                            this.game.stopMusic();
                            this.game.playMusic(6);
                            this.musicflag6 = true;
                        }

                        this.game.playOgg("stair");
                        this.updataInfo();
                        return;
                    }

                    if (this.isMonster(var4)) {
                        this.heroMove(var2);
                        this.game.playOgg("atk");
                        this.hero.fight(var4);
                        this.updataInfo();
                        return;
                    }

                    if (var4 == -32) {
                        this.game.win();
                    }
                } else {
                    if (this.isSaveOrLoad(var3)) {
                        switch (var3) {
                            case 49:
                                this.game.save(1);
                                break;
                            case 50:
                                this.game.save(2);
                                break;
                            case 51:
                                this.game.save(3);
                                break;
                            case 52:
                                this.game.save(4);
                                break;
                            case 53:
                                this.game.load(1);
                                break;
                            case 54:
                                this.game.load(2);
                                break;
                            case 55:
                                this.game.load(3);
                                break;
                            case 56:
                                this.game.load(4);
                        }

                        this.updataInfo();
                        return;
                    }

                    if (this.isChangeFloor(var3)) {
                        if (81 == var3) {
                            if (this.game.curfloor + 2 > this.game.maxfloor) {
                                return;
                            }

                            if (this.isNearStair(var2)) {
                                ++this.game.curfloor;
                                this.updataInfo();
                            }
                        } else if (87 == var3) {
                            if (this.game.curfloor - 1 <= 0) {
                                return;
                            }

                            if (this.isNearStair(var2)) {
                                --this.game.curfloor;
                                this.updataInfo();
                            }
                        }
                    } else if (var3 == 72) {
                        this.cheath = true;
                    } else if (var3 == 69) {
                        this.cheate = true;
                    } else if (var3 == 82) {
                        this.cheatr = true;
                    } else if (var3 == 79) {
                        this.cheato = true;
                        if (this.cheath && this.cheate && this.cheatr && this.cheato) {
                            this.hero.setHp(999999);
                            this.hero.setAtk(99999);
                            this.hero.setDef(99999);
                            this.hero.setYkey(9999);
                            this.hero.setBkey(9999);
                            this.hero.setRkey(9999);
                            this.updataInfo();
                        }
                    }
                }

            }
        }
    }

    public void keyTyped(KeyEvent var1) {
    }

    public void keyReleased(KeyEvent var1) {
    }

    void drawEdge(Graphics var1) {
        for (int var2 = 0; var2 < 12; ++var2) {
            Image var3 = (new ImageIcon("pic/-1.png")).getImage();
            var1.drawImage(var3, 0, var2 * 32, 32, 32, this);
            var1.drawImage(var3, var2 * 32, 0, 32, 32, this);
            var1.drawImage(var3, 352, var2 * 32, 32, 32, this);
            var1.drawImage(var3, var2 * 32, 352, 32, 32, this);
        }

    }

    void drawLand(Graphics var1) {
        Image var2 = (new ImageIcon("pic/bg.png")).getImage();
        var1.drawImage(var2, 32, 32, this);
    }

    void drawMap(Graphics var1) {
        for (int var2 = 0; var2 < 10; ++var2) {
            for (int var3 = 0; var3 < 10; ++var3) {
                int var4 = this.game.mt[this.game.curfloor - 1][var2][var3];
                if (!this.isopeningdoor || this.doorxy.x != var2 || this.doorxy.y != var3) {
                    if (this.isMonster(var4)) {
                        this.drawMonster(var1, new Point(var2, var3), var4);
                    } else {
                        this.drawStatic(var1, new Point(var2, var3), var4);
                    }
                }
            }
        }

        if (this.isopeningdoor) {
            this.drawDoor(var1, this.doorxy, this.game.mt[this.game.curfloor - 1][this.doorxy.x][this.doorxy.y]);
        }

    }

    void drawHero(Graphics var1) {
        Image var2 = (new ImageIcon("pic/hero.png")).getImage();
        int var3 = 0;
        int var4 = 0;
        boolean var5 = false;
        boolean var6 = false;
        int var7;
        int var8;
        if (this.herocnt == 0) {
            var3 = (this.hero.getX() + 1) * 32;
            var4 = (this.hero.getY() + 1) * 32;
            var7 = 0;
            var8 = this.hero.getDir() * 32;
        } else {
            if (this.hero.getDir() == 0) {
                var3 = (this.hero.getX() + 1) * 32;
                var4 = (this.hero.getY() + 1) * 32 + this.herocnt * 8;
            } else if (this.hero.getDir() == 3) {
                var3 = (this.hero.getX() + 1) * 32;
                var4 = (this.hero.getY() + 1) * 32 - this.herocnt * 8;
            } else if (this.hero.getDir() == 1) {
                var3 = (this.hero.getX() + 1) * 32 - this.herocnt * 8;
                var4 = (this.hero.getY() + 1) * 32;
            } else if (this.hero.getDir() == 2) {
                var3 = (this.hero.getX() + 1) * 32 + this.herocnt * 8;
                var4 = (this.hero.getY() + 1) * 32;
            }

            var7 = this.herocnt % 4 * 32;
            var8 = this.hero.getDir() * 32;
        }

        var1.drawImage(var2, var3, var4, var3 + 32, var4 + 32, var7, var8, var7 + 32, var8 + 32, this);
        if (this.herocnt >= 3) {
            this.hero.moved();
        }

    }

    void drawMonster(Graphics var1, Point var2, int var3) {
        Image var4 = (new ImageIcon("pic/m" + (var3 + 3) / 4 + ".png")).getImage();
        int var5 = (var2.x + 1) * 32;
        int var6 = (var2.y + 1) * 32;
        int var7 = this.monstercnt * 32;
        int var8 = (var3 - 1) % 4 * 32;
        var1.drawImage(var4, var5, var6, var5 + 32, var6 + 32, var7, var8, var7 + 32, var8 + 32, this);
    }

    void drawStatic(Graphics var1, Point var2, int var3) {
        Image var4 = (new ImageIcon("pic/" + var3 + ".png")).getImage();
        int var5 = (var2.x + 1) * 32;
        int var6 = (var2.y + 1) * 32;
        var1.drawImage(var4, var5, var6, 32, 32, this);
    }

    void drawDoor(Graphics var1, Point var2, int var3) {
        byte var4 = 0;
        Game var10001 = this.game;
        if (var3 == -12) {
            var4 = 1;
        } else {
            var10001 = this.game;
            if (var3 == -13) {
                var4 = 2;
            } else {
                var10001 = this.game;
                if (var3 == -14) {
                    var4 = 3;
                }
            }
        }

        Image var5 = (new ImageIcon("pic/door.png")).getImage();
        int var6 = (var2.x + 1) * 32;
        int var7 = (var2.y + 1) * 32;
        int var8 = (var4 - 1) * 32;
        int var9 = this.doorcnt * 32;
        var1.drawImage(var5, var6, var7, var6 + 32, var7 + 32, var8, var9, var8 + 32, var9 + 32, this);
    }

    boolean isDir(int var1) {
        return var1 == 37 || var1 == 39 || var1 == 40 || var1 == 38;
    }

    boolean isSaveOrLoad(int var1) {
        return var1 == 49 || var1 == 50 || var1 == 51 || var1 == 52 || var1 == 53 || var1 == 54 || var1 == 55 || var1 == 56;
    }

    boolean isChangeFloor(int var1) {
        return var1 == 81 || var1 == 87;
    }

    boolean isOutBound(Point var1) {
        return var1.x < 0 || var1.y < 0 || var1.x >= 10 || var1.y >= 10;
    }

    boolean isWall(int var1) {
        return -1 == var1;
    }

    boolean isLand(int var1) {
        return var1 == 0 || var1 == -19;
    }

    boolean isDoor(int var1) {
        if (var1 != -12) {
            Game var10001 = this.game;
            if (var1 != -13 && var1 != -14) {
                return false;
            }
        }

        return true;
    }

    boolean isItem(int var1) {
        boolean var2 = false;
        switch (var1) {
            case -9:
                var2 = true;
                break;
            case -8:
                var2 = true;
                break;
            case -7:
                var2 = true;
                break;
            case -6:
                var2 = true;
                break;
            case -5:
                var2 = true;
                break;
            case -4:
                var2 = true;
                break;
            case -3:
                var2 = true;
                break;
            case -2:
                var2 = true;
        }

        if (var1 <= -20 && var1 >= -29) {
            var2 = true;
        }

        return var2;
    }

    boolean isMonster(int var1) {
        Game var10001 = this.game;
        return var1 > 0;
    }

    boolean isStair(int var1) {
        Game var10000 = this.game;
        if (-10 != var1) {
            var10000 = this.game;
            if (-11 != var1) {
                return false;
            }
        }

        return true;
    }

    boolean isNearStair(Point var1) {
        Game var10000 = this.game;
        if (-10 != this.game.mt[this.game.curfloor - 1][var1.x - 1][var1.y]) {
            var10000 = this.game;
            if (-10 != this.game.mt[this.game.curfloor - 1][var1.x + 1][var1.y]) {
                var10000 = this.game;
                if (-11 != this.game.mt[this.game.curfloor - 1][var1.x - 1][var1.y]) {
                    var10000 = this.game;
                    if (-11 != this.game.mt[this.game.curfloor - 1][var1.x + 1][var1.y]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    void updataInfo() {
        this.sp.repaint();
        this.bp.showInfo();
        this.repaint();
    }

    void heroMove(Point var1) {
        this.heroxy = new Point(var1);
        this.hero.moving();
        this.herocnt = 1;
        this.game.mt[this.game.curfloor - 1][var1.x][var1.y] = 0;
    }
}

