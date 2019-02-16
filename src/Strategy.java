import java.util.ArrayList;

public interface Strategy {
    public void addClient(ArrayList<Casa> list, Client client) throws InterruptedException;

}
