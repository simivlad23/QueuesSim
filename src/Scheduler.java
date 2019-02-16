

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private ArrayList<Casa> listaCase = new ArrayList<>();
    private int maNoServers;
    private int maxTasksPerSerer;
    private Strategy strategy;


    public Scheduler(int maNoServers, int maxTasksPerSerer,SelectionPolicy policy) {
        this.maNoServers = maNoServers;
        this.maxTasksPerSerer = maxTasksPerSerer;

        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if (policy == SelectionPolicy.SHORTEST_TIME)
        strategy = new ConcreteStrategyTime();

        listaCase = new ArrayList<>();
        for (int i = 1; i <= maNoServers; i++) {

            Casa casa = new Casa(maxTasksPerSerer);
            Thread tred = new Thread(casa);
            tred.setName("Casa: " + i);
            tred.start();

            System.out.println("Casa :" + i + " a fost creata");
            SimulationManager.appendScrolText("Casa :" + i + " a fost creata" + "\n");

            listaCase.add(casa);

        }
        System.out.println("numar de case :" + listaCase.size());
        //System.out.println("numar clienti casa 2" +);


    }


    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if (policy == SelectionPolicy.SHORTEST_TIME) ;
        strategy = new ConcreteStrategyTime();
    }

    public void disppatchTask(Client client) throws InterruptedException {


        strategy.addClient(listaCase, client);
//
//
    }


    public List<Casa> getListaCase() {
        return listaCase;
    }
}


