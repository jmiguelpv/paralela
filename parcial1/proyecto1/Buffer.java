
import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

public class Buffer {

    public int i;
    public int max;
    private Semaphore puerta;
    public JLabel label;

    public Buffer(int tam, JLabel label) {
        this.max = tam;
        this.i = 0;
        this.label = label;
        this.puerta = new Semaphore(1, true);
        this.label.setText("Panes en almacen: " + i);
    }

    public int guardar() throws InterruptedException {
        puerta.acquire();
        if (i >= max) {
            puerta.release();
            return max;
        }
        i += 1;
        this.label.setText("Guardando pan: " + i);
        Thread.sleep(1000);
        this.label.setText("Panes en almacen: " + i);
        puerta.release();
        return i;
    }

    public int sacar() throws InterruptedException {
        puerta.acquire();
        if (i <= 0) {
            puerta.release();
            return 0;
        }
        this.label.setText("Sacando pan\n " + i + " Panes guardados ");
        i -= 1;
        Thread.sleep(1000);
        this.label.setText("Panes en almacen: " + i);
        puerta.release();
        return i;
    }

}
