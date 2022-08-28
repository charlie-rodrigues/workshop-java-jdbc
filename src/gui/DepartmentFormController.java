package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements Initializable {
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
@FXML
private void btnActionSave() {
	System.out.println("btnActionSave");
}
@FXML
private void btnActionCancel() {
	System.out.println("btnActionCancel");
}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

}
