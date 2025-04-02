package com.recursion;
import java.util.logging.Logger;
public class FactorialCalculator {
	private static final Logger logger = Logger.getLogger(FactorialCalculator.class.getName());


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int number = 5;
        logger.info("Starting factorial calculation for: " + number);
        int result = factorial(number);
        logger.info("Factorial of " + number + " is: " + result);
    }

    public static int factorial(int n) {
        logger.info("Entering factorial(" + n + ")");
        if (n == 0 || n == 1) {
            logger.info("Base case reached: factorial(" + n + ") = 1");
            return 1;
        }
        int result = n * factorial(n - 1);
        logger.info("Returning factorial(" + n + ") = " + result);
        return result;
    }


	}


