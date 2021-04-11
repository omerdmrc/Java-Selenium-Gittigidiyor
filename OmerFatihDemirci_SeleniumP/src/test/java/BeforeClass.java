
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;




public class BeforeClass {



    public WebDriver webDriver;


    @Before
    public void SetUP(){

        System.setProperty("webdriver.gecko.driver","C:\\Users\\omerd\\IdeaProjects\\OmerFatihDemirci_SeleniumP");


        webDriver=new ChromeDriver();
        ChromeOptions options =new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-popup-blocking");
        setWebDriver(new ChromeDriver(options));
        String URL="https://www.gittigidiyor.com/";
        getWebDriver().navigate().to(URL);
        String CURL=webDriver.getCurrentUrl();

        //anasayfa kontrolü
        Assert.assertEquals(URL,CURL);


    }

    public BeforeClass(){

        SetUP();
        System.out.println("Setup Methodu başladı.");

    }

    public  WebDriver getWebDriver(){

        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver){

        this.webDriver=webDriver;

    }










}
