import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class booksinfo extends JFrame {

	private JPanel contentPane;
	private JTable table;			//table

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booksinfo frame = new booksinfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textFieldTitle;
	private JTextField textFieldAuthor;
	private JTextField textFieldYear;
	private JTextField textFieldBNO;
	private JTextField textFieldSN;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelection = new JComboBox();
	
	//book no validation
	public boolean bnvalid (String q)
	{
		if( (!q.equals("")) && (q.matches("^[0-9]*$")))
			return true;
		else
			return false;
	}
	
	//year validation
	public boolean YearValid (String v)
	{
		if( (!v.equals("")) && (v.matches("^[0-9]*$")) && (v.length() == 4) )
			return true;
		else
			return false;
	}
	
	//Title validation only alphanumeric values
	public boolean TitleValid ( String x)
	{
		if ( (!x.equals("")) && (x.matches("^[A-Za-z0-9]*$")) )
			return true;
		else
			return false;
	}
	
	//select query 
	public void refreshTable()
	{
		try
		{
			String query = "select *from books ";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}

	/**
	 * Create the frame.
	 */
	public booksinfo() {
		//check db connection
		conn = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 501);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(243, 95, 442, 343);
		contentPane.add(scrollPane);
		
		//display data
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
				{
					int row = table.getSelectedRow();
					String sn_=(table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select *from books where SN='"+sn_+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{
						textFieldSN.setText(rs.getString("SN"));
						textFieldTitle.setText(rs.getString("title"));
						textFieldAuthor.setText(rs.getString("author"));
						textFieldYear.setText(rs.getString("year"));
						textFieldBNO.setText(rs.getString("bno"));
					}
					
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		//load data
		JButton btnLoadTable = new JButton("Load");
		btnLoadTable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnLoadTable.setBounds(113, 386, 100, 23);
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query = "select *from books ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		contentPane.add(btnLoadTable);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(29, 157, 46, 14);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(96, 154, 86, 20);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(29, 198, 46, 14);
		lblAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(lblAuthor);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(96, 195, 86, 20);
		textFieldAuthor.setColumns(10);
		contentPane.add(textFieldAuthor);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(29, 251, 46, 14);
		lblYear.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(lblYear);
		
		textFieldYear = new JTextField();
		textFieldYear.setBounds(96, 248, 86, 20);
		textFieldYear.setColumns(10);
		contentPane.add(textFieldYear);
		
		JLabel lblBno = new JLabel("BNO");
		lblBno.setBounds(29, 299, 46, 14);
		lblBno.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(lblBno);
		
		textFieldBNO = new JTextField();
		textFieldBNO.setBounds(96, 296, 86, 20);
		textFieldBNO.setColumns(10);
		contentPane.add(textFieldBNO);
		
		//insert data
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					boolean h , k, l;
					h = bnvalid(textFieldBNO.getText());				// book no validation
					k = YearValid(textFieldYear.getText());				// year validation
					l = TitleValid(textFieldTitle.getText());			// Title validation
					
					if(h == true && k == true && l == true)
					{
						String query = "insert into books (SN,Title,Author,Year,BNO) values (?,?,?,?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1,textFieldSN.getText());
						pst.setString(2,textFieldTitle.getText());
						pst.setString(3,textFieldAuthor.getText());
						pst.setString(4,textFieldYear.getText());
						pst.setString(5,textFieldBNO.getText());
						pst.execute();
						
						JOptionPane.showMessageDialog(null,"Data Saved");
						
						pst.close();
						
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				refreshTable();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnSave.setBounds(10, 346, 89, 23);
		contentPane.add(btnSave);
		
		
		//updata data
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query = "update books set SN='"+textFieldSN.getText()+"', Title='"+textFieldTitle.getText()+"' , Author='"+textFieldAuthor.getText()+"',Year='"+textFieldYear.getText()+"' , BNO='"+textFieldBNO.getText()+"' where BNO='"+textFieldBNO.getText()+"' or SN='"+textFieldSN.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Updated");
					
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				refreshTable();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnUpdate.setBounds(113, 346, 99, 23);
		contentPane.add(btnUpdate);
		
		//delete data
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if ( action == 0 )
				{	
					try
					{
						String query = "delete from books where BNO='"+textFieldBNO.getText()+"' ";
						PreparedStatement pst = conn.prepareStatement(query);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null,"Data Updated");
						
						pst.close();
						
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e);
					}
				refreshTable();
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnDelete.setBounds(10, 387, 90, 23);
		contentPane.add(btnDelete);
		
		textFieldSN = new JTextField();
		textFieldSN.setColumns(10);
		textFieldSN.setBounds(96, 107, 86, 20);
		contentPane.add(textFieldSN);
		
		JLabel lblSn = new JLabel("SN");
		lblSn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSn.setBounds(29, 110, 46, 14);
		contentPane.add(lblSn);
		
		
		//display data from combo box 
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try
				{
					String selection = (String)comboBoxSelection.getSelectedItem();				
					String query = "select *from books where "+selection+"=? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		textFieldSearch.setBounds(487, 41, 153, 30);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		
		comboBoxSelection.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"SN", "Titile", "Author", "Year", "BNO"}));
		comboBoxSelection.setBounds(290, 41, 161, 30);
		contentPane.add(comboBoxSelection);
	}
}
