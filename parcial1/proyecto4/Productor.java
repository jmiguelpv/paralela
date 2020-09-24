import java.util.Random;
import javax.swing.JLabel;


public class Productor extends Thread{

    private Random r = new Random();
    private Boolean full, run;
    private int contador;
    private Buffer buf;
    public String name;
    public JLabel label;


    public Productor (Buffer buf, String name,JLabel label){
        this.name = name;
        this.buf = buf;
        this.contador=0;
        this.run = true;
        this.label = label;
        this.label.setText(name+" me crearon.");
    }

    @Override
    public void run(){
        while(run){
            int aux = r.nextInt(100);
            try{
                if(buf.poner(aux,name)){
                    this.label.setText(name+" me durmieron");
                    Thread.sleep(6000);
                }else{
                    this.label.setText(name+" agregue producto");
                }
                Thread.sleep(1000);
                this.label.setText(name+" espero ");
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void stopped(){
        run = false;
    }
}
