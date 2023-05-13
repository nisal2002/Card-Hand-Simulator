import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static int getSuitIndexSort(String suit, ArrayList<String> order) { //returns the index of suit, to suit order

        for (int i = 0; i < order.size(); i++) {
            if (suit.equals(order.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static int getRankSort(String rank) { //returns the rank value, to rank order
        switch (rank) {
            case "2":
                return 0;
            case "3":
                return 1;
            case "4":
                return 2;
            case "5":
                return 3;
            case "6":
                return 4;
            case "7":
                return 5;
            case "8":
                return 6;
            case "9":
                return 7;
            case "10":
                return 8;
            case "Jack":
                return 9;
            case "Queen":
                return 10;
            case "King":
                return 11;
            case "Ace":
                return 12;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> suit = new ArrayList<>(); //adding suits to array
        suit.add("Clubs");
        suit.add("Diamonds");
        suit.add("Hearts");
        suit.add("Spades");

        ArrayList<String> rank = new ArrayList<>(); //adding ranks to array
        rank.add("2");
        rank.add("3");
        rank.add("4");
        rank.add("5");
        rank.add("6");
        rank.add("7");
        rank.add("8");
        rank.add("9");
        rank.add("10");
        rank.add("Jack");
        rank.add("Queen");
        rank.add("King");
        rank.add("Ace");

        ArrayList<String> pack = new ArrayList<>(); //create array for save all hands[i]

        for(String s : suit){
            for (String r : rank){
                pack.add(r+"-"+s); //adding hands[i] into pack
            }
        }
        System.out.println("Card Pack : "+pack);


        Scanner sc= new Scanner(System.in);
        System.out.println("\nHow many players participate ? ");

        try {
            int participants = sc.nextInt(); //input how many participants
            Collections.shuffle(pack); //shuffle the pack

            ArrayList<String>[] hands = new ArrayList[participants];

            for(int i =0; i< participants; i++){ //creating the no. of arrayLists
                hands[i] = new ArrayList<>();
            }

            int j=1;
            int dealAfterHand=0;
            for(int i=0; i< pack.size();i++){ //insert the indexes of pack array into new created no. of arrayLists
                int handIndex = i%participants; //check what arrayList to insert got index
                hands[handIndex].add(pack.get(i));
                if((handIndex+1)==1){
                    System.out.println("\n----- Deal "+j+" -----");
                    j++;
                }
                if(dealAfterHand==participants){ //after every deal print the whole hand
                    dealAfterHand=0;

                    System.out.println("Player "+(handIndex+1)+" : "+hands[dealAfterHand]);
                    dealAfterHand++;
                }else {
                    System.out.println("Player "+(handIndex+1)+" : "+hands[dealAfterHand]);
                    dealAfterHand++;
                }
            }

            System.out.println("\n\n<<<<<<<<<< <<<<<<<<<< <<<<<<<<<< <<<<<<<<<< FINAL HANDS OF THE PLAYERS >>>>>>>>>> >>>>>>>>>> >>>>>>>>>> >>>>>>>>>>");
            for(int i =0; i <participants;i++){ //display the finalized arrayLists(hand)
                System.out.println("Player "+(i+1)+" hand : "+hands[i]);

                ArrayList<String> secStrings = new ArrayList<String>();

                for (String second :hands[i]){
                    String [] parts = second.split("-");
                    String secString = parts[1];
                    if(!secStrings.contains(secString)){
                        secStrings.add(secString);
                    }
                }
                int n = hands[i].size();
                for (int q = 0; q < n-1; q++) {
                    for (int r = 0; r < n-q-1; r++) {
                        String card1 = hands[i].get(r);
                        String card2 = hands[i].get(r+1);

                        String suit1 = card1.split("-")[1]; //extracting suit values from hands[i]
                        String suit2 = card2.split("-")[1];

                        int rank1 = getRankSort(card1.split("-")[0]); //extracting rank values from hands[i]
                        int rank2 = getRankSort(card2.split("-")[0]);

                        // Sorting according to suit and rank order
                        if (getSuitIndexSort(suit1, secStrings) > getSuitIndexSort(suit2, secStrings) || (getSuitIndexSort(suit1, secStrings) == getSuitIndexSort(suit2, secStrings) && rank1 > rank2)) {
                            hands[i].set(r, card2);
                            hands[i].set(r+1, card1);
                        }
                    }
                }
                System.out.println("Sorted Hand   : "+hands[i]+"\n");
            }


            Scanner tc = new Scanner(System.in);
            String func = "";

            while (!func.equals("ESC")) {
                System.out.println("\nWhat do you want to do?\nType 'PLAY' for play game\nType 'FIND' for check suits\nType 'ESC' for exit");
                func = tc.nextLine();
                func = func.toUpperCase();

                if (func.equals("PLAY")) {
                    int playSuit = 0;
                    while (playSuit < 1 || playSuit > 4) {
                        String clubs="Clubs";
                        String diamonds="Diamonds";
                        String hearts="Hearts";
                        String spades="Spades";

                        System.out.println("--------------------\nSELECT SUIT TO PLAY\n--------------------");
                        System.out.println("PRESS 1 for "+clubs+"\nPRESS 2 for "+diamonds+"\nPRESS 3 for "+hearts+"\nPRESS 4 for "+spades);

                        if (tc.hasNextInt()) {
                            playSuit = tc.nextInt();
                            if (playSuit >= 1 && playSuit <= 4) {

                                if(playSuit==1){
                                    System.out.println("Selected suit - "+clubs);
                                    for(int i =0; i <participants;i++) {

                                        System.out.println("Player " + (i + 1) + " hand : " + hands[i]); //print sort hand

                                        String suitCheck = clubs;
                                        boolean isSuitThere = false;
                                        for (String ccc: hands[i]){
                                            if (ccc.endsWith(suitCheck)){ //check the index last string suit with entered suit
                                                hands[i].remove(ccc); //remove the first card of the suit
                                                isSuitThere=true;
                                                break;
                                            }
                                        }

                                        if(!isSuitThere){ //if entered suit not in there randomly select card and remove it
                                            Random rand1=new Random();
                                            int removeAny=rand1.nextInt(hands[i].size());
                                            hands[i].remove(removeAny);
                                        }
                                        System.out.println("Now hand      : "+hands[i]+"\n"); //print hand
                                    }

                                }else if (playSuit==2) {
                                    System.out.println("Selected suit - "+diamonds);
                                    for(int i =0; i <participants;i++) {

                                        System.out.println("Player " + (i + 1) + " hand : " + hands[i]);

                                        String suitCheck = diamonds;
                                        boolean isSuitThere = false;
                                        for (String ccc: hands[i]){
                                            if (ccc.endsWith(suitCheck)){
                                                hands[i].remove(ccc);
                                                isSuitThere=true;
                                                break;
                                            }
                                        }

                                        if(!isSuitThere){
                                            Random rand2=new Random();
                                            int removeAny=rand2.nextInt(hands[i].size());
                                            hands[i].remove(removeAny);
                                        }
                                        System.out.println("Now hand      : "+hands[i]+"\n");
                                    }

                                }else if (playSuit==3) {
                                    System.out.println("Selected suit - " +hearts);
                                    for(int i =0; i <participants;i++) {

                                        System.out.println("Player " + (i + 1) + " hand : " + hands[i]);

                                        String suitCheck = hearts;
                                        boolean isSuitThere = false;
                                        for (String ccc: hands[i]){
                                            if (ccc.endsWith(suitCheck)){
                                                hands[i].remove(ccc);
                                                isSuitThere=true;
                                                break;
                                            }
                                        }

                                        if(!isSuitThere){
                                            Random rand3=new Random();
                                            int removeAny=rand3.nextInt(hands[i].size());
                                            hands[i].remove(removeAny);
                                        }
                                        System.out.println("Now hand      : "+hands[i]+"\n");
                                    }

                                }else if (playSuit==4) {
                                    System.out.println("Selected suit - " + spades);
                                    for(int i =0; i <participants;i++) {

                                        System.out.println("Player " + (i + 1) + " hand : " + hands[i]);

                                        String suitCheck = spades;
                                        boolean isSuitThere = false;
                                        for (String ccc: hands[i]){
                                            if (ccc.endsWith(suitCheck)){
                                                hands[i].remove(ccc);
                                                isSuitThere=true;
                                                break;
                                            }
                                        }

                                        if(!isSuitThere){
                                            Random rand4=new Random();
                                            int removeAny=rand4.nextInt(hands[i].size());
                                            hands[i].remove(removeAny);
                                        }
                                        System.out.println("Now hand      : "+hands[i]+"\n");
                                    }
                                }
                            }else {
                                System.out.println("Invalid input. Please select a number between 1 and 4.");
                            }

                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            tc.nextLine(); // consume the invalid input
                        }
                    }
                    tc.nextLine(); // consume the new line character left by nextInt()

                } else if (func.equals("FIND")) {
                    int playSuit = 0;
                    while (playSuit < 1 || playSuit > 4) {
                        String clubs = "Clubs";
                        String diamonds = "Diamonds";
                        String hearts = "Hearts";
                        String spades = "Spades";

                        System.out.println("--------------------\nSELECT SUIT TO FIND\n--------------------");
                        System.out.println("PRESS 1 for " + clubs + "\nPRESS 2 for " + diamonds + "\nPRESS 3 for " + hearts + "\nPRESS 4 for " + spades);

                        if (tc.hasNextInt()) {
                            playSuit = tc.nextInt();
                            if (playSuit >= 1 && playSuit <= 4) {

                                if (playSuit == 1) {
                                    System.out.println("--------------------\nSelected suit - " + clubs+"\n--------------------");
                                    for (int i = 0; i < participants; i++) {

                                        System.out.println("\nPlayer " + (i + 1) + " hand : " + hands[i]); //print sort hand

                                        String suitCheck = clubs;
                                        boolean isSuitThere = false;
                                        for (String ccc : hands[i]) {
                                            if (ccc.endsWith(suitCheck)) { //check the index last string suit with entered suit
                                                System.out.println(ccc); //print the cards of the suit
                                                isSuitThere = true;
                                            }
                                        }

                                        if (!isSuitThere) { //if entered suit not in there print message
                                            System.out.println("No card from the selected suit");
                                        }
                                    }
                                } else if (playSuit == 2) {
                                    System.out.println("--------------------\nSelected suit - " + diamonds+"\n--------------------");
                                    for (int i = 0; i < participants; i++) {

                                        System.out.println("\nPlayer " + (i + 1) + " hand : " + hands[i]); //print sort hand

                                        String suitCheck = diamonds;
                                        boolean isSuitThere = false;
                                        for (String ccc : hands[i]) {
                                            if (ccc.endsWith(suitCheck)) { //check the index last string suit with entered suit
                                                System.out.println(ccc); //print the cards of the suit
                                                isSuitThere = true;
                                            }
                                        }

                                        if (!isSuitThere) { //if entered suit not in there print message
                                            System.out.println("No card from the selected suit");
                                        }
                                    }
                                } else if (playSuit == 3) {
                                    System.out.println("--------------------\nSelected suit - " + hearts+"\n--------------------");
                                    for (int i = 0; i < participants; i++) {

                                        System.out.println("\nPlayer " + (i + 1) + " hand : " + hands[i]); //print sort hand

                                        String suitCheck = hearts;
                                        boolean isSuitThere = false;
                                        for (String ccc : hands[i]) {
                                            if (ccc.endsWith(suitCheck)) { //check the index last string suit with entered suit
                                                System.out.println(ccc); //print the cards of the suit
                                                isSuitThere = true;
                                            }
                                        }

                                        if (!isSuitThere) { //if entered suit not in there print message
                                            System.out.println("No card from the selected suit");
                                        }
                                    }
                                } else if (playSuit == 4) {
                                    System.out.println("--------------------\nSelected suit - " + spades+"\n--------------------");
                                    for (int i = 0; i < participants; i++) {

                                        System.out.println("\nPlayer " + (i + 1) + " hand : " + hands[i]); //print sort hand

                                        String suitCheck = spades;
                                        boolean isSuitThere = false;
                                        for (String ccc : hands[i]) {
                                            if (ccc.endsWith(suitCheck)) { //check the index last string suit with entered suit
                                                System.out.println(ccc); //print the cards of the suit
                                                isSuitThere = true;
                                            }
                                        }

                                        if (!isSuitThere) { //if entered suit not in there print message
                                            System.out.println("No card from the selected suit");
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid input. Please select a number between 1 and 4.");
                                }

                            } else {
                                System.out.println("Invalid input. Please enter a number.");
                                tc.nextLine(); // consume the invalid input
                            }
                        }
                        tc.nextLine(); // consume the new line character left by nextInt()
                    }
                } else if (!func.equals("ESC")) {
                    System.out.println("Invalid input. Please enter PLAY or FIND or ESC.");
                }
            }
            System.out.println("Exiting program...");
        }catch (Exception InputMismatchException){
            System.out.println("Error : Input Mismatch Exception ");
        }
    }
}