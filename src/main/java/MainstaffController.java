import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileOutputStream;

public class MainstaffController {
	@FXML
	private Label msnhanvien;
	@FXML
	private Label msngay;
	@FXML
	private Label sp1;
	@FXML
	private Label sp2;
	@FXML
	private Label sp3;
	@FXML
	private Label sp4;
	@FXML
	private Label sp5;
	@FXML
	private Label sp6;
	@FXML
	private Label sp7;
	@FXML
	private Label sp8;
	@FXML
	private Label sp9;
	@FXML
	private Label sp10;
	@FXML
	private Label m1;
	@FXML
	private Label m2;
	@FXML
	private Label m3;
	@FXML
	private Label m4;
	@FXML
	private Label m5;
	@FXML
	private Label m6;
	@FXML
	private Label m7;
	@FXML
	private Label m8;
	@FXML
	private Label m9;
	@FXML
	private Label m10;
	@FXML
	private Label sl1;
	@FXML
	private Label sl2;
	@FXML
	private Label sl3;
	@FXML
	private Label sl4;
	@FXML
	private Label sl5;
	@FXML
	private Label sl6;
	@FXML
	private Label sl7;
	@FXML
	private Label sl8;
	@FXML
	private Label sl9;
	@FXML
	private Label sl10;
	@FXML
	private Label dg1;
	@FXML
	private Label dg2;
	@FXML
	private Label dg3;
	@FXML
	private Label dg4;
	@FXML
	private Label dg5;
	@FXML
	private Label dg6;
	@FXML
	private Label dg7;
	@FXML
	private Label dg8;
	@FXML
	private Label dg9;
	@FXML
	private Label dg10;
	@FXML
	private TextField msnhapma;
	@FXML
	private TextField msnhapso;
	@FXML
	private Label mstong;
	
	int tong = 0;
	//xử lí nút quay lại
	public void Quaylai(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Choice.fxml"));
		Scene scene = new Scene(root,500,300);
		scene.getStylesheets().add(getClass().getResource("Choice.fxml").toExternalForm());
		stage.setTitle("Make by Hipp");
		stage.setScene(scene);
		stage.show();
	}
	
	//xử lí nút tạo mới
	public void Tao(ActionEvent event) throws Exception {
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase db = client.getDatabase("quanli");
		MongoCollection<Document> col = db.getCollection("Phien");
		Document nhanvien = new Document(); 
		Document projection = new Document("User", 1).append("_id", 0);
		MongoCursor<Document> cursor = col.find().projection(projection).sort(new Document("_id", -1)).limit(1).iterator();
		try {
        	while (cursor.hasNext()) {
        		  nhanvien = cursor.next();
        	}
        } finally {
        	cursor.close();
        }
		msnhanvien.setText(nhanvien.getString("User"));
		Date date = new Date();
		msngay.setText(date.toString());
		sp1.setText(""); m1.setText(null); sl1.setText(null); dg1.setText(null);
		sp2.setText(""); m2.setText(null); sl2.setText(null); dg2.setText(null);
		sp3.setText(""); m3.setText(null); sl3.setText(null); dg3.setText(null);
		sp4.setText(""); m4.setText(null); sl4.setText(null); dg4.setText(null);
		sp5.setText(""); m5.setText(null); sl5.setText(null); dg5.setText(null);
		sp6.setText(""); m6.setText(null); sl6.setText(null); dg6.setText(null);
		sp7.setText(""); m7.setText(null); sl7.setText(null); dg7.setText(null);
		sp8.setText(""); m8.setText(null); sl8.setText(null); dg8.setText(null);
		sp9.setText(""); m9.setText(null); sl9.setText(null); dg9.setText(null);
		sp10.setText(""); m10.setText(null); sl10.setText(null); dg10.setText(null);
		mstong.setText(null);
		client.close();
	}
	
	// Xử lí nút Thêm
	public void Them(ActionEvent event) throws Exception {
		if (msnhanvien.getText().equals("")) {// nhớ đổi chỗ này
			JOptionPane.showMessageDialog(null, "Ai đang thực hiện giao dịch?", "Cảnh báo người dùng", JOptionPane.WARNING_MESSAGE);
		} else {
			MongoClient client = new MongoClient("localhost",27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("Menu");
			//
			Document ma = new Document("Ma", msnhapma.getText());
			Document ten = new Document("Ten san pham", msnhapma.getText());
			ArrayList<Document> mahoacten = new ArrayList<Document>();
			mahoacten.add(ma);
			mahoacten.add(ten);
			Document query = new Document("$or", mahoacten);
			Document projection = new Document("_id", 0).append("Phan loai", 0);
			MongoCursor<Document> cursor = col.find(query).projection(projection).iterator();
			Document sp = cursor.next();
			//
			if (sp1.getText().equals("")) {
				sp1.setText("1. "+sp.getString("Ten san pham"));
				dg1.setText(""+sp.getInteger("Gia"));
				m1.setText(""+sp.getString("Ma"));
				sl1.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText()); //Input number from keybroad (String to int)
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp2.getText().equals("")) {
				sp2.setText("2. "+sp.getString("Ten san pham"));
				dg2.setText(""+sp.getInteger("Gia"));
				m2.setText(""+sp.getString("Ma"));
				sl2.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp3.getText().equals("")) {
				sp3.setText("3. "+sp.getString("Ten san pham"));
				dg3.setText(""+sp.getInteger("Gia"));
				m3.setText(""+sp.getString("Ma"));
				sl3.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp4.getText().equals("")) {
				sp4.setText("4. "+sp.getString("Ten san pham"));
				dg4.setText(""+sp.getInteger("Gia"));
				m4.setText(""+sp.getString("Ma"));
				sl4.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp5.getText().equals("")) {
				sp5.setText("5. "+sp.getString("Ten san pham"));
				dg5.setText(""+sp.getInteger("Gia"));
				m5.setText(""+sp.getString("Ma"));
				sl5.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp6.getText().equals("")) {
				sp6.setText("6. "+sp.getString("Ten san pham"));
				dg6.setText(""+sp.getInteger("Gia"));
				m6.setText(""+sp.getString("Ma"));
				sl6.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp7.getText().equals("")) {
				sp7.setText("7. "+sp.getString("Ten san pham"));
				dg7.setText(""+sp.getInteger("Gia"));
				m7.setText(""+sp.getString("Ma"));
				sl7.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp8.getText().equals("")) {
				sp8.setText("8. "+sp.getString("Ten san pham"));
				dg8.setText(""+sp.getInteger("Gia"));
				m8.setText(""+sp.getString("Ma"));
				sl8.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp9.getText().equals("")) {
				sp9.setText("9. "+sp.getString("Ten san pham"));
				dg9.setText(""+sp.getInteger("Gia"));
				m9.setText(""+sp.getString("Ma"));
				sl9.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else if (sp10.getText().equals("")) {
				sp10.setText("10. "+sp.getString("Ten san pham"));
				dg10.setText(""+sp.getInteger("Gia"));
				m10.setText(""+sp.getString("Ma"));
				sl10.setText(msnhapso.getText());
				int i = Integer.parseInt(msnhapso.getText());
				tong = tong + sp.getInteger("Gia")*i;
				mstong.setText(""+tong);
				msnhapma.clear();
				msnhapso.setText(""+1);
			}
			else {
				JOptionPane.showMessageDialog(null, "Hóa đơn đã đầy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				msnhapma.clear();
			}
			client.close();
		}
	}
	
	//xử lí nút in hóa đơn
	public void inhd(ActionEvent event) {
		if (sp1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Không thể in hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Document hoadon = new Document("Nhan vien", msnhanvien.getText()).append("Thoi gian", msngay.getText())
					.append("sp1", new Document("Ten san pham", sp1.getText()).append("So luong", sl1.getText()));
			if (!(sp2.getText().equals(""))) {
				hoadon = hoadon.append("sp2", new Document("Ten san pham", sp2.getText()).append("So luong", sl2.getText()));
			}
			if (!(sp3.getText().equals(""))) {
				hoadon = hoadon.append("sp3", new Document("Ten san pham", sp3.getText()).append("So luong", sl3.getText()));
			}
			if (!(sp4.getText().equals(""))) {
				hoadon = hoadon.append("sp4", new Document("Ten san pham", sp4.getText()).append("So luong", sl4.getText()));
			}
			if (!(sp5.getText().equals(""))) {
				hoadon = hoadon.append("sp5", new Document("Ten san pham", sp5.getText()).append("So luong", sl5.getText()));
			}
			if (!(sp6.getText().equals(""))) {
				hoadon = hoadon.append("sp6", new Document("Ten san pham", sp6.getText()).append("So luong", sl6.getText()));
			}
			if (!(sp7.getText().equals(""))) {
				hoadon = hoadon.append("sp7", new Document("Ten san pham", sp7.getText()).append("So luong", sl7.getText()));
			}
			if (!(sp8.getText().equals(""))) {
				hoadon = hoadon.append("sp8", new Document("Ten san pham", sp8.getText()).append("So luong", sl8.getText()));
			}
			if (!(sp9.getText().equals(""))) {
				hoadon = hoadon.append("sp9", new Document("Ten san pham", sp9.getText()).append("So luong", sl9.getText()));
			}
			if (!(sp10.getText().equals(""))) {
				hoadon = hoadon.append("sp10", new Document("Ten san pham", sp10.getText()).append("So luong", sl10.getText()));
			}
			hoadon = hoadon.append("Tong thanh toan", tong);
			
			// Insert 1 record to HoaDon in Database
			MongoClient client = new MongoClient("localhost",27017);
			MongoDatabase db = client.getDatabase("quanli");
			MongoCollection<Document> col = db.getCollection("HoaDon");
			col.insertOne(hoadon);
			
			//
			try {
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				PdfWriter.getInstance(doc, new FileOutputStream("Hoadon.pdf"));
				doc.open();
				//Nội dung hóa đơn
				Paragraph para1 = new Paragraph("TRA SUA CAPTAIN TEA");
				Paragraph para2 = new Paragraph("DT: 01643512015");
				Paragraph para3 = new Paragraph("www.captaintea.com.vn");
				Paragraph para4 = new Paragraph("GIO MO CUA: 8H00 DEN 22H00");
				Paragraph para5 = new Paragraph("SAN PHAM");
				Paragraph para51 = new Paragraph("MA                   SO LUONG           DON GIA");
				Paragraph para6 = new Paragraph("--------------------------------------------------------");
				//
				para1.setAlignment(Element.ALIGN_CENTER);
				para2.setAlignment(Element.ALIGN_CENTER);
				para3.setAlignment(Element.ALIGN_CENTER);
				para4.setAlignment(Element.ALIGN_CENTER);
				para5.setSpacingBefore(10);
				para5.setIndentationLeft(145);
				para51.setAlignment(Element.ALIGN_CENTER);
				para6.setAlignment(Element.ALIGN_CENTER);
				//
				doc.add(para1);
				doc.add(para2);
				doc.add(para3);
				doc.add(para4);
				doc.add(para5);
				doc.add(para51);
				doc.add(para6);
				
				//Dữ liệu của hóa đơn
				Paragraph data1 = new Paragraph(sp1.getText());
				Paragraph data12 = new Paragraph(m1.getText()+"                                "+sl1.getText()+"                "+dg1.getText());
				data1.setIndentationLeft(145);
				data12.setIndentationLeft(145);
				doc.add(data1);
				doc.add(data12);
				if (!(sp2.getText().equals(""))) {
					Paragraph data2 = new Paragraph(sp2.getText());
					Paragraph data22 = new Paragraph(m2.getText()+"                                "+sl2.getText()+"                "+dg2.getText());
					data2.setIndentationLeft(145);
					data22.setIndentationLeft(145);
					doc.add(data2);
					doc.add(data22);			
				}
				if (!(sp3.getText().equals(""))) {
					Paragraph data3 = new Paragraph(sp3.getText());
					Paragraph data32 = new Paragraph(m3.getText()+"                                "+sl3.getText()+"                "+dg3.getText());
					data3.setIndentationLeft(145);
					data32.setIndentationLeft(145);
					doc.add(data3);
					doc.add(data32);			
				}
				if (!(sp4.getText().equals(""))) {
					Paragraph data4 = new Paragraph(sp4.getText());
					Paragraph data42 = new Paragraph(m4.getText()+"                                "+sl4.getText()+"                "+dg4.getText());
					data4.setIndentationLeft(145);
					data42.setIndentationLeft(145);
					doc.add(data4);
					doc.add(data42);			
				}
				if (!(sp5.getText().equals(""))) {
					Paragraph data5 = new Paragraph(sp5.getText());
					Paragraph data52 = new Paragraph(m5.getText()+"                                "+sl5.getText()+"                "+dg5.getText());
					data5.setIndentationLeft(145);
					data52.setIndentationLeft(145);
					doc.add(data5);
					doc.add(data52);			
				}
				if (!(sp6.getText().equals(""))) {
					Paragraph data6 = new Paragraph(sp6.getText());
					Paragraph data62 = new Paragraph(m6.getText()+"                                "+sl6.getText()+"                "+dg6.getText());
					data6.setIndentationLeft(145);
					data62.setIndentationLeft(145);
					doc.add(data6);
					doc.add(data62);			
				}
				if (!(sp7.getText().equals(""))) {
					Paragraph data7 = new Paragraph(sp7.getText());
					Paragraph data72 = new Paragraph(m7.getText()+"                                "+sl7.getText()+"                "+dg7.getText());
					data7.setIndentationLeft(145);
					data72.setIndentationLeft(145);
					doc.add(data7);
					doc.add(data72);			
				}
				if (!(sp8.getText().equals(""))) {
					Paragraph data8 = new Paragraph(sp8.getText());
					Paragraph data82 = new Paragraph(m8.getText()+"                                "+sl8.getText()+"                "+dg8.getText());
					data8.setIndentationLeft(145);
					data82.setIndentationLeft(145);
					doc.add(data8);
					doc.add(data82);			
				}
				if (!(sp9.getText().equals(""))) {
					Paragraph data9 = new Paragraph(sp9.getText());
					Paragraph data92 = new Paragraph(m9.getText()+"                                "+sl9.getText()+"                "+dg9.getText());
					data9.setIndentationLeft(145);
					data92.setIndentationLeft(145);
					doc.add(data9);
					doc.add(data92);			
				}
				if (!(sp10.getText().equals(""))) {
					Paragraph data10 = new Paragraph(sp10.getText());
					Paragraph data102 = new Paragraph(m10.getText()+"                                "+sl10.getText()+"                "+dg10.getText());
					data10.setIndentationLeft(145);
					data102.setIndentationLeft(145);
					doc.add(data10);
					doc.add(data102);			
				}
				//
				Paragraph para7 = new Paragraph("--------------------------------------------------------");
				para7.setAlignment(Element.ALIGN_CENTER);
				doc.add(para7);
				Paragraph data11 = new Paragraph("TONG THANH TOAN                       "+tong);
				data11.setIndentationLeft(145);
				data11.setSpacingBefore(10);
				doc.add(data11);
				Paragraph para8 = new Paragraph("--------------------------------------------------------");
				para8.setAlignment(Element.ALIGN_CENTER);
				doc.add(para8);
				Paragraph data13 = new Paragraph("THU NGAN: "+msnhanvien.getText());
				data13.setIndentationLeft(145);
				doc.add(data13);
				Paragraph data14 = new Paragraph("THOI GIAN: "+msngay.getText());
				data14.setIndentationLeft(145);
				doc.add(data14);
				Paragraph para9 = new Paragraph("--------------------------------------------------------");
				para9.setAlignment(Element.ALIGN_CENTER);
				doc.add(para9);
				Paragraph para10 = new Paragraph("HOA DON CO GIA TRI  TRONG  NGAY ");
				para10.setAlignment(Element.ALIGN_CENTER);
				doc.add(para10);
				Paragraph para11 = new Paragraph("VUI LONG GIU PHIEU DE DOI, TRA LAI");
				para11.setAlignment(Element.ALIGN_CENTER);
				doc.add(para11);
				Paragraph para12 = new Paragraph("CHI TIET TAI DICH VU KHACH HANG");
				para12.setAlignment(Element.ALIGN_CENTER);
				doc.add(para12);
				Paragraph para13 = new Paragraph("CAM ON QUY KHACH - HEN GAP LAI");
				para13.setAlignment(Element.ALIGN_CENTER);
				doc.add(para13);
				//
				doc.close();
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Hieuhip\\eclipse-workspace\\Product\\Hoadon.pdf");
			} catch (Exception e) {
	            e.printStackTrace();
	        }
			
			// Clear data and create one new record
			sp1.setText(""); m1.setText(null); sl1.setText(null); dg1.setText(null);
			sp2.setText(""); m2.setText(null); sl2.setText(null); dg2.setText(null);
			sp3.setText(""); m3.setText(null); sl3.setText(null); dg3.setText(null);
			sp4.setText(""); m4.setText(null); sl4.setText(null); dg4.setText(null);
			sp5.setText(""); m5.setText(null); sl5.setText(null); dg5.setText(null);
			sp6.setText(""); m6.setText(null); sl6.setText(null); dg6.setText(null);
			sp7.setText(""); m7.setText(null); sl7.setText(null); dg7.setText(null);
			sp8.setText(""); m8.setText(null); sl8.setText(null); dg8.setText(null);
			sp9.setText(""); m9.setText(null); sl9.setText(null); dg9.setText(null);
			sp10.setText(""); m10.setText(null); sl10.setText(null); dg10.setText(null);
			mstong.setText(null);
			Date date = new Date();
			msngay.setText(date.toString());
		client.close();
		}	
	}	
}
