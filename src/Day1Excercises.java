public class Day1Excercises {
    public static void main(String[] args) {
        //Easy excersises 
        //Write a program that divides two integer variables and prints the result
        int a = 6;
        int b = 3;
        int c = a/b;
        System.out.println(c);

        //Write a program that prints a nice message to the console
        System.out.println("You're awesome!");

        //Write a program that creates an array of numbers and prints out the first value
        int[] myArray = {0, 1, 2, 3, 4};
        System.out.println(myArray[0]);

        //Medium excercises 
        //Write a program that calculates the sum of the first and last values in the first row of a 2D array of 4 rows and 4 columns
        int[][] my2DArray = {{0, 1, 2, 3}, {2, 3, 4, 5}, {4, 5, 6, 7}, {6, 7, 8, 9}};
        int sum = my2DArray[0][0] + my2DArray[0][3];
        System.out.println(sum);

        //Write a program that has an integer variable x and computes the value of (x + (2 * x) + (3 * x)
        int x = 4;
        System.out.println(x + (2*x) + (3*x));

        //Write a program that prints the first ten odd numbers (manually)
        System.out.println("1");
        System.out.println("3");
        System.out.println("5");
        System.out.println("7");
        System.out.println("9");

        //Hard Excercises
        //Write a program that calculates the sum of all the numbers in an array with 3 items and prints the value to the screen 
        int[] array3Numbers = {2, 4, 6};
        sum = array3Numbers[0] + array3Numbers[1] + array3Numbers[2]; //The reason why sum isn't declared an int here is because I'm
                                                                      //reusing the sum variable we used for the 2D array excercise earlier
        System.out.println(sum);                                      //and so because sum isn't a new varibale, it's type doesn't need to be stated

        //Write a program that swaps the values of two variables
        int d = 7;
        int e = 3;
        int temp = d;
        d = e;
        e = temp;

        //Write a program that prints out a message with the value of an integer in that message (this one is a little tricky)
        int value = 8;
        System.out.println("The value is equal to " + value);

    }


}
