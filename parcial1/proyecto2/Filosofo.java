import javax.swing.JLabel;

public class Filosofo extends Thread{

    private Boolean full, run;
    private Tenedor tenedorDerecha, tenedorIzquierda;
    public String name;
    public int id;
    public JLabel label;


    public Filosofo (Tenedor tenedorDerecha,Tenedor tenedorIzquierda, String name,JLabel label, int id){
        this.label = label;
        this.run = true;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.id = id;
        this.name = name;
        this.label.setText(id+" Esperando Creacion "+tenedorDerecha.id+":"+tenedorIzquierda.id);
    }

    @Override
    public void run(){
        while(run){
            if(tenedorDerecha.status && tenedorIzquierda.status){
                try{
                    tenedorDerecha.usar(id);
                    tenedorIzquierda.usar(id);
                    this.label.setText(id+" Comiendo");
                    Thread.sleep(5000);
                }catch(InterruptedException ex){
                }
                tenedorDerecha.soltar();
                tenedorIzquierda.soltar();

                try{
                    this.label.setText(id+" digiriendo");
                    Thread.sleep(5000);
                }catch(InterruptedException ex){
                }
            }else{
                this.label.setText(id+" Pensando");
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){
                }

            }

        }
    }

    public void stopped(){
        run = false;
    }
}
