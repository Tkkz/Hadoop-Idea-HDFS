package Mode;

/**
 * Created by sssss on 2017/3/16.
 */
class MonsterShake extends Shake {
    MonsterShake(MyPanel var1) {
        super(var1);
    }

    public synchronized void run() {
        while (true) {
            this.mp.monstercnt = this.cnt;

            try {
                Thread.sleep(150L);
            } catch (Exception var2) {
                ;
            }

            ++this.cnt;
            this.cnt %= 4;
            this.mp.repaint();
        }
    }
}