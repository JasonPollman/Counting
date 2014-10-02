/*
 * Jason James Pollman
 * ITCS-2215-001
 * Programming Project 1
 */

import java.io.*;
import java.util.*;

class Counting {
	private int[] inputArr; // Stores the file input data.
	private int queryInt, queryCount; // The integer to be compared to, The number of ints <= queryInt.
  private File inputFile, outputFile; // The File objects for the input and output files.

  /*
   * @inputFile: The sting filename for the input file containing the list of integers.
   * @outputFile: The location to write the output file.
   */
  public Counting(String inputFile, String outputFile) throws CountingException, IOException {
    this.inputFile = new File(inputFile);
    this.outputFile = new File(outputFile);

    if(!this.inputFile.exists()) { throw new CountingException("\"" + inputFile + "\" is not a valid input file."); }
  }

  /*
   * Reads in a file and extracts the ints into an array (this.inputArr)
   */
	public void preprocess() throws IOException {
    Scanner input = new Scanner(inputFile);

    // Compute the line count for the file to determine the size of this.inputArr.
    int lineCount = 0;
    while(input.hasNext()) {
      lineCount++;
      input.nextLine();
    }

    // Reset pointer to beginning of input file, then set first int to this.queryInt.
    input = new Scanner(inputFile);
    this.queryInt = input.nextInt();

    this.inputArr = new int[lineCount-1];
    for(int i = 0; i < lineCount-1; i++) {
      this.inputArr[i] = input.nextInt();
    }

    input.close();
	}

  /*
   * Sorts this.inputArr using insertion sort.
   */
	public void insertionSort() {
    int v, j;
    for(int i = 1; i < this.inputArr.length; i++) {
      v = this.inputArr[i];
      j = i-1;

      while(j >= 0 && this.inputArr[j] > v) {
        this.inputArr[j+1] = this.inputArr[j];
        j -= 1;
      }

      this.inputArr[j+1] = v;
    }
	}

  /*
   * Determines the number of ints in this.inputArr that are <= this.queryInt
   */
	public int count() {
    // Define upper and lower bounds for integer comparison checking.
    int lowerBound, upperBound;

    // Check if all or none of the sorted integer array is <= this.queryInt
    if(inputArr[0] > this.queryInt) { this.queryCount = 0; return 0; }
    if(inputArr[inputArr.length-1] <= this.queryInt) { this.queryCount = inputArr.length; return inputArr.length; }

    // Divide the this.inputArr in half and determine which half of the array to compare to this.queryInt.
    if(inputArr[(inputArr.length/2)-1] <= this.queryInt) {
      lowerBound = (inputArr.length/2);
      upperBound = inputArr.length;
    }
    else {
      lowerBound = 0;
      upperBound = (inputArr.length/2);
    }

    // Perform comparison of this.inputArr[i] and this.queryInt based on upper and lower bounds.
    for(int i = lowerBound; i < upperBound; i++) {
      if(this.inputArr[i] > this.queryInt) {
        this.queryCount = i;
        break;
      }
    }
		return this.queryCount;
	}

  /*
   * Writes the value of this.queryCount to the first line of the file (this.outputFile),
   * then writes the sorted elements of this.inputArr one per line.
   */
	public void writeOutput() throws IOException {
    if(this.outputFile.getParentFile() != null) { 
      outputFile.getParentFile().mkdirs();
    }
    PrintWriter output = new PrintWriter(this.outputFile);
    output.println(queryCount);

    for(int i = 0; i < inputArr.length; i++) {
      output.println(inputArr[i]);
    }

    output.close();
	}

	// Getters, Setters
	public int[] getInputArr() { return this.inputArr; }
	public int getQueryInt() { return this.queryInt; }
	public int getQueryCount() { return this.queryCount; }
  public File getInputFile() { return this.inputFile; }
  public File getOutputFile() { return this.outputFile; }

	public void setInputArr(int[] inputArr) { this.inputArr = inputArr; }
	public void setQueryInt(int queryInt) { this.queryInt = queryInt; }
	public void setQueryCount(int queryCount) { this.queryCount = queryCount; }
}