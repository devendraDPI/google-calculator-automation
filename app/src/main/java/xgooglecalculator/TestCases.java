package xgooglecalculator;

import java.io.File;
import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        System.out.println("Start Tests: TestCases");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        File theDir = new File("logs");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "logs" + File.separator + "chromedriver.log");

        driver = new ChromeDriver(options);

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void endTest() {
        System.out.println("End Tests: TestCases");
        driver.quit();
    }

    /** <STRONG>Test Case 01</STRONG>: Verify the Homepage URL and Initial Display <p>
     *  <STRONG>Description</STRONG>: Verify that the Google Calculator is loaded and the initial display shows zero (i.e. 0) <p>
     *  <STRONG>Expected Result</STRONG>: The Google Calculator should be loaded, and the initial display should show the specified value (i.e. 0) <p>
     *
     *  1. Launch chrome browser. <p>
     *  2. Navigate to Google https://www.google.com/ <p>
     *  3. Type "calculator" in the Google search bar and press Enter. <p>
     *  4. Verify that the Google Calculator loads. <p>
     *  5. Confirm that the initial display shows zero (0). <p>
     */
    public void testCase01() throws InterruptedException {
        System.out.println("\nTestCase01: START");
        // Launch chrome browser
        // Navigate to Google https://www.google.com
        driver.get("https://www.google.com");

        // Type "calculator" in the Google search bar and press Enter
        WebElement searchBox = driver.findElement(By.xpath("//textarea[contains(@name, 'q')]"));
        searchBox.sendKeys("calculator");
        searchBox.sendKeys(Keys.ENTER);

        // Verify current url contains google
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "google";
        if (actualUrl.contains(expectedUrl)) {
            System.out.println("TestCase01.01: PASS: Url of the page contains google");
        } else {
            System.out.println("TestCase01.01: FAIL: Url of the page does not contains google");
        }

        // Verify that the Google Calculator loads
        WebElement googleCalculator = driver.findElement(By.xpath("//div[contains(@jscontroller, 'GCPuBe')]"));
        if (googleCalculator.isDisplayed()) {
            System.out.println("TestCase01.02: PASS: Google Calculator loaded successfully");
        } else {
            System.out.println("TestCase01.02: FAIL: Google Calculator failed to loaded");
        }

        // Confirm that the initial display shows zero (0)
        WebElement result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));
        if (result.getText().equals("0")) {
            System.out.println("TestCase01.03: PASS: Initial display shows zero");
        } else {
            System.out.println("TestCase01.03: FAIL: Initial display does not shows zero");
        }

        System.out.println("TestCase01: END\n");
    }

    /** <STRONG>Test Case 02</STRONG>: Verify Addition and Subtraction Operations <p>
     *  <STRONG>Description</STRONG>: Verify that the Google Calculator can perform addition and subtraction correctly <p>
     *  <STRONG>Expected Result</STRONG>: The Google Calculator should correctly perform the addition operation and the subtraction operation, and the displayed result should be 12 and 7 <p>
     *
     *  1. Launch chrome browser. <p>
     *  2. Navigate to Google https://www.google.com/  <p>
     *  3. Perform an addition operation, 5 + 7. <p>
     *  4. Confirm that the displayed result is correct (in this case, 12). <p>
     *  5. Perform a subtraction operation, 15 - 8. <p>
     *  6. Confirm that the displayed result is correct (in this case, 7). <p>
     */
    public void testCase02() throws InterruptedException {
        System.out.println("\nTestCase02: START");
        // Launch chrome browser
        // Navigate to Google https://www.google.com
        driver.get("https://www.google.com");

        // Type "calculator" in the Google search bar and press Enter
        WebElement searchBox = driver.findElement(By.xpath("//textarea[contains(@name, 'q')]"));
        searchBox.sendKeys("calculator");
        searchBox.sendKeys(Keys.ENTER);

        // Buttons
        WebElement fiveButton = driver.findElement(By.xpath("//div[text()='5']"));
        WebElement sevenButton = driver.findElement(By.xpath("//div[text()='7']"));
        WebElement oneButton = driver.findElement(By.xpath("//div[text()='1']"));
        WebElement eightButton = driver.findElement(By.xpath("//div[text()='8']"));
        WebElement plusButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'plus')]"));
        WebElement minusButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'minus')]"));
        WebElement equalToButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'equals')]"));

        // Perform an addition operation, 5 + 7
        fiveButton.click();
        plusButton.click();
        sevenButton.click();
        equalToButton.click();

        WebElement result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));

        // Confirm that the displayed result is correct (in this case, 12)
        if (result.getText().equals("12")) {
            System.out.println("TestCase02.01: PASS: Displayed result is correct");
        } else {
            System.out.println("TestCase02.01: FAIL: displayed result is not correct");
        }

        // Perform a subtraction operation, 15 - 8
        oneButton.click();
        fiveButton.click();
        minusButton.click();
        eightButton.click();
        equalToButton.click();

        result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));

        // Confirm that the displayed result is correct (in this case, 7)
        if (result.getText().equals("7")) {
            System.out.println("TestCase02.02: PASS: Displayed result is correct");
        } else {
            System.out.println("TestCase02.02: FAIL: Displayed result is not correct");
        }

        System.out.println("TestCase02: END\n");
    }

    /** <STRONG>Test Case 03</STRONG>: Verify the Functionality of the All Clear (AC) Button and Multiplication Operation <p>
     *  <STRONG>Description</STRONG>: Verify that the Google Calculator's "All Clear" (AC) button clears the display, and the calculator remains functional for multiplication operation <p>
     *  <STRONG>Expected Result</STRONG>: The "All Clear" (AC) button should clear the display, and the Google Calculator should correctly perform the multiplication operation <p>
     *
     *  1. Launch chrome browser. <p>
     *  2. Navigate to Google https://www.google.com/  <p>
     *  3. Perform a multiplication operation, 10 * 3. <p>
     *  4. Confirm that the displayed result is correct (in this case, 30). <p>
     *  5. Click the "AC" (All Clear) button on the calculator. <p>
     *  6. Verify that the display clears. <p>
     */
    public void testCase03() throws InterruptedException {
        System.out.println("\nTestCase03: START");
        //  Launch chrome browser.
        //  Navigate to Google https://www.google.com/
        driver.get("https://www.google.com");

        // Type "calculator" in the Google search bar and press Enter
        WebElement searchBox = driver.findElement(By.xpath("//textarea[contains(@name, 'q')]"));
        searchBox.sendKeys("calculator");
        searchBox.sendKeys(Keys.ENTER);

        // Buttons
        WebElement oneButton = driver.findElement(By.xpath("//div[text()='1']"));
        WebElement zeroButton = driver.findElement(By.xpath("//div[text()='0']"));
        WebElement threeButton = driver.findElement(By.xpath("//div[text()='3']"));
        WebElement multiplyButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'multiply')]"));
        WebElement equalToButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'equals')]"));
        WebElement acButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'all clear')]"));

        //  Perform a multiplication operation, 10 * 3.
        oneButton.click();
        zeroButton.click();
        multiplyButton.click();
        threeButton.click();
        equalToButton.click();

        WebElement result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));

        //  Confirm that the displayed result is correct (in this case, 30).
        if (result.getText().equals("30")) {
            System.out.println("TestCase03.01: PASS: Displayed result is correct");
        } else {
            System.out.println("TestCase03.01: FAIL: Displayed result is not correct");
        }

        //  Click the "AC" (All Clear) button on the calculator.
        acButton.click();

        // Verify that the display clears.
        result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));
        if (result.getText().equals("0")) {
            System.out.println("TestCase03.02: PASS: Display clears");
        } else {
            System.out.println("TestCase03.02: FAIL: Display failed to clear");
        }

        System.out.println("TestCase03: END\n");
    }

    /** <STRONG>Test Case 04</STRONG>: Verify Division Operation <p>
     *  <STRONG>Description</STRONG>: Verify that the Google Calculator can perform division correctly <p>
     *  <STRONG>Expected Result</STRONG>: The Google Calculator should correctly perform the division operation <p>
     *
     *  1. Launch chrome browser. <p>
     *  2. Navigate to Google https://www.google.com/ <p>
     *  3. Perform a division operation, 20 / 4. <p>
     *  4. Confirm that the displayed result is correct (in this case, 5). <p>
     */
    public void testCase04() throws InterruptedException {
        System.out.println("\nTestCase04: START");
        // Launch chrome browser
        // Navigate to Google https://www.google.com
        driver.get("https://www.google.com");

        // Type "calculator" in the Google search bar and press Enter
        WebElement searchBox = driver.findElement(By.xpath("//textarea[contains(@name, 'q')]"));
        searchBox.sendKeys("calculator");
        searchBox.sendKeys(Keys.ENTER);

        // Buttons
        WebElement twoButton = driver.findElement(By.xpath("//div[text()='2']"));
        WebElement zeroButton = driver.findElement(By.xpath("//div[text()='0']"));
        WebElement fourButton = driver.findElement(By.xpath("//div[text()='4']"));
        WebElement divideButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'divide')]"));
        WebElement equalToButton = driver.findElement(By.xpath("//div[contains(@aria-label, 'equals')]"));

        // Perform a division operation, 20 / 4
        twoButton.click();
        zeroButton.click();
        divideButton.click();
        fourButton.click();
        equalToButton.click();

        WebElement result = driver.findElement(By.xpath("//span[contains(@id, 'cwos')]"));

        // Confirm that the displayed result is correct (in this case, 5)
        if (result.getText().equals("5")) {
            System.out.println("TestCase04: PASS: Displayed result is correct");
        } else {
            System.out.println("TestCase04: FAIL: Displayed result is not correct");
        }

        System.out.println("TestCase04: END\n");
    }
}
