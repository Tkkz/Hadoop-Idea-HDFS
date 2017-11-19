package Mode;

/**
 * Created by sssss on 2017/3/16.
 */
class HeroShake extends Shake {
    HeroShake(MyPanel var1) {
        super(var1);
        this.cnt = 1;
    }

    public synchronized void run() {
        while (true) {
            this.mp.herocnt = this.cnt;

            try {
                Thread.sleep(25L);
            } catch (Exception var2) {
                ;
            }

            ++this.cnt;
            if (this.cnt >= 5) {
                return;
            }

            this.mp.repaint();
        }
    }
}