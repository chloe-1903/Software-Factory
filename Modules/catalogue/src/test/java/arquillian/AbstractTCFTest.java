package arquillian;

import fr.unice.polytech.isa.tcf.CatalogueExploration;
import fr.unice.polytech.isa.tcf.components.CatalogueBean;
import fr.unice.polytech.isa.tcf.entities.Customer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractTCFTest {

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Entities
				.addPackage(Customer.class.getPackage())
				// Components Interfaces
				.addPackage(CatalogueExploration.class.getPackage())
				// Interceptors
				// Exceptions
				// Components implementations
				.addPackage(CatalogueBean.class.getPackage());
	}

}
