package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.util.Util;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.service.DepartmentServices;

public class DepartmentListController implements Initializable, DataChangeListener {
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
    private void onBtnNewAction(ActionEvent event) {
    	Department obj = new Department();
     Stage parentStage = Utils.currentStage(event);
     createDialogForm(obj,"/gui/DepartmentForm.fxml", parentStage);
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
	private void createDialogForm(Department obj, String absolutName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			Pane pane = loader.load();
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			controller.setDepartmentService(new DepartmentServices());
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			
			
		} catch (IOException e) {
			Alerts.showAlerts("IO exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
updateTableView();		
	}

}
