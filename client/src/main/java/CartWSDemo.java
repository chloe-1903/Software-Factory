import stubs.cart.CartWebService;
import stubs.cart.CartWebServiceImplService;
import stubs.cart.Cookies;
import stubs.cart.Item;
import stubs.customerCare.CustomerCareService;
import stubs.customerCare.CustomerCareServiceImplService;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.List;
public class CartWSDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("#### Collecting arguments (host, port)");
        String host = ( args.length == 0 ? "localhost" : args[0] );
        String port = ( args.length < 2  ? "8080"      : args[1] );
        CartWebService ws = initialize(host, port);
        CustomerCareService wsCustomer = initializeCustomer(host, port);
        System.out.println("#### Running the demo");
        demo(ws, wsCustomer);
    }
    private static void demo(CartWebService ws, CustomerCareService wsCustomer) throws Exception {
        wsCustomer.register("Nicolas", "896983");
        Item i = new Item();
        i.setCookie(Cookies.CHOCOLALALA);
        i.setQuantity(3);
        ws.addItemToCustomerCart("Nicolas", i);
        ws.validate("Nicolas");
    }
    private static CartWebService initialize(String host, String port) {
        System.out.println("#### Loading the WSDL contract");
        URL wsdlLocation = CartWSDemo.class.getResource("/CartWS.wsdl");
        System.out.println("#### Instantiating the WS Proxy");
        CartWebServiceImplService factory = new CartWebServiceImplService(wsdlLocation);
        CartWebService ws = factory.getCartWebServiceImplPort();
        System.out.println("#### Updating the endpoint address dynamically");
        String address = "http://"+host+":"+port+"/webserv-1.0-SNAPSHOT/webservices/CartWS";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }
    private static CustomerCareService initializeCustomer(String host, String port) {
        System.out.println("#### Loading the WSDL contract");
        URL wsdlLocation = CartWSDemo.class.getResource("/CustomerCareWS.wsdl");
        System.out.println("#### Instantiating the WS Proxy");
        CustomerCareServiceImplService factory = new CustomerCareServiceImplService(wsdlLocation);
        CustomerCareService ws = factory.getCustomerCareServiceImplPort();
        System.out.println("#### Updating the endpoint address dynamically");
        String address = "http://"+host+":"+port+"/webserv-1.0-SNAPSHOT/webservices/CustomerCareWS";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }
}