import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailSPController {
	@FXML
	private TextField dttensp;
	@FXML
	private TextField dtmasp;
	@FXML
	private TextField dtphanloai;
	@FXML
	private TextField dttheloai;
	@FXML
	private TextField dtdongia;
	//
	public void Xacnhan(ActionEvent event) throws Exception {
		if (dttensp.getText().equals("") || dtmasp.getText().equals("") || dtphanloai.getText().equals("") 
				|| dttheloai.getText().equals("") || dtdongia.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Thiếu dữ liệu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Menu");
			int gia = Integer.parseInt(dtdongia.getText());
			Document menu = new Document("Ten san pham", dttensp.getText()).append("Phan loai", dtphanloai.getText())
					.append("The loai", dttheloai.getText()).append("Ma", dtmasp.getText()).append("Gia", gia);
			col.insertOne(menu);
			client.close();
			//
			JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			((Node) (event.getSource())).getScene().getWindow().hide();
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CheckMenu.fxml"));
			Scene scene = new Scene(root,700,800);
			scene.getStylesheets().add(getClass().getResource("CheckMenu.fxml").toExternalForm());
			stage.setScene(scene);
			stage.show();
			}
	}
	//
	public void Huy(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("CheckMenu.fxml"));
		Scene scene = new Scene(root,700,800);
		scene.getStylesheets().add(getClass().getResource("CheckMenu.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
}
// Thiếu phần kiểm tra đơn giá và kiểm tra có trùng hay k