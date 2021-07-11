import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

    public class Pay2pay {

        // 1. определение UI элементов для взаимодействия

        //Элементы создания платежа
        By fromCard= By.xpath("//input[@data-qa-node='numberdebitSource']");
        By expireDate= By.xpath("//input[@data-qa-node='expiredebitSource']");
        By cvv= By.xpath("//input[@data-qa-node='cvvdebitSource']");
        By toCard= By.xpath("//input[@data-qa-node='numberreceiver']");
        By amount= By.xpath("//input[@data-qa-node='amount']");
        By currencyButton= By.xpath("//button[@data-qa-node='currency']");
        By currency= By.xpath("//button[@data-qa-value='USD']");
        By submitButton= By.xpath("//button[@type='submit']");
        By firstnameCardFrom=By.xpath("//input[@data-qa-node='firstNamedebitSource']");
        By lastnameCardFrom=By.xpath("//input[@data-qa-node='lastNamedebitSource']");
        By firsnameCardTo=By.xpath("//input[@data-qa-node='firstNamereceiver']");
        By lastnameCardTo=By.xpath("//input[@data-qa-node='lastNamereceiver']");
        By addcomment= By.xpath("//span[@data-qa-node='toggle-comment']");
        By comment=By.xpath("//textarea[@data-qa-node='comment']");


        //Элементы корзины
        By commentInCard= By.xpath("//div[@data-qa-node='comment']");
        By payerCard = By.xpath("//span[@data-qa-node='payer-card']");
        By payerAmount = By.xpath("//div[@data-qa-node='payer-amount']");
        By payerComission = By.xpath("//div[@data-qa-node='payer-currency']");
        By receiverName = By.xpath("//div[@data-qa-node='receiver-name']");
        By receiverCard = By.xpath("//span[@data-qa-node='receiver-card']");
        By receiverAmount = By.xpath("//div[@data-qa-node='receiver-amount']");
        By receiverComission = By.xpath("//div[@data-qa-node='receiver-currency']");
        By totalAmount = By.xpath("//div[@data-qa-node='total']");

        // 3.Написание тестов
        @Test
        public void checkMinPaymentSum() throws InterruptedException {
            //1.Создаем системную переменную
            //Создаем образ драйвера
            //Указываем ожидание элементов

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Кatrine\\Desktop\\SeleniumFirstProject\\src\\main\\resources\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            // 2. Действия с элементами
            // Описание вспомогательных методов по работе с элементами

            driver.get("https://next.privat24.ua/money-transfer/card");
            driver.findElement(fromCard).sendKeys("4552331448138217");
            driver.findElement(expireDate).sendKeys("0524");
            driver.findElement(cvv).sendKeys("926");
            driver.findElement(firstnameCardFrom).sendKeys("Shayne");
            driver.findElement(lastnameCardFrom).sendKeys("McConnell");
            driver.findElement(toCard).sendKeys("4004159115449003");
            driver.findElement(firsnameCardTo).sendKeys("Philipp");
            driver.findElement(lastnameCardTo).sendKeys("McCauley");
            driver.findElement(amount).sendKeys("10");
            driver.findElement(currencyButton).click();
            driver.findElement(currency).sendKeys("USD");
            driver.findElement(currency).click();
            driver.findElement(addcomment).click();
            driver.findElement(comment).sendKeys("Перевод с карты A на карту B");
            driver.findElement(submitButton).submit();

            // 3. Проверка ОР с ФР
            Assert.assertEquals("Перевод с карты A на карту B", driver.findElement(commentInCard).getText());
            Assert.assertEquals("4552 3314 4813 8217", driver.findElement(payerCard).getText());
            Assert.assertEquals("10 USD", driver.findElement(payerAmount).getText());
            Assert.assertEquals("3.15 USD", driver.findElement(payerComission).getText());
            Assert.assertEquals("P****** M*******", driver.findElement(receiverName).getText());
            Assert.assertEquals("4004 1591 1544 9003", driver.findElement(receiverCard).getText());
            Assert.assertEquals("10 USD", driver.findElement(receiverAmount).getText());
            Assert.assertEquals("0 USD", driver.findElement(receiverComission).getText());
            Assert.assertEquals("Разом до списання 13.15 USD", driver.findElement(totalAmount).getText());

        }
    }
