
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
		UrlValidator urlValTrue = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		try {
			String[] validURL =	new String[]{"http://www.google.com:80/test1?action=viewvalidity","http://go.com:80/test1?action=viewvalidity","http://go.au:80/test1?action=viewvalidity",
																			 "http://255.com:80/test1?action=viewvalidity", "http://go.com:65535/test1?action=edit&mode=up"};

			for(int n = 0; n < validURL.length; n++){
				boolean resultTrue = urlValTrue.isValid(validURL[n]);
				if(resultTrue)
						System.out.println("Failed Manual Assertion: " + validURL[n]);
			}

			String[] invalidURL =	new String[]{ "http://go.a1a:80/test1?action=viewvalidity","http://go.1aa:80/test1?action=viewvalidity","http://:80/test1?action=viewvalidity",
																					"http://go.com:-1/test1?action=viewvalidity","http://go.com:65a/test1?action=viewvalidity"};

			for(int n = 0; n < validURL.length; n++){
				boolean resultTrue = urlValTrue.isValid(validURL[n]);
				if(resultTrue)
						System.out.println("Failed Manual Assertion: " + validURL[n]);
			}

		} catch (Exception e) {
				System.out.println("Caught exception");
		}
	}

	public static void runTests(String[] valid, String[] test, int element, boolean result){

 	 UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
 	 for (int i = 0; i < test.length; i++){
 		 try {
 			 StringBuilder testURL = new StringBuilder();
 			 for(int n = 0; n < valid.length; n++){
 				 if(n == element){
 					 testURL.append(test[i]);
 				 }else{
 					 testURL.append(valid[n]);
 				 }
 			 }
 			 String url = testURL.toString();
 			 boolean isValid = urlVal.isValid(url);
 			 if(isValid != result)
 					 System.out.println("Failed Assertion: " + url + "\tProblem: " + test[i] + " Result: " + isValid);
 		 }	catch (Exception e){
 			 System.out.println("Caught runTests excption");
 		 }
  }
 }

	/**Partition Testing of isValid method.****/
	@Test
	public void PartitionTest() {

		       String[] valid = new String[] {"http://","go.com",":80","/test1","?action=view"};

		       //Valid Scheme
		       String[] validScheme = new String[] {"http://", "ftp://", "h3t://"};
		       runTests(valid, validScheme, 0, true);
		       String[] invalidScheme = new String[] {"http:/", "http:","http/", "3ht://","://", ""};
		       runTests(valid, invalidScheme, 0, false);

		       String[] validAuthority = new String[] {"www.google.com", "go.com", "go.au", "255.com"};
		       runTests(valid, validAuthority, 1, true);
		       String[] invalidAuthority = new String[] { "go.a", "go.a1a", "go.1aa", ""};
		       runTests(valid, invalidAuthority, 1, false);

		       String[] validPort = new String[] {":80",":65535",":0",""};
		       runTests(valid, validPort, 2, true);
		       String[] invalidPort = new String[] {":-1",":65a"};
		       runTests(valid, invalidPort, 2, false);

		       String[] validPath = new String[] {"/test1/","/test1/file","/#","/#/file", "/#//file","/$23","/$23/file",""};
		       runTests(valid, validPath, 3, true);
		       String[] invalidPath = new String[] {"/..","/../","/../file","/..//file", "/test1//file"};
		       runTests(valid, invalidPath, 3, false);

		       String[] validQuery = {"?action=edit&mode=up", "SDF#$DFSEW",""};
		       runTests(valid, validQuery, 4, true);

		       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

		       try{
		         String[] regs = {""};
		         RegexValidator testReg = new RegexValidator(regs);
		         boolean check1 = urlVal.isValid("http://www.google.com/");
		         fail();
		       } catch (IllegalArgumentException e) {
		         System.out.println("Successfully Caught IllegalArgumentException");
		       }
		}

	public static String SelectRandomScheme(Random random){
        String[] methodArray = new String[] {"http://","ftp://","://","","h3t://","http//","file://"};// List of test Schemes
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n] ; // return random scheme
    }


	public static String SelectRandomAuthority(Random random){
        String[] methodArray = new String[] {"go.com","go.com","go.au","0.0.0.0","","","255.255.255.255"};// List of test authorities
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n] ; // return random authority
    }


	public static String SelectRandomPort(Random random){
        String[] methodArray = new String[] {"/:80","/:80","/:80","/:65535","/:0","","/:-1"};// List of test ports
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n] ; // return random port
    }


	public static String SelectRandomPath(Random random){
        String[] methodArray = new String[] {"/test1","/$23","/$23","/..","/test1/","","/test1/file"};// List of test paths
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n] ; // return random path
    }


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
				UrlValidator urlVal = new UrlValidator(null, null, 1); //new UrlValidator(null, null, 1)
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
			 		if(check2 == true)
						System.out.println("Valid URL: " + testURL);
		}
		System.out.println("Done Testing isValid...");
	 }
}

