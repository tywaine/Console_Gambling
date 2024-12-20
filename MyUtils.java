import java.util.Scanner;

public interface MyUtils{
    static void pressEnterToContinue(){
        System.out.print("Press enter to continue...");
        try {
            // Use Scanner to capture any key press
            new Scanner(System.in).nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method for pausing (seconds)
    static void waitTimer(int sec){
        try {
            for (int i = 0; i < sec; i++) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void pause(int milli){
        try {
            Thread.sleep(milli);

            } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void displayWaitTimer(int sec){
        try {
            for (int i = 0; i < sec; i++) {
                System.out.print(sec-i);
                Thread.sleep(1000);
                System.out.print("\033[2K\033[1G");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void extraSpace(int space){
        for (int i = 0; i < space; i++) {
            System.out.println();
        }
    }

    static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void clearLine(){
        System.out.print("\033[2K\033[1G");
    }

    static void clearLine(int amountOfTimes){
        for(int i = 0; i < amountOfTimes; i++){
            System.out.print("\033[2K\033[1G");
        }
    }

}
