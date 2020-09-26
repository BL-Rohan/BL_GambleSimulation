import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

public class GamblerSimulation {

    /*
     * @useCase 1: Setting STAKE as 100 and and BET 1
     * @author   :Rohan kadam
     * */
    public static final int STAKE = 100;
    public static final int BET = 1;
    public static final int WIN = BET;
    public static final int LOSS = 0;
    public int winningAmount, losingAmount,
            stake, days, totalAmountEarned,day=0;

    LinkedHashMap<Integer, Integer> daysWon = new LinkedHashMap<>();
    LinkedHashMap<Integer, Integer> daysLoss = new LinkedHashMap<>();

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
                daysLoss.put(day,stake);
                stop = false;
            }
            if (stake == winningAmount) {
                daysWon.put(day,stake);
                stop = false;
            }
        }

        return stake;
    }

    /*
     * @useCase 4:Calculating  Total Amount for 20 days
     * @author   :Rohan Kadam
     * */
    public int totalAmountWonOrLoss(int days) {
        int day_stake = 0;
        while (days > 0) {
            day++;
            day_stake = resignStake();
            days = days - 1;
            totalAmountEarned = totalAmountEarned + day_stake;

        }

        System.out.println("Total Amount Earned or Loss by Gambler at End of given period:- " + totalAmountEarned);
        return totalAmountEarned;
    }

    /*
    * @useCase 5:Calculating  Total Amount for  given month and
    *            loss & win days with amount.
    * @author   :Rohan Kadam
    * */

    public int calculateForMonth(int days) {

        totalAmountWonOrLoss(days);
        System.out.println("****Days Won in  month****");
        printDaysWithValue(daysWon);
        System.out.println("****Days Loss in  month****");
        printDaysWithValue(daysLoss);

        return totalAmountEarned;
    }

    public void printDaysWithValue(LinkedHashMap<Integer, Integer> daysValue) {
        daysValue.forEach(((day, value) -> {
            System.out.println("Day:- " + day + " Earned:- " + value);
        }));
    }


    public static void main(String[] args) {
        GamblerSimulation gamblerSimulation = new GamblerSimulation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of days in Month to Play not more than 31");
        gamblerSimulation.calculateForMonth(scanner.nextInt());
    }


}
