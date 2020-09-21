
import java.util.Stack;
import java.util.Iterator;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener, KeyListener{
    public Stack<Productor> stackProductores;
    public Stack<Consumidor> stackConsumidores;
    public Buffer buf;

    public Boolean change;
    public ImageIcon productorImg;
    public ImageIcon consumidorImg;

    public JButton crearProductorButton;
    public JButton destroyProductorButton;
    public JButton destroyConsumidorButton;
    public JButton crearConsumidorButton;
    public JLabel label;
    public JFrame frame;


    public Main(){
        frame = new JFrame();

        consumidorImg = new ImageIcon("provedor.png");
        productorImg = new ImageIcon("consumidor.png");

        change = false;
        buf = new Buffer(100);
        stackProductores = new Stack<Productor>();
        stackConsumidores = new Stack<Consumidor>();

        crearProductorButton = new JButton("Crear Productor");
        crearProductorButton.setBounds(50,800,200,30);
        crearProductorButton.setActionCommand("createProductor");
        crearProductorButton.addActionListener(this);
        frame.add(crearProductorButton);

        destroyProductorButton = new JButton("Destruir Productor");
        destroyProductorButton.setBounds(50,900,200,30);
        destroyProductorButton.setActionCommand("destroyProductor");
        destroyProductorButton.addActionListener(this);
        frame.add(destroyProductorButton);

        crearConsumidorButton = new JButton("Crear Consumidor");
        crearConsumidorButton.setBounds(400,800,200,30);
        crearConsumidorButton.setActionCommand("createConsumidor");
        crearConsumidorButton.addActionListener(this);
        frame.add(crearConsumidorButton);

        destroyConsumidorButton = new JButton("Destruir Productor");
        destroyConsumidorButton.setBounds(400,900,200,30);
        destroyConsumidorButton.setActionCommand("destroyConsumidor");
        destroyConsumidorButton.addActionListener(this);
        frame.add(destroyConsumidorButton);

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


    public JLabel createImageLabel(Stack stack, ImageIcon image,int xbound){
        JLabel label = new JLabel(image);

        label.setBounds(xbound,(60)*(stack.size()),60,60);
        label.setLayout(null);
        this.change = true;
        frame.add(label);
        return label;
    }

    public void destroyImageLabel(JLabel label){
        frame.remove(label);
        change = true;
    }

    public void createProductor(String name){
        JLabel label2 = this.createImageLabel(stackProductores,productorImg,20);
        Productor productor = new Productor(this.buf,name,label2);
        productor.start();
        stackProductores.push(productor);
    }

    public void destroyProductor(){
        if(stackProductores.size()>0){
            Productor productor = stackProductores.pop();
            productor.stopped();
            this.destroyImageLabel(productor.label);
        }
    }

    public void createConsumidor(String name){
        this.label = this.createImageLabel(stackConsumidores,consumidorImg,600);
        Consumidor consumidor = new Consumidor(this.buf,name,this.label);
        consumidor.start();
        stackConsumidores.push(consumidor);
    }

    public void destroyConsumidor(){
        if(stackConsumidores.size()>0){
            Consumidor consumidor = stackConsumidores.pop();
            consumidor.stopped();
            this.destroyImageLabel(consumidor.label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        switch ((String)(e.getActionCommand())){
            case "createProductor":
            this.createProductor("Provedor "+stackProductores.size());
            break;

            case "destroyProductor":
            this.destroyProductor();
            break;

            case "createConsumidor":
            this.createConsumidor("Consumidor "+stackConsumidores.size());
            break;

            case "destroyConsumidor":
            this.destroyConsumidor();
            break;

            default:
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

}
