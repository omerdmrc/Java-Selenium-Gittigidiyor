

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class TestClass {





    public WebDriver driver;

    BeforeClass startFUN = new BeforeClass();


    @Test
    public void Testing() throws InterruptedException {


        //Web driverlar eşlendi.
        driver= startFUN.webDriver;

        //MethodClassının objesi olultu.
        MethodClass methods =new MethodClass(driver);


        methods.Click(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div[1]/div[2]"));
        methods.SleepF();
        //X path ile Giriş yapın altında ki giriş butonuna tıklanır.


        methods.Click(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a"));
        methods.SleepF();

        //***********Login sayfası kontrol**************

        String LoginURL="https://www.gittigidiyor.com/uye-girisi";
        String CLoginURL=driver.getCurrentUrl();
        Assert.assertEquals(LoginURL,CLoginURL);


        // **************username girişi*************

        methods.Click(By.id("L-UserNameField"));
        methods.SendK(By.id("L-UserNameField"),"omerdmrc@hotmail.com");//Your mail



        //*********** Password Girişi**************

        methods.Click(By.id("L-PasswordField"));
        methods.SendK(By.id("L-PasswordField"),"d23d23d23");//Your Password

        //*********Login Buttonun bulunup tıklanması**************


        methods.Click(By.id("gg-login-enter"));
        Thread.sleep(3000);

        Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi",driver.getTitle());


        methods.Click(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
        methods.SendK(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"),"Bilgisayar");
        methods.Click(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[2]/form/div/div[2]/button"));
        methods.SleepF();


        //********2. Sayfaya geçiş**************




        Thread.sleep(3000);
        methods.Click(By.xpath("/html/body/div[5]/div[3]/div/div/a/span"));
        methods.Click(By.cssSelector("a[href='/arama/?k=Bilgisayar&sf=2']"));
        String PageInf=methods.convText(By.cssSelector("a[class='current']"));
        Assert.assertEquals("2",PageInf);
        Thread.sleep(3000);


        //************Rastgele ürün seçilimi*********
        methods.Click(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[2]/ul/li[27]/a/div"));
        methods.SleepF();



        //*********Seçilen ürünün Fiyatının tutulması************
        Thread.sleep(3000);
        String NSelectedP=methods.convText(By.cssSelector("div[id='sp-price-lowPrice']"));
        System.out.println("price is "+NSelectedP);
        methods.SleepF();




        //**************Seçilen ürünün Sepete eklenmesi**************
        Thread.sleep(3000);
        methods.Click(By.cssSelector("button[id='add-to-basket']"));
        methods.SleepF();


        //************SEPETE gidilmesi************
        methods.Click(By.cssSelector("a[class='dIB']"));





        //****************Sepetteki ürün fiyati*************

        String NBprice =methods.convText(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[5]/div[1]/div[2]"));
        System.out.println("New Price"+NBprice);
        methods.SleepF();



        //************Fiyat Kıyaslaması if ve assertla iki kez kontrol ediliyor***********


        if(NSelectedP.equals(NBprice)){

            System.out.println("Fiyat karşılaştırması doğru Teste Devam..");

        }


        else {

            System.out.println("Fiyat Karşılaştırması tutarlı değil..");
        }

        Assert.assertEquals(NSelectedP,NBprice);


        //**************Adet arttırımı************


        Thread.sleep(3000);
        WebElement adetCounter = methods.FindE(By.cssSelector("select[class='amount']"));
        int dataController=Integer.parseInt(adetCounter.getAttribute("data-maxamount"));

        //***Secilen ürünün stok sayısı yetersiz olduğunda consola yazdırıyor.***

        if(dataController<=1){

            System.out.println("Sistemde kayıtlı bir tane ürün bulunmakta adet arttırılamaz...");
            System.out.println("Sepet boşaltılacak.");

        }
        else {


            //WebElement selectedCounter = methods.FindE(By.cssSelector("option[value='2']"));
            methods.Click(By.cssSelector("option[value='2']"));
            String selectedCounter=methods.convText(By.cssSelector("option[value='2']"));
            System.out.println("Ürün Adedi ikiye çıkarıldı..");

            //*************Sepetteki ürün adedi kontrolu**********************

            Assert.assertEquals("2",selectedCounter);

        }



        //*********Sepetin Boşaltılması***********


        Thread.sleep(3000);
        methods.Click(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a/i"));
        methods.SleepF();
        System.out.println("Sepet boşaltıldı..");
        driver.get("https://www.gittigidiyor.com/sepetim");


        //***************Sepet Konrotolü*************


        Thread.sleep(3000);
        methods.SleepF();
        String EmptyBox=methods.convText(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div[1]/div/div[1]/div[1]/div[1]/div/div[2]/h2"));
        System.out.println("Sepet Durumu:  "+EmptyBox);
        String BeklenenBox="Sepetinizde ürün bulunmamaktadır.";
        Assert.assertEquals(BeklenenBox,EmptyBox);
        methods.SleepF();

    }

    //********Testin Bitişi****************
    @After
    public void EndofTesting(){

        driver= startFUN.webDriver;
        MethodClass mtd =new MethodClass(driver);
        mtd.SleepF();
        driver.quit();




    }















}
