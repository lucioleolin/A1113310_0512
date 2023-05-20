import java.util.Random;
import java.util.Scanner;

class TintinDumplingRestaurant {
    private int porkDumplings = 5000;
    private int beefDumplings = 3000;
    private int vegetableDumplings = 1000;

    private Random random = new Random();

    public synchronized void serveCustomer() {
        try {
            Thread.sleep(3000); // Waiting time for the next customer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int numDumplings = random.nextInt(41) + 10; // Generate a random number of dumplings (between 10 and 50)
        String dumplingType = getRandomDumplingType(); // Randomly select the type of dumplings

        switch (dumplingType) {
            case "pork":
                if (porkDumplings >= numDumplings) {
                    porkDumplings -= numDumplings;
                    System.out.println(numDumplings + " pork dumplings served to the customer.");
                } else {
                    System.out.println("Sorry, not enough pork dumplings available.");
                }
                break;
            case "beef":
                if (beefDumplings >= numDumplings) {
                    beefDumplings -= numDumplings;
                    System.out.println(numDumplings + " beef dumplings served to the customer.");
                } else {
                    System.out.println("Sorry, not enough beef dumplings available.");
                }
                break;
            case "vegetable":
                if (vegetableDumplings >= numDumplings) {
                    vegetableDumplings -= numDumplings;
                    System.out.println(numDumplings + " vegetable dumplings served to the customer.");
                } else {
                    System.out.println("Sorry, not enough vegetable dumplings available.");
                }
                break;
        }
    }

    private String getRandomDumplingType() {
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                return "pork";
            case 1:
                return "beef";
            case 2:
                return "vegetable";
            default:
                return "vegetable";
        }
    }
}

public class A1113310_0512 {
    public static void main(String[] args) {
        TintinDumplingRestaurant restaurant = new TintinDumplingRestaurant();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of customers visiting at the same time: ");
        int numCustomers = scanner.nextInt();

        Thread[] threads = new Thread[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            threads[i] = new Thread(restaurant::serveCustomer);
            threads[i].start();
        }

        for (int i = 0; i < numCustomers; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
