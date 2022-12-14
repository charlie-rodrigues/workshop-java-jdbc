package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.service.SellerServices;

public class MainViewController implements Initializable {
@FXML	
private MenuItem menuItemSeller;
@FXML
private MenuItem menuItemDepartment;
@FXML
private MenuItem menuItemAbout;

@FXML
public void onMenuItemSelerAction() {

loadView("/gui/SellerList.fxml",(SellerListController controller)->{
	controller.setSellerService(new SellerServices());
	controller.updateTableView();
});
}
@FXML
public void onMenuItemDepartmentAction() {
loadView("/gui/DepartmentList.fxml",(DepartmentListController controller)->{
	controller.setDepartmentService(new DepartmentServices());
	controller.updateTableView();
});
}
@FXML
public void onMenuItemAbountAction() {
	//System.out.println("onMenuItemAbountAction");
   loadView("/gui/About.fxml",(x->{}));
}
@Override
public void initialize(URL uri, ResourceBundle rb) {
	
}
public synchronized<T> void loadView(String absoluteName, Consumer<T>inicializingAction) {
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainVbox =(VBox) ((ScrollPane)mainScene.getRoot()).getContent();
		Node mainMenu = mainVbox.getChildren().get(0);
		mainVbox.getChildren().clear();
		mainVbox.getChildren().add(mainMenu);
		mainVbox.getChildren().addAll(newVbox.getChildren());
		T controller = loader.getController();
		inicializingAction.accept(controller);
				
	} catch (IOException e) {
    Alerts.showAlerts("io exception", "error in loader view", e.getMessage(), AlertType.ERROR);
	}	
}




}
