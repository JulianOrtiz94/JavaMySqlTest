package utilities;


import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
 
    //Constructor
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }
 
    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }
    
  //Wait Wrapper Method
    public WebElement waitPresenceOfElement(By elementBy) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        return element;
    }
 
    //Click Method
    public void click (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }
    
    //Submit Method
    public void submit (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).submit();
    }
 
    //Write Text
    public void writeText (By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }
 
    //Read Text
    public String readText (By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }
    
  //Read Text
    public int sizeElement (By elementBy) {
        return driver.findElements(elementBy).size();        
    }
 
    //Assert
    public void assertEquals (By elementBy, String expectedText) {
        waitVisibility(elementBy);
        //Assert.assertEquals(readText(elementBy), expectedText);
    }
    
    public void getEvidence(String nombre) throws Exception{
    	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
    	FileHandler.copy(src, new File("C:\\SELENIUM\\Evidencia\\"+nombre+".png"));    	
    }
    
}

