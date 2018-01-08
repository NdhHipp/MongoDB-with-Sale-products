import javax.swing.JOptionPane;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	//Start FXML
	@FXML
	private TextField lguser;
	@FXML
	private TextField lgpwd;
	
	// Xử lí nút đăng nhập
	public void Login(ActionEvent event) throws Exception {                          
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("quanli");
		MongoCollection<Document> col = db.getCollection("NhanVien");
		
		// Xử lí user và password nhập vào từ bàn phím
		Document user = new Document("User",lguser.getText())
							.append("Pwd", lgpwd.getText());
		Document projection = new Document("User", 1).append("Pwd", 1).append("_id", 0);
		int i = 0;
		MongoCursor<Document> cursor = col.find().projection(projection).iterator();
		try {
        	while (cursor.hasNext()) {
        		Document doc = cursor.next();
        		if (user.equals(doc)) {
        			i+=1;
        		}
        	}
        } finally {
        	cursor.close();
        }
		
		// Lấy ra dữ liệu của người dùng khi đúng
		Document phien = new Document();
		Document projection2 = new Document("_id", 0);
		MongoCursor<Document> cursor2 = col.find(user).projection(projection2).iterator();
		try {
        	while (cursor2.hasNext()) {
        		 phien = cursor2.next();
        	}
        } finally {
        	cursor2.close();
        }
		//
		if (i == 1) {
			//Insert dữ liệu người dùng vào Collection phiên sử dụng
			MongoCollection<Document> coll = db.getCollection("Phien");
			Document Phien = phien;
			coll.insertOne(Phien);
			((Node) (event.getSource())).getScene().getWindow().hide();
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Choice.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("Choice.fxml").toExternalForm());
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
		} else {
			JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu", "Xác nhận tài khoản", JOptionPane.WARNING_MESSAGE);
			lgpwd.clear();
		}
		client.close();
	}
	public void thoat(ActionEvent event) {
		System.exit(0);
	}

}
