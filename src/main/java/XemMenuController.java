import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class XemMenuController {
	@FXML
	private TextField xmtimkiem;
	@FXML
	private ListView<String> xmlist;
	@FXML
	private TextField xmtensp;
	
	public void Timkiem(ActionEvent event) {
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Menu");
			//
			ObservableList<String> doc = FXCollections.observableArrayList();
			Document regex = new Document("$regex", xmtimkiem.getText());
			Document tensp = new Document("Ten san pham", regex);
			Document phanloai = new Document("Phan loai", regex);
			Document theloai = new Document("The loai", regex);
			Document masp = new Document("Ma", regex);
			
			ArrayList<Document> timkiem = new ArrayList<>();
			timkiem.add(tensp);
			timkiem.add(phanloai);
			timkiem.add(theloai);
			timkiem.add(masp);
			Document query = new Document("$or", timkiem);
			MongoCursor<Document> cursor = col.find(query).iterator();
			while (cursor.hasNext()) {
				Document document = cursor.next();
	        	String ten = document.getString("Ten san pham");
	        	String pl = document.getString("Phan loai");
	        	String tl = document.getString("The loai");
	        	String ma = document.getString("Ma");
	        	int gia = document.getInteger("Gia");
	        	doc.add(ten+", "+ma+", "+pl+", "+tl+", "+gia);
			}
			xmlist.setItems(doc);
			xmlist.getSelectionModel().selectedItemProperty().addListener(
		               (ObservableValue<? extends String> ov, String old_val,
		                       String new_val) -> {
		                  int endindex = new_val.indexOf(",");
		                  String tensp1 = new_val.substring(0, endindex);
		                  xmtensp.setText(tensp1);		                  
		                });
			client.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
}
	//
	public void Quaylai(ActionEvent event) throws Exception {
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
	public void Them(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("DetailSP.fxml"));
		Scene scene = new Scene(root,500,300);
		scene.getStylesheets().add(getClass().getResource("DetailSP.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
	//
	public void chitiet(ActionEvent event) throws Exception {
		int i = xmtensp.getText().indexOf(",");
		if (xmtensp.getText().equals("") || !( i == -1 )) {
			JOptionPane.showMessageDialog(null, "Không thông tin/ đã hiển thị chi tiết về sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Menu");
			MongoCursor<Document> cursor = col.find(new Document("Ten san pham", xmtensp.getText())).iterator();
			Document document = cursor.next();
			String ten = document.getString("Ten san pham");
			String ma = document.getString("Ma");
			String pl = document.getString("Phan loai");
			String tl = document.getString("The loai");
			int gia = document.getInteger("Gia");
			xmtensp.setText(ten+", "+ma+", "+pl+", "+tl+", "+gia);
			client.close();
		}
		
	}
	public void Sua(ActionEvent event) throws Exception {
		int i = xmtensp.getText().indexOf(",");
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Chưa có chi tiết về sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			String s = xmtensp.getText();
			int ind = s.indexOf(",");
			String s1 = s.substring(0, ind);
			s = s.substring(ind+2);
			ind = s.indexOf(",");
			String s2 = s.substring(0, ind);
			s = s.substring(ind+2);
			ind = s.indexOf(",");
			String s3 = s.substring(0, ind);
			s = s.substring(ind+2);
			ind = s.indexOf(",");
			String s4 = s.substring(0, ind);
			s = s.substring(ind+2);
			String s5 = s;
			int cost = Integer.parseInt(s5);
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4);
			System.out.println(s5);
			
			int out = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa thông tin sản phẩm này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if (out == JOptionPane.YES_OPTION) {
				
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Menu");
			Document query = new Document("Ma", s2);
			Document value = new Document("Ten san pham", s1).append("Phan loai", s3).append("The loai", s4).append("Gia", cost);
			col.updateOne(query, new Document("$set", value));
			client.close();
			}
		}
	}
	//
	public void Xoa(ActionEvent event) {
		int i = xmtensp.getText().indexOf(",");
		if (xmtensp.getText().equals("") || !( i == -1 )) {
			JOptionPane.showMessageDialog(null, "Không có/sai thông tin về sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			int out = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa thông tin sản phẩm này?", "Thông báo", JOptionPane.YES_NO_OPTION);
			if (out == JOptionPane.YES_OPTION) {
				MongoClient client = new MongoClient("localhost", 27017);
				MongoDatabase db = client.getDatabase("quanli");
				MongoCollection<Document> col = db.getCollection("Menu");
				String sanpham = xmtensp.getText().trim();
				col.deleteOne(new Document("Ten san pham", sanpham));
				client.close();
			}
			
		}
	}
}
