import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        SocialNetwork socialNetwork = new SocialNetwork();

        int choice;

        do {

            Menu.showMenu();
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Nama User : ");
                    String user = input.nextLine();

                    socialNetwork.addUser(user);
                    break;

                case 2:

                    System.out.print("User A : ");
                    String a = input.nextLine();

                    System.out.print("User B : ");
                    String b = input.nextLine();

                    socialNetwork.addFriendship(a, b);
                    break;

                case 3:

                    System.out.print("User 1 : ");
                    String u1 = input.nextLine();

                    System.out.print("User 2 : ");
                    String u2 = input.nextLine();

                    socialNetwork.findMutualFriends(u1, u2);
                    break;

                case 4:

                    System.out.print("User : ");
                    String target = input.nextLine();

                    socialNetwork.recommendFriends(target);
                    break;

                case 5:

                    System.out.print("User Awal : ");
                    String start = input.nextLine();

                    System.out.print("User Tujuan : ");
                    String end = input.nextLine();

                    BFS.findPath(
                            socialNetwork.getGraph(),
                            start,
                            end
                    );
                    break;

                case 6:

                    socialNetwork.displayGraph();
                    break;

                case 7:

                    System.out.println("Program selesai.");
                    break;

                default:

                    System.out.println("Pilihan tidak valid.");
            }

        } while (choice != 7);
    }
}