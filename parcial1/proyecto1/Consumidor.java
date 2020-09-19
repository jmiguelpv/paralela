

public class Consumidor extends Thread{
    private Buffer buf;
    private int iter;
    private String name;
    private Boolean run;


    public Consumidor(Buffer b, int iter, String name){
        this.buf=b;
        this.iter=iter;
        this.name=name;
        this.run = true;
    }

    @Override
    public void run(){
        while(run) {
            int aux;
            try{
                aux = buf.extraer(name);
                if(aux == 0){
                    System.out.println("consumidor " + name + " duerme");
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
