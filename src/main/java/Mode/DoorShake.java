package Mode;

/**
 * Created by sssss on 2017/3/16.
 */
class DoorShake extends Shake {
    DoorShake(MyPanel var1) {
        super(var1);
    }

    public synchronized void run() {
        while (true) {
            this.mp.doorcnt = this.cnt;

            try {
                Thread.sleep(50L);
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
