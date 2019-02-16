import javax.swing.*;
import java.awt.*;


public class AnimationPanel extends JPanel{

    SimulationManager sim;
    public AnimationPanel(SimulationManager sim){
        this.sim=sim;
    }

    public void updateThis()
    {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;

        for(int i=0; i < sim.scheduler.getListaCase().size(); i++)
        {
            int y = 0;
            drawQueue(g, x, y,i+1);

            for(int j=0; j < sim.scheduler.getListaCase().get(i).getClineti().size(); j++) {
                y += 20;



                // int id = sim.scheduler.getListaCase().get(i).getCoadaClienti().peek().queues[i].getCurrentClient(j).getId() + 1;

                int timpDeAsteptare = sim.scheduler.getListaCase().get(i).getTimpDeAsteptare();
                int clientTime= sim.scheduler.getListaCase().get(i).getClineti().get(j).getTimpProcesare();
                int processingTime = sim.scheduler.getListaCase().get(i).getClineti().get(j).getTimpProcesare();
                int timpDeAsteptare2=  sim.scheduler.getListaCase().get(i).getClineti().get(j).getTimpDeAsteptare();
                int inTime=sim.scheduler.getListaCase().get(i).getClineti().get(j).getSosireTimp();
                int timpRamas;
              //   timpRamas=processingTime+timpDeAsteptare-clientTime;
                if(j==0){
                    //timpRamas=timpDeAsteptare+sim.scheduler.getListaCase().get(i).getClineti().get(j-1).getTimpProcesare();
                   // timpRamas=timpDeAsteptare+timp2-clientTime;0
                    drawClient(g, x + 20, y, Color.red, timpDeAsteptare2,inTime);

                }else
                //timpRamas=timpDeAsteptare+timp2;


               // timpRamas=timpDeAsteptare+timp2-clientTime;


                drawClient(g, x + 20, y, Color.red, processingTime,inTime);
            }
            x += 100;
        }

    }
    private void drawClient(Graphics g, int x, int y, Color c, int processingTime,int inTime){
        g.setColor(c);
        g.fillRect(x, y, 50, 15);
        g.setColor(Color.GREEN);
        g.drawString(  Integer.toString(inTime)+"  " + Integer.toString(processingTime) , x+20, y+12);
        this.repaint();
    }
    private void drawQueue(Graphics g, int x, int y,int index){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 90, 20);
        g.setColor(Color.white);
        g.drawString("CASA -"+index, x+15, y+15);
        this.repaint();
    }


}