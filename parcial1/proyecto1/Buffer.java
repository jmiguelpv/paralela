
import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

public class Buffer {

    private int[] buff;
    public int i = 0;
    private Semaphore puerta;
    public JLabel label;

    public Buffer(int tam, JLabel label) {
        buff = new int[tam];
        this.label = label;
        puerta = new Semaphore(1, true);
        this.label.setText("Panes en almacen: " + i);

    }

    public boolean poner(int dato, String name) throws InterruptedException {

        puerta.acquire();
        if ((i + 1) % buff.length >= 50) {
            puerta.release();
            return true;
        }
        buff[i] = dato;
        i = (i + 1) % buff.length;
        this.label.setText("Cargando pan: " + i);
        Thread.sleep(1000);
        puerta.release();

        return false;

    }

    public int extraer(String name) throws InterruptedException {
        puerta.acquire();
        if ((i - 1) % buff.length <= 0) {
            puerta.release();
            return 0;
        }
        i = (i - 1) % buff.length;
        this.label.setText("Sacando pan: " + i);
        Thread.sleep(1000);
        puerta.release();
        return i;
    }

}
