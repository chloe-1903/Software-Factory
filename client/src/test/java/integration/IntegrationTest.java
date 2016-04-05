package integration;

import api.TCFPublicAPI;
import org.junit.Before;
import stubs.cart.CartWebService;
import stubs.cart.CartWebServiceImplService;
import stubs.customerCare.Cookies;
import stubs.customerCare.AlreadyExistingCustomerException_Exception;
import stubs.customerCare.CustomerCareService;
import stubs.customerCare.CustomerCareServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 05/04/16.
 */
public class IntegrationTest {
    CartWebService cartWs;
    CustomerCareService customerWs;

    @Before
    public void setContext(){
        TCFPublicAPI api= new TCFPublicAPI("localhost", "8080");
        cartWs= api.carts;
        customerWs=api.ccs;
    }

    public void normalUsageTest(){
        List<Cookies> cookiesAvailable= customerWs.listAllRecipes();
        System.out.println("The customer wants to see available cookies:");
        for (Cookies c: cookiesAvailable)
            System.out.println(" \t -"+c);
        System.out.println("The customer registers:");
        try {
            customerWs.register("Nicolas", "30896983");
            System.out.println("The customer choose the first cookie:");
            //cartWs.addItemToCustomerCart("Nicolas", cookiesAvailable.get(0));
        } catch (AlreadyExistingCustomerException_Exception e) {
            e.printStackTrace();
        }


    }

    public void customerRegisterTwice(){

    }
}
