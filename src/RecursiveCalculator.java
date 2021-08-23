import java.util.Scanner;


public class RecursiveCalculator
{


    public static String[] exp;     //an array to keep every part of the equation together
    public static int pointer;  //tells us which portion we are currently looking at



    public static int parseExpression()         // an addition only checker
    {

        int number = parseMultiplicationExpression(); //FIRST, go up a level to the MultiplicationExpression function
                                //
            // pointer already forward at this point

        if (!exp[pointer].equals("+"))   // BASE CASE if the pointer is NOT an addition sign (either being a '*', '**' or '=' in this case), return number
        {
            return number;
        }
            else {
                pointer += 1;       //move pointer to next token
                int secondNumber = parseExpression();   // using second number to add to first if it is an addition sign
                return number + secondNumber;
                }

    }





    public static int parseMultiplicationExpression()
    {

       int number = parsePowerExpression();


        // base case or recursive? recursive if next is a *
            if (!exp[pointer].equals("*"))  // if it's not a multiplication sign like we're looking for
            {
                return number;          //just go back to the equation and then check if it's addition or if we're done
            }
            else
                {
                pointer++ ;   // move past the *
                int secondNumber = parseMultiplicationExpression();
                return number * secondNumber;
            }
    }


    public static int parsePowerExpression()
    {

       int  number = Integer.parseInt(exp[pointer]);  //inner-most handles parsing the number first

          pointer++; // inner-most expression moves the pointer along


        if (!exp[pointer].equals("**"))
        {   // token can be more than one char in a row to be recognized, in this case '**' works
            return number;
        }
        else
            {
                pointer++;  //moves past the '**' and does the powerExp calculation for the number after the '**', returns the power of those numbers
            int secondNumber = parsePowerExpression();
            return (int) Math.pow(number, secondNumber);
            }


    }


    public static void main (String[] args) {

        //String input = "25 + 3 + 14 + 39 + -8 + 17 = ";
        //String input = "2 * 3 ** 2 + 4 ** 2 * 6 + 8 = ";

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        exp = input.split(" ");
        System.out.println(parseExpression());

    }

}
 /*


an "expression" is:
	base case:  a multiplication-expression =
	recursive case:  a multiplication-expression + an expression

a "multiplication expression" is:
	base case:  a power-expression
	recursive case:  a power-expression * a multiplication-expression

a "power-expression" is:
	base case:  a number
	recursive case:  a number ** a power-expression


    5 + 2 * 3 + 1 =


        exp( 5 + 2 * 3 + 1):
            multexp( 5 + 2 * 3 + 1)
                5 becomes the base case for multcase because what's after 5 is a + and multiexpression can't handle addition so it's done

                base case, return 5

                5
                +, recursive case for exp()
                so pulls out the + and calls exp() again.
                exp (2*3+1)
                    multexp(2*3+1)
                    2
                    * means recursive case, so we call multexp() again
                    multexp(3+1)
                    3
                    base case, return 3;

                    now track backwards,
                    3
                    2*3 = return 6;
                 6
                 exp(1 =)
                        multexp(1=)
                            base case 1, return 1
                            1
                            6+1=7, return 7
                 5+7=12 return 12, done



//use exponent Math.pow() for exponent


     */