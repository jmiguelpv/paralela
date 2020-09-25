import javax.swing.JLabel;
import java.util.concurrent.ThreadLocalRandom;

public class Despertador extends Thread {
    Boolean awakePanaderos;
    Boolean awakeAldeanos;
    private Boolean run;

    public Despertador() {
        awakePanaderos = false;
        awakeAldeanos = false;
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            try {
                if (awakePanaderos) {
                    Thread.sleep(1000);
                    awakePanaderos = false;
                }
                if (awakeAldeanos) {
                    Thread.sleep(1000);
                    awakeAldeanos = false;
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopped() {
        run = false;
    }
}
