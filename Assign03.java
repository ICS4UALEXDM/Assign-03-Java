import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* This program is my Assignment 3.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class Assign03 {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private Assign03() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        try {
            // new file object
            final File input = new File("input.txt");

            // Creating the writer
            final FileWriter writer = new FileWriter("output.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(input);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    // getting next line and putting it in the interim list
                    line = scanner.nextLine();
                    lines.add(line);
                }
                // Creating the header in the text file that indicates which
                // program is being run
                writer.write("Output for the recIsPalindrome() function:\n\n");
                // Looping through the input list and running the
                // recIsPalindrome() function that will determine whether or not
                // the input is a palindrome
                for (String potentialPal : lines) {
                    // getting the length of the line
                    final int length = potentialPal.length();
                    // empty lines are technically palindromes because they are
                    // symmetrical
                    if (length != 0) {
                        // calling the function to check for palindrome
                        final boolean isPal = recIsPalindrome(
                                potentialPal, 0, length - 1);
                        // what to do based on the input
                        if (isPal) {
                            writer.write(potentialPal + " is a palindrome!\n");
                        } else {
                            writer.write(
                                    potentialPal + " is not a palindrome!\n");
                        }
                    } else {
                        writer.write("The empty line is a palindrome!\n");
                    }
                }
                // Heading for the depth of palindrome function
                writer.write("\nOutput for depthOfPalindrome() function:\n\n");
                for (String aLine : lines) {
                    if (aLine.length() == 0) {
                        writer.write("ERROR: Empty line  \n");
                    } else {
                        try {
                            final int aNum = Integer.parseInt(aLine);
                            writer.write("The depth for palindrome of "
                                + aLine + " is: "
                                + depthForPalindrome(aNum) + "  \n");
                        } catch (NumberFormatException error) {
                            writer.write("ERROR: Not a valid number  \n");
                        }
                    }
                }
                // Heading for the recursive Calc Sum function
                writer.write("\nOutput for recCalcSum() function which"
                    + " calculates the triangular number of a \nnumber:\n");
                for (String aline : lines) {
                    if (aline.length() == 0) {
                        writer.write("ERROR: Empty line. \n");
                    } else {
                        try {
                            final int number = Integer.parseInt(aline);
                            final int sum = recCalcSum(number);
                            writer.write("The triangular number of " + number
                                + " is " + sum + "\n");
                        } catch (NumberFormatException error) {
                            writer.write("ERROR: Not a valid number \n");
                        }
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }

    /**
    * This function checks to see if the input is a palindrome or not.
    *
    * @param line This is the line we are checking
    * @param start This is the the index that begins from the start
    * @param end This is the index that begins from the end
    * @return whether or not the line is a palindrome
    */

    public static boolean recIsPalindrome(String line, int start, int end) {
        // This is to check if the string only has one character. If so, then
        // it is a palindrome
        if (start == end) {
            return true;
        } else if (end < start) {
            return true;
            // Checking to see if the first and last characters match. If they
            // don't then it can not be a palindrome, if they do, then we check
            // the next two characters
        } else if (line.charAt(start) != line.charAt(end)) {
            return false;
        } else {
            return recIsPalindrome(line, start + 1, end - 1);
        }
    }

    /**
    * This function takes a number and sees how many times it needs to reverse
    * and add the number before it becomes a palindrome.
    *
    * @param number This is the number to find the depth of
    * @return the depth for the palindrome
    */

    public static int depthForPalindrome(int number) {
        if (recIsPalindrome(
                Integer.toString(number), 0, Integer.toString(
                number).length() - 1)) {
            return 0;
        } else {
            final int newNum = Integer.parseInt(
                recReverse(Integer.toString(number)));
            return depthForPalindrome(number + newNum) + 1;
        }
    }

    /**
    * This is the method reverseRecs the string.
    *
    * @param number This is the line that is to be reversed
    * @return the reversed number
    */

    public static String recReverse(String number) {
        if (number.isEmpty()) {
            return number;
        } else {
            return recReverse(number.substring(1)) + number.charAt(0);
        }
    }
    /**
    * This is the method finds the sum of every number up to a number.
    *
    * @param number This is the number being summed
    * @return the sum
    */

    public static int recCalcSum(int number) {
        // base case checks for the final number needed to add
        if (number <= 1) {
            return number;
        } else {
            // recalling the function with the number below the last
            return recCalcSum(number - 1) + number;
        }
    }
}
