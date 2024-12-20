import java.util.*;
import java.text.*;

public class Gambling {

    private final Random random = new Random();
    private long money;
    private long computerMoney;
    private int diceGameWins = 0;
    private int diceGameLoses = 0;
    private int diceGameDraws = 0;
    private int blackjackWins = 0;
    private int blackjackLoses = 0;
    private int blackjackDraws = 0;
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public Gambling(){
        this.money = 0;
        this.computerMoney = 0;
    }

    public Gambling(long startingCash){
        this.money = startingCash;
        this.computerMoney = startingCash;
    }

    public long getMoney(){
        return money;
    }

    public void addMoney(long money){
        this.money+= money;
    }

    public void withdrawMoney(long money){
        this.money-= money;
    }

    public String toString(){
        return String.valueOf(money);
    }

    public String balance(){
        return formatter.format(getMoney());
    }

    public void displayBalance(){
        System.out.println(balance());
    }


    public void diceGame1Dice(long bet){
        int personRand;
        int computerRand;
        // do while loop to get a random dice roll number for the person and the computer between 6 and 1.
        do{
            personRand = random.nextInt(7);
            computerRand = random.nextInt(7);
        }while (personRand == 0 || computerRand == 0);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("You rolled a " + personRand + "\nComputer rolled a " + computerRand + "\n");

        if(personRand > computerRand){
            money += bet;
            computerMoney -= bet;
            diceGameWins++;
            System.out.println("Congratulations you won! " + formatter.format(bet) + " was added to your total balance.");
        }
        else if(personRand < computerRand){
            money -= bet;
            computerMoney += bet;
            System.out.println("You Lost... " + formatter.format(bet) + " was taken from your total balance.");
        }
        else{
            diceGameDraws++;
            System.out.println("It was a draw... Your balance stays the same.");
        }
        System.out.println("Your Balance is " + formatter.format(money) + "\nComputer Balance is " + formatter.format(computerMoney) + "\n");
        System.out.println("Total Dice game wins = " + diceGameWins + "\nTotal Dice game loses = " + diceGameLoses + "\nTotal Dice game draws = " + diceGameDraws);
        System.out.println("-----------------------------------------------------------------------------------------");
    }


    public void diceGame2Dice(long bet){
        int personRand1;
        int personRand2;
        int computerRand1;
        int computerRand2;
        do{
            personRand1 = random.nextInt(7);
            personRand2 = random.nextInt(7);
            computerRand1 = random.nextInt(7);
            computerRand2 = random.nextInt(7);
        }while (personRand1 == 0 || computerRand1 == 0 || personRand2 == 0 || computerRand2 == 0);
        int totalRollPerson = personRand1 + personRand2;
        int totalRollComputer = computerRand1 + computerRand2;
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("     You rolled a " + personRand1 + " and a " + personRand2 + ".      Total: " + totalRollPerson);
        MyUtils.waitTimer(2);
        System.out.println("Computer rolled a " + computerRand1 + " and a " + computerRand2 + ".      Total: " + totalRollComputer + "\n");
        MyUtils.waitTimer(2);

        if(personRand1 + personRand2 > computerRand1 + computerRand2){
            money += bet;
            computerMoney -= bet;
            diceGameWins++;
            System.out.println("Congratulations you won! " + formatter.format(bet) + " was added to your total balance from the computer.\n");
        }
        else if(personRand1 + personRand2 < computerRand1 + computerRand2){
            money -= bet;
            computerMoney += bet;
            diceGameLoses++;
            System.out.println("You Lost... " + formatter.format(bet) + " was taken from your total balance and added to the computer.\n");
        }
        else{
            diceGameDraws++;
            System.out.println("It was a draw... Balances stays the same.\n");
        }
        MyUtils.waitTimer(1);
        System.out.println("Your Balance is " + formatter.format(money) + "\nComputer Balance is " + formatter.format(computerMoney) + "\n");
        System.out.println("Total Dice game wins = " + diceGameWins + "\nTotal Dice game loses = " + diceGameLoses + "\nTotal Dice game draws = " + diceGameDraws);
        System.out.println("-----------------------------------------------------------------------------------------\n");
    }

    public void blackjack(long bet) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 4);
        map.put(2, 4);
        map.put(3, 4);
        map.put(4, 4);
        map.put(5, 4);
        map.put(6, 4);
        map.put(7, 4);
        map.put(8, 4);
        map.put(9, 4);
        map.put(10, 16);

        int personHand = 0;
        int computerHand = 0;

        System.out.println("-----------------------------------------------------------------------------------------");

        // Draw two cards for the player
        personHand += drawCard2(map,"You", true);
        MyUtils.waitTimer(1);

        // Draw two cards for the computer
        computerHand += drawCard2(map,"Computer", false);
        MyUtils.waitTimer(1);

        // Player's turn
        personHand = playerTurn(map, personHand);

        // Dealer's turn
        computerHand = dealerTurn(map, computerHand, personHand);
        MyUtils.waitTimer(1);

        // Compare hands and determine the winner
        determineWinner(personHand, computerHand, bet);
    }

    private int drawCard2(Map<Integer, Integer> map, String player, boolean isPlayer) {
        int draw1;
        int draw2;
        do {
            draw1 = random.nextInt(11);
            draw2 = random.nextInt(11);
        } while (draw1 == 0 || draw2 == 0);

        Scanner sc = new Scanner(System.in);

        System.out.print(player + " were given a ");
        if(isPlayer){
            if (draw1 != 1) {
                System.out.println(draw1 + " as your first card");
                map.put(draw1, map.get(draw1) - 1);
            } else {
                System.out.print("ace as your first card, what value do you want it to be (1 or 11): ");
                int value = sc.nextInt();
                while(value != 1 && value != 11){
                    System.out.println();
                    System.out.print("Invalid choice. Please enter 1 or 11: ");
                    value = sc.nextInt();
                }
                map.put(draw1, map.get(draw1) - 1);
                draw1 = value;
            }
            MyUtils.waitTimer(1);
            System.out.print(player + " were given a ");
            if(draw2 != 1){
                System.out.println(draw2 + " as your second card");
                map.put(draw2, map.get(draw2) - 1);
            } else{
                System.out.print("ace as your second card, what value do you want it to be (1 or 11): ");
                int value = sc.nextInt();
                while(value != 1 && value != 11){
                    System.out.println();
                    System.out.print("Invalid choice. Please enter 1 or 11: ");
                    value = sc.nextInt();
                }
                map.put(draw2, map.get(draw2) - 1);
                draw2 = value;
            }
        }
        else{
            if (draw1 != 1) {
                System.out.println(draw1 + " as their first card");
                map.put(draw1, map.get(draw1) - 1);
            } else {
                System.out.println("ace as their first card, they will make it value 11");
                map.put(draw1, map.get(draw1) - 1);
                draw1 = 11;
            }
            MyUtils.waitTimer(1);
            System.out.print(player + " were given a ");
            if(draw2 != 1){
                System.out.println(draw2 + " as their second card");
                map.put(draw2, map.get(draw2) - 1);
            } else{
                System.out.println("ace as their second card, they will make it value 11");
                map.put(draw2, map.get(draw2) - 1);
                draw2 = 11;
            }

        }
        return draw1 + draw2;
    }

    private int drawCard(Map<Integer, Integer> map, String player, boolean isPlayer, int hand) {
        int draw;
        do {
            draw = random.nextInt(11);
        } while (draw == 0 || map.get(draw) == 0);

        Scanner sc = new Scanner(System.in);

        System.out.print(player + " were given a ");
        if(isPlayer){
            if (draw != 1) {
                System.out.println(draw + " as your card");
                map.put(draw, map.get(draw) - 1);
            } else {
                System.out.print("ace as your card, what value do you want it to be (1 or 11): ");
                int value = sc.nextInt();
                while(value != 1 && value != 11){
                    System.out.println();
                    System.out.print("Invalid choice. Please enter 1 or 11: ");
                    value = sc.nextInt();
                }
                map.put(draw, map.get(draw) - 1);
                draw = value;
            }
        }
        else{
            if(draw != 1){
                System.out.println(draw + " as their card");
                map.put(draw, map.get(draw) - 1);
            } else {
                if(hand + 11 > 21){
                    System.out.print("ace as their card, they will make it value 1\n");
                }
                else{
                    System.out.print("ace as their card, they will make it value 11\n");
                    draw = 11;
                }
                map.put(1, map.get(1) - 1);
            }
        }
        return draw;
    }

    private int playerTurn(Map<Integer, Integer> map, int personHand) {
        Scanner sc = new Scanner(System.in);

        while (personHand < 21) {
            MyUtils.waitTimer(1);
            System.out.println("Your current hand is " + personHand);
            MyUtils.waitTimer(1);
            System.out.print("Do you want to hit? (1 for Yes, 2 for No): ");
            int choice = sc.nextInt();

            if (choice == 1) {
                int drawnCard = drawCard(map,"You", true, personHand);
                personHand += drawnCard;
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
        MyUtils.waitTimer(1);

        System.out.println("Your final hand: " + personHand);

        return personHand;
    }

    private int dealerTurn(Map<Integer, Integer> map, int computerHand, int personHand) {
        while (computerHand < 21  && personHand <= 21) {
            MyUtils.waitTimer(1);
            System.out.println("Computer current hand is " + computerHand);
            MyUtils.waitTimer(1);
            int drawnCard = drawCard(map, "Computer", false, computerHand);
            computerHand += drawnCard;
            if(computerHand > personHand){
                break;
            }
        }
        MyUtils.waitTimer(1);
        System.out.println("Computer's final hand: " + computerHand);

        return computerHand;
    }

    private void determineWinner(int personHand, int computerHand, long bet) {
        if (personHand > 21 || (computerHand <= 21 && computerHand > personHand)) {
            money -= bet;
            computerMoney += bet;
            blackjackLoses++;
            System.out.println("You Lost... " + formatter.format(bet) + " was taken from your total balance and added to the computer.\n");
        } else if (personHand == computerHand) {
            blackjackDraws++;
            System.out.println("It was a draw... Balances stay the same.\n");
        } else {
            money += bet;
            computerMoney -= bet;
            blackjackWins++;
            System.out.println("Congratulations you won! " + formatter.format(bet) + " was added to your total balance from the computer.\n");
        }
        MyUtils.waitTimer(1);
        System.out.println("Your Balance is " + formatter.format(money) + "\nComputer Balance is " + formatter.format(computerMoney) + "\n");
        System.out.println("Total Blackjack wins = " + blackjackWins + "\nTotal Blackjack loses = " + blackjackLoses + "\nTotal Blackjack draws = " + blackjackDraws);
        System.out.println("-----------------------------------------------------------------------------------------\n");
    }


    public boolean validBet(long bet){
        return bet <= 100000 && bet > 0;
    }


    public void homeScreen(){
        System.out.println("Which game would you like to play:");
        System.out.print("                                     Press 1 for Dice roll\n");
        System.out.print("                                     Press 2 for BlackJack\n");
        System.out.print("                                     Press 3 for quit\n... ");
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        MyUtils.clearConsole();

        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Tywaine's Gambling Casino!!\n\n");
        System.out.println("We offer two games, Dice roll and BlackJack");
        System.out.println("You will start with $10,000 as your total balance\n Also the max bet is $100,000\n");

        int choice;

        do {
            try {
                homeScreen();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                while (choice > 3 || choice < 1) {
                    System.out.println("Invalid choice");
                    homeScreen();
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }

                if (choice != 3) {
                    MyUtils.clearConsole();
                    if(choice == 1){
                        System.out.println("Welcome to Dice Roll!!\n");
                    }
                    else{
                        System.out.println("Welcome to BlackJack!!\n");
                    }
                    System.out.print("Place your bet: ");
                    String betStr = scanner.nextLine();

                    try {
                        long bet = Long.parseLong(betStr);

                        while (!validBet(bet)) {
                            System.out.println("\nNot a valid bet (must be greater than $0 and less than or equal to $100000");
                            System.out.print("Place your bet: ");
                            betStr = scanner.nextLine();
                            bet = Long.parseLong(betStr);
                        }

                        if (choice == 1) {
                            diceGame2Dice(bet);
                        } else {
                            blackjack(bet);
                        }

                        MyUtils.waitTimer(1);
                        MyUtils.pressEnterToContinue();
                        MyUtils.clearConsole();

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for the bet...\n\n");
                        MyUtils.waitTimer(2);
                        MyUtils.pressEnterToContinue();
                        MyUtils.clearConsole();
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the choice...\n\n");
                scanner.nextLine();
                MyUtils.waitTimer(2);
                MyUtils.pressEnterToContinue();
                choice = 0; // Set choice to an invalid value to continue the loop
                MyUtils.clearConsole();
            }

        } while (choice != 3);
        scanner.close();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if (getMoney() > 10000) {
            long n = getMoney() - 10000;
            System.out.println("\n\nThank you for participating in Tywaine's Casino. You left the casino with a profit of " + formatter.format(n));
        } else if (getMoney() < 10000) {
            long n = 10000 - getMoney();
            System.out.println("\n\nThank you for participating in Tywaine's Casino. You left the casino with a loss of " + formatter.format(n));
        } else {
            System.out.println("\n\nThank you for participating in Tywaine's Casino. You left the casino with what you started out with...   " + formatter.format(10000));
        }
        MyUtils.waitTimer(1);
        System.out.println("Final cash balance: " + balance());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        MyUtils.waitTimer(1);
        MyUtils.extraSpace(5);
        System.out.println("Program will close in 2 seconds");
        MyUtils.waitTimer(2);
        MyUtils.clearConsole();
        System.exit(0);
    }

    public static void main(String[] args){
        Gambling gambling = new Gambling(10000);
        gambling.play();
    }
}
