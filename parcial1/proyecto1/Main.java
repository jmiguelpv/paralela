
import java.util.Stack;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener {
    public Stack<Productor> stackProductores;
    public Stack<Consumidor> stackConsumidores;
    public Buffer buf;

    public Boolean change;
    public ImageIcon productorImg;
    public ImageIcon consumidorImg;

    public JButton newPanaderoBTN;
    public JButton killPanaderoBTN;
    public JButton newaldeanoBTN;
    public JButton killAldeanoBTN;
    public JLabel labelBuffer;
    public JFrame frame;

    public Main() {
        frame = new JFrame();
        change = false;

        productorImg = new ImageIcon(
                "C:/Users/Juan Carlos/OneDrive/tarea/Paralela/Migue/paralela/parcial1/proyecto1/productor.png");
        consumidorImg = new ImageIcon(
                "C:/Users/Juan Carlos/OneDrive/tarea/Paralela/Migue/paralela/parcial1/proyecto1/consumidor.png");

        labelBuffer = new JLabel();
        labelBuffer.setBounds(400, 700, 500, 60);
        labelBuffer.setLayout(null);
        frame.add(labelBuffer);
        buf = new Buffer(100, labelBuffer);

        stackProductores = new Stack<Productor>();
        stackConsumidores = new Stack<Consumidor>();

        newPanaderoBTN = new JButton("Contratar panadero");
        newPanaderoBTN.setBounds(50, 800, 200, 30);
        newPanaderoBTN.setActionCommand("newPanadero");
        newPanaderoBTN.addActionListener(this);
        frame.add(newPanaderoBTN);

        killPanaderoBTN = new JButton("Matar panadero");
        killPanaderoBTN.setBounds(50, 900, 200, 30);
        killPanaderoBTN.setActionCommand("killPanadero");
        killPanaderoBTN.addActionListener(this);
        frame.add(killPanaderoBTN);
        newaldeanoBTN = new JButton("Concebir aldeano");

        newaldeanoBTN.setBounds(400, 800, 200, 30);
        newaldeanoBTN.setActionCommand("newAldeano");
        newaldeanoBTN.addActionListener(this);
        frame.add(newaldeanoBTN);

        killAldeanoBTN = new JButton("Matar aldeano");
        killAldeanoBTN.setBounds(400, 900, 200, 30);
        killAldeanoBTN.setActionCommand("killAldeano");
        killAldeanoBTN.addActionListener(this);
        frame.add(killAldeanoBTN);

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        while (true) {
            if (change) {
                SwingUtilities.updateComponentTreeUI(frame);
                change = false;
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public JLabel createImageLabel(Stack stack, ImageIcon image, int xbound) {
        JLabel label = new JLabel(image);
        label.setBounds(xbound, (60) * (stack.size()), 400, 60);
        label.setLayout(null);
        this.change = true;
        frame.add(label);
        return label;
    }

    public void destroyImageLabel(JLabel label) {
        frame.remove(label);
        this.change = true;
    }

    public void newPanadero(String name) {
        JLabel label = this.createImageLabel(stackProductores, productorImg, 20);
        Productor productor = new Productor(this.buf, name, label);
        productor.start();
        stackProductores.push(productor);
    }

    public void killPanadero() {
        if (stackProductores.size() > 0) {
            Productor productor = stackProductores.pop();
            productor.stopped();
            this.destroyImageLabel(productor.label);
        }
    }

    public void newAldeano(String name) {
        JLabel label = this.createImageLabel(stackConsumidores, consumidorImg, 600);
        Consumidor consumidor = new Consumidor(this.buf, name, label);
        consumidor.start();
        stackConsumidores.push(consumidor);
    }

    public void killAldeano() {
        if (stackConsumidores.size() > 0) {
            Consumidor consumidor = stackConsumidores.pop();
            consumidor.stopped();
            this.destroyImageLabel(consumidor.label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch ((String) (e.getActionCommand())) {
            case "newPanadero":
                this.newPanadero("Panadero " + stackProductores.size());
                break;

            case "killPanadero":
                this.killPanadero();
                break;

            case "newAldeano":
                this.newAldeano("Aldeano " + stackConsumidores.size());
                break;

            case "killAldeano":
                this.killAldeano();
                break;
            default:
                break;
        }
    }

}
