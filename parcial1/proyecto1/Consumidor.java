import javax.swing.JLabel;

public class Consumidor extends Thread {
    private Buffer buf;
    public String name;
    private Boolean run;
    public JLabel label;

    public Consumidor(Buffer b, String name, JLabel label) {
        this.buf = b;
        this.run = true;
        this.name = name;
        this.label = label;
        this.label.setText(name + "Nuevo aldeano.");

    }

    @Override
    public void run() {
        while (run) {
            int aux;
            try {
                aux = buf.extraer(name);
                if (aux == 0) {
                    this.label.setText(name + "Hibernando");
                    Thread.sleep(5000);
                } else {
                    this.label.setText(name + "Comiendo pan");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.label.setText(name + "Esperando pan");
        }
    }

    public void stopped() {
        run = false;
    }
}
