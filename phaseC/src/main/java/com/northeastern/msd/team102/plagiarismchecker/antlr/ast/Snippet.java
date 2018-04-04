package com.northeastern.msd.team102.plagiarismchecker.antlr.ast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @author mrunal
 * The class is responsible to generate
 * similar chunk of code among two different python programs.
 * reference for Longest Common subsequence : https://www.geeksforgeeks.org/print-longest-common-substring/
 *
 */
public class Snippet {

    /**
     *
     * @param file python file
     * @return ArrayList ProgramLines -> array of lines in the python file. 
     * @throws IOException
     */
    public  List<String> fileToList(File file) throws IOException {

        ArrayList<String>programLines = new ArrayList();
        String pyLine;
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        try {
            while ((pyLine = br.readLine()) != null) {
            	if ((pyLine.length()) != 0 && isComment(pyLine)) {
            		programLines.add(pyLine);
            	}
                }
            } catch (IOException e) {
            	e.printStackTrace();
        } finally {
            br.close();
        }
        return programLines;
    }

    /**
     *
     * @param pyLine String to check.
     * @return true if String passed is not a comment.
     */   
    private Boolean isComment(String pyLine) {
        String comment = "'''";
        String oneLineComment = "#";
        return (!((pyLine.startsWith(comment)) || (pyLine.startsWith(oneLineComment))));

    }
    /**
     * 
     * @param file1Strings List of lines in given python file.
     * @param file2Strings List of lines in suspected python file.
     * @return ArrayList of File lines which are suspected to be plagiaried.
     * @throws IOException 
     */
    public List<Map<Integer, String>> findSimilarLines(File file1, File file2) throws IOException {
    	List<String> file1Strings = fileToList(file1);
    	List<String> file2Strings = fileToList(file2);
    	List<Map<Integer, String>> similarFiles = new ArrayList<>() ;
    	Map<Integer,String> similarLines1 = new HashMap<>();
    	Map<Integer,String> similarLines2 = new HashMap<>();
    	
    	if(file1Strings == null || file2Strings == null)
    		return similarFiles;
    	int lineNo1 = 1;
    	for(String file1Line : file1Strings) {
    		int maxSimilarLength = 0;
    		int lineNo2=1;
    		for (String file2Line : file2Strings) {
    			String snippet =LCS.lcsSubString(file1Line, file2Line);
    			if(snippet.length() > maxSimilarLength && 
    					(snippet.length() >= file1Line.length() * 0.5) && 
    						(snippet.length() >= file2Line.length() * 0.5)) {
    				if(!similarLines1.containsKey(lineNo1)){
    					similarLines1.put(lineNo1, file1Line);
    				}
    				similarLines2.put(lineNo2, file2Line);
    				maxSimilarLength=snippet.length();
    			}
    			lineNo2++;
    		}
    		lineNo1++;
    	}
    	
    	similarFiles.add(similarLines1);
    	similarFiles.add(similarLines2);
    	
    	return similarFiles; 	
    }
    
    public String generateHtmlDiv(Map<Integer,String> similarLines) throws IOException {
    
    	StringBuilder htmlStrings=new StringBuilder();
    	htmlStrings=htmlStrings.append("<div><p style=\"white-space :pre-wrap ;color :red\">");
    	for (Entry<Integer, String> entry : similarLines.entrySet()) {
           htmlStrings.append(entry.getKey()+entry.getValue()+"<br>");
		}
    	htmlStrings.append("</p></div>");
		return htmlStrings.toString();
    	
    }
    
    public String[] generateSnippets(File file1, File file2) throws IOException {
	
    	List<Map<Integer, String>> similarFiles=findSimilarLines(file1, file2);
    	Map<Integer, String> similarLines1=similarFiles.get(0);
    	Map<Integer, String> similarLines2=similarFiles.get(1);
    	
    	String htmlStrings1=generateHtmlDiv(similarLines1);
    	String htmlStrings2=generateHtmlDiv(similarLines2);
		
    	String[] detailedReport=new String[2];
    	detailedReport[0]=htmlStrings1;
    	detailedReport[1]=htmlStrings2;
    	
    	return detailedReport; 
    }   
}