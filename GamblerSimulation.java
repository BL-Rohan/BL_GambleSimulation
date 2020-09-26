import java.util.Random;

public class GamblerSimulation {

    /*
     * @useCase 1: Setting STAKE as 100 and and BET 1
     * @author   :Rohan kadam
     * */
    public static final int STAKE = 100;
    public static final int BET = 1;
    public static final int WIN = BET;
    public static final int LOSS = 0;
    public int winningAmount, losingAmount, stake;

    /*
     * @useCase 2: Win Or Loss
     * @author   :Rohan Kadam
     * */

    public int winOrLoss() {
        Random random = new Random();
        int value = random.nextInt(2) + LOSS;
        if (value == WIN) {
            System.out.println("***Gambler Won***");
            stake++;  //Stake incremented on winning
            return WIN;
        }

        System.out.println("***Gambler Loss***");
        stake--;//Stake decremented on lossing
        return LOSS;

    }

    /*
     * @useCase 3:Gambler Plays for 50% of if won or loss
     * @author   :Rohan Kadam
     * */

    public int resignStake() {
        losingAmount = (int) Math.round(STAKE * 0.5);
        winningAmount = (int) Math.round(STAKE + (STAKE * 0.5));
        boolean stop = true;
        stake = STAKE;
        while (stop == true) {
            winOrLoss();
            if (stake == losingAmount) {
                stop = false;
            }
            if (stake == winningAmount) {
                stop = false;
            }
        }

        return stake;
    }

    public static void main(String[] args) {
        GamblerSimulation gamblerSimulation = new GamblerSimulation();
        System.out.println(gamblerSimulation.resignStake());
    }


}
