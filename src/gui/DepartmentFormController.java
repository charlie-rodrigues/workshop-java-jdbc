package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.service.DepartmentServices;

public class DepartmentFormController implements Initializable {
	private Department entity;
	private DepartmentServices services;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<DataChangeListener>();
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtName;
	@FXML
	private Label lblErrorName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;

	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	public void setDepartmentService(DepartmentServices services) {
		this.services = services;
	}
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	private void btnActionSave(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("entity was null!");
		}
		if(services == null) {
			throw new IllegalStateException("services was null!");
		}
	        try {
	        	entity = getFormData();
	            services.saveOrUpdate(entity);
	            Alerts.showAlerts("saving obj", null, "salving", AlertType.INFORMATION);
               notifyDataChangeListeners();
		    Utils.currentStage(event).close();;
			} catch (DbException e) {
          Alerts.showAlerts("Error saving obj", null, e.getMessage(), AlertType.ERROR);
			}	
	}

	private void notifyDataChangeListeners() {
	for (DataChangeListener Listener : dataChangeListeners) {
		Listener.onDataChanged();
	}
		
	}

	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtID.getText()));
		obj.setName(txtName.getText());
		return obj;
	}

	@FXML
	private void btnActionCancel(ActionEvent event) {
		Utils.currentStage(event).close();;
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("entity was null!");
		}
		txtID.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
