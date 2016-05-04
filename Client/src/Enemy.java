import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enemy {
	private float baseHP;
	// maxHP is calculated client-side by multiplying baseHP and hpMult
	private float maxHP;
	private float dmgMult;
	private float hpMult;
	private String weaponName;
	private String weaponPoison;
	private float maxWeaponDmg;
	// con must be used to access DB
	Connection con;
	// CallableStatement is used for stored procedures
	CallableStatement stmt;

	public Enemy(int lvl, String chName) throws SQLException {
		// Make the connection to SQL Server for queries.
		this.con = ConnectURL.makeConnection();
		String sql = "{call getUEnemy (?, ?)}";
		stmt = con.prepareCall(sql);
		stmt.setString(1, chName);
		stmt.setString(2, Integer.toString(lvl));
		ResultSet res = stmt.executeQuery();
		// Use res.getString to get columns from returned row
		// 1: Actual_hp 2: Base_HP 3: Exp 4: Floor 5: Room
		// 6: ChID 7: InID
		while (res.next()) {
			this.baseHP = Float.parseFloat(res.getString(1));
		}
		sql = "{call getMultipliersLvl (?)}";
		stmt = con.prepareCall(sql);
		stmt.setString(1, Integer.toString(lvl));
		res = stmt.executeQuery();
		while (res.next()) {
			this.dmgMult = Float.parseFloat(res.getString(1));
			this.hpMult = Float.parseFloat(res.getString(2));
		}
		this.maxHP = this.baseHP * this.hpMult;
	}

}
