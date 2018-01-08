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

public class TaoNVController {
	@FXML
	private TextField taotk;
	@FXML
	private TextField taomk;
	@FXML
	private TextField taocv;
	//
	public void Huy(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("MainQuanli.fxml"));
		Scene scene = new Scene(root,500,300);
		scene.getStylesheets().add(getClass().getResource("MainQuanli.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
	//
	public void Ok(ActionEvent event) throws Exception {
		if (taotk.getText().equals("") || taomk.getText().equals("") || taocv.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Thiếu dữ liệu", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else if ( taocv.getText().equals("Manager") || taocv.getText().equals("Staff")) {
			int out = JOptionPane.showConfirmDialog(null, "Bạn muốn tạo tài khoản cho nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if (out == JOptionPane.YES_OPTION) {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("NhanVien");
			Document document = new Document("User", taotk.getText()).append("Pwd", taomk.getText()).append("Position", taocv.getText());
			col.insertOne(document);
			client.close();
			
			JOptionPane.showMessageDialog(null, "Tạo thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);	
			taotk.setText("");
			taomk.setText("");
			taocv.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Chức vụ phải là \"Manager\" hoặc \"Staff\"", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
}
