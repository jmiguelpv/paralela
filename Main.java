import java.util.Stack;

public class Main extends Thread {
    public Stack<Productor> stackProductores;
    public Stack<Consumidor> stackConsumidores;

    public Main(){
        stackProductores = new Stack<Productor>();
        stackConsumidores = new Stack<Consumidor>();
    }


    public static void main(String[] args){
        Main main = new Main();
        Buffer buf = new Buffer(100);

        // main.createProductor(buf,100,"****************");
        // main.createConsumidor(buf,100,"45454");

        // main.destroyProductor();
        // main.destroyConsumidor();

        // System.out.println("done");
    }

    public void createProductor(Buffer buf,int size,String name){
        Productor productor = new Productor(buf,size,name);
        productor.start();
        stackProductores.push(productor);
    }

    public void destroyProductor(){
        stackProductores.pop().stopped();
    }

    public void createConsumidor(Buffer buf,int size,String name){
        Consumidor consumidor = new Consumidor(buf,size,name);
        consumidor.start();
        stackConsumidores.push(consumidor);
    }

    public void destroyConsumidor(){
        stackConsumidores.pop().stopped();
    }

}
