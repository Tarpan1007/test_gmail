
package init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.internal.Utils;

import com.login.IndexPage.loginIndexpage;
import com.login.Verification.loginVerification;

public class SeleniumInit  {



	
	public String suiteName="";
	public String testName="";
	/* Minimum requirement for test configuration */
	protected String testUrl; // Test url
	protected String seleniumHub; // Selenium hub IP
	protected String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = "";//Get Current Window handle
	public static String browserName = "";
	public static String osName="";
	public static String browserVersion = "";

	// Variables For Login 
	
	public String userName_Temp = "socialtables2278@mailinator.com";
	
	public String userName_tarpan = "tarpanworld13@gmail.com";
	
	public String pass_tarpan = "taps@107";
	
	
	// screen-shot folder
	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test

	protected static Logger logger = Logger.getLogger("testing");
	protected WebDriver driver;
	//Common Common = new Common(driver);

	/* Page's declaration */
	public loginVerification l_verify;
	public loginIndexpage l_index_page;

	
	
//	public Event_indexpage event_indexpage;
//	public Event_verification event_verify;

	/**
	 * Fetches suite-configuration from XML suite file.
	 * 
	 * @param testContext
	 */
/*	
	@BeforeSuite
	public void setup1() throws IOException, InterruptedException{
	Runtime runtime = Runtime.getRuntime();
	 p1 = runtime.exec("java -jar E:\\Tarpan\\Software\\Selenium_2.47\\Server\\selenium-server-standalone-2.46.0.jar -role hub");

	System.out.println("==================Server started=================");
	Thread.sleep(2000);



	Runtime runtime2 = Runtime.getRuntime();
	p2 =runtime2.exec("java -jar E:\\Tarpan\\Software\\Selenium_2.47\\Server\\selenium-server-standalone-2.46.0.jar -role node -port 5554");
	System.out.println("=======================Node started====================");
	Thread.sleep(2000);
	
	}
	*/
	
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) 
	{
		testUrl = testContext.getCurrentXmlTest().getParameter("selenium.url");
		System.out.println("\n ======"+testUrl+"========= \n");
		seleniumHub = testContext.getCurrentXmlTest().getParameter(
				"selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter(
				"selenium.port");
		targetBrowser = testContext.getCurrentXmlTest().getParameter(
				"selenium.browser");
		
		
	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException 
	 * @throws InterruptedException
	 */

	
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method,ITestContext testContext) throws IOException, InterruptedException {

		
	
		
		String path="";
		if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X"))
	    {
			path="/Users/Nishil/developer/test-automation";
	    }
		else
		{
			path="c:\\Downloads_new";
		}
		File theDir = new File(path);
		  // if the directory does not exist, create it
			if (!theDir.exists()) {
		    System.out.println("creating directory: ");
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		     } catch(SecurityException se){
		        //handle it
		     }        
		     if(result) {    
		       System.out.println("DIR created");  
		     }
		  }
		
		currentTest = method.getName(); // get Name of current test.
		URL remote_grid = new URL("http://" + seleniumHub + ":"
				+ seleniumHubPort + "/wd/hub");

		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";

		test_data_folder_path = new File(TESTDATA_FOLDER_NAME)
				.getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME)
				.getAbsolutePath();
		
		DesiredCapabilities capability = null;
		if (targetBrowser == null || targetBrowser.contains("firefox")) 
		{
			FirefoxProfile profile = new FirefoxProfile();
			if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X"))
		    {
				path="/Users/Nishil/developer/test-automation";
		    }
		    else
		    {
		    	 path="c:\\Downloads_new";
		    }
			
			profile.setPreference("dom.max_chrome_script_run_time", "999");
			profile.setPreference("dom.max_script_run_time", "999");
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", path);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/msword,application/csv,text/csv,image/png,image/jpeg,application/pdf,text/html,text/plain,application/octet-stream");
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download,manager.focusWhenStarting",false);
			//profile.setPreference("browser.download.useDownloadDir",true);
			profile.setPreference("browser.helperApps.alwaysAsk.force",false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen",false);
			profile.setPreference("browser.download.manager.closeWhenDone",false);
			profile.setPreference("browser.download.manager.showAlertOnComplete",false);
			profile.setPreference("browser.download.manager.useWindow",false);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting",false);
			profile.setPreference("pdfjs.disabled", true);
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.OCSP.enabled", 0);
			profile.setEnableNativeEvents(false);
			profile.setPreference("network.http.use-cache", false);
						
			//added Dependancy to disable hardware acceleration.
			
			/*profile.setPreference("gfx.direct2d.disabled",true);
			profile.setPreference("layers.acceleration.disabled", true);*/
			
			profile.setPreference("gfx.direct2d.disabled",true);
			profile.setPreference("layers.acceleration.disabled", true);
			//profile.setPreference("webgl.force-enabled", true);
		//	Proxy proxy = new Proxy().setHttpProxy("localhost:3129");
	        
	        //cap.setCapability(CapabilityType.PROXY, proxy);
																
			capability = DesiredCapabilities.firefox();
			// proxy code
		//	capability.setCapability(CapabilityType.PROXY,proxy);
			capability.setJavascriptEnabled(true);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			browserName = capability.getBrowserName();
			osName = System.getProperty("os.name");
			browserVersion = capability.getVersion().toString();
			
			System.out.println("========="+"firefox Driver "+"==========");
		} else if (targetBrowser.contains("ie9")) {

			capability = DesiredCapabilities.internetExplorer();
			capability.setPlatform(Platform.ANY);
			capability.setBrowserName("internet explorer");
			// capability.setVersion("8.0");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("chrome")) {

			capability = DesiredCapabilities.chrome();
			File file = new File("/Users/Nishil/Documents/workspace/socialtables_Sandbox/lib/chromedriver");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			
			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("ie9")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		}else if (targetBrowser.contains("safari")) {
			
		//System.setProperty("webdriver.safari.driver","/Users/jesus/Desktop/SafariDriver.safariextz");
		//driver = new SafariDriver();
			SafariDriver profile = new SafariDriver();	
		
			capability = DesiredCapabilities.safari();
			
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			//capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY, profile);
			this.driver = new SafariDriver(capability);
		}
		else if (targetBrowser.contains("saucelab")) {
			
			capability = DesiredCapabilities.firefox();
			capability.setCapability("platform", "Windows 7");
			capability.setCapability("name", "Tarpan_firstcry");
			capability.setCapability("version", "40.0");
			driver = new RemoteWebDriver(new URL("http://Tarpan:106559c4-02d3-4819-a7c4-e4c6de5bd61a@ondemand.saucelabs.com:80/wd/hub"),capability);
		}
		
		
		driver = new RemoteWebDriver(remote_grid, capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(testUrl);
		driver.manage().window().maximize();
		currentWindowHandle = driver.getWindowHandle();
		System.out.println("Current Window Handle ID:--->"+currentWindowHandle);
		
		suiteName  = testContext.getSuite().getName();
		System.out.println("Current Xml Suite is:---->"+suiteName);
		
		
		
		//Page Object Initialization According To Its Test Suite.
		
		l_verify = new loginVerification(driver);
		l_index_page = new loginIndexpage(driver);
	
		
//		event_verify = new Event_verification(driver);
//		event_indexpage = new Event_indexpage(driver);
	
	} 
	
	/**
	 * Log For Failure Test Exception.
	 * @param tests  
	 */
	private void getShortException(IResultMap tests) {

        for (ITestResult result : tests.getAllResults()) {
            
            Throwable exception = result.getThrowable();
            List<String> msgs = Reporter.getOutput(result);
            boolean hasReporterOutput = msgs.size() > 0;
            boolean hasThrowable = exception != null;
            if (hasThrowable) {
                boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
                if (hasReporterOutput) {
                    log("<h3>"
                            + (wantsMinimalOutput ? "Expected Exception"
                                    : "Failure Reason:") + "</h3>");
                }

                // Getting first line of the stack trace
                String str = Utils.stackTrace(exception, true)[0];
                Scanner scanner = new Scanner(str);
                String firstLine = scanner.nextLine();
                log(firstLine);
            }
        }
    }
	
	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {
		
		ITestContext ex = testResult.getTestContext();
		
		try {
			 testName = testResult.getName();
			if (!testResult.isSuccess()) {

			//	 Print test result to Jenkins Console 
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: "
						+ testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);

			//	 Make a screenshot for test that failed 
				String screenshotName = Common.getCurrentTimeStampString()
						+ testName;
				Reporter.log("<br> <b>Please look to the screenshot - </b>");
			//	Common.makepassScreenshot(driver, screenshotName);
			//	Common.makefailScreenshot(driver, screenshotName);
				//Reporter.log(testResult.getThrowable().getMessage());
				getShortException(ex.getFailedTests());
			} 
			else 
			{
				try
				{
					driver.findElement(By.xpath(".//*[@id='main-header']/div/div[2]/div[1]/a/span")).click();
					Common.pause(2);
					driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
				}
				catch(Exception e)
				{
					log("<br></br> Not able to perform logout");
				}
				
				System.out.println("TEST PASSED - " + testName + "\n"); // Print test resule to Jenkins Console
			}
			System.out.println("here is test status--------------------"+testResult.getStatus());

			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
			
			/*p2.destroy();
			System.out.println("======Node Disconnect========");
			p1.destroy();
			System.out.println("====== Hub Close========");*/
			
		
		} catch (Exception throwable) {
				System.out.println("message from tear down"+throwable);
		}
	}
	
	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public static void log(String msg) {
		Reporter.log("<br></br>"+msg);
	}

}
