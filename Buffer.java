
import java.util.concurrent.*;

public class Buffer {

    private int[] buff;
    private int i = 0;
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
        //System.out.println(name + " produce "+dato);
        i=(i+1)%buff.length;
        System.out.println(i +" "+ name);
        Thread.sleep(300);
        hayEspacio.release();

        return false;

    }

    public int extraer(String name) throws InterruptedException{
        hayEspacio.acquire();
        //System.out.println("consumidor Consume" +buff[aux]);
        if((i-1)%buff.length <= 0 ){
            hayEspacio.release();
            return 0;
        }
        i = (i-1)%buff.length;
        hayEspacio.release();
        System.out.println(i+" consume "+ name);
        Thread.sleep(100);
        return i;
    }


}
