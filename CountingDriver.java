/*
 * Jason James Pollman
 * ITCS-2215-001
 * Programming Project 1
 */

import java.util.*;
import java.io.*;

/*
 * Driver class for Counting.java
 */
class CountingDriver {
	public static void main(String args[]) throws CountingException, IOException {
		// Check to make sure that the program is passed at least 2 arguments.
    if(args.length < 2) {
			throw new CountingException("Please Enter 2 Arguments.\n1. The location of the input file.\n2. The location of the output file.");
		}

    // Create new Counting object.
    Counting obj = new Counting(args[0], args[1]);

    // Read input file.
    obj.preprocess();
    // Sort input array.
    obj.insertionSort();
    // Perform <= comparison with first integer of input file and sorted input array and print it to the screen.
    System.out.println("There are " + obj.count() + " integers less than or equal to " + obj.getQueryInt() + ".");
    // Write the output file.
    obj.writeOutput();

    System.out.println("The data has been processed and written to \"" + args[1] + "\".");
	}
}

