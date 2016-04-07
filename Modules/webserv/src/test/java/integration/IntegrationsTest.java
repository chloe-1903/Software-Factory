package integration;


import arquillian.AbstractTCFTest;
import fr.unice.polytech.isa.tcf.entities.Cookies;
import fr.unice.polytech.isa.tcf.entities.Item;
import fr.unice.polytech.isa.tcf.entities.OrderStatus;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.isa.tcf.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.exceptions.UnknownCustomerException;
import fr.unice.polytech.isa.tcf.exceptions.UnknownOrderId;
import fr.unice.polytech.isa.tcf.webservice.CartWebService;
import fr.unice.polytech.isa.tcf.webservice.CustomerCareService;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class IntegrationsTest extends AbstractTCFTest {


	@EJB CustomerCareService customerWs;
	@EJB CartWebService cartWs;

	@Test
	public void normalUsageTest(){
		//The customer lists all the cookies available
		Set<Cookies> cookiesAvailable= customerWs.listAllRecipes();
		assertTrue(cookiesAvailable.contains(Cookies.CHOCOLALALA));
		assertTrue(cookiesAvailable.contains(Cookies.DARK_TEMPTATION));
		assertTrue(cookiesAvailable.contains(Cookies.SOO_CHOCOLATE));
		try {
			//He registers
			customerWs.register("Nicolas", "30896983");
			//He chooses cookies
			Item i = new Item();
			i.setCookie(Cookies.CHOCOLALALA);
			i.setQuantity(10);
			cartWs.addItemToCustomerCart("Nicolas", i);
			Item i2 = new Item();
			i2.setCookie(Cookies.SOO_CHOCOLATE);
			i2.setQuantity(50);
			cartWs.addItemToCustomerCart("Nicolas", i2);
			//He wants to see what he has chosen
			Set<Item> cart  = cartWs.getCustomerCartContents("Nicolas");
			//Contains n'appelle pas la bonne version d'equals, on doit donc faire
			int cpt=0;
			for (Item item : cart)
				if ((item.getCookie().equals(i.getCookie()) && item.getQuantity()==i.getQuantity())
						|| (item.getCookie().equals(i2.getCookie()) && item.getQuantity()==i2.getQuantity())) cpt++;
			assertEquals(2, cpt);

			//He took too many SOO_CHOCOLATE, he removes them all
			cartWs.removeItemToCustomerCart("Nicolas", i2);
			//He checks if it has been removed
			cart  = cartWs.getCustomerCartContents("Nicolas");
			for (Item item: cart)
				assertNotEquals(Cookies.SOO_CHOCOLATE, item.getCookie());
			//He now wants to pay
			String orderID=cartWs.validate("Nicolas");
			//After a while, he wants to track his order
			OrderStatus status=customerWs.track(orderID);
			assertEquals(OrderStatus.VALIDATED, status);
		} catch (AlreadyExistingCustomerException e) {
			e.printStackTrace();
		} catch (PaymentException e) {
			e.printStackTrace();
		} catch (UnknownCustomerException e) {
			e.printStackTrace();
		} catch (UnknownOrderId unknownOrderId) {
			unknownOrderId.printStackTrace();
		}
	}

    @Test
    public void customerValidateEmptyCart(){
        try {
            //The client registers registers
            customerWs.register("Paul", "10896983");
            //He now wants to pay
            String orderID=cartWs.validate("Paul");
            OrderStatus status=customerWs.track(orderID);
            assertEquals(OrderStatus.VALIDATED, status);
        } catch (AlreadyExistingCustomerException e) {
            e.printStackTrace();
        } catch (PaymentException e) {
            e.printStackTrace();
        } catch (UnknownCustomerException e) {
            e.printStackTrace();
        } catch (UnknownOrderId unknownOrderId) {
            unknownOrderId.printStackTrace();
        }
    }


    @Test
    public void customerShowNotExistingCart(){
        try {
            customerWs.register("Jean", "40896983");
            //He wants to see what he has chosen
            Set<Item> cart  = cartWs.getCustomerCartContents("Jean");
            assertTrue(cart.isEmpty());
        } catch (AlreadyExistingCustomerException e) {
            e.printStackTrace();
        } catch (UnknownCustomerException e) {
            e.printStackTrace();
        }

    }
}
