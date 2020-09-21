
import java.util.concurrent.*;
import javax.swing.JLabel;



public class Buffer {

    private int[] buff;
    public int i = 0;
    private Semaphore hayEspacio;
    public JLabel label;

    public Buffer(int tam, JLabel label){
        buff = new int[tam];
        this.label = label;
        hayEspacio = new Semaphore(1,true);
        this.label.setText("me crearon -------- Cantidad :"+i);

    }

    public boolean poner(int dato, String name) throws InterruptedException{

        hayEspacio.acquire();
        if((i+1)%buff.length >= 50 ){
            hayEspacio.release();
            return true;
        }
        buff[i] = dato;
        i=(i+1)%buff.length;
        this.label.setText("me llenan -------- Cantidad :"+i);
        Thread.sleep(1000);
        hayEspacio.release();

        return false;

    }

    public int extraer(String name) throws InterruptedException{
        hayEspacio.acquire();
        if((i-1)%buff.length <= 0 ){
            hayEspacio.release();
            return 0;
        }
        i = (i-1)%buff.length;
        this.label.setText("me Vacian----------- Cantidad :"+i);
        Thread.sleep(1000);
        hayEspacio.release();
        return i;
    }


}
