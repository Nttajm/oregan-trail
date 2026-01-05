import java.util.Scanner;
import java.util.Random;

public class TrailGame {
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();
    // hello there
    // code that checks supplies and returns conditions
    public int checkSupplies(int food, int ammo) {
        if (food < 3 && ammo < 3) {
            System.out.println("You're in bad shape—starving and unarmed!");
            return 0;
        } else if (food < 3) {
            System.out.println("You're short on food. Everyone’s getting hangry.");
            return 1;
        } else if (ammo < 3) {
            System.out.println("You’re short on ammo—dangerous out here.");
            return 2;
        }
        System.out.println("Supplies are holding up fine.");
        return 3;
    }

    public void startJourney() {
        int health = 10, food = 6, ammo = 6;
        System.out.println("====== Welcome to the Oregon Trail ======");
        System.out.println("It’s 1848. You and your wagon party set off for the West.\n");

        System.out.print("Start in (1) Spring or (2) Summer: ");
        int season = scanner.nextInt();
        if (season == 1) System.out.println("Spring—longer trip but mild weather.");
        else {
            System.out.println("Summer—less time before snow hits the passes.");
            health -= 1;
        }

        System.out.print("Choose your profession (1) Farmer, (2) Hunter, (3) Banker: ");
        int role = scanner.nextInt();
        if (role == 1) food += 2;
        else if (role == 2) ammo += 3;
        else System.out.println("Banker—rich but soft hands. Good luck.");

        System.out.println("\nYour wagon creaks forward onto the dirt trail...");
        System.out.print("At the first fork, go (1) River Route or (2) Plains Route: ");
        int route = scanner.nextInt();

        if (route == 1) riverRoute(health, food, ammo);
        else plainsRoute(health, food, ammo);
    }

    // -------- River Route --------
    private void riverRoute(int health, int food, int ammo) {
        System.out.println("\nYou follow the riverbanks, lush but full of surprises.");

        System.out.print("You find a fishing spot. Fish? (1) yes / (2) no: ");
        int fish = scanner.nextInt();
        if (fish == 1) {
            if (rand.nextBoolean()) {
                System.out.println("You caught a ton of fish! Food +3");
                food += 3;
            } else {
                System.out.println("No luck—mosquitoes got you instead. Health -1");
                health -= 1;
            }
        }

        System.out.println("\nStorm clouds roll in fast...");
        System.out.print("Do you (1) seek shelter or (2) push forward: ");
        int storm = scanner.nextInt();
        if (storm == 1) {
            System.out.println("You wait it out but lose 2 food to rot.");
            food -= 2;
        } else {
            System.out.println("Wagon damaged by hail! Health -2");
            health -= 2;
        }

        checkSupplies(food, ammo);

        System.out.println("\nA campfire night—people tell stories.");
        System.out.print("Do you (1) rest or (2) stay up and keep watch: ");
        int camp = scanner.nextInt();
        if (camp == 1) {
            System.out.println("You sleep well and recover health.");
            health += 2;
        } else {
            System.out.println("You spot wolves circling and scare them off with ammo.");
            ammo -= 1;
        }

        // Random bad luck or fortune
        int event = rand.nextInt(3);
        if (event == 0) {
            System.out.println("\nA thief steals some food at night! Food -2");
            food -= 2;
        } else if (event == 1) {
            System.out.println("\nYou find wild berries. Food +2");
            food += 2;
        } else {
            System.out.println("\nNothing special—quiet night by the water.");
        }

        System.out.println("\nYou reach a ferry crossing the wide Columbia River.");
        System.out.print("Cross by (1) paying ferryman (costs 2 food) or (2) build raft (risky): ");
        int cross = scanner.nextInt();
        if (cross == 1) {
            food -= 2;
            System.out.println("You pay safely and cross smoothly.");
        } else {
            if (rand.nextBoolean()) {
                System.out.println("Raft holds! You save food and cross.");
            } else {
                System.out.println("Raft collapses! You lose supplies.");
                food -= 3;
                health -= 2;
            }
        }

        System.out.println("\nAfter many days, you finally see the green hills of Oregon...");
        endResult(health, food, ammo);
    }

    // -------- Plains Route --------
    private void plainsRoute(int health, int food, int ammo) {
        System.out.println("\nYou travel across endless grassland and heat waves shimmer.");

        System.out.print("You spot buffalo. Hunt them? (1) yes / (2) no: ");
        int hunt = scanner.nextInt();
        if (hunt == 1 && ammo >= 2) {
            System.out.println("You bring down a buffalo! Food +4, Ammo -2");
            food += 4;
            ammo -= 2;
        } else if (hunt == 1) {
            System.out.println("Out of ammo—you miss your chance.");
        } else {
            System.out.println("You conserve ammo but lose a day of travel.");
            food -= 1;
        }

        checkSupplies(food, ammo);

        System.out.print("\nA stranger offers you a map for 1 ammo. Trade? (1) yes / (2) no: ");
        int trade = scanner.nextInt();
        if (trade == 1 && ammo > 0) {
            System.out.println("You get the map—find a shortcut later!");
            ammo -= 1;
        } else {
            System.out.println("You refuse. The stranger shrugs and walks away.");
        }

        System.out.print("\nA dust storm approaches! (1) Hide or (2) Keep moving: ");
        int dust = scanner.nextInt();
        if (dust == 1) {
            System.out.println("You hide and wait. Lose 1 food.");
            food -= 1;
        } else {
            System.out.println("The storm wrecks your wagon! Health -3");
            health -= 3;
        }

        System.out.println("\nAt night, your group gathers at the campfire.");
        System.out.print("Sing songs (1) or tell ghost stories (2): ");
        int night = scanner.nextInt();
        if (night == 1) {
            System.out.println("Spirits rise. Morale improves. Health +1");
            health += 1;
        } else {
            System.out.println("Too spooky—no one sleeps. Health -1");
            health -= 1;
        }

        System.out.print("\nYou see a mountain pass ahead. (1) Climb it / (2) Take long detour: ");
        int path = scanner.nextInt();
        if (path == 1) {
            System.out.println("You climb—it’s brutal. Lose 2 health but reach Oregon faster.");
            health -= 2;
        } else {
            System.out.println("You take the long road but find berries. Food +2");
            food += 2;
        }

        System.out.println("\nAfter weeks, the plains fade behind you. You made it to Oregon!");
        endResult(health, food, ammo);
    }

    private void endResult(int health, int food, int ammo) {
        System.out.println("\n===== Journey Complete =====");
        System.out.println("Health: " + health + " | Food: " + food + " | Ammo: " + ammo);
        if (health > 6 && food > 3) {
            System.out.println("You made it with flying colors! Oregon welcomes you!");
        } else if (health > 0) {
            System.out.println("You barely made it, but you made it.");
        } else {
            System.out.println("You died somewhere on the trail. Your name fades into legend.");
        }
    }
}
/**
 * TrailGame.java
 * A simplified text-based Oregon Trail adventure game.

 */
