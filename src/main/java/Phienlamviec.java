import java.sql.Date;
import java.sql.Time;

public class Phienlamviec {
	private String user;
	private String password;
	private String position;
	private Date ngay;
	private Time gio;
	//
	public void setuser(String user) {
		this.user = user;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public void setposition(String position) {
		this.position = position;
	}
	public void setngay(Date ngay) {
		this.ngay = ngay;
	}
	public void setgio(Time gio) {
		this.gio = gio;
	}
	//
	public String getuser() {
		return user;
	}
	public String getpassword() {
		return password;
	}
	public String getposition() {
		return position;
	}
	public Date getngay() {
		return ngay;
	}
	public Time getgio() {
		return gio;
	}
		
}
