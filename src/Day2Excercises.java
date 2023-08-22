
public class Day2Excercises {
    public static void main(String[] args) throws Exception {
        evenTilNum(17);
    }

    //Easy excercises 
    //Write a method in java that computes the average of three numbers taken in as parameters
    public double threeNumAverage(int num1, int num2, int num3){
        double result = (num1 + num2 + num3)/3;
        return result;
    }

    //Write a method in java that finds the length of a string taken in as a parameter
    public int stringLength(String str){
        return str.length();
    }

    //Write a method that prints a nice message on the screen
    public void niceMessage(){
        String[] messages = {"You're great!", "Stay positive", "You bring sunshine to people's days!"};
        int choice = (int)(Math.random()*10)/4;
        System.out.println(messages[choice]);
    }

    //Medium Excercises
    //Write a method that changes an Array using parameters in the method - change wording later
    public static void incrementArray(int add, int[] arr){
        for(int i = 0; i < arr.length; i++){ // -> discuss difference bewtwen length and length()
            arr[i] += add;
        }
    }

    //Write a method that returns the smallest of three integer parameters
    public static int findMin(int a, int b, int c){
        if ((a < b) && (b < c)) {
            return a;
        } else if ((b < a) && (b < c)) {
            return b;
        } else {
            return c;
        }
    }

    //Write a method that checks whether a certain person's inputted birth year makes them younger or older than you
    //In this case I'm returning boolesns, but there are many different options for return values here and in other methods as well
    //I'm also only using one parameter because of what the prompt is asking for, but there can also be a comparison bewteen two parameters
    public static boolean olderOrYounger(int userYear) {
        return userYear < 2005; //True if older, and False if younger
                                //This is also an example of boolean zen
    }

    //Hard excercises
    //Write a method that takes in an Array/Array List and prints out all its items
    public static void printList(String[] arr) {
        for (String str : arr) {
            System.out.println(str);
        }
    }

    //Write a method that prints out all the even numbers before an inputted number
    public static void evenTilNum(int num){
        if (num%2 == 1) {
            num++;
        }
        int temp = num;
        while ((num - temp) < num ){
            System.out.println(num-temp);
            temp -= 2;
        }
    }

    //Write a method that calculates the area of a square
    //Lots of assumptions here, but assuming that the method is given an int side length, here's a couple options on how to do it
    public static int squareArea(int side){
        return side*side;
        //return Math.pow((side), 2); is also another form of squaring a value
    }

    //Write a method that uses a WHILE loop to print out all the numbers between an inputted number and its double.
    public static void numCounter(int num) {
        int dub = 2*num; 
        num++;
        while (num < dub){
            System.out.println(num);
        }
    }

    //Challenge problems
    //NOTE: just because this is how these answers were written here does not mean that they are the only correct answers
    //check the length of a string without using the .length() method
    public int stringLengthChallenge(String str) {
        int count = 0;
        char[] chars = str.toCharArray();
        for (char c : chars){
            count++;
        }
        return count;
    }
 
    //checking to see if a word is a palindrome
    public static boolean isPalindrome(String str) {
        for(int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(str.length()-i)) {
                return false;
            }
        }
        return true;
    }




}