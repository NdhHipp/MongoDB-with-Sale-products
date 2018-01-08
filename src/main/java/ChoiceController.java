import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChoiceController {
	@FXML
	private Label thongbao;
		//Xử lí nút chọn chế độ làm việc quản lí
		public void ChonQL(ActionEvent event) throws Exception {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Phien");
			
			// Tìm kiếm phiên sử dụng mới nhất, sau khi người sử dụng vừa đăng nhập
			Document max = new Document("_id", -1);
			Document positionphien = new Document();
			Document projection = new Document("Position", 1).append("_id", 0); 
			MongoCursor<Document> cursor = col.find().projection(projection).sort(max).limit(1).iterator();
			try {
	        	while (cursor.hasNext()) {
	        		  positionphien = cursor.next();
	        	}
	        } finally {
	        	cursor.close();
	        }
			if (positionphien.equals(new Document("Position","Manager"))) {
				((Node) (event.getSource())).getScene().getWindow().hide();
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("MainQuanli.fxml"));
				Scene scene = new Scene(root,500,300);
				scene.getStylesheets().add(getClass().getResource("MainQuanli.fxml").toExternalForm());
				stage.setTitle("Make by Hipp");
				stage.setScene(scene);
				stage.show();
			}
			else {
				thongbao.setText("Chỉ có quản lí mới có quyền truy cập");
			}
			client.close();
		}
		
		//Xử lí nút chọn chế độ làm việc nhân viên
		public void ChonNV(ActionEvent event) throws Exception {
			((Node) (event.getSource())).getScene().getWindow().hide();
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("MainStaff.fxml"));
			Scene scene = new Scene(root,700,800);
			scene.getStylesheets().add(getClass().getResource("MainStaff.fxml").toExternalForm());
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
		}
		
		//Xử lí nút đổi mật khẩu
		public void Doimk(ActionEvent event) throws Exception {
			((Node) (event.getSource())).getScene().getWindow().hide();
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Changepass.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("Changepass.fxml").toExternalForm());
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
		}
		
		//Xử lí nút đăng xuất
		public void Dangxuat(ActionEvent event) throws Exception {
			((Node) (event.getSource())).getScene().getWindow().hide();
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("Start.fxml").toExternalForm());
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
		}
}
