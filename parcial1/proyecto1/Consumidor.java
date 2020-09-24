import javax.swing.JLabel;
import java.util.concurrent.ThreadLocalRandom;

public class Consumidor extends Thread {
    private Buffer buf;
    public String name;
    private Boolean run;
    public JLabel label;
    private Boolean awake;

    public Consumidor(Buffer b, String name, JLabel label) {
        this.buf = b;
        this.run = true;
        this.name = name;
        this.label = label;
        this.label.setText(name + "Nuevo aldeano.");
        this.awake = true;
    }

    @Override
    public void run() {
        while (run) {
            int aux;
            try {
                if (awake) {
                    aux = buf.sacar();
                    if (aux == 0) {
                        awake = false;
                    } else {
                        this.label.setText(name + " Comiendo");
                        Thread.sleep(1000);
                        this.label.setText(name + " Saciado");
                        int randomNum = ThreadLocalRandom.current().nextInt(1000, 20000);
                        Thread.sleep(randomNum);
                    }
                } else {
                    this.label.setText(name + " Hibernando");
                    Thread.sleep(1000);
                    if (buf.i > 5) {
                        awake = true;
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                this.label.setText(name + " Hambriento");
            }
        }
    }

    public void stopped() {
        run = false;
    }
}
