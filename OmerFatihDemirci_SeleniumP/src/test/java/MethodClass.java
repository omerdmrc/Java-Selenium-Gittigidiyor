
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;



public class MethodClass {





    WebDriver driver;


    public MethodClass(WebDriver driver){

        this.driver=driver;
        System.out.println("Constructor....");

    }

    //Method for findElement
    public WebElement FindE(By by){

        return driver.findElement(by);
    }
    //Method for Click
    public void Click(By by){

        driver.findElement(by).click();

    }
    public  String convText(By by){

        return driver.findElement(by).getText();

    }

    public void SendK(By by,String IN){

        driver.findElement(by).sendKeys(IN);

    }
    public void SleepF(){

        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);


    }





}
