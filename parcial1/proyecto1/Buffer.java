
import java.util.concurrent.*;

public class Buffer {

    private int[] buff;
    public int i = 0;
    private Semaphore hayEspacio;

    public Buffer(int tam){
        buff = new int[tam];
        hayEspacio = new Semaphore(1,true);
    }

    public boolean poner(int dato, String name) throws InterruptedException{

        hayEspacio.acquire();
        if((i+1)%buff.length >= 50 ){
            hayEspacio.release();
            return true;
        }
        buff[i] = dato;
        i=(i+1)%buff.length;
        Thread.sleep(200);
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
        Thread.sleep(100);
        hayEspacio.release();
        return i;
    }


}
