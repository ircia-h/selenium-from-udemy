package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NegativeTests {

    @Test
    public void invalidLoginTest() {

        System.out.println("Starting invalid login test");
        //open browser
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //maximize page window
        driver.manage().window().maximize();

        //open login page
        String url = "http://the-internet.herokuapp.com/login";
        driver.navigate().to(url);
        System.out.println("Page is opened.");

        //providing incorrect login information
        String incorrectLogin = "incorrect";
        //String incorrectLogin = "tomsmith";
        driver.findElement(By.id("username")).sendKeys(incorrectLogin);
        //providing password
        String password = "SuperSecretPassword!";
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        //clicking Login button
        driver.findElement(By.className("radius")).click();

        //Validation
        //Incorrect login popup validation
        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(By.className("flash")).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "The message displayed for incorrect " +
                "login attempt is wrong. \n Expected message: " + expectedMessage +
                "\n Actual message: " + actualMessage);

        //blank login field validation
        String loginFieldText = driver.findElement(By.id("username")).getText();
        Assert.assertTrue(loginFieldText.isBlank(), "Login field must be blank. " +
                "\n Actual content:" + loginFieldText);

        //blank password field validation
        String passwordFieldText = driver.findElement(By.id("password")).getText();
        Assert.assertTrue(passwordFieldText.isBlank(), "Password field must be blank. " +
                "\n Actual content: " + passwordFieldText);

        //current page validation
        String expectedPageUrl = "http://the-internet.herokuapp.com/login";
        String actualPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualPageUrl, expectedPageUrl, "Actual page URL doesn't match the expected one. " +
                "\n Actual URL: " + actualPageUrl + "\n Expected URL: " + expectedPageUrl);

        //close browser
        driver.quit();
    }

    @Test
    public void incorrectPasswordTest() {

        System.out.println("Incorrect password test started...");

        //open browser
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //open web page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);

        //providing login
        String login = "tomsmith";
        driver.findElement(By.id("username")).sendKeys(login);
        //providing incorrect password
        String password = "WrongPassword!";
        //String password = "SuperSecretPassword!";
        driver.findElement(By.id("password")).sendKeys(password);
        //clicking the login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Validations
        //Incorrect login popup validation

        String expectedMessage = "Your password is invalid!";
        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Actual message is incorrect \n Actual message: "
                        + actualMessage + "\n Expected message: " + expectedMessage);
        //blank login field validation
        String loginFieldContent = driver.findElement(By.id("username")).getText();
        Assert.assertTrue(loginFieldContent.isBlank(), "After an incorrect login attempt username field" +
                "should be blank. \n Actual username field content: " + loginFieldContent);

        //blank password field validation
        String passwordFieldContent = driver.findElement(By.id("password")).getText();
        Assert.assertTrue(passwordFieldContent.isBlank(), "After an incorrect login attempt password field" +
                "should be blank. \n Actual username field content: " + passwordFieldContent);

        //current page validation
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, url, "Current URL is incorrect. \n Expected URL: " + url +
                "\n Actual URL: " + currentURL);

        //close driver
        driver.quit();
    }

}
