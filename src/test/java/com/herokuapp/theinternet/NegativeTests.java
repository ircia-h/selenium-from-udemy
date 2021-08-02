package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void invalidLoginTest () {

        System.out.println("Starting invalid login test");
        //open browser
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //maximize page window
        driver.manage().window().maximize();

        //open login page
        String url = "http://the-internet.herokuapp.com/login";
        driver.navigate().to(url);
        System.out.println("Page is opened.");

        //providing incorrect login information
        String incorrectLogin = "incorrect";
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
        Assert.assertTrue(actualMessage.contains(expectedMessage),"The message displayed for incorrect login attempt doesn't match the expected one. " +
                        "\n. Expected message: " + expectedMessage + "\n Actual message: " + actualMessage);

        //blank login field validation
        String loginFieldText = driver.findElement(By.id("username")).getText();
        Assert.assertTrue(loginFieldText.isBlank(), "Login field must be blank. \n Actual content:" + loginFieldText);

        //blank password field validation
        String passwordFieldText = driver.findElement(By.id("password")).getText();
        Assert.assertTrue(passwordFieldText.isBlank(), "Password field must be blank. \n Actual content:" + passwordFieldText);

        //current page validation
        String expectedPageUrl = "http://the-internet.herokuapp.com/login";
        String actualPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualPageUrl,expectedPageUrl, "Actual page URL doesn't match the expected one. \n" +
                "Actual URL: " + actualPageUrl + "\n Expected URL: " + expectedPageUrl);

        driver.quit();
    }
}
