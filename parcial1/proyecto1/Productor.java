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
    public Despertador despertador;

    public Productor(Buffer buf, String name, JLabel label, Despertador despertador) {
        this.name = name;
        this.buf = buf;
        this.run = true;
        this.label = label;
        this.label.setText(name + "Nuevo panadero.");
        this.baketime = ThreadLocalRandom.current().nextInt(1000, 10000);
        this.awake = true;
        this.despertador = despertador;
    }

    @Override
    public void run() {
        while (run) {
            int aux;
            try {
                if (despertador.awakePanaderos) {
                    awake = true;
                }
                if (awake) {
                    this.label.setText(name + " Horneando.");
                    Thread.sleep(baketime);
                    aux = buf.guardar();
                    if (aux == buf.max) {
                        awake = false;
                    } else {
                        this.label.setText(name + " Guardando");
                        Thread.sleep(1000);
                        if (aux >= buf.max - 5) {
                            despertador.awakeAldeanos = true;
                        }
                    }
                } else {
                    this.label.setText(name + " Estoy mimiendo");
                    Thread.sleep(1000);
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
