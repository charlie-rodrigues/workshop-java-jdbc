package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.service.DepartmentServices;

public class MainViewController implements Initializable {
@FXML	
private MenuItem menuItemSeller;
@FXML
private MenuItem menuItemDepartment;
@FXML
private MenuItem menuItemAbout;

@FXML
public void onMenuItemSelerAction() {
	System.out.println("onMenuItemSelerAction");
}
@FXML
public void onMenuItemDepartmentAction() {
loadView2("/gui/DepartmentList.fxml");
}
@FXML
public void onMenuItemAbountAction() {
	//System.out.println("onMenuItemAbountAction");
   loadView("/gui/About.fxml");
}
@Override
public void initialize(URL uri, ResourceBundle rb) {
	
}
public synchronized void loadView(String absoluteName) {
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainVbox =(VBox) ((ScrollPane)mainScene.getRoot()).getContent();
		Node mainMenu = mainVbox.getChildren().get(0);
		mainVbox.getChildren().clear();
		mainVbox.getChildren().add(mainMenu);
		mainVbox.getChildren().addAll(newVbox.getChildren());
	} catch (IOException e) {
    Alerts.showAlerts("io exception", "error in loader view", e.getMessage(), AlertType.ERROR);
	}	
}
public synchronized void loadView2(String absoluteName) {
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainVbox =(VBox) ((ScrollPane)mainScene.getRoot()).getContent();
		Node mainMenu = mainVbox.getChildren().get(0);
		mainVbox.getChildren().clear();
		mainVbox.getChildren().add(mainMenu);
		mainVbox.getChildren().addAll(newVbox.getChildren());
		DepartmentListController controller = loader.getController();
		controller.setDepartmentService(new DepartmentServices());
		controller.updateTableView();
	} catch (IOException e) {
    Alerts.showAlerts("io exception", "error in loader view", e.getMessage(), AlertType.ERROR);
	}	
}




}
