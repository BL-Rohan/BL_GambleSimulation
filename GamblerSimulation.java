import java.util.Random;

public class GamblerSimulation {

    /*
    * @useCase 1: Setting STAKE as 100 and and BET 1
    * @author   :Rohan kadam
    * */
    public static final int STAKE=100;
    public static final  int BET=1;
    public static final int WIN=BET;
    public static final int LOSS=0;

    /*
    * @useCase 2: Win Or Loss
    * @author   :Rohan Kadam
    * */

    public int winOrLoss() {
        Random random = new Random();
        int value = random.nextInt(2) + LOSS;
        if (value == WIN) {
            System.out.println("***Gambler Won***");
            return WIN;
        }
        System.out.println("***Gambler Loss***");
        return LOSS;

    }

    public static void main(String[] args) {
      GamblerSimulation gamblerSimulation=new GamblerSimulation();
      gamblerSimulation.winOrLoss();
    }


}
