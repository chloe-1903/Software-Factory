package arquillian;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.components.CashierBean;
import fr.unice.polytech.isa.tcf.components.CustomerRegistryBean;
import fr.unice.polytech.isa.tcf.components.KitchenBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatefulBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatelessBean;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.isa.tcf.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.interceptors.CartCounter;
import fr.unice.polytech.isa.tcf.interceptors.ItemVerifier;
import fr.unice.polytech.isa.tcf.interceptors.Logger;
import fr.unice.polytech.isa.tcf.utils.BankAPI;
import fr.unice.polytech.isa.tcf.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;


public abstract class AbstractTCFTest {

	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addPackage(Database.class.getPackage())
				.addPackage(BankAPI.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				// Components Interfaces
				.addPackage(CartModifier.class.getPackage())
				.addPackage(CustomerRegistration.class.getPackage())
				.addPackage(CustomerFinder.class.getPackage())
				.addPackage(Payment.class.getPackage())
				.addPackage(OrderProcessing.class.getPackage())
				// Cart components
				.addPackage(CartStatefulBean.class.getPackage())
				.addPackage(CartStatelessBean.class.getPackage())
				// Interceptors
				.addPackage(Logger.class.getPackage())
				.addPackage(ItemVerifier.class.getPackage())
				.addPackage(CartCounter.class.getPackage())
				// Exceptions
				.addPackage(AlreadyExistingCustomerException.class.getPackage())
				.addPackage(PaymentException.class.getPackage())
				// Components implementations
				//.addPackage(CartBean.class.getPackage())
				.addPackage(CustomerRegistryBean.class.getPackage())
				.addPackage(CashierBean.class.getPackage())
				.addPackage(KitchenBean.class.getPackage());
	}
}


