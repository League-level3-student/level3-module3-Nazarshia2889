package _00_Intro_To_String_Methods;

import java.util.Arrays;
import java.util.Base64;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

    // Given Strings s1 and s2, return the longer String
    public static String longerString(String s1, String s2) {
    	if(s1.length()>s2.length()) {
    		return s1;
    	}
        return s2;
    }

    // If String s contains the word "underscores", change all of the spaces
    // to underscores
    public static String formatSpaces(String s) {
        if(s.contains("underscores")) {
        	return s.replace(" ", "_");
        }
        return s;
    }

    // Return the name of the person whose LAST name would appear first if they
    // were in alphabetical order.
    // You cannot assume there are no extra spaces around the name, but you can
    // assume there is only one space between the first and last name
    public static String lineLeader(String s1, String s2, String s3) {
    	String a = s1.trim();
    	Character one = a.charAt(a.length()-1);
    	
    	String b = s2.trim();
    	Character two = b.trim().charAt(b.length()-1);
    	
    	String c = s3.trim();
    	Character three = c.trim().charAt(c.length()-1);
    	if(one.compareTo(two) < 0 && one.compareTo(three) < 0) {
    		return a;
    	}
    	else if(two.compareTo(one) < 0 && two.compareTo(three) < 0) {
    		return b;
    	}
    	else if(three.compareTo(one) < 0 && three.compareTo(two) < 0){
    		return c;
    	}
    	return a;
    }

    // Return the sum of all numerical digits in the String
    public static int numeralSum(String s) {
    	char[] numbers = s.toCharArray();
    	int sum = 0;
    	for(int i = 0;i<numbers.length;i++) {
    		if(Character.isDigit(numbers[i])) {
    			int x = Character.getNumericValue(numbers[i]);
    			sum += x;
    		}
    	}
    	
        return sum;
    }

    // Return the number of times String substring appears in String s
    public static int substringCount(String s, String substring) {
    	int nums = 0;
        int index = s.indexOf(substring);
        while( index != -1 ) {
            nums++;
            index = s.indexOf(substring, index + substring.length());
        }
        return nums;
    }

    // Call Utilities.encrypt at the bottom of this file to encrypt String s
    public static String encrypt(String s, char key) {
        return Utilities.encrypt(s.getBytes(), (byte) key);
    }

    // Call Utilities.decrypt at the bottom of this file to decrypt the
    // cyphertext (encrypted text)
    public static String decrypt(String s, char key) {
        return Utilities.decrypt(s, (byte) key);
    }

    // Return the number of words in String s that end with String substring
    // You can assume there are no punctuation marks between words
    public static int wordsEndsWithSubstring(String s, String substring) {
    	String[] words = s.split(" ");
    	int nums = 0;
    	for(int i = 0;i<words.length;i++) {
    		if(words[i].indexOf(substring) != -1) {
    			if(words[i].substring(words[i].length() - substring.length(), words[i].length()).equalsIgnoreCase(substring)) {
    				nums++;
    			}
    		}
    	}
        return nums;
    }

    // Given String s, return the number of characters between the first
    // occurrence of String substring and the final occurrence
    // You can assume that substring will appear at least twice
    public static int distance(String s, String substring) {
    	int start = s.indexOf(substring) + substring.length();
    	int last = s.indexOf(substring);
		while((last+substring.length())<s.length()) {
			last = s.indexOf(substring, last + substring.length());
		}
		System.out.println(s);
		System.out.println(start);
		System.out.println(last);
		if(s.indexOf(substring) != -1) {
			return last-start;
    	}
		else {
			return 0;
		}
    }

    // Return true if String s is a palindrome
    // palindromes are words or phrases are read the same forward as backward.
    // HINT: ignore/remove all punctuation and spaces in the String
    public static boolean palindrome(String s) {
        String n = s.replaceAll("\\p{Punct}", "");
        n = n.replace(" ", "");
        n = n.toLowerCase();
        char[] chars = n.toCharArray();
        char[] reversed = new char[chars.length];
        int counter = 0;
        for(int i = chars.length-1;i>=0;i--) {
        	reversed[counter] = chars[i];
        	counter += 1;
        }
        return Arrays.equals(reversed, chars);
    }
}

class Utilities {
    // This basic encryption scheme is called single-byte xor. It takes a
    // single byte and uses exclusive-or on every character in the String.
    public static String encrypt(byte[] plaintext, byte key) {
        for (int i = 0; i < plaintext.length; i++) {
            plaintext[i] = (byte) (plaintext[i] ^ key);
        }
        return Base64.getEncoder().encodeToString(plaintext);
    }

    public static String decrypt(String cyphertext, byte key) {
        byte[] b = Base64.getDecoder().decode(cyphertext);
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] ^ key);
        }
        return new String(b);
    }
}
