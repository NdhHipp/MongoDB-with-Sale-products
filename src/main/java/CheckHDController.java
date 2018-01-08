import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckHDController {
	@FXML
	private TextField chdtimkiem;
	@FXML
	private ListView<String> chdlist;
	@FXML
	private Label chdma;
	@FXML
	private Label chdthoigian;
	@FXML
	private Label chdtongtt;
	@FXML
	private Label chdnhanvien;
	@FXML
	private Label chdsp1;
	@FXML
	private Label chdsp2;
	@FXML
	private Label chdsp3;
	@FXML
	private Label chdsp4;
	@FXML
	private Label chdsp5;
	@FXML
	private Label chdsp6;
	@FXML
	private Label chdsp7;
	@FXML
	private Label chdsp8;
	@FXML
	private Label chdsp9;
	@FXML
	private Label chdsp10;
	
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
	public void Timkiem(ActionEvent event) {
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("HoaDon");
			//
			ObservableList<String> doc = FXCollections.observableArrayList();
			Document regex = new Document("$regex", chdtimkiem.getText());
			Document Nhanvien = new Document("Nhan vien", regex);
			Document Thoigian = new Document("Thoi gian", regex);
			ArrayList<Document> timkiem = new ArrayList<>();
			timkiem.add(Nhanvien);
			timkiem.add(Thoigian);
			Document query = new Document("$or", timkiem);
			MongoCursor<Document> cursor = col.find(query).sort(new Document("_id", -1)).iterator();
			while (cursor.hasNext()) {
				Document document = cursor.next();
	        	String nhanvien = document.getString("Nhan vien");
	        	String thoigian = document.getString("Thoi gian");
	        	doc.add(thoigian+", "+nhanvien);
			}
			chdlist.setItems(doc);	
			chdlist.getSelectionModel().selectedItemProperty().addListener(
		               (ObservableValue<? extends String> ov, String old_val,
		                       String new_val) -> {
		                  //
		                  chdsp1.setText("");
		                  chdsp2.setText("");
		                  chdsp3.setText("");
		                  chdsp4.setText("");
		                  chdsp5.setText("");
		                  chdsp6.setText("");
		                  chdsp7.setText("");
		                  chdsp8.setText("");
		                  chdsp9.setText("");
		                  chdsp10.setText("");
		                 
		                  //
		                  String sub = new_val.substring(0, 28);
		                  MongoCursor<Document> cursor2 = col.find(new Document("Thoi gian", sub)).iterator();
		                  Document hoadon2 = cursor2.next();
		                  //
		                  chdma.setText(hoadon2.get("_id").toString());
		                  chdthoigian.setText(hoadon2.getString("Thoi gian"));
		                  chdtongtt.setText(hoadon2.get("Tong thanh toan").toString());
		                  chdnhanvien.setText(hoadon2.getString("Nhan vien"));
		                  //
		                  Iterator<String> tontai = hoadon2.keySet().iterator();
		                  while (tontai.hasNext()) {
		                	 String sp = tontai.next();
		                	 if (sp.equals("sp1")) {
		                	 Document sp1 = (Document) hoadon2.get("sp1");
		                	 chdsp1.setText(sp1.values().toString());
		                	 }
		                	 else if (sp.equals("sp2")) {
		                	 Document sp2 = (Document) hoadon2.get("sp2");
		                	 chdsp2.setText(sp2.values().toString());
		                	 }
		                	 else if (sp.equals("sp3")) {
		                	 Document sp3 = (Document) hoadon2.get("sp3");		                	
		                	 chdsp3.setText(sp3.values().toString());
		                	 }
		                	 else if (sp.equals("sp4")) {
		                	 Document sp4 = (Document) hoadon2.get("sp4");		                	
		                	 chdsp4.setText(sp4.values().toString());
		                	 }
		                	 else if (sp.equals("sp5")) {
		                	 Document sp5 = (Document) hoadon2.get("sp5");		                
		                	 chdsp5.setText(sp5.values().toString());
		                	 }
		                	 else if (sp.equals("sp6")) {
		                	 Document sp6 = (Document) hoadon2.get("sp6");		     
		                	 chdsp6.setText(sp6.values().toString());
		                	 }
		                	 else if (sp.equals("sp7")) {
		                	 Document sp7 = (Document) hoadon2.get("sp7");		                	 
		                	 chdsp7.setText(sp7.values().toString());
		                	 }
		                	 else if (sp.equals("sp8")) {
		                	 Document sp8 = (Document) hoadon2.get("sp8");		               
		                	 chdsp8.setText(sp8.values().toString());
		                	 }
		                	 else if (sp.equals("sp9")) {
		                	 Document sp9 = (Document) hoadon2.get("sp9");		                	
		                	 chdsp9.setText(sp9.values().toString());
		                	 }
		                	 else if (sp.equals("sp10")) {
		                	 Document sp10 = (Document) hoadon2.get("sp10");		                	
		                	 chdsp10.setText(sp10.values().toString());
		                	 }
		                  }
		                  //
		                });							
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
