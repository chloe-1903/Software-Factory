package arquillian;

import fr.unice.polytech.isa.tcf.CustomerFinder;
import fr.unice.polytech.isa.tcf.CustomerRegistration;
import fr.unice.polytech.isa.tcf.OrderProcessing;
import fr.unice.polytech.isa.tcf.Tracker;
import fr.unice.polytech.isa.tcf.components.CustomerRegistryBean;
import fr.unice.polytech.isa.tcf.components.KitchenBean;
import fr.unice.polytech.isa.tcf.entities.Customer;
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
				.addClasses(CustomerRegistration.class, CustomerFinder.class, CustomerRegistryBean.class)
				// Utils
				.addPackage(Database.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				// Components Interfaces
				.addPackage(OrderProcessing.class.getPackage())
				.addPackage(Tracker.class.getPackage())
				// Components implementations
				.addPackage(KitchenBean.class.getPackage());
	}
}


