import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationManager extends Thread {

    public int timeLimit = 30;
    public int maxProcTime = 6;
    public int minProcTime = 2;
    public SelectionPolicy selectionPolicy ;
    public int numarCase = 3;
    public int numarClineti = 15;
    private static String textLog = "";
    public static int timpMediuDeServire;
    public static int timpMediuDeAsteptare;
    public int minArrialTime;
    public int maxArrivalTime;


    int currentTime = 0;
    int oraDeVArf=0;
    int maximTimpDeAsteptare=-1;


    public Scheduler scheduler;
    private ArrayList<Client> generatedTask;
    public AnimationPanel animationPanel;


    private void updateAnimationView() {
        animationPanel.updateThis();
    }

    public SimulationManager(int minProcTime, int maxProcTime, int minArrialTime, int maxArrivalTime, int numarCase, int timeLimit ,String strat) {

        this.minProcTime = minProcTime;
        this.maxProcTime = maxProcTime;
        this.minArrialTime = minArrialTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numarCase = numarCase;
        this.timeLimit = timeLimit;
        if(strat=="SHORTEST_QUEUE"){
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        }
        else
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        scheduler = new Scheduler(numarCase, 10,selectionPolicy);

        generatedTask = new ArrayList<Client>();
        this.generateNRandomClinets();

    }

    public SimulationManager() {

        scheduler = new Scheduler(numarCase, 10,SelectionPolicy.SHORTEST_QUEUE);
        generatedTask = new ArrayList<Client>();
        this.generateNRandomClinets();

    }

    synchronized public static void appendScrolText(String s) {
        textLog += s;
    }

    synchronized public static String getLog() {
        return textLog;
    }

    private void generateNRandomClinets() {
        for (int i = 0; i < numarClineti; i++) {
            //Client c =new Client((int)(Math.random()*15+1),(int)(Math.random()*maxProcTime+minProcTime));
            Client c = new Client(i + 1, (int) (Math.random() * maxProcTime + minProcTime));
           // Client c = new Client((int) (Math.random() * maxArrivalTime + minArrialTime), (int) (Math.random() * maxProcTime + minProcTime));
            // System.out.println(c);
            generatedTask.add(c);
        }
        generatedTask.sort(Client::compareTo);

    }
    public List<Client> getGeneratedTask() {
        return generatedTask;
    }

    private void startUp() {

        JFrame graficPopup = new JFrame();
        graficPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graficPopup.setTitle("Graphical Simulation");
        graficPopup.setLocation(670, 200);
        graficPopup.setSize(400, 400);
        graficPopup.add(animationPanel = new AnimationPanel(this));
        graficPopup.setVisible(true);

    }

    @Override
    public void run() {
        for (int i = 0; i < generatedTask.size(); i++)
            System.out.println(generatedTask.get(i).getSosireTimp() + "   wait: " + generatedTask.get(i).getTimpProcesare());
        System.out.println("\n\n");
        startUp();
        // int currentTime = 0;
        while (currentTime < timeLimit) {
            System.out.println("Curent time is :" + currentTime + "  ");
            GUI.textTime.setText(Integer.toString(currentTime));
            for (int j = 0; j < generatedTask.size(); j++) {
                if (generatedTask.get(j).getSosireTimp() == currentTime) {
                    try {
                        //System.out.println(generatedTask.size());
                        scheduler.disppatchTask(generatedTask.get(j));
                        for(int k=0;k<scheduler.getListaCase().size();k++){
                            if(scheduler.getListaCase().get(j).getTimpDeAsteptare()>maximTimpDeAsteptare){
                                maximTimpDeAsteptare=scheduler.getListaCase().get(k).getTimpDeAsteptare();
                                oraDeVArf=currentTime;
                            }
                        }
                        // System.out.println(generatedTask.get(j).getSosireTimp()+" ");
                        // generatedTask.remove(j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            for (int j = 0; j < generatedTask.size(); j++) {
                if (generatedTask.get(j).getSosireTimp() == currentTime) {

                    generatedTask.remove(j);

                }
            }
            updateAnimationView();

            try {
                GUI.textScrol.setText(SimulationManager.getLog());

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime++;
            System.out.println("-----------------------------------------");
        }
        System.out.println("\n-----Final----------------------------");
        System.out.println("Secunda de varf este:"+oraDeVArf);

        SimulationManager.appendScrolText("\n -----Final----------------------------\n Secunda de varf este:"+oraDeVArf+"\n");
        for(int r=0;r<scheduler.getListaCase().size();r++){
            int average=scheduler.getListaCase().get(r).getTimpTotalAsteptare()/scheduler.getListaCase().get(r).getNumarClienti();
            int average2=scheduler.getListaCase().get(r).getTimpTotalDeServire()/scheduler.getListaCase().get(r).getNumarClienti();
            SimulationManager.appendScrolText("Timpul mediu de asteptare la coada "+(r+1)+"este de: "+String.valueOf(average)+" secunde\n");
            SimulationManager.appendScrolText("Timpul mediu de servire la coada "+(r+1)+"este de: "+String.valueOf(average2)+" secunde\n");

        }

        GUI.textScrol.setText(SimulationManager.getLog());
    }

}
