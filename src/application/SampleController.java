package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import com.coffeemachine.controllers.ClientController;
import com.coffeemachine.controllers.MachineController;
import com.coffeemachine.controllers.ProductController;
import com.coffeemachine.controllers.TechnicianController;
import com.coffeemachine.models.Client;
import com.coffeemachine.models.Machine;
import com.coffeemachine.models.Product;
import com.coffeemachine.models.Technician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SampleController implements Initializable{
//	technician id 
	@FXML private TextField name,phone,reference,code;

	
//	table 
	
	@FXML private TableView<Technician> table;

	@FXML private TableColumn<Technician, String> idC,nameC,phoneC,referenceC,codeC;

//    start client id 


	@FXML private TextField name1,phone1,choice,coins;

	@FXML private TableView<Client> clientTable;
	
	@FXML private TableColumn<Client, String> clid,nameClient,phonClient,choiceC,coinsC;
	
//	end client id 
	
//	start product id 
	
	@FXML private TextField productName,price;
	@FXML private TableView<Product> productTable;
	@FXML private TableColumn<Product, String> productId,productNameC,priceC;
	
//	end prodct id 
	
//	start machine id 
	@FXML private TableView<Machine> machineTable;
	@FXML private TableColumn<Machine, String> machineId,sum,isTurnedOn;
//	end machine id 
	@FXML private ComboBox<Machine> com;
	@FXML private TextField label1;
	@FXML private ListView<String> list;
//	@FXML private 
	
	TechnicianController tecnicienController = new TechnicianController();
	Technician technicien;
	public ObservableList<Technician> techniciens = FXCollections.observableArrayList();
	
	ClientController clientController = new ClientController();
	Client client;
	public ObservableList<Client> clients = FXCollections.observableArrayList();
	
//	------------------------product -------------------------
	
	ProductController productCotroller = new ProductController();
	Product product;
	public ObservableList<Product> products = FXCollections.observableArrayList();
	
//	---------------------machine-----------------------------------
	MachineController machineController = new MachineController();
	Machine machine;
	public ObservableList<Machine> machines = FXCollections.observableArrayList();
	
	public void AddTechnician() throws Exception {
		technicien = new Technician(randomId(),name.getText(),phone.getText(),reference.getText(),code.getText());
		
//		techniciens.add(technicien);
		tecnicienController.AddTechnician(techniciens, technicien);
//		
		for (Technician technician : techniciens) {
			System.out.println(technician.toString());
			System.out.println("------------------------------------------------------------");
		}
	}

	public void ac() {
		    idC.setCellValueFactory(new PropertyValueFactory<Technician,String>("id"));
	        nameC.setCellValueFactory(new PropertyValueFactory<Technician,String>("name"));
	        phoneC.setCellValueFactory(new PropertyValueFactory<Technician,String>("phone"));
	        referenceC.setCellValueFactory(new PropertyValueFactory<Technician,String>("idRef"));
	        codeC.setCellValueFactory(new PropertyValueFactory<Technician,String>("accessCode"));
	       
	        
	        table.setItems(techniciens);
	 }
	
//	client part 
//	Double coinsd = Double.parseDouble(coins); 
//	double d = Double.parseDouble(coins.getText()); 
	public void addClient() throws Exception {
		client = new Client(randomId(), name1.getText(), phone1.getText(), choice.getText(), Double.parseDouble(coins.getText()));
		clientController.AddClient(clients, client);
	}
//	method for diplay in table 
	public void clientDispaly() {
		clid.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
		nameClient.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		phonClient.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		choiceC.setCellValueFactory(new PropertyValueFactory<Client, String>("choice"));
		coinsC.setCellValueFactory(new PropertyValueFactory<Client, String>("coins"));
		clientTable.setItems(clients);
	}
	
//	product part 
	public void addProduct() throws Exception {
		product = new Product(randomId(), productName.getText(), Double.parseDouble(price.getText()));
		productCotroller.AddProduct(products, product);
	}
	
	public void displayProduct() {
		productId.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		productNameC.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		priceC.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		productTable.setItems(products);
	}
	
//	machine part 
	public Machine addMachine() throws Exception {
		machine = new Machine(randomId());
		machineController.AddMachine(machines, machine);
		return machine;
	}
	public void displayMachine() {
		machineId.setCellValueFactory(new PropertyValueFactory<Machine, String>("id"));
		sum.setCellValueFactory(new PropertyValueFactory<Machine, String>("sumChange"));
		machineTable.setItems(machines);
	} 
//	method for random Id 
	public Long randomId() {
		Random r = new Random();
		Long id = Math.abs(r.nextLong());
		return id;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		com.setItems(machines);
//		list.setItems(products);
//		selectItem();
		
	}
	
	public void selectItem() {
		label1.setText(com.getSelectionModel().getSelectedItem().toString());
	}
	public void selectProduct() {
		label1.setText(list.getSelectionModel().getSelectedItem().toString());
	}
	public void turnOn() throws Exception {
		label1.setText(machineController.StartMachine(products, addMachine(), 150));
		
		ObservableList<String> names = FXCollections.observableArrayList();
		
		for(Product p : products) {
			
//			String s =String.valueOf(d);  
			
			names.add(p.getName());
		//	names.add(p.getPrice());
			
		}
        ObservableList<String> prices = FXCollections.observableArrayList();
		
		for(Product p : products) {
			prices.add(String.valueOf(p.getPrice()));
			
		}
		
		list.setItems(prices);
		
		
	}
	

}
