
import java.util.Stack;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame implements ActionListener{
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
    public JLabel labelBuffer;
    public JFrame frame;


    public Main(){
        frame = new JFrame();
        change = false;

        consumidorImg = new ImageIcon("provedor.png");
        productorImg = new ImageIcon("consumidor.png");


        labelBuffer = new JLabel();
        labelBuffer.setBounds(400,700,500,60);
        labelBuffer.setLayout(null);
        frame.add(labelBuffer);
        buf = new Buffer(100,labelBuffer);

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

        destroyConsumidorButton = new JButton("Destruir Consumidor");
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
        label.setBounds(xbound,(60)*(stack.size()),400,60);
        label.setLayout(null);
        this.change = true;
        frame.add(label);
        return label;
    }

    public void destroyImageLabel(JLabel label){
        frame.remove(label);
        this.change = true;
    }

    public void createProductor(String name){
        JLabel label = this.createImageLabel(stackProductores,productorImg,20);
        Productor productor = new Productor(this.buf,name,label);
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
        JLabel label = this.createImageLabel(stackConsumidores,consumidorImg,600);
        Consumidor consumidor = new Consumidor(this.buf,name,label);
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


}
