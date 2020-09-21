import javax.swing.*;

public class Consumidor extends Thread{
    private Buffer buf;
    private String name;
    private Boolean run;
    public JLabel label;




    public Consumidor(Buffer b, String name, JLabel label){
        this.buf = b;
        this.run = true;
        this.name = name;
        this.label = label;
    }

    @Override
    public void run(){
        while(run) {
            int aux;
            try{
                aux = buf.extraer(name);
                System.out.println(this.name+" running");
            if(aux == 0){
                System.out.println(this.name + " sleep------------------------------");
                Thread.sleep(6000);
            }
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
