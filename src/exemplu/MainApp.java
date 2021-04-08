package exemplu;

import java.sql.*;

public class MainApp {

	public static void main(String[] args) throws SQLException {
		
		//TEST mysql connection
		
		String url = "jdbc:mysql://127.0.0.1:3306/magazin";
		Connection connect = DriverManager.getConnection(url,"root","");
		Statement sql;
		sql = (Statement)connect.createStatement();
						
		ResultSet result ;				
		result = sql.executeQuery("select * from produs");
		System.out.println("Before update:");
		while(result.next()) {
			System.out.println("Linia " +result.getRow());
			System.out.println("id :"+result.getInt("ID") + " nume produs :"+result.getString("nume_produs")+ " pret : "+result.getDouble("pret")+ " cantitate " + result.getInt("cantitate")+"\n");
			
		}
		
		PreparedStatement sql2 ;
		String query = "update produs set nume_produs =? where ID=? ";
		sql2 = connect.prepareStatement(query);
		sql2.setString(1, "Banane");
		sql2.setInt(2, 1);		
		sql2.executeUpdate();
		
		
		//Pentru procedura stocata!
		Statement sql3;
		CallableStatement call = connect.prepareCall("{call adaugare(?,?,?)}");	
		call.setString(1,"Ananas");
		call.setDouble(2, 23.3);
		call.setInt(3, 10);
		call.execute();
		sql3 = (Statement)connect.createStatement();
		
		ResultSet rs = sql3.executeQuery("select * from produs");
		System.out.println("After update:");
		while(rs.next()) {
			System.out.println("ID "+ rs.getInt("ID") + " nume_produs : "+rs.getString(2) + " pret :"+rs.getDouble(3) + " cantitate : "+ rs.getInt("cantitate"));
		}
		
		connect.close();
		sql.close();
		sql2.close();
		sql3.close();
		result.close();
	}

}
