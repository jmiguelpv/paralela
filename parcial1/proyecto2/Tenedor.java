import java.util.concurrent.Semaphore;
import javax.swing.JLabel;



public class Tenedor {

    private final Semaphore hayEspacio;
    public JLabel label;
    public int id;
    public Boolean status;

    public Tenedor(final JLabel label, final int id) {
        this.id = id;
        this.label = label;
        this.hayEspacio = new Semaphore(1, true);
        this.label.setText("Tenedor " + id);
        this.status = true;
    }

    public void usar(final int filosofoID) throws InterruptedException {

        hayEspacio.acquire();
        this.status = false;
        this.label.setText("me usa "+filosofoID);

    }

    public void soltar(){
        hayEspacio.release();
        this.label.setText(id+" ");
        this.status = true;
    }

}
