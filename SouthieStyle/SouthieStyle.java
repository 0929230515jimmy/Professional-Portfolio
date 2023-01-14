import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class SouthieStyle {
	public static boolean checkvowel(String a) {
		String c = a.substring(0, 1);
		if (c.equalsIgnoreCase("A") || c.equalsIgnoreCase("E") || c.equalsIgnoreCase("I") || c.equalsIgnoreCase("O") || c.equalsIgnoreCase("U")) {
			return true;
		} else
			return false;
	}

	public static String ruleNo1(String a) {

		String[] vowels = {"a", "e", "i", "o", "u"};

		while (a.toLowerCase().contains("ar") || a.toLowerCase().contains("er") || a.toLowerCase().contains("ir") || a.toLowerCase().contains("or")
		|| a.toLowerCase().contains("ur")) {

			for (int j = 0; j < vowels.length; j++) {
				if (a.toLowerCase().contains(vowels[j] + "r")) {
					int i = a.toLowerCase().indexOf(vowels[j] + "r");
					if (i != -1) {
						if (Character.isUpperCase(a.charAt(i)) && Character.isUpperCase(a.charAt(i + 1)))
							a = a.replace(vowels[j].toUpperCase() + "R", vowels[j].toUpperCase() + "H");
						else if (Character.isUpperCase(a.charAt(i)) && Character.isLowerCase(a.charAt(i + 1)))
							a = a.replace(vowels[j].toUpperCase() + "r", vowels[j].toUpperCase() + "h");
						else if (Character.isLowerCase(a.charAt(i)) && Character.isLowerCase(a.charAt(i + 1)))
							a = a.replace(vowels[j].toLowerCase() + "r", vowels[j].toLowerCase() + "h");
						else
						a = a.replace(vowels[j] + "R", vowels[j].toLowerCase() + "H");
						}
					}
				}
			}
			return a;
	}

	public static String ruleNo2(String a) {
		String last = "";
	    if (a.length() > 1)
	       last = a.substring(a.length() - 1);
	    else
	       return a;
	    if (last.equalsIgnoreCase("a"))
	       return a + "r";
	    return a;
	}

	
	public static String ruleNo3(String a) {
		if (a.equalsIgnoreCase("very"))
			return "wicked";
		else
			return a;

	}


	public static String exceptionsNo1(String a) {
		String last3 = "";
	    if (a.length() > 3)
	       last3 = a.substring(a.length() - 3, a.length());
	    else
	       return a;
	    if (last3.equalsIgnoreCase("eer") || last3.substring(1).equalsIgnoreCase("ir"))
	       return a.substring(0, a.length() - 1) + "yah";
	    return a;
	}

	public static String exceptionsNo2(String a) {
	    String last3 = "";
	    if (a.length() > 3)
	       last3 = a.substring(a.length() - 3, a.length());
	    else
	       return a;
	    if (last3.equalsIgnoreCase("oor"))
	       return a.substring(0, a.length() - 1) + "wah";
	       return a;
	}

	public static String Useinword(String s) {
		String a = ""; 
		a = ruleNo3(s);
		a = exceptionsNo1(a);
		a = exceptionsNo2(a);
		a = ruleNo1(a);
		a = ruleNo2(a);
		return a;
	}

	public static String convertSouthie(String s) {
		StringBuilder b = new StringBuilder();
	       String[] words = s.split(" ");
	       for (String w : words) {
	           if (!w.equals("")) {
	               b.append(Useinword(w));
	               b.append(" ");
	           } else
	               b.append(" ");
	       }
	       return b.toString();
	}

	public static void Southiejaws() throws FileNotFoundException {
		File f = new File("/Users/jimmywu/eclipse-workspace/SouthieStyle/src/jaws.txt");
		Scanner sc = new Scanner(f);
		

		while (sc.hasNextLine()) {
			System.out.println(convertSouthie(sc.nextLine()));
		}
		PrintStream o = new PrintStream(new File("jawsSouthie.txt"));
		System.setOut(o);
		}
	
	

	public static void main(String args[]) throws FileNotFoundException {

		Southiejaws();
		}

}


