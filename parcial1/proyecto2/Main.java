
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame {
    public Filosofo filosofos[];
    public Tenedor tenedores[];


    public Boolean change;
    public ImageIcon filosofoImg;
    public ImageIcon tenedorImg;

    public JButton crearProductorButton;
    public JButton destroyProductorButton;
    public JButton destroyConsumidorButton;
    public JButton crearConsumidorButton;
    public JFrame frame;
    public JLabel label;


    public Main(){

        filosofos = new Filosofo[5];
        tenedores = new Tenedor[5];

        frame = new JFrame();
        change = false;
        filosofoImg = new ImageIcon("provedor.png");
        tenedorImg = new ImageIcon("tenedor.png");


        for(int i = 0; i < 5; i++){
            label = this.createImageLabel(i*72,tenedorImg,0);
            label.setText("IDLE");
            Tenedor tenedor = new Tenedor(label,i);
            tenedores[i] = tenedor;
        }

        for(int i = 0; i < 5; i++){
            label = this.createImageLabel(i*72,filosofoImg,-36);
            int tenedor1 = i-1;
            if(tenedor1 < 0){
                tenedor1 = 4;
            }
            if(tenedor1 > 4){
                tenedor1 = 0;
            }
            label.setText("IDLE");
            Filosofo filosofo = new Filosofo(tenedores[tenedor1],tenedores[i],"Filosofo",label,i);
            filosofos[i]=filosofo;
        }

        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public static void main(String[] args){
        Main main = new Main();
        main.start();

    }

    public void start(){
        for(int i = 0; i<5; i++){
            this.activate(i);
        }


        while(true){
            if(change){
                SwingUtilities.updateComponentTreeUI(frame);
                change = false;
            }
            try{
                Thread.sleep(16);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }

    }
    public void activate(int i){
        filosofos[i].start();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }


    public JLabel createImageLabel(int angulo, ImageIcon image, int offsetAngulo){
        angulo = offsetAngulo+angulo;
        JLabel label = new JLabel(image);
        int x = 300; // B
        int y = 300; // C
        int radio = 300;
        double b = radio*Math.cos(Math.toRadians(angulo));
        double c = b*Math.tan(Math.toRadians(angulo));
        x = x+(int)b;
        y = y+(int)c;

        label.setBounds(x,y,300,300);
        label.setLayout(null);
        this.change = true;
        frame.add(label);
        return label;
    }

    public void createFilosofo(String name){
        // JLabel label = this.createImageLabel(stackConsumidores,consumidorImg,600);
        // Filosofo filosofo = new Filosofo(this.buf,name,label);
        // consumidor.start();
        // stackConsumidores.push(consumidor);
    }

}
