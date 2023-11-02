//Solve For X Program//
//Symopsis: Ask the user for a number of problems that you are going to ask, the number has to be withing 1 and 10, than ask the user for 
// minimum and maximum alues they want want for the coefficient, b, and the number the equation equates to. After that create the coefficient value,
// b value, and equate value for each equation within the set min and max given by the user and create a mx + b = c equation for the number of problems
//.Ask the user for the answer for each problem, then print the right answer for the given equation and if the answer given by the user is 
//correct. Finally print the number of questions that the user got correct, incorrect, and the percentage of problems they got correct.
//Lila Hilizah//
//References: Jalen Smith//
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class SolveForX {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("How many problems do you want? ");
        int numproblems = sc.nextInt();
        //Continues to ask the user for a number of problems
        while(!(numproblems >= 1 && numproblems <= 10)){
            System.out.print("How many problems do you want? ");
            numproblems = sc.nextInt();
        }
        Random r = new Random();
        //Creates a new arraylist for each component of each equation
        System.out.print("Enter minimum coefficient: ");
        int MIN_COEFF = sc.nextInt();
        System.out.print("Enter maximum coefficient: ");
        int MAX_COEFF = sc.nextInt();
        System.out.print("Enter minimum value: ");
        int MIN_VAL = sc.nextInt();
        System.out.print("Enter maximum value: ");
        int MAX_VAL = sc.nextInt();
        System.out.print("Enter minimum equals: ");
        int MIN_EQ = sc.nextInt();
        System.out.print("Enter maximum equals: ");
        int MAX_EQ = sc.nextInt();
        ArrayList <Integer> coeff = generateRandomArray(numproblems, MIN_COEFF, MAX_COEFF);
        ArrayList <Integer> vals = generateRandomArray(numproblems, MIN_VAL, MAX_VAL);
        ArrayList <Integer> eq = generateRandomArray(numproblems, MIN_EQ, MAX_EQ);
        // 1 is addition and 2 is subtraction
        ArrayList <Integer> signs = generateRandomArray(numproblems, 1, 2);
        //Converts the random + or - sign to an actual + or minus
        ArrayList <String> sign = new ArrayList(signs.size());
        //Gets the answers for each of the equations
        ArrayList<Double> allTheAnswers = generateAnswers(coeff, vals, signs, eq);
        ArrayList <Double> c = new ArrayList(allTheAnswers.size());
        ArrayList <Boolean> compare = new ArrayList(allTheAnswers.size());
        for(int b = 0; b < signs.size(); b++){
            if(signs.get(b) == 1){
                sign.add(b, "+");
            }
            else{
                sign.add(b, "-");
            }
        }
        for(int l = 0; l < numproblems; l++){
            //The number at the l index
            System.out.printf("%dx %s %d = %d%n", coeff.get(l),sign.get(l),vals.get(l),eq.get(l));
            System.out.printf("What is x? ");
            //getting the users guess for each of the problems
            Double userguess = sc.nextDouble();
            c.add(l, userguess);
        }
        //Getting if the users answers is wrong or not and adding that to the resultant arraylist
        ArrayList<String> convert = new ArrayList(compare.size());
        for(int q = 0; q < allTheAnswers.size(); q++){
            compare.add(q,compareAnswer(c.get(q), allTheAnswers.get(q)));
            if(compare.get(q) == true){
                convert.add(q, "Correct");
            }else{
                convert.add(q,"Incorrect");
            }

        }

        for(int t = 0; t < numproblems; t++){
            System.out.printf("%dx %s %d = %d          x = %f     %s%n", coeff.get(t),sign.get(t),vals.get(t),eq.get(t),allTheAnswers.get(t),convert.get(t));
        }
        //Getting the number of problems that the got correct and incorrect out of their guesses and converting that to their score
        int cap = 0; 
        for(int g = 0; g < compare.size();g++){
            if(compare.get(g) == true){
            cap += 1;
            }
        }
        cap /= compare.size();
        int turo = cap;
        cap *=100;
        int wrong = compare.size()- turo;
        System.out.printf("Num correct   = %d%nNum incorrect = %d%nYour score   = %d%%",turo,wrong,cap);
            
    
        }
    static ArrayList<Integer> generateRandomArray(int size, int minValue, int maxValue){
        Random r = new Random();
        ArrayList<Integer> arr = new ArrayList(size);
        for(int i = 0; i < size; i++){
            //Random number within the min and max value given for each component
            //Since the 2nd number is exclusive
            arr.add(r.nextInt(maxValue - minValue + 1) + minValue);
        }
        return arr;
    }
    static double generateAnswer(int coeff, int value, int sign, int equals){
        double RightSide =0;
        //addition
        if(sign == 1){
            RightSide = equals;
            //divide
            RightSide -= value;
        }
        else{
            //Subtraction
            RightSide = equals;
            //divide
            RightSide += value;
        }
        RightSide /= coeff;
        return RightSide;
    }
    static ArrayList<Double> generateAnswers(ArrayList<Integer> coeffs, ArrayList<Integer> values, ArrayList<Integer> signs, ArrayList<Integer> equals){
        ArrayList<Double> ans = new ArrayList(coeffs.size());
        //Going through each equation and determining the answer for each of the problems
        for(int k = 0; k < coeffs.size();k++){
            ans.add(k,generateAnswer(coeffs.get(k), values.get(k), signs.get(k), equals.get(k))); 
        }
        return ans;
    }
    static boolean compareAnswer(double left, double right){
        //Epsilon value
        return Math.abs(left - right) < 0.01;
    }
}
