public class DayOneExercises {
    
    /** 
     * Easy exercises
     * 
     * Write a program that divides two integer variables and prints the result
     */

    public void divideIntegers() {
        int a = 3465;
        int b = 3;

        System.out.println(a/b);
    }

    /**
     * Write a program that prints a nice message to the console
     */

    public void niceMessage() {
        System.out.println("Have a great day!");
    }

    /**
     * Write a program that creates an array of numbers and prints out the first one 
     */

    public void arrayOfNumbers() {
        int[] arr1 = {1, 6, 3, 6 , 56};
        System.out.println(arr1[0]);
    }

    /**
     * Medium exercises
     * 
     * Write a program that calculates the sum of the first and last values in the first row of a 2D array of 4 rows and 4 columns
     */

    public void twoDArraySum() {
        int[][] twoDArray = new int[4][4];
        int sum = twoDArray[0][0] + twoDArray[0][3];
    }

    /**
     * Write a program that has an integer variable x and computes the value of (x + (2 * x) + (3 * x)
     */

    public void arithmetic() {
        int x = 34;
        int result = (x + (2 * x) + (3 * x));
    }

    /**
     * Write a program that prints out the first 10 odd numbers (manually)
     */

    public void firstTenOddNumbers() {
        System.out.println("1" + " 3" + " 5" + " 7" + " 9" + " 11" + " 15" + " 17" + " 19" + " 21");
    }

    /**
     * Hard exercises 
     * 
     * Write a program that calculates the sum of all the numbers in an array with 3 items and prints the value to the screen
     */

    public void arraySum() {
        int[] arr1 = {342342, 245435, 897345};
        System.out.println(arr1[0] + arr1[1] + arr1[2]);
    }

    /**
     * Write a program that swaps the values of two variables
     */

    public void swapVariables() {
        int variableOne = 30;
        int variableTwo = 50;
        int temp;

        temp = variableOne;
        variableOne = variableTwo;
        variableTwo = temp;
    }

    /**
     * Write a program that prints out a message with the value of an integer in that message
     * 
     * I may have misinterpreted what this is asking but it seems a bit too easy?
     */

    public void printMessage() {
        System.out.println("Two as a number is " + 2);
    }
}
