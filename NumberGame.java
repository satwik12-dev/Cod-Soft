import java.util.Random;
import java.util.Scanner;
public class NumberGame {
    public static void main(String[] args) {
        
        while(true){
            Random rand = new Random();
        
            int random_number = rand.nextInt(100);
            if(random_number >= 0 && random_number <= 50){
                System.out.println("HINT: The nummber is in between 0 to 50.....");
            }
            else if(random_number >= 50 && random_number <= 100){
                System.out.println("HINT: The nummber is in between 50 to 100.....");
            }
        
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the chances in which you can guess the number:");
            int chances = sc.nextInt();

            boolean guessedCorrectly = false;
            for(int i=0; i<chances; i++){
                System.out.println("Enter the guessed number:");
                int n = sc.nextInt();

                if(n>random_number){
                    System.out.println("Number you guessed is too large....");
                }
                else if(n<random_number){
                    System.out.println("Number you guessed is very small....");
                }
                else if(n== random_number){
                    System.out.println("Congratulations!! You guessed the right number");
                    guessedCorrectly = true;
                    break;
                }
            }

            if(!guessedCorrectly) {
                System.out.println("You were not able to guess the right number in your " + chances + " chances. So the correct number is: " + random_number);
            }

            System.out.println("You want to play again (yes/no):");
            String choice = sc.next();
            if(choice.equalsIgnoreCase("yes")){
                continue;
            } else {
                System.out.println("Thank you for playing.....");
                sc.close();
                return;
            }

        }

    }
}
