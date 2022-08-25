package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.service.DepartmentServices;

public class DepartmentListController implements Initializable {
   private DepartmentServices services;
	
	@FXML 
	private TableView<Department> tableView;
    @FXML
    private TableColumn<Department, Integer> tableColumnID;
    @FXML
    private TableColumn<Department, String> tableColumnName;
    @FXML
    private Button btnNew;
    
    private ObservableList<Department> obsList;
    
    @FXML
    private void onBtnNewAction() {
    	System.out.println("onBtnAction");
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	public void setDepartmentService(DepartmentServices services) {
		this.services = services;
	}

	private void initializeNodes() {
	tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
	tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
	Stage stage =(Stage) Main.getMainScene().getWindow();
	tableView.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(services == null) {
			throw new IllegalStateException("service was null!");
		}
		List<Department> list = services.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableView.setItems(obsList);
		
		
		
		
	}

}
