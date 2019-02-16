import java.util.ArrayList;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addClient(ArrayList<Casa> list, Client client) throws InterruptedException {

        int index = 100;
        int dimensiune=list.get(0).getCoadaClienti().size();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Coada:"+(i+1)+ " are capacitatea " +list.get(i).getCoadaClienti().size());

            if (list.get(i).getCoadaClienti().isEmpty()) {
                index = i;
                //System.out.println("Coada:"+(i+1)+ " e goala");

            }else if(list.get(i).getCoadaClienti().size()<=dimensiune){
                dimensiune=list.get(i).getCoadaClienti().size();
                index=i;

            }

        }
        System.out.println(index+1);
        if(index==100){
            System.out.println("eroare !!! index tot 100");
        }else {
            SimulationManager.appendScrolText("Clientul -> in:"+client.getSosireTimp()+" si wait: "+client.getTimpProcesare()+"  sa asezat la coada "+ (index+1) + "\n");

            System.out.println("Clientul -> in:"+client.getSosireTimp()+" si wait: "+client.getTimpProcesare()+"  sa asezat la coada "+ (index+1));
            //listaCase.get(index).getCoadaClienti().put(client);

            list.get(index).addClient(client);
            client.setTimpDeAsteptare(list.get(index).getTimpDeAsteptare());
            //System.out.println(listaCase.get(index).getCoadaClienti().size());

        }
    }
}
