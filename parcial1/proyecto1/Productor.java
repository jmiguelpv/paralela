

import java.util.Random;

public class Productor extends Thread{

    private Random r = new Random();
    private Buffer buf;
    private int iter;
    private Boolean full, run;
    private int contador;
    private String name;

    public Productor (Buffer buff, int iter, String name){
        this.buf=buff;
        this.iter=iter;
        this.name = name;
        this.contador=0;
        this.run = true;
    }

    @Override
    public void run(){
        while(run){
            try{
                int aux = r.nextInt(100);
                if(buf.poner(aux,name)){
                    System.out.println(this.name+" sleep");
                    Thread.sleep(6000);
                }
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void stopped(){
        run = false;
    }
}
