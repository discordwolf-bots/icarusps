package ethos.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ethos.model.players.Player;
import ethos.model.players.Right;
 
public class Highscores implements Runnable {

	public static final String HOST = "198.71.55.34";
	public static final String USER = "wolf";
	public static final String PASS = "52637_Md3108"; 
	public static final String DATABASE = "icarus";
	public static final String TABLE = "icarus_hs";
	
	private Player player;
	private Connection conn;
	private Statement stmt;
	
	public Highscores(Player player) {
		this.player = player;
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
			
			int mode = 0;
			if(player.getMode().isHardcore()) mode = 1;
			if(player.getMode().isIronman()) mode = 2;
			if(player.getMode().isUltimateIronman()) mode = 3;
			int rights = player.getRights().getPrimary().getValue();
			
			if(player.getRights().isOrInherits(Right.ADMINISTRATOR)) {
				return;
			}
			
			String name = player.getName();
			String queryFetch = "SELECT * FROM "+TABLE+" WHERE username='"+name+"'";
			Statement stmtFetch = conn.createStatement();
			ResultSet rsFetchI = stmtFetch.executeQuery(queryFetch);
			
			int rcFetch = rsFetchI.last() ? rsFetchI.getRow() : 0;
			
			if(rcFetch == 0) {
				System.out.println("New user: " + name);
				PreparedStatement stmtInsertRow = prepare("INSERT INTO "+TABLE+" ("
						+ "username, "
						+ "mode, "
						+ "rights) "
						+ "VALUES (?,?,?)");
				stmtInsertRow.setString(1, name);
				stmtInsertRow.setInt(2, mode);
				stmtInsertRow.setInt(3, rights);
				stmtInsertRow.execute();
			}
			ResultSet rsFetch = stmtFetch.executeQuery(queryFetch);
			while(rsFetch.next()) {
				boolean updateRequired = false;
				
				int oMode = rsFetch.getInt("mode");
				int nMode = mode;
				if(oMode != nMode) updateRequired = true;
				
				int oRights = rsFetch.getInt("rights");
				int nRights = rights;
				if(oRights != nRights) updateRequired = true;
				
				int oTLevel = rsFetch.getInt("overall_level");
				int nTLevel = player.getPA().totalLevel();
				if(oTLevel != nTLevel) updateRequired = true;
				
				long oTExp = rsFetch.getInt("overall_xp");
				long nTExp = player.getPA().getTotalXP();
				if(oTExp != nTExp) updateRequired = true;
				
				double oAttack = rsFetch.getInt("attack_xp");
				double nAttack = Double.valueOf(player.playerXP[player.playerAttack]).longValue();
				if(oAttack != nAttack) updateRequired = true;
				
				double oDefence = rsFetch.getInt("defence_xp");
				double nDefence = Double.valueOf(player.playerXP[player.playerDefence]).longValue();
				if(oDefence != nDefence) updateRequired = true;
				
				double oStrength = rsFetch.getInt("strength_xp");
				double nStrength = Double.valueOf(player.playerXP[player.playerStrength]).longValue();
				if(oStrength != nStrength) updateRequired = true;
				
				double oConstitution = rsFetch.getInt("constitution_xp");
				double nConstitution = Double.valueOf(player.playerXP[player.playerHitpoints]).longValue();
				if(oConstitution != nConstitution) updateRequired = true;
				
				double oRanged = rsFetch.getInt("ranged_xp");
				double nRanged = Double.valueOf(player.playerXP[player.playerRanged]).longValue();
				if(oRanged != nRanged) updateRequired = true;
				
				double oPrayer = rsFetch.getInt("prayer_xp");
				double nPrayer = Double.valueOf(player.playerXP[player.playerPrayer]).longValue();
				if(oPrayer != nPrayer) updateRequired = true;
				
				double oMagic = rsFetch.getInt("magic_xp");
				double nMagic = Double.valueOf(player.playerXP[player.playerMagic]).longValue();
				if(oMagic != nMagic) updateRequired = true;
				
				double oCooking = rsFetch.getInt("cooking_xp");
				double nCooking = Double.valueOf(player.playerXP[player.playerCooking]).longValue();
				if(oCooking != nCooking) updateRequired = true;
				
				double oWoodcutting = rsFetch.getInt("woodcutting_xp");
				double nWoodcutting = Double.valueOf(player.playerXP[player.playerWoodcutting]).longValue();
				if(oWoodcutting != nWoodcutting) updateRequired = true;
				
				double oFletching = rsFetch.getInt("fletching_xp");
				double nFletching = Double.valueOf(player.playerXP[player.playerFletching]).longValue();
				if(oFletching != nFletching) updateRequired = true;
				
				double oFishing = rsFetch.getInt("fishing_xp");
				double nFishing = Double.valueOf(player.playerXP[player.playerFishing]).longValue();
				if(oFishing != nFishing) updateRequired = true;
				
				double oFiremaking = rsFetch.getInt("firemaking_xp");
				double nFiremaking = Double.valueOf(player.playerXP[player.playerFiremaking]).longValue();
				if(oFiremaking != nFiremaking) updateRequired = true;
				
				double oCrafting = rsFetch.getInt("crafting_xp");
				double nCrafting = Double.valueOf(player.playerXP[player.playerCrafting]).longValue();
				if(oCrafting != nCrafting) updateRequired = true;
				
				double oSmithing = rsFetch.getInt("smithing_xp");
				double nSmithing = Double.valueOf(player.playerXP[player.playerSmithing]).longValue();
				if(oSmithing != nSmithing) updateRequired = true;
				
				double oMining = rsFetch.getInt("mining_xp");
				double nMining = Double.valueOf(player.playerXP[player.playerMining]).longValue();
				if(oMining != nMining) updateRequired = true;
				
				double oHerblore = rsFetch.getInt("herblore_xp");
				double nHerblore = Double.valueOf(player.playerXP[player.playerHerblore]).longValue();
				if(oHerblore != nHerblore) updateRequired = true;
				
				double oAgility = rsFetch.getInt("agility_xp");
				double nAgility = Double.valueOf(player.playerXP[player.playerAgility]).longValue();
				if(oAgility != nAgility) updateRequired = true;
				
				double oThieving = rsFetch.getInt("thieving_xp");
				double nThieving = Double.valueOf(player.playerXP[player.playerThieving]).longValue();
				if(oThieving != nThieving) updateRequired = true;
				
				double oSlayer = rsFetch.getInt("slayer_xp");
				double nSlayer = Double.valueOf(player.playerXP[player.playerSlayer]).longValue();
				if(oSlayer != nSlayer) updateRequired = true;
				
				double oFarming = rsFetch.getInt("farming_xp");
				double nFarming = Double.valueOf(player.playerXP[player.playerFarming]).longValue();
				if(oFarming != nFarming) updateRequired = true;
				
				double oRunecraft = rsFetch.getInt("runecrafting_xp");
				double nRunecraft = Double.valueOf(player.playerXP[player.playerRunecrafting]).longValue();
				if(oRunecraft != nRunecraft) updateRequired = true;
				
				double oHunter = rsFetch.getInt("hunter_xp");
				double nHunter = Double.valueOf(player.playerXP[21]).longValue();
				if(oHunter != nHunter) updateRequired = true;				
				
				if(updateRequired) {
					String updateQuery = "UPDATE "+TABLE+" SET ";
					String updateQueryTime = "UPDATE "+TABLE+" SET ";
					if(oMode != nMode) {
						updateQuery += "mode=" + mode +", ";
					}
					if(oRights != nRights) {
						updateQuery += "rights=" + nRights +", ";
					}
					if(oTLevel != nTLevel) {
						updateQuery += "overall_level=" + nTLevel +", ";
					}
					if(oTExp != nTExp) {
						updateQuery += "overall_xp=" + nTExp +", ";
						updateQueryTime += "overall_ts=CURRENT_TIMESTAMP, ";
					}
					if(oAttack != nAttack) {
						updateQuery += "attack_xp=" + nAttack +", ";
						updateQueryTime += "attack_ts=CURRENT_TIMESTAMP, ";
					}
					if(oDefence != nDefence) {
						updateQuery += "defence_xp=" + nDefence +", ";
						updateQueryTime += "defence_ts=CURRENT_TIMESTAMP, ";
					}
					if(oStrength != nStrength) {
						updateQuery += "strength_xp=" + nStrength +", ";
						updateQueryTime += "strength_ts=CURRENT_TIMESTAMP, ";
					}
					if(oConstitution != nConstitution) {
						updateQuery += "constitution_xp=" + nConstitution +", ";
						updateQueryTime += "constitution_ts=CURRENT_TIMESTAMP, ";
					}
					if(oRanged != nRanged) {
						updateQuery += "ranged_xp=" + nRanged +", ";
						updateQueryTime += "ranged_ts=CURRENT_TIMESTAMP, ";
					}
					if(oPrayer != nPrayer) {
						updateQuery += "prayer_xp=" + nPrayer +", ";
						updateQueryTime += "prayer_ts=CURRENT_TIMESTAMP, ";
					}
					if(oMagic != nMagic) {
						updateQuery += "magic_xp=" + nMagic +", ";
						updateQueryTime += "magic_ts=CURRENT_TIMESTAMP, ";
					}
					if(oCooking != nCooking) {
						updateQuery += "cooking_xp=" + nCooking +", ";
						updateQueryTime += "cooking_ts=CURRENT_TIMESTAMP, ";
					}
					if(oWoodcutting != nWoodcutting) {
						updateQuery += "woodcutting_xp=" + nWoodcutting +", ";
						updateQueryTime += "woodcutting_ts=CURRENT_TIMESTAMP, ";
					}
					if(oFletching != nFletching) {
						updateQuery += "fletching_xp=" + nFletching +", ";
						updateQueryTime += "fletching_ts=CURRENT_TIMESTAMP, ";
					}
					if(oFishing != nFishing) {
						updateQuery += "fishing_xp=" + nFishing +", ";
						updateQueryTime += "fishing_ts=CURRENT_TIMESTAMP, ";
					}
					if(oFiremaking != nFiremaking) {
						updateQuery += "firemaking_xp=" + nFiremaking +", ";
						updateQueryTime += "firemaking_ts=CURRENT_TIMESTAMP, ";
					}
					if(oCrafting != nCrafting) {
						updateQuery += "crafting_xp=" + nCrafting +", ";
						updateQueryTime += "crafting_ts=CURRENT_TIMESTAMP, ";
					}
					if(oSmithing != nSmithing) {
						updateQuery += "smithing_xp=" + nSmithing +", ";
						updateQueryTime += "smithing_ts=CURRENT_TIMESTAMP, ";
					}
					if(oMining != nMining) {
						updateQuery += "mining_xp=" + nMining +", ";
						updateQueryTime += "mining_ts=CURRENT_TIMESTAMP, ";
					}
					if(oHerblore != nHerblore) {
						updateQuery += "herblore_xp=" + nHerblore +", ";
						updateQueryTime += "herblore_ts=CURRENT_TIMESTAMP, ";
					}
					if(oAgility != nAgility) {
						updateQuery += "agility_xp=" + nAgility +", ";
						updateQueryTime += "agility_ts=CURRENT_TIMESTAMP, ";
					}
					if(oThieving != nThieving) {
						updateQuery += "thieving_xp=" + nThieving +", ";
						updateQueryTime += "thieving_ts=CURRENT_TIMESTAMP, ";
					}
					if(oSlayer != nSlayer) {
						updateQuery += "slayer_xp=" + nSlayer +", ";
						updateQueryTime += "slayer_ts=CURRENT_TIMESTAMP, ";
					}
					if(oFarming != nFarming) {
						updateQuery += "farming_xp=" + nFarming +", ";
						updateQueryTime += "farming_ts=CURRENT_TIMESTAMP, ";
					}
					if(oRunecraft != nRunecraft) {
						updateQuery += "runecrafting_xp=" + nRunecraft +", ";
						updateQueryTime += "runecrafting_ts=CURRENT_TIMESTAMP, ";
					}
					if(oHunter != nHunter) {
						updateQuery += "hunter_xp=" + nHunter +", ";
						updateQueryTime += "hunter_ts=CURRENT_TIMESTAMP, ";
					}
					
					updateQuery = updateQuery.substring(0, updateQuery.length()-2);
					updateQueryTime = updateQueryTime.substring(0, updateQueryTime.length()-2);
					
					updateQuery += " WHERE username='" + name + "'";
					updateQueryTime += " WHERE username='" + name + "'";
					
					PreparedStatement stmtUpdate = prepare(updateQuery);
					PreparedStatement stmtUpdateTime = prepare(updateQueryTime);
					
					stmtUpdate.execute();
					stmtUpdate.close();
					
					stmtUpdateTime.execute();
					stmtUpdateTime.close();
					System.out.println("Updated highscores: " + name);
				}
			}
				
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