package xgooglecalculator;

public class App {
    public void getGreeting() throws InterruptedException {
        // This is to remove unnecessary warnings from your console
        System.setProperty("java.util.logging.config.file", "logging.properties");

        TestCases tests = new TestCases(); // Initialize your test class

        // TODO: call your test case functions one after other here
        // START Tests
        tests.testCase01();
        tests.testCase02();
        tests.testCase03();
        tests.testCase04();

        // END Tests
        tests.endTest(); // End your test by clearing connections and closing browser
    }

    public static void main(String[] args) throws InterruptedException {
        new App().getGreeting();
    }
}
