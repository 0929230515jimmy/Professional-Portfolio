import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.xml.stream.events.Characters;

public class Hangman {
	public static Map<Integer, List<String>> getWordByLength(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
        Map<Integer, List<String>> wordlist = new HashMap<>();

        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            word = word.toLowerCase(Locale.ROOT);

            if (!wordlist.containsKey(word.length())) {
            	wordlist.put(word.length(), new ArrayList<>());
            }
            wordlist.get(word.length()).add(word);
        }

        return wordlist;
	
	}
	
	public static Map<String, List<String>> createWordFamilies(List<String> wordlist, Set<Character> guessedletters){
		Map<String, List<String>> wordfamilies = new HashMap<>();
        for(String string : wordlist) {
            String boardState = "";
            for(char letter : string.toCharArray()) {
                if(guessedletters.contains(letter)){
                    boardState += letter;
                } else {
                    boardState += "_";
                }
            }
            if (!wordfamilies.containsKey(boardState)) {
            	wordfamilies.put(boardState, new ArrayList<>());
            }
            wordfamilies.get(boardState).add(string);
        }

        return wordfamilies;
 	}
	
	
	public static List<String> getBestFamily(Map<String, List<String>> wordFamilies) {
		List<String> bestfamily = null;
        for(List<String> list : wordFamilies.values()) {

            if (bestfamily == null || list.size() > bestfamily.size()) {
            	bestfamily = list;
            }
        }

        for (String string : wordFamilies.keySet()) {
            if (wordFamilies.get(string) == bestfamily) {
                System.out.println(string);
            }
        }

        return bestfamily;
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		String filename = "words.txt";
        Scanner scanner = new Scanner(new File(filename));
        Scanner sc = new Scanner(System.in);

        Set<Character> guesses = new HashSet<>();
        Set<Integer> existWordLength = new HashSet<>();
        List<String> wordList;

        while (scanner.hasNextLine()) {
            String words = scanner.nextLine();
            existWordLength.add(words.length());
        }

        while (true) {
            System.out.println("Word length: ");
            int input;

            while (true) {
                try {
                    input = Integer.parseInt(sc.nextLine());
                    while (!existWordLength.contains(input)) {
                        System.out.println("There is no word in this length");
                        input = Integer.parseInt(sc.nextLine());
                    }
                    break;
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Enter number only");
                }
            }

            wordList = getWordByLength(filename).get(input);

            int turns = 0;
            int chance;
            while (true) {
                try {
                    System.out.println("How many guesses do you want to have");
                    chance = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Enter number only");
                }
            }

            System.out.println("\nGame start");

            while (true) {
                System.out.println("Please enter a character");
                char character = sc.nextLine().charAt(0);
                character = Character.toLowerCase(character);

                while (!Character.isAlphabetic(character)) {
                    System.out.println("Enter a character only");
                    character = sc.nextLine().charAt(0);
                }

                while (guesses.contains(character)) {
                    System.out.println("You've guessed this character before");
                    character = sc.nextLine().charAt(0);
                }

                guesses.add(character);
                System.out.println("Result: ");
                Map<String, List<String>> families = createWordFamilies(wordList,guesses);
                wordList = getBestFamily(families);
                System.out.println("\nCharacter you've guessed: " + guesses);

                turns++;
                System.out.println("Turns: " + turns + " | " + "Turns left: " + (chance-turns) + "\n");
                System.out.println(wordList.size());

                if (turns == chance && wordList.size()!=1) {
                    System.out.println("You loose");
                    String answer = wordList.get(1);
                    System.out.println("The answer is " + answer);
                    break;
                }

                if(wordList.size() == 1) {
                    System.out.println("You win!");
                }
            }

            System.out.println("\nEnter 1 to start a new game.");
            System.out.println("Enter other to end the game.");
            String startNewGame = sc.nextLine();

            if (startNewGame != "1") {
                System.out.println("End of the game");
                break;
            }
        }
		
	}
}






