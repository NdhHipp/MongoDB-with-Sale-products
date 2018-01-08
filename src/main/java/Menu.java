
public class Menu {
	private String tensp;
	private String theloai;
	private String masp;
	private double gia;
	//
	public Menu(String tensp, String theloai, String masp, double gia) {
		super();
		this.tensp = tensp;
		this.theloai = theloai;
		this.masp = masp;
		this.gia = gia;
	}
	public void settensp(String tensp) {
		this.tensp = tensp;
	}
	public void settheloai(String theloai) {
		this.theloai = theloai;
	}
	public void setmasp(String masp) {
		this.masp = masp;
	}
	public void setgia(double gia) {
		this.gia = gia;
	}
	public String gettensp() {
		return tensp;
	}
	public String gettheloai() {
		return theloai;
	}
	public String getmasp() {
		return masp;
	}
	public double getgia() {
		return gia;
	}
}
