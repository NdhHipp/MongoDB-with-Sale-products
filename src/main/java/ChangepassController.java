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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangepassController {
	@FXML
	private TextField matkhaucu;
	@FXML
	private TextField matkhaumoi;
	@FXML
	private TextField xacnhanmk;
	@FXML
	private Label thongbao;
	
	//Xử lí nút hủy
	public void Huy(ActionEvent event) {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("Choice.fxml"));
			Scene scene = new Scene(root,500,300);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Make by Hipp");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Xử lí nút xác nhận
	public void XacNhan(ActionEvent event) {
		try {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("quanli");
		MongoCollection<Document> col = db.getCollection("Phien");
		
		// Tìm kiếm thông tin user đang sử dụng
		Document max = new Document("_id", -1);
		Document projection = new Document("_id", 0).append("User", 1).append("Pwd", 1);
		Document dangsudung = new Document();		
		MongoCursor<Document> cursor = col.find().projection(projection).sort(max).limit(1).iterator();
		try {
        	while (cursor.hasNext()) {
        		  dangsudung = cursor.next();
        	}
        } finally {
        	cursor.close();
        }
		
		// Tìm kiếm thông tin của User đó
        MongoCollection<Document> col2 = db.getCollection("NhanVien");
        Document projection2 = new Document("_id", 0).append("Pwd", 1);
        Document mkdangsudung = new Document();
        MongoCursor<Document> cursor2 = col2.find(dangsudung).projection(projection2).iterator();
        try {
        	while (cursor2.hasNext()) {
        		   mkdangsudung = cursor2.next();
        	}
        } finally {
        	cursor2.close();
        	}
        // Kiểm tra lúc nhập và xử lí đổi mật khẩu
        if (new Document("Pwd",matkhaucu.getText()).equals(mkdangsudung) && matkhaumoi.getText().equals(xacnhanmk.getText())) {
        	// cập nhật lại mật khẩu
        	col2.updateOne(dangsudung, new Document("$set", new Document("Pwd", matkhaumoi.getText())));
        	((Node) (event.getSource())).getScene().getWindow().hide();
    		Stage stage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("Choice.fxml"));
    		Scene scene = new Scene(root,500,300);
    		scene.getStylesheets().add(getClass().getResource("Choice.fxml").toExternalForm());
    		stage.setTitle("Make by Hipp");
    		stage.setScene(scene);
    		stage.show();
    		JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		matkhaucu.setText(null);
        		matkhaumoi.setText(null);
        		xacnhanmk.setText(null);
        	}
        client.close();
      } catch (Exception e) {
    	  System.err.println(e.getClass().getName() + ": " + e.getMessage());
          e.printStackTrace();
      }
	}
}
