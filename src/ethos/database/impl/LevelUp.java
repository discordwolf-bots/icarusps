package ethos.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ethos.model.players.Player;
import ethos.model.players.Right;

public class LevelUp implements Runnable {

	public static final String HOST = "198.71.55.34";
	public static final String USER = "wolf";
	public static final String PASS = "52637_Md3108"; 
	public static final String DATABASE = "icarus";
	public static final String TABLE = "icarus_milestones";
	
	private Player player;
	private String milestone;
	private int skill;
	private Connection conn;
	private Statement stmt;
	
	public LevelUp(Player player, int skill, String milestone) {
		this.player = player;
		this.skill = skill;
		this.milestone = milestone;
	}
	
	public boolean connect(String host, String database, String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE, USER, PASS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				System.out.println("Error connecting!");
				return;
			}
			
			String name = player.getName();
			int mode = 0;
			if(player.getMode().isHardcoreIronman()) mode = 1;
			if(player.getMode().isIronman()) mode = 2;
			if(player.getMode().isUltimateIronman()) mode = 3;
			int rights = player.getRights().getPrimary().getValue();
			
			if(player.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				return;
			}
			
			PreparedStatement stmt = prepare("INSERT INTO "+TABLE+" ("
					+ "username, "
					+ "mode, "
					+ "rights, "
					+ "skillId, "
					+ "milestone) "
					+ "VALUES (?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setInt(2, mode);
			stmt.setInt(3, rights);
			stmt.setInt(4, skill);
			stmt.setString(5, milestone);
			stmt.execute();
			
			conn.close();
			destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public PreparedStatement prepare(String query) throws SQLException {
		return conn.prepareStatement(query);
	}
	
	public void destroy() {
        try {
    		conn.close();
        	conn = null;
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
}
