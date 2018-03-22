package com.northeastern.msd.team102.plagiarismchecker.antlr.ast;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

/**
 * 
 *Comparison Strategy for Comparing two ASTs for python files.
 *
 */
public class CompareStrategyHashMapTest {

	/**
	 * Comparison of samplepythonFile1.py and SamplePythonFile2.py.
	 * @throws IOException
	 */
	@Test
	public void test_compareAST1withAST2Boundary1() throws IOException {
		CompareStrategy c = new CompareStrategyHashMap();
	    File file1=new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/SamplePythonFile1.py");
		byte[] encodedFile1 = Files.readAllBytes(file1.toPath());
	    File file2=new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/SamplePythonFile2.py");
		byte[] encodedFile2 = Files.readAllBytes(file2.toPath());
	    assertEquals(94.00, c.compareFiles(encodedFile1, encodedFile2),2);
	}
	
	/**
	 * Comparison of samplepythonFile1.py and SamplePythonFile2.py.
	 * @throws IOException
	 */
	@Test
	public void test_compareAST1withAST2() throws IOException {
		CompareStrategy c = new CompareStrategyHashMap();
		File file1 = new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/SamplePythonFile1.py");
		byte[] encodedFile1 = Files.readAllBytes(file1.toPath());
	    File file2 = new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/SamplePythonFile2.py");
		byte[] encodedFile2 = Files.readAllBytes(file2.toPath());
	    assertEquals(72.00, c.compareFiles(encodedFile2, encodedFile1),2);
	}
	
	/**
	 * Comparison of samplepythonFile1.py and calc.py.
	 * @throws IOException
	 */
	@Test
	public void test_compareAST1withAST2Boundary2() throws IOException {
		CompareStrategy c = new CompareStrategyHashMap();
		File file1=new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/SamplePythonFile1.py");
		byte[] encodedFile1 = Files.readAllBytes(file1.toPath());
	    File file2=new File("src/main/java/com/northeastern/msd/team102/plagiarismchecker/samplepython/calc.py");
		byte[] encodedFile2 = Files.readAllBytes(file2.toPath());
	    assertEquals(8.00, c.compareFiles(encodedFile2, encodedFile1),2);
	}	
	
}
