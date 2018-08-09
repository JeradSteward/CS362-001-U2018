
package finalprojectB;

import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import static org.junit.Assert.*;



/****Unit tests for UrlValidator isValid method.****/
public class UrlValidatorTest {

	/**Manual Testing of isValid method.****/
	@Test
	public void ManualTest() {
		
	}
	
	/**Partition Testing of isValid method.****/
	@Test 
	public void PartitionTest() {
		 
	}
	
	/****Pick Random Scheme****/
	public static String SelectRandomScheme(Random random){
        String[] methodArray = new String[] {"http://","ftp://","://","","h3t://","http//","file://"};// List of test Schemes
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return random scheme
    }

	/****Pick Random Authority****/
	public static String SelectRandomAuthority(Random random){
        String[] methodArray = new String[] {"go.com","go.com","go.au","0.0.0.0","","","255.255.255.255"};// List of test authorities
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return random authority
    }
	
	/****Pick Random Port****/
	public static String SelectRandomPort(Random random){
        String[] methodArray = new String[] {"/:80","/:80","/:80","/:65535","/:0","","/:-1"};// List of test ports
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return random port
    }
	
	/****Pick Random Path****/
	public static String SelectRandomPath(Random random){
        String[] methodArray = new String[] {"/test1","/$23","/$23","/..","/test1/","","/test1/file"};// List of test paths
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return random path
    }
	
	/****Pick Random Query****/
	public static String SelectRandomQuery(Random random){
        String[] methodArray = new String[] {"?action=view","?action=edit&mode=up","","?what"};// List of test Querys
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return random Query
    }
	private static final int NUM_TESTS = 500;
	
	/**Generate Random Tests that tests isValid method.****/
	@Test
	public void ProgramTest()  throws Throwable  {
		
		System.out.println("Start Testing isValid...");
		System.out.println("");
		long randomseed = System.currentTimeMillis();
		Random random = new Random(randomseed);
		for (int i = 0; i < NUM_TESTS; i++) {
			UrlValidator urlVal = new UrlValidator(null, null, 1);
			boolean check1,check2;
				
			StringBuilder testBuffer = new StringBuilder();
			String scheme = SelectRandomScheme(random);
		    String authority = SelectRandomAuthority(random);
		    String port = SelectRandomPort(random);
		    String path = SelectRandomPath(random);
		    String query = SelectRandomQuery(random);
		    if(i > 400) {
		        scheme = "http://";
		    }
		    if(i > 420) {
		        authority = "google.com";
		    }
			if(i > 440) {
				port = ":80";       	
			}
			if(i > 460) {
				path = "/test";
			}
		    testBuffer.append(scheme);
		    testBuffer.append(authority);
		    testBuffer.append(port);
		    testBuffer.append(path);
		    testBuffer.append(query);

		    String testURL = testBuffer.toString();
		    if(i == 255) {
		        testURL = null;
		    }
		        
		    /****Assertion****/
		    if (scheme == "file://" && authority == "" && port != "/:-1" && path != "/..") { // Special case - file: allows an empty authority
		        check1 = true;
		     }
		     else {
		    	 try{
		    		 check1 = urlVal.isValid(testURL);
		    	 }catch(NullPointerException e){check1 = false;}
		     }
		     try{
	    		 check2 = urlVal.isValid(testURL);
	    	 }catch(NullPointerException e){check2 = false;}
		     System.out.println("Test "+ (i+1) + " of "+NUM_TESTS);
		     if(check1 != check2) {
			     System.out.println("Failure using this URL: "+testURL);
			     System.out.println("");
			 }
		}
		System.out.println("Done Testing isValid...");
	 }
}