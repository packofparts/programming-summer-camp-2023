
public class Day2Excercises {
    String name = "Bob";
    public static void main(String[] args) throws Exception {

    }

    //Easy excercises 
    //Write a method in java that computes the average of three numbers taken in as parameters
    public double threeNumAverage(int num1, int num2, int num3){
        double result = (num1 + num2 + num3)/3;
        return result;
    }

    //Write a method in java that finds the length of a string taken in as a parameter
    public int stringLength(String str){
        //In this case the .length() function already exists, however in the case that it didn't, here is an alternate method to do so
        /*int count = 0;
        char[] chars = str.toCharArray();
        for (char c : chars){
            count++;
        }
        return count;
        */
        //The kids won't know that though, so this is what they'll probably end up using:
        return str.length();
    }

    //Write a method that prints a nice message on the screen
    public void niceMessage(){
        String[] messages = {"You're great!", "Stay positive", "You bring sunshine to people's days!"};
        int choice = (int)(Math.random()*10)/4;
        System.out.println(messages[choice]);
    }

    //Write a method that assigns parameter values to variables in the method -Wot? - give off setter vibes, that's day 3 remove later
    //Easier lang - create a setter/getter method
    //This example can also add a bit about scope
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    //Medium Excercises
    //Write a method that changes an Array using parameters in the method - change wording later


}
