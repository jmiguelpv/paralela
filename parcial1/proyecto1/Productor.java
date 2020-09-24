import java.util.Random;
import javax.swing.JLabel;
import java.util.concurrent.ThreadLocalRandom;

public class Productor extends Thread {

    private Random r = new Random();
    private Boolean run;
    private Buffer buf;
    public String name;
    public JLabel label;
    public int baketime;
    public boolean awake;

    public Productor(Buffer buf, String name, JLabel label) {
        this.name = name;
        this.buf = buf;
        this.run = true;
        this.label = label;
        this.label.setText(name + "Nuevo panadero.");
        this.baketime = ThreadLocalRandom.current().nextInt(1000, 10000);
        this.awake = true;
    }

    @Override
    public void run() {
        while (run) {
            try {
                if (awake) {
                    this.label.setText(name + " Horneando.");
                    Thread.sleep(baketime);
                    if (buf.guardar()) {
                        awake = false;
                    } else {
                        this.label.setText(name + " Guardando");
                        Thread.sleep(1000);
                    }
                } else {
                    this.label.setText(name + " Estoy mimiendo");
                    Thread.sleep(1000);
                    if (buf.i < buf.max - 5) {
                        awake = true;
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                this.label.setText(name + " El pan se enfria!!");
            }
        }
    }

    public void stopped() {
        run = false;
    }
}
