import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainQLController {
	//
	public void KiemtraMenu(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("CheckMenu.fxml"));
		Scene scene = new Scene(root,700,800);
		scene.getStylesheets().add(getClass().getResource("CheckMenu.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
	//
	public void KiemtraHoadon(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("CheckHoadon.fxml"));
		Scene scene = new Scene(root,700,800);
		scene.getStylesheets().add(getClass().getResource("CheckHoadon.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
	//
	public void Quaylai(ActionEvent event) {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("Choice.fxml"));
			Scene scene = new Scene(root,500,300);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
			System.out.println("Back to Choice!");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void tao(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("TaoNV.fxml"));
		Scene scene = new Scene(root,500,300);
		scene.getStylesheets().add(getClass().getResource("TaoNV.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
}
