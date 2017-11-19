package Mode;

/**
 * Created by sssss on 2017/3/16.
 */

class Monster extends Stuff {
    public String name;

    Monster(String var1, int var2, int var3, int var4, int var5, int var6) {
        this.name = var1;
        this.hp = var2;
        this.atk = var3;
        this.def = var4;
        this.money = var5;
        this.exp = var6;
    }
}
