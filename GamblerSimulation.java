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
            stake, days, totalAmountEarned, day = 0;

    LinkedHashMap<Integer, Integer> daysWon = new LinkedHashMap<>();
    LinkedHashMap<Integer, Integer> daysLoss = new LinkedHashMap<>();
    LinkedHashMap<Integer, Integer> daysValue = new LinkedHashMap<>();


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
        losingAmount = (int) Math.round(STAKE * 0.5);//losing Amount 50
        winningAmount = (int) Math.round(STAKE + (STAKE * 0.5));// winning Amount 150
        boolean stop = true;
        stake = STAKE;
        while (stop == true) {
            winOrLoss();
            if (stake == losingAmount) {
                daysLoss.put(day, stake);
                stop = false;
            }
            if (stake == winningAmount) {
                daysWon.put(day, stake);
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
            daysValue.put(day,totalAmountEarned);

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

    /*
     * @useCase 6:Calculating  luckiest and Unluckiest Days in month
     * @author   :Rohan Kadam
     * */

    public void luckiestDayAndUnluckiestDay() {
          luckiestDay(daysValue);
          unluckiestDay(daysValue);
    }

    public void luckiestDay(LinkedHashMap<Integer, Integer> daysWon) {
        int max = 0;
        int max_key = 0;
        for (Integer value : daysWon.keySet()) {
            if (max < daysWon.get(value)) {
                max = daysWon.get(value);
                max_key = value;
            }
        }
        System.out.println("Luckiest Day:-  " + max_key + " value:-   " + max);


    }

    public void unluckiestDay(LinkedHashMap<Integer, Integer> daysLoss) {
        int min = 0;
        int min_key = 0;
        for (Integer value : daysLoss.keySet()) {
            if (min > daysLoss.get(value)) {
                min = daysLoss.get(value);
                min_key = value;
            }
        }
        System.out.println("Unluckiest Day:- " + min_key + " value:-   " + min);

    }


    /*
    * Print function for print days and amount earned.
    * */
    public void printDaysWithValue(LinkedHashMap<Integer, Integer> daysValue) {
        daysValue.forEach(((day, value) -> {
            System.out.println("Day:- " + day + " Earned:- " + value);
        }));
    }

    /*
    * @useCase 7:Keep playing for  next month or not.
    * @author   :Rohan Kadam
    * */

    public void continueOrStop(int month, int days) {
        int winning = stake * days;
        int losing = (int) Math.round(stake * 0.5* days);
        int amount = 0;
        while (month > 0) {
            amount = calculateForMonth(days);
            if (amount > winning) {
                System.out.println("###Gambler is ***winning*** the game###");
                month = month - 1;
                continue;

            }
            if (amount < losing) {
                System.out.println("#######Gambler is ***losing*** the game######");
                month = 0;
                break;

            }

        }
    }

    public static void main(String[] args) {
        GamblerSimulation gamblerSimulation = new GamblerSimulation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of days in Month to Play not more than 31");
        int days=scanner.nextInt();
        System.out.println("Enter the months you want to play.");
        int month=scanner.nextInt();
        gamblerSimulation.continueOrStop(month,days);
        gamblerSimulation.luckiestDayAndUnluckiestDay();
    }


}
