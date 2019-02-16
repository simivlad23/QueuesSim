import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Casa implements Runnable {
    private BlockingQueue<Client> coadaClienti;
    private AtomicInteger timpDeAsteptare;
    private AtomicInteger timpTotalAsteptare= new AtomicInteger(0);
    private AtomicInteger numarClienti=new AtomicInteger(0);
    private AtomicInteger timpTotalDeServire =new AtomicInteger(0);



    public Casa(int capacity) {

        coadaClienti = new ArrayBlockingQueue<>(capacity);
        this.timpDeAsteptare = new AtomicInteger(0);
    }

    public int getTimpTotalAsteptare() {
        return timpTotalAsteptare.intValue();
    }

    public int getTimpTotalDeServire() {
        return timpTotalDeServire.intValue();
    }

    public void addClient(Client newClient) throws InterruptedException {
        coadaClienti.put(newClient);
        timpDeAsteptare.addAndGet(newClient.getTimpProcesare());
        timpTotalAsteptare.addAndGet(newClient.getTimpProcesare());
       // timpTotalAsteptare.addAndGet(timpDeAsteptare);
        numarClienti.addAndGet(1);
        timpTotalDeServire.addAndGet(newClient.getTimpProcesare());


    }

    public int getNumarClienti() {
        return numarClienti.intValue();
    }

    private void updateClient(){

    }
    public void run() {
        while (true) {
            try {
                if (coadaClienti.isEmpty() == false) {
                    //sleep(100);
                    Client c = coadaClienti.peek();
                    SimulationManager.appendScrolText("Clientul " + c.getSosireTimp() + " :" + c.getTimpProcesare() + " este sevit la coada " + Thread.currentThread().getName() + "\n");
                    System.out.println("Clientul " + c.getSosireTimp() + " :" + c.getTimpProcesare() + " este sevit la coada " + Thread.currentThread().getName());
                   //sleep(c.getTimpProcesare() * 1000);
                    for (int j = 1; j <= c.getTimpProcesare(); j++) {
                        c.setTimpDeAsteptare(c.getTimpProcesare()-j+1);
                        sleep(1000);

                        timpDeAsteptare.decrementAndGet();
                        //c.setTimpDeAsteptare(c.getTimpProcesare()-j);
                    }
                    SimulationManager.appendScrolText("Clientul " + c.getSosireTimp() + " :" + c.getTimpProcesare() + " a parasit coada " + Thread.currentThread().getName() + "\n");
                    System.out.println("Clientul " + c.getSosireTimp() + " :" + c.getTimpProcesare() + " a parasit coada " + Thread.currentThread().getName());

//                    for (int j = 1; j <= c.getTimpProcesare(); j++)
//                        timpDeAsteptare.decrementAndGet();

                    coadaClienti.take();
                   // sleep(100);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }


        }
    }

    public void setTimpDeAsteptare(int timpDeAsteptare) {

    }

    public int getTimpDeAsteptare() {
        return timpDeAsteptare.intValue();
    }

    public BlockingQueue<Client> getCoadaClienti() {

        return coadaClienti;
    }

    public ArrayList<Client> getClineti() {
        ArrayList<Client> list = new ArrayList<>(coadaClienti);

        return list;
    }
}
