import java.util.Random;
import javax.swing.JLabel;

public class Productor extends Thread {

    private Random r = new Random();
    private Boolean run;
    private Buffer buf;
    public String name;
    public JLabel label;

    public Productor(Buffer buf, String name, JLabel label) {
        this.name = name;
        this.buf = buf;
        this.run = true;
        this.label = label;
        this.label.setText(name + "Nuevo panadero.");
    }

    @Override
    public void run() {
        while (run) {
            int aux = r.nextInt(100);
            try {
                if (buf.poner(aux, name)) {
                    this.label.setText(name + "Estoy descanzando.");
                    Thread.sleep(6000);
                } else {
                    this.label.setText(name + "Horneando.");
                }
                Thread.sleep(1000);
                this.label.setText(name + "El pan se enfria!!");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopped() {
        run = false;
    }
}
