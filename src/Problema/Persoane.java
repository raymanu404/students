package Problema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sql.ConnectionEventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.*;

public class Persoane extends JFrame {
	private boolean clickedInsert = false;
	private boolean clickedUpdate = false;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNume;
	private JTextField txtVarsta;
	private JToolBar toolBar;
	private JButton btnFastLeft;
	private JButton btnFastRight;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnFind;
	private JButton btnSave;
	private JButton	btnDisclaimer;
	private JTextField txtPage;
	private JLabel lblMesaj;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Persoane frame = new Persoane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Persoane() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Tabel - Persoane");
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 612, 38);				
		
		//instantierea butoanelor 
		btnFastLeft = new JButton();
		btnFastRight = new JButton();
		btnLeft = new JButton();
		btnRight = new JButton();
		btnInsert = new JButton();
		btnUpdate = new JButton();
		btnFind = new JButton();
		btnDelete = new JButton();
		btnSave = new JButton();
		btnDisclaimer = new JButton();
		txtPage = new JTextField(7);
		txtPage.setMaximumSize(txtPage.getPreferredSize());
		
		//setarea icon-urilor 
		btnFastLeft.setIcon(new ImageIcon("src/img/rewind.png"));		
		btnLeft.setIcon(new ImageIcon("src/img/left.png"));		
		btnFastRight.setIcon(new ImageIcon("src/img/fast_forward.png"));
		btnRight.setIcon(new ImageIcon("src/img/right.png"));
		btnInsert.setIcon(new ImageIcon("src/img/add.png"));
		btnUpdate.setIcon(new ImageIcon("src/img/edit.png"));
		btnFind.setIcon(new ImageIcon("src/img/find.png"));
		btnDelete.setIcon(new ImageIcon("src/img/delete.png"));
		btnSave.setIcon(new ImageIcon("src/img/save.png"));
		btnDisclaimer.setIcon(new ImageIcon("src/img/disclaimer.png"));
		
		
		//adaugarea butoanelor in JToolBar
		
		toolBar.add(btnFastLeft);
		toolBar.add(btnLeft);
		toolBar.add(txtPage);
		toolBar.add(btnRight);
		toolBar.add(btnFastRight);
		toolBar.add(btnInsert);
		toolBar.add(btnUpdate);
		toolBar.add(btnDelete);
		toolBar.add(btnFind);
		toolBar.add(btnSave);
		toolBar.add(btnDisclaimer);
		contentPane.add(toolBar);

		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(158, 87, 59, 19);
		contentPane.add(lblNewLabel);
	
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setBounds(227, 87, 96, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nume");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(158, 116, 59, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Varsta");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(158, 162, 50, 38);
		contentPane.add(lblNewLabel_2);
		
		txtNume = new JTextField();
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNume.setColumns(10);
		txtNume.setBounds(227, 126, 155, 19);
		contentPane.add(txtNume);
		
		txtVarsta = new JTextField();
		txtVarsta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVarsta.setColumns(10);
		txtVarsta.setBounds(227, 172, 96, 19);
		contentPane.add(txtVarsta);
		
		lblMesaj = new JLabel("_");
		lblMesaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMesaj.setBackground(Color.WHITE);
		lblMesaj.setOpaque(true);
		lblMesaj.setBounds(121, 276, 388, 38);
		contentPane.add(lblMesaj);
		
		
		try {
			
			ConectareMySQL con  = new ConectareMySQL();
			
			//BTN FAST LEFT
			btnFastLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {	
						
						con.getFirst();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {}
				}
			});
			//BTN LEFT
			btnLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {	
						
						con.getPrevious();	
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {						
					}
				}
			});
											
			//BTN RIGHT
			
			btnRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {						
						con.getNext();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {						
					}
				}
			});
			
			//BTN FAST RIGHT
			btnFastRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						con.getLast();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {}
				}
			});
			
			//BTN INSERT
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						 clickedInsert	= true;
						 con.getActivateButtons(false);						 
						 txtPage.setText(con.line+ "/"+ con.count);						 
					
				}
			});
			
			//BTN UPDATE
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
						 clickedUpdate = true;
						 con.getActivateButtons(false);						 
						 txtPage.setText(con.line+ "/"+ con.count);
											
				}
			});
			
			//BTN DELETE
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						con.getDelete();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {}
				}
			});
			
			//BTN FIND
			btnFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						con.getFind();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {}										
				}
			});
			
			//BTN SAVE
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						con.getActivateButtons(false);
						con.getSave();
						txtPage.setText(con.line+ "/"+ con.count);
						con.getActivateButtons(true);
					}catch(SQLException ex) {}
				}
			});
			
			//BTN DISCLAIMER
			btnDisclaimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						con.getDisclaimer();
						txtPage.setText(con.line+ "/"+ con.count);
					}catch(SQLException ex) {}
				}
			});
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	class ConectareMySQL {
		private Connection connect;
		private PreparedStatement insert;
		private PreparedStatement update;
		private PreparedStatement delete;
		private PreparedStatement find;
		public final String url = "jdbc:mysql://127.0.0.1:3306/test";
		public Statement sql;
		public ResultSet rs;
		public int line;
		public int count;
		
		public ConectareMySQL() throws SQLException  {
			
			connect = DriverManager.getConnection(url,"root","root");
			sql = (Statement)connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //neaparat pt update (si cursor)
			rs = sql.executeQuery("select * from persoane");
			count = 0;
			while(rs.next()) {
				count++;
			}
						
			txtID.setEnabled(false);		
			txtNume.setEnabled(false);		
			txtVarsta.setEnabled(false);
			btnSave.setEnabled(false);
			btnDisclaimer.setEnabled(false);	
			rs.first();
			txtID.setText(Integer.toString(rs.getInt("ID")));
			txtNume.setText(rs.getString("nume"));
			txtVarsta.setText(Integer.toString(rs.getInt(3)));
			line = 1;
		}
				
		
		public void getNext() throws SQLException{	
			
			if(line == count -1) {
				btnFastRight.setEnabled(false);
				btnRight.setEnabled(false);
			}
			
			if(!rs.isLast()) {
				btnFastLeft.setEnabled(true);
				btnLeft.setEnabled(true);
				rs.next();			
				txtID.setText(Integer.toString(rs.getInt("ID")));
				txtNume.setText(rs.getString("nume"));
				txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
				line++;
			}
												
		}
		public void getPrevious() throws SQLException{	
			
			if(line == 2) {
				btnFastLeft.setEnabled(false);
				btnLeft.setEnabled(false);
			}
			if(!rs.isFirst()) {
				btnFastRight.setEnabled(true);
				btnRight.setEnabled(true);
				rs.previous();			
				txtID.setText(Integer.toString(rs.getInt("ID")));
				txtNume.setText(rs.getString("nume"));
				txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
				line--;
			}
		}
		public void getFirst() throws SQLException{
				line = 1;
				btnFastRight.setEnabled(true);
				btnRight.setEnabled(true);
				rs.first();
				txtID.setText(Integer.toString(rs.getInt("ID")));
				txtNume.setText(rs.getString("nume"));
				txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
				btnFastLeft.setEnabled(false);
				btnLeft.setEnabled(false);	
				
		}
		public void getLast() throws SQLException {	
				line = count;
				btnFastLeft.setEnabled(true);
				btnLeft.setEnabled(true);
				rs.last();
				txtID.setText(Integer.toString(rs.getInt("ID")));
				txtNume.setText(rs.getString("nume"));
				txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
				btnFastRight.setEnabled(false);
				btnRight.setEnabled(false);
				
		}
		public void getActivateButtons(boolean flag){
			
				btnFastLeft.setEnabled(flag);
				btnLeft.setEnabled(flag);
				btnFastRight.setEnabled(flag);
				btnRight.setEnabled(flag);
				btnInsert.setEnabled(flag);
				btnUpdate.setEnabled(flag);
				btnDelete.setEnabled(flag);			
				btnFind.setEnabled(flag);
				
				btnSave.setEnabled(!flag);
				btnDisclaimer.setEnabled(!flag);
				txtID.setEnabled(!flag);
				txtNume.setEnabled(!flag);
				txtVarsta.setEnabled(!flag);
												
		}
		
		public void getInsert() throws SQLException{
			
			int id = 0 ,varsta = 0;
			String nume = "";			
			nume = txtNume.getText();
			
			if(!txtID.getText().isEmpty() && !txtNume.getText().isEmpty() && !txtVarsta.getText().isEmpty()) {
				if(cifre(txtID.getText().toString()) && cifre(txtVarsta.getText().toString())) {
					id = Integer.parseInt(txtID.getText().toString());
					varsta = Integer.parseInt(txtVarsta.getText().toString());
					
					insert = connect.prepareStatement("insert into persoane(id,nume,varsta) values (?,?,?);");					
					insert.setInt(1, id);
					insert.setString(2, nume);
					insert.setInt(3, varsta);
					insert.execute();
					lblMesaj.setText("Ati adaugat o persoana noua!");
					rs = sql.executeQuery("select * from persoane");
					rs.first();
					txtID.setText(Integer.toString(rs.getInt("ID")));
					txtNume.setText(rs.getString("nume"));
					txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
					line++;
					count++;
					clickedInsert = false;
				}				
			}				
		}
		
		public void getUpdate() throws SQLException{
			int id = 0 ,varsta = 0;
			String nume = "";			
			nume = txtNume.getText();
			txtID.setEnabled(false);
			if(!txtID.getText().isEmpty() && !txtNume.getText().isEmpty() && !txtVarsta.getText().isEmpty()) {
				if(cifre(txtID.getText().toString()) && cifre(txtVarsta.getText().toString())) {
					id = Integer.parseInt(txtID.getText().toString());
					varsta = Integer.parseInt(txtVarsta.getText().toString());					
					update  = connect.prepareStatement("update persoane set nume= ? , varsta = ? where id=?;");										
					update.setString(1, nume);
					update.setInt(2, varsta);
					update.setInt(3, id);
					update.executeUpdate();
					
					lblMesaj.setText("Ati editat  o persoana!");
					rs = sql.executeQuery("select * from persoane");
					rs.first();
					txtID.setText(Integer.toString(rs.getInt("ID")));
					txtNume.setText(rs.getString("nume"));
					txtVarsta.setText(Integer.toString(rs.getInt("varsta")));					
					getActivateButtons(true);
					clickedUpdate = false;
				}
				
			}	
		
		}
		public void getDelete() throws SQLException,IllegalStateException {
			int id = Integer.parseInt(txtID.getText().toString());				
			String nume = txtNume.getText();
			int choice = JOptionPane.showOptionDialog(null,"Sunteti sigur ca doriti sa il/o stergeti pe  "+nume,"Confirmare Stergere",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
			if(choice == JOptionPane.YES_OPTION) {
				delete  = connect.prepareStatement("delete from persoane where id=?;");										
				delete.setInt(1, id);			
				delete.execute();					
				lblMesaj.setText("Ati sters  persoana!");
				rs = sql.executeQuery("select * from persoane");
				rs.first();
				txtID.setText(Integer.toString(rs.getInt("ID")));
				txtNume.setText(rs.getString("nume"));
				txtVarsta.setText(Integer.toString(rs.getInt("varsta")));
				line--;
				count--;
			}
																								
		}
		public void getFind() throws SQLException {
			 String numele =  JOptionPane.showInputDialog(null,"Cautare dupa nume: ");
			 if(numele != null) {
				
				find = connect.prepareStatement("SELECT count(*) from persoane where nume= ?;");
				find.setString(1, numele);
				
				ResultSet result = find.executeQuery();
				result.next();
				if(result.getInt(1) > 0) {
					lblMesaj.setText("Felicitari  " + numele + " sunteti in baza noastra de date!");
				}else {
					lblMesaj.setText("Ne pare rau nu sunteti in Baza noastra de date!");
				}								
			 }else {
				 lblMesaj.setText("Nu ati introdus nimic!");
			 }
		}
		public void getSave() throws SQLException {
				if(clickedInsert) {
					getInsert();
				}
				
				if(clickedUpdate) {
					getUpdate();
				}
							
		}
		public void getDisclaimer() throws SQLException{
			getActivateButtons(true);
		}
		public boolean cifre(String a) {
			int  i = 0;int nr=0;
			while(i < a.length() ) {
				   if(  a.charAt(i) == '0' ||
						a.charAt(i) == '1' ||
						a.charAt(i) == '2' ||
						a.charAt(i) == '3' ||
						a.charAt(i) == '4' ||
						a.charAt(i) == '5' ||
						a.charAt(i) == '6' ||
						a.charAt(i) == '7' ||
						a.charAt(i) == '8' ||
						a.charAt(i) == '9' ) 
					   	nr++;			
				 
				   i++;
			}
			if( nr == a.length() )
				return true;
			
			return false;
		}
		
	}
}
