import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame guiFrame = new JFrame("Queue Simulation");
    public static JTextArea textScrol = new JTextArea();
    public static JTextField textTime = new JTextField();
     JTextField tfMinArrival =new JTextField();
     JTextField tfMaxArrival = new JTextField();
     JTextField txtMinServing= new JTextField();
     JTextField tfMaxServing = new JTextField();
     JTextField tfNumarCase = new JTextField();
     JTextField tfSimTime = new JTextField();
    String[] comboContent ={"SHORTEST_QUEUE","SHORTEST_TIME"};
    JComboBox<String> strategyCombo = new JComboBox<>(comboContent);
    JLabel lblMinArr = new JLabel("Min Arrival Time");
    JLabel lblMaxArrivalTime = new JLabel("Max Arrival Time");
    JLabel lblMinServingTime = new JLabel("Min Serving Time");
    JLabel lblMaxServingTime = new JLabel("Max Serving Time");
    JLabel lblNumarCase = new JLabel("Queue Number");
    JLabel lblSimulationTime = new JLabel("Simulation Time");
    JButton btnStartSim = new JButton("Start Simulation");
    JLabel lblNewLabel = new JLabel("Time");
    JScrollPane scrollPane = new JScrollPane();




    public GUI() {

        guiFrame.setLocation(50,200);
        guiFrame.setSize(600,400);

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.getContentPane().setLayout(null);
        textTime.setEditable(false);
        scrollPane.setViewportView(textScrol);

        btnStartSim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                createSimulationManager();
            }

        });





        lblMinArr.setLocation(10,10);
        lblMinArr.setSize(130,20);

        tfMinArrival.setLocation(10,30);
        tfMinArrival.setSize(100,20);

        lblMaxArrivalTime.setLocation(10,60);
        lblMaxArrivalTime.setSize(130,20);

        tfMaxArrival.setLocation(10,80);
        tfMaxArrival.setSize(100,20);


        lblMinServingTime.setLocation(10,110);
        lblMinServingTime.setSize(130,20);
        txtMinServing.setLocation(10,130);
        txtMinServing.setSize(100,20);

        lblMaxServingTime.setLocation(10,160);
        lblMaxServingTime.setSize(130,20);

        tfMaxServing.setLocation(10,180);
        tfMaxServing.setSize(100,20);

        lblNumarCase.setLocation(10,210);
        lblNumarCase.setSize(130,20);

        tfNumarCase.setLocation(10,230);
        tfNumarCase.setSize(100,20);


        lblSimulationTime.setLocation(10,260);
        lblSimulationTime.setSize(130,20);

        tfSimTime.setLocation(10,280);
        tfSimTime.setSize(100,20);

        strategyCombo.setLocation(10,320);
        strategyCombo.setSize(150,20);

        btnStartSim.setLocation(230,320);
        btnStartSim.setSize(140,30);

        lblNewLabel.setLocation(150,10);
        lblNewLabel.setSize(40,20);

        textTime.setLocation(200,10);
        textTime.setSize(50,20);
        scrollPane.setLocation(150,50);
        scrollPane.setSize(350,250);



        guiFrame.getContentPane().add(lblMinArr);
        guiFrame.getContentPane().add(lblMaxArrivalTime);
        guiFrame.getContentPane().add(lblMinServingTime);
        guiFrame.getContentPane().add(lblMaxServingTime);
        guiFrame.getContentPane().add(lblNumarCase);
        guiFrame.getContentPane().add(lblSimulationTime);
        guiFrame.getContentPane().add(tfMinArrival);
        guiFrame.getContentPane().add(tfMaxArrival);
        guiFrame.getContentPane().add(txtMinServing);
        guiFrame.getContentPane().add(tfMaxServing);
        guiFrame.getContentPane().add(tfNumarCase);
        guiFrame.getContentPane().add(tfSimTime);
        guiFrame.getContentPane().add(btnStartSim);

        guiFrame.getContentPane().add(scrollPane);
        guiFrame.getContentPane().add(textTime);
        guiFrame.getContentPane().add(lblNewLabel);
        guiFrame.getContentPane().add(strategyCombo);

    }
    private void createSimulationManager() {
//
                    int timpMinimDeServire = (int) Integer.parseInt(txtMinServing.getText());
                    int timpMaximDeServire = (int) Integer.parseInt(tfMaxServing.getText());
                    int timpMinimDeIntrare = (int) Integer.parseInt(tfMinArrival.getText());
                    int timpMaximDeIntrare = (int) Integer.parseInt(tfMaxArrival.getText());
                    int numarDeCozi = (int) Integer.parseInt(tfNumarCase.getText());
                    int timpDeSimulare = (int) Integer.parseInt(tfSimTime.getText());
                        String strat =(String)strategyCombo.getSelectedItem();


//
       SimulationManager qm = new SimulationManager(timpMinimDeServire, timpMaximDeServire, timpMinimDeIntrare, timpMaximDeIntrare, numarDeCozi, timpDeSimulare,strat);

       // SimulationManager qm = new SimulationManager();
        qm.start();
    }
    public static void main(String[] args) {

        GUI window = new GUI();
        window.guiFrame.setVisible(true);
    }
}
