package mappedClasses;
// Generated 9 feb 2024 13:32:47 by Hibernate Tools 6.3.1.Final

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2249119385915570663L;
	private byte deptNo;
	private String dnombre;
	private String loc;

	public Departamentos() {
	}

	public Departamentos(byte deptNo, String dnombre, String loc) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}

	public byte getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(byte deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
