public class Client implements Comparable<Client> {
    private int sosireTimp;
    private int timpProcesare;
    private int timpDeAsteptare;

    public int getTimpDeAsteptare() {
        return timpDeAsteptare;
    }
    public void decrementTime(){
        timpDeAsteptare=timpDeAsteptare-1;
    }

    public void setTimpDeAsteptare(int timpDeAsteptare) {
        this.timpDeAsteptare = timpDeAsteptare;
    }

    public Client(int sosireTimp, int timpProcesare) {
        this.sosireTimp = sosireTimp;
        this.timpProcesare = timpProcesare;
    }

    public int getSosireTimp() {
        return sosireTimp;
    }

    public int getTimpProcesare() {
        return timpProcesare;
    }


    @Override
    public int compareTo(Client o) {
        int compareage=((Client)o).getSosireTimp();
        /* For Ascending order*/
        return this.sosireTimp-compareage;

    }

    @Override
    public String toString() {
        return "Clientul cu timp sosire:" +timpProcesare+" a fost creat";
    }
}
