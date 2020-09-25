import javax.swing.JLabel;
import java.util.concurrent.ThreadLocalRandom;

public class Consumidor extends Thread {
    private Buffer buf;
    private String name;
    private Boolean run;
    JLabel label;
    private Boolean awake;
    private Despertador despertador;

    public Consumidor(Buffer b, String name, JLabel label, Despertador despertador) {
        this.buf = b;
        this.run = true;
        this.name = name;
        this.label = label;
        this.label.setText(name + "Nuevo aldeano.");
        this.awake = true;
        this.despertador = despertador;
    }

    @Override
    public void run() {
        while (run) {
            int aux;
            try {
                if (despertador.awakeAldeanos) {
                    awake = true;
                }
                if (awake) {
                    aux = buf.sacar();
                    if (aux == 0) {
                        awake = false;
                    } else {
                        if (aux < 5) {
                            despertador.awakePanaderos = true;
                        }
                        this.label.setText(name + " Comiendo");
                        Thread.sleep(1000);
                        this.label.setText(name + " Saciado");
                        int randomNum = ThreadLocalRandom.current().nextInt(1000, 20000);
                        Thread.sleep(randomNum);
                    }
                } else {
                    this.label.setText(name + " Hibernando");
                    Thread.sleep(1000);
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
