package application;
	
import java.util.ArrayList;
import java.util.List;

import com.coffeemachine.controllers.ClientController;
import com.coffeemachine.controllers.MachineController;
import com.coffeemachine.controllers.ProductController;
import com.coffeemachine.controllers.TechnicianController;
import com.coffeemachine.models.Client;
import com.coffeemachine.models.Machine;
import com.coffeemachine.models.Product;
import com.coffeemachine.models.Technician;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;

import javafx.fxml.FXMLLoader;


public class Main extends Application {
	// Clients-Technicians-Machines-Products Lists 
		static List<Technician> technicians;
		static List<Client> clients;
		static List<Machine> machines;
		static List<Product> products;
		
		// Controllers 
		static TechnicianController technicianController;
		static ClientController clientController;
		static MachineController machineController;
		static ProductController productController;
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		technicianController = new TechnicianController();
		clientController = new ClientController();
		machineController = new MachineController();
		productController = new ProductController();
		
		technicians = new ArrayList<Technician>();
		clients = new ArrayList<Client>();
		machines = new ArrayList<Machine>();
		products = new ArrayList<Product>();
		
		// New Technician
		Technician technician = 
				new Technician(12563408, "Kamal Bouhfid", "+212 600 00 00 00", "RF-08", "0000");
		try {
			System.out.println(technicianController.AddTechnician(technicians, technician));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// New Client
		Client client = 
				new Client(65301248, "Ahmed Mohammed", "+212 611 11 11 11", "expresso", 1.00);
		try {
			System.out.println(clientController.AddClient(clients, client));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// New Machine
		Machine machine = new Machine(78118903);
		try {
			System.out.println(machineController.AddMachine(machines, machine));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Add Products
		Product p1 = new Product(32196577, "chocolat", 1.0);
		Product p2 = new Product(63100485, "expresso", 2.5);
		Product p3 = new Product(11299873, "lait-chocolat", 3.0);
		Product p4 = new Product(31005687, "coffee-long", 2.5);
		Product p5 = new Product(88621999, "tea", 2.0);
		
		try {
			System.out.println(productController.AddProduct(products, p1));
			System.out.println(productController.AddProduct(products, p2));
			System.out.println(productController.AddProduct(products, p3));
			System.out.println(productController.AddProduct(products, p4));
			System.out.println(productController.AddProduct(products, p5));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(machineController.StartMachine(products, machine,6.0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for(Product product : machineController.ShowMachineItems(machine)) {
				System.out.println(product.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(Product product : machineController.ShowMachineItems(machine)) {
				if(client.getChoice().equalsIgnoreCase(product.getName())) {
					if(client.getCoins() >= product.getPrice()) {
						double rest = client.getCoins() - product.getPrice();
						if(rest<= machine.getSumChange()) {
							client.setRest(rest);
							System.out.println("Thank you, do not forget your change("+rest+"), good by !");
						}else {
							System.out.println("insufficient money in the machine !");
						}
						
					}else {
						System.out.println("You do not have enough money, sorry !");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
