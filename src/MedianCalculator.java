public class MedianCalculator {
    public static void main(String[] args) {
        double[] arr = {1.0, 2.3, 1.2, 3.4, 5.5};
        System.out.println(median(arr));
    }

    public static double median (double[] array) {
        // sorting
        int pos;
        double temp;
        for (int i = 0; i < array.length; i++) 
        { 
            pos = i; 
            for (int j = i+1; j < array.length; j++) 
           {
                if (array[j] < array[pos])                  
                {
                    pos = j;
                }
            }

            temp = array[pos];            
            array[pos] = array[i]; 
            array[i] = temp; 
        } 

        // calculating median
        if (array.length % 2 == 0) {
            return (array[array.length/2] + array[array.length/2 - 1])/2;
        }
        return array[array.length/2];
    }
}

