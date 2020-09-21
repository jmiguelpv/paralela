import javax.swing.JLabel;

public class Consumidor extends Thread{
    private Buffer buf;
    public String name;
    private Boolean run;
    public JLabel label;




    public Consumidor(Buffer b, String name, JLabel label){
        this.buf = b;
        this.run = true;
        this.name = name;
        this.label = label;
        this.label.setText(name + "me crearon");

    }

    @Override
    public void run(){
        while(run) {
            int aux;
            try{
                aux = buf.extraer(name);
            if(aux == 0){
                this.label.setText(name+" me durmieron: ");
                Thread.sleep(6000);
            }else{
                this.label.setText(name+" saque objeto");
                Thread.sleep(1000);
            }
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            this.label.setText(name+" espero");
        }
    }

    public void stopped(){
        run = false;
    }
}
