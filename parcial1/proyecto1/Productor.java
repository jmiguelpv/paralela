

import java.util.Random;
import javax.swing.*;


public class Productor extends Thread{

    private Random r = new Random();
    private Boolean full, run;
    private int contador;
    private Buffer buf;
    private String name;
    public JLabel label;


    public Productor (Buffer buf, String name,JLabel label){
        this.name = name;
        this.buf = buf;
        this.contador=0;
        this.run = true;
        this.label = label;
    }

    @Override
    public void run(){
        while(run){
            try{
                System.out.println(this.name+" running");
                int aux = r.nextInt(100);
                if(buf.poner(aux,name)){
                    System.out.println(this.name+" sleep---------------------------");
                    Thread.sleep(6000);
                }
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void stopped(){
        System.out.println(this.name+" ded");
        run = false;
    }
}
