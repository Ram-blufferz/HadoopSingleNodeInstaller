package view;
import service.HadoopService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.border.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.table.AbstractTableModel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableCellRenderer;
public class Introduction extends JFrame implements ActionListener,WindowListener{
	HadoopService hadoopService = new HadoopService();
	JLabel welcome;
	public JTextArea log=new JTextArea();
	public JProgressBar progress;
	JLabel[] label=new JLabel[10];
	ArrayList<String> softwareToBeInstalled =new ArrayList<String>();
	public BufferedImage tick,icon;
	public BufferedImage cross;
	JLabel detailedIntro;
	TitledBorder title;
	JPanel[] contentPanel=new JPanel[10];
	JPanel titlePanel=new JPanel();
	JPanel stagesPanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	Border blackLine=BorderFactory.createLineBorder(Color.black);
	Border blueLine=BorderFactory.createLineBorder(Color.blue);
	Border compound,raisedbevel,loweredbevel;
	static int labelCount=0;
	String[] text=new String[10];
	int max=0;
	boolean javaInstalled=true;
	String[] softwares={"curl","sed","tar"};
	public JLabel[] results=new JLabel[softwares.length];
	JTable softwareTable,sshTable,hadoopDirTable,configTable,javaVariableTable,completeTable,preTable;
	public  JTextArea location;
	public  JButton openButton,next,quit,install,back,finish;
	public JFileChooser fc;
	JLabel browseLabel=new JLabel("<html><b><font color='blue' size='3'>Choose Location for Hadoop Directory</font></b></html>");
	public String javaLocation;
	public JLabel logLabel=new JLabel("LOG :");
	public JCheckBox pig=new JCheckBox("    Pig 0.10.0");
	public JCheckBox hive=new JCheckBox("   Hive 0.11.0");
	public JLabel utilitiesLabel=new JLabel("<html><b><font color='blue' size='3'>Choose utilities you want to install</font></b></html>");
	public JLabel softwareLabel=new JLabel("<html><b><font color='blue' size='5'>Required Softwares</font></b></html>");
	public JLabel sshLabel=new JLabel("<html><b><font color='blue' size='5'>SSH Configuration</font></b></html>");
	public JLabel configLabel=new JLabel("<html><b><font color='blue' size='5'>Hadoop Configuration</font></b></html>");
	public JLabel javaLabel=new JLabel("<html><b><font color='blue' size='5'>Java Variables Setup</font></b></html>");
	public JLabel installationLabel=new JLabel("<html><b><font color='blue' size='5'>Installation Progress</font></b></html>");
	public JLabel preLabel=new JLabel("<html><b><font color='blue' size='5'>Prerequisites</font></b></html>");
	int ic=1;
	JLabel iconLabel;
	Object[] columnNames = {" ","Software Name","Status"};
	Object[] columnNames1 = {" ","Action","Status"};
	Object[] columnNames2 = {" ","Resource","Status","Comments"};
		
Object[][] data = new Object[3][3];
Object[][] data1 = new Object[2][3];
Object[][] data2 = new Object[2][3];
Object[][] data3 = new Object[5][3];
Object[][] data4 = new Object[3][3];
Object[][] data6 = new Object[24][3];
Object[][] data7 = new Object[2][4];
	public static void main(String args[]){
		new Introduction();
	}
	
	Introduction() {
		try {
		icon = ImageIO.read(getClass().getResource("resource/lister.png"));
		tick = ImageIO.read(getClass().getResource("resource/tick.png"));
		cross = ImageIO.read(getClass().getResource("resource/cross.png"));
		fc = new JFileChooser();

	        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		openButton = new JButton("Browse");
	        openButton.addActionListener(this);
		
		location = new JTextArea("/");
		
		location.setEditable(false);
		
		label[0]= new JLabel("<html><b><font color='blue' size='3'>Introduction to Hadoop</font></b></html>");
		label[1]= new JLabel("<html><b><font color='blue' size='3'>Prerequisites</font></b></html>");
		label[2]= new JLabel("<html><b><font color='blue' size='3'>Check softwares</font></b></html>");
		label[3]= new JLabel("<html><b><font color='blue' size='3'>Configure SSH</font></b></html>");
		label[4]= new JLabel("<html><b><font color='blue' size='3'>Set Hadoop directory</font></b></html>");
		label[5]= new JLabel("<html><b><font color='blue' size='3'>Setup Java</font></b></html>");
		label[6]= new JLabel("<html><b><font color='blue' size='3'>Configure hadoop</font></b></html>");
		label[7]= new JLabel("<html><b><font color='blue' size='3'>Optional Utilities</font></b></html>");
		label[8]= new JLabel("<html><b><font color='blue' size='3'>Ready to Setup</font></b></html>");
		label[9]= new JLabel("<html><b><font color='blue' size='3'>Complete</font></b></html>");
		iconLabel=new JLabel();
		contentPanel[0]=new JPanel();
		contentPanel[1]=new JPanel();
		contentPanel[2]=new JPanel();
		contentPanel[3]=new JPanel();
		contentPanel[4]=new JPanel();
		contentPanel[5]=new JPanel();
		contentPanel[6]=new JPanel();
		contentPanel[7]=new JPanel();
		contentPanel[8]=new JPanel();
		contentPanel[9]=new JPanel();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);
		title = BorderFactory.createTitledBorder(compound, "STAGES");
		titlePanel.setBorder(compound);
		log.setBorder(compound);
		location.setBorder(compound);
	
		compound = BorderFactory.createCompoundBorder(
                          blueLine, compound);
		
		title.setTitleJustification(TitledBorder.CENTER);
		stagesPanel.setBorder(title);
		stagesPanel.setLayout(null);
		titlePanel.setLayout(null);
		
		contentPanel[0].setLayout(null);
		contentPanel[1].setLayout(null);
		contentPanel[2].setLayout(null);
		contentPanel[3].setLayout(null);
		contentPanel[4].setLayout(null);
		contentPanel[5].setLayout(null);
		contentPanel[6].setLayout(null);
		contentPanel[7].setLayout(null);
		contentPanel[8].setLayout(null);
		contentPanel[9].setLayout(null);
		buttonPanel.setLayout(null);

		text[0]=new String("<html><b><font color='blue' size='5'>Welcome to Hadoop Single Node Setup</font></b><br><br>This screen will give you an overview about hadoop, its version, its develpoer and its website.</html>");
		text[1]=new String("<html><b><font color='blue' size='5'>Prerequisites for Hadoop Single Node Setup</font></b><br><br>This screen will check scan your system for the needful resources.</html>");
		text[2]=new String("<html><b><font color='blue' size='5'>Required Softwares for Installation</font></b><br><br>This screen checks whether the required softwares are installed and install those that are not present.</html>");
		text[3]=new String("<html><b><font color='blue' size='5'>Configure SSH</font></b><br><br>This screen generates rsa key pair and adds it to your authorized keys.</html>");
		text[4]=new String("<html><b><font color='blue' size='5'>Download Hadoop 1.12.1 and set up Hadoop Directory</font></b><br><br>This screen downloads hadoop 1.2.1 from apache and sets up the hadoop directory according to the location you have provided.</html>");
		text[5]=new String("<html><b><font color='blue' size='5'>Java and its variables Setup</font></b><br><br>This screen checks if Sun Java is present. If not, it installs jdk1.6.017 and sets java variables in $HOME/.bashrc.</html>");
		text[6]=new String("<html><b><font color='blue' size='5'>Configure Hadoop Files</font></b><br><br>This screen configures hadoop-env.sh, core-site.xml, mapred-site.xml, hdfs-site.xml.</html>");
		text[7]=new String("<html><b><font color='blue' size='5'>Optional Utilities</font></b><br><br>This screen give you an option to install additional utilities like pig and hive after successful setup of hadoop single node.</html>");
		text[8]=new String("<html><b><font color='blue' size='5'>Ready to Setup Hadoop Single Node</font></b><br><br>This screen asks you confirmation before setting up hadoop single node. Clicking Install will set up hadoop single node in your system.</html>");
		text[9]=new String("<html><b><font color='blue' size='5'>Completed!</font></b><br><br>Hadoop single Node successfully setup in your machine.</html>");
		label[0].setBorder(compound);
		
		welcome = new JLabel(text[0]);
		
		detailedIntro = new JLabel("<html><br><font color='blue' size='5'>About Hadoop</font><br><br><ul><li>Hadoop is an open-source software framework for storage and large scale processing of data-sets on clusters of commodity hardware.</li><li> Hadoop is an Apache top-level project being built and used by a global community of contributors and users.</li><li> It is licensed under the Apache License 2.0.</li></ul><center><table><tr><td>Developer :</td><td>Apache Software Foundation</td></tr><tr><td>Developement Status:</td><td>Active</td></tr> <tr><td>Written in:</td><td>Java</td></tr><tr><td>Operating system:</td><td>Cross-platform</td></tr><tr><td>Type:</td><td>Distributed File System</td></tr><tr><td>License:</td><td>Apache License 2.0</td></tr><tr><td>Website:</td><td><a href='hadoop.apache.org'>hadoop.apache.org</a></td></tr></table></center></html>");
		next=new JButton("Next");
		quit=new JButton("Quit");
		install=new JButton("Install");
		finish=new JButton("Finish");
		back=new JButton("Back");
		next.addActionListener(this);
		install.addActionListener(this);
		quit.addActionListener(this);
		back.addActionListener(this);
		finish.addActionListener(this);
		back.setEnabled(false);
         	finish.setVisible(false);
	for(int i=0;i<softwares.length;i++) {
		
		 if(hadoopService.softwareExists(softwares[i],this)) {
			 JLabel softName = new JLabel("<html><font color='green' size='3'>"+softwares[i]+"</font> </html>");
		
			data[i][0]=new ImageIcon(tick);			
			data[i][1]=softName;
			 
			
			data[i][2]=new JLabel("<html><font color='green' size='3'>Installed </font> </html>");
			
		}
		else {
			JLabel softName = new JLabel("<html><font color='green' size='3'>"+softwares[i]+"</font></html>");
			
			data[i][0]=new ImageIcon(cross);
			data[i][1]=softName;
			softwareToBeInstalled.add(softwares[i]);
			
			data[i][2]=new JLabel("<html><font color='red' size='3'>Will Be Installed</font></html>");
			
		}
		
	}

data1[0][0]=new ImageIcon(cross);
data1[0][1]=new JLabel("<html><font color='green' size='3'>Generate rsa key pair</font> </html>");
data1[0][2]=new JLabel("<html><font color='red' size='3'>Will Be generated</font></html>");
data1[1][0]=new ImageIcon(cross);
data1[1][1]=new JLabel("<html><font color='green' size='3'>Add rsa to authorized keys</font> </html>");
data1[1][2]=new JLabel("<html><font color='red' size='3'>Will Be added</font></html>");

data2[0][0]=new ImageIcon(cross);
data2[0][1]=new JLabel("<html><font color='green' size='3'>Download Hadoop 1.12.1</font> </html>");
data2[0][2]=new JLabel("<html><font color='red' size='3'>Will Be downloaded</font></html>");
data2[1][0]=new ImageIcon(cross);
data2[1][1]=new JLabel("<html><font color='green' size='3'>Setup Hadoop Directory</font> </html>");
data2[1][2]=new JLabel("<html><font color='red' size='3'>Will Be set</font></html>");

data3[0][0]=new ImageIcon(cross);
data3[0][1]=new JLabel("<html><font color='green' size='3'>Configuring Hadoop-env.sh</font></html>");
data3[0][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");
data3[1][0]=new ImageIcon(cross);
data3[1][1]=new JLabel("<html><font color='green' size='3'>create directory /app/hadoop/tmp</font></html>");
data3[1][2]=new JLabel("<html><font color='red' size='3'>Will Be created</font></html>");
data3[2][0]=new ImageIcon(cross);
data3[2][1]=new JLabel("<html><font color='green' size='3'>Configuring core-site.xml</font></html>");
data3[2][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");
data3[3][0]=new ImageIcon(cross);
data3[3][1]=new JLabel("<html><font color='green' size='3'>Configuring mapred-site.xml</font></html>");
data3[3][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");
data3[4][0]=new ImageIcon(cross);
data3[4][1]=new JLabel("<html><font color='green' size='3'>Configuring hdfs-site.xml</font></html>");
data3[4][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");


data4[0][0]=new ImageIcon(cross);
data4[0][1]=new JLabel("<html><font color='green' size='3'>Setting HADOOP_HOME </font></html>");
data4[0][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");
data4[1][0]=new ImageIcon(cross);
data4[1][1]=new JLabel("<html><font color='green' size='3'>Setting JAVA_HOME </font></html>");
data4[1][2]=new JLabel("<html><font color='red' size='3'>Will Be created</font></html>");
data4[2][0]=new ImageIcon(cross);
data4[2][1]=new JLabel("<html><font color='green' size='3'>Setting PATH </font></html>");
data4[2][2]=new JLabel("<html><font color='red' size='3'>Will Be configured</font></html>");



data6[0][0]=new JLabel("");
data6[0][1]=new JLabel("<html><font color='blue' size='3'>Softwares Installed</font></html>");
data6[0][2]=new JLabel("");



for(ic=1;ic<softwareToBeInstalled.size()+1;ic++) {
data6[ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>"+softwareToBeInstalled.get(ic-1)+"</font></html>");
data6[ic][2]=new JLabel("<html><font color='red' size='3'>Installed</font></html>");
}


	softwareTable = new JTable(data, columnNames);
	softwareTable.setRowHeight(50);
	softwareTable.getColumnModel().getColumn(0).setMaxWidth(25);
	softwareTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	softwareTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	softwareTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

completeTable = new JTable(data6, columnNames);
	completeTable.setRowHeight(50);
	completeTable.getColumnModel().getColumn(0).setMaxWidth(25);
	completeTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	completeTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	completeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

preTable = new JTable(data7, columnNames2);
	preTable.setRowHeight(50);
	preTable.getColumnModel().getColumn(0).setMaxWidth(25);
	preTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	preTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	preTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});


	sshTable = new JTable(data1, columnNames1);
	sshTable.setRowHeight(75);
	sshTable.getColumnModel().getColumn(0).setMaxWidth(25);
	sshTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	sshTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	sshTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

hadoopDirTable = new JTable(data2, columnNames1);
	hadoopDirTable.setRowHeight(75);
	hadoopDirTable.getColumnModel().getColumn(0).setMaxWidth(25);
	hadoopDirTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	hadoopDirTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	hadoopDirTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

configTable = new JTable(data3, columnNames1);
	configTable.setRowHeight(65);
	configTable.getColumnModel().getColumn(0).setMaxWidth(25);
	configTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	configTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	configTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

javaVariableTable = new JTable(data4, columnNames1);
	javaVariableTable.setRowHeight(65);
	javaVariableTable.getColumnModel().getColumn(0).setMaxWidth(25);
	javaVariableTable.getColumnModel().getColumn(1).setPreferredWidth(60);
	javaVariableTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	javaVariableTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    protected void setValue(Object value) {
        if( value instanceof JLabel ) {
            setIcon(null);
            setText(((JLabel)value).getText());
        }
	else if( value instanceof ImageIcon ) {
            setIcon((ImageIcon)value);
            setText("");
        } 
	
	else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

softwareTable.setEnabled(false);
sshTable.setEnabled(false);
hadoopDirTable.setEnabled(false);
configTable.setEnabled(false);
javaVariableTable.setEnabled(false);
completeTable.setEnabled(false);
preTable.setEnabled(false);


progress = new JProgressBar();
		progress.setBorder(compound);
		progress.setPreferredSize( new Dimension( 300, 20 ) );
		progress.setMinimum( 0 );
		
		progress.setValue( 0 );
		progress.setBounds(60,200, 370, 40);
		installationLabel.setBounds(60,140, 370, 40);

		JScrollPane logScroll=new JScrollPane(log);
		logScroll.setBounds(8,335,180,200);
		JScrollPane jsp=new JScrollPane(softwareTable);
		jsp.setBounds(10,80,450,172);
		JScrollPane jsp1=new JScrollPane(sshTable);
		jsp1.setBounds(10,80,450,168);
		JScrollPane jsp7=new JScrollPane(preTable);
		jsp7.setBounds(10,80,450,122);
		JScrollPane jsp2=new JScrollPane(hadoopDirTable);
		jsp2.setBounds(10,150,450,168);
		JScrollPane jsp3=new JScrollPane(configTable);
		jsp3.setBounds(10,80,450,343);
		JScrollPane jsp6=new JScrollPane(completeTable);
		jsp6.setBounds(10,80,450,343);
		JScrollPane jsp4=new JScrollPane(javaVariableTable);
		jsp4.setBounds(10,80,450,343);
		
		browseLabel.setBounds(10,30,338,20);
		location.setBounds(10,50, 338, 30);
		openButton.setBounds(368,50,90,30);
	
	
		
		pig.setBounds(50,50,210,100);
		hive.setBounds(50,120,210,100);
		utilitiesLabel.setBounds(50,10,310,40);
		softwareLabel.setBounds(10,10,450,70);
		sshLabel.setBounds(10,10,450,70);
		configLabel.setBounds(10,10,450,70);
		javaLabel.setBounds(10,10,450,70);
		preLabel.setBounds(10,10,450,70);
		contentPanel[2].add(jsp);
		contentPanel[2].add(softwareLabel);
		contentPanel[3].add(jsp1);
		contentPanel[3].add(sshLabel);
		contentPanel[4].add(jsp2);
		contentPanel[4].add(location);
		contentPanel[4].add(openButton);
		contentPanel[4].add(browseLabel);
		contentPanel[5].add(jsp4);
		contentPanel[5].add(javaLabel);
		contentPanel[6].add(jsp3);
		contentPanel[6].add(configLabel);
		contentPanel[1].add(jsp7);
		contentPanel[1].add(preLabel);
		contentPanel[7].add(pig);
		contentPanel[7].add(hive);
		contentPanel[7].add(utilitiesLabel);
		contentPanel[8].add(progress);
		contentPanel[8].add(installationLabel);
		contentPanel[9].add(jsp6);
		this.setSize(700, 700);
		welcome.setBounds(20,10,500,80);
		log.setEditable(false);
		quit.setBounds(50,0, 90, 40);
		detailedIntro.setBounds(10,0, 480, 400);
		next.setBounds(350,0, 90, 40);
		finish.setBounds(350,0, 90, 40);
		back.setBounds(200,0, 90, 40);
		install.setBounds(350,0, 90, 40);
		label[0].setBounds(8,20,180,30);
		label[1].setBounds(8,50,180,30);
		label[2].setBounds(8,80,180,30);
		label[3].setBounds(8,110,180,30);
		label[4].setBounds(8,140,180,30);
		label[5].setBounds(8,170,180,30);
		label[6].setBounds(8,200,180,30);
		label[7].setBounds(8,230,180,30);
		label[8].setBounds(8,260,180,30);
		label[9].setBounds(8,290,180,30);
		addWindowListener(this);
		setTitle("Welcome to Hadoop Installation");
		setResizable(false);
		setVisible(true);
		titlePanel.add(welcome);
		install.setVisible(false);
		
		logLabel.setBounds(8,315,180,20);
		buttonPanel.add(quit);
		buttonPanel.add(next);
		buttonPanel.add(install);
		buttonPanel.add(back);
		buttonPanel.add(finish);
		contentPanel[0].add(detailedIntro);
		stagesPanel.add(label[0]);
		stagesPanel.add(label[1]);
		stagesPanel.add(label[2]);
		stagesPanel.add(label[3]);
		stagesPanel.add(label[4]);
		stagesPanel.add(label[5]);
		stagesPanel.add(label[6]);
		stagesPanel.add(label[7]);
		stagesPanel.add(label[8]);
		stagesPanel.add(label[9]);
		stagesPanel.add(logScroll);
		stagesPanel.add(logLabel);
		add(buttonPanel);
		
		iconLabel.setBounds(500,0,200,150);
		
		titlePanel.setBounds(0,0,700,150);
		titlePanel.add(iconLabel);
		contentPanel[0].setBounds(200,150,500,550);
		contentPanel[1].setBounds(200,150,500,550);
		contentPanel[2].setBounds(200,150,500,550);
		contentPanel[3].setBounds(200,150,500,550);
		contentPanel[4].setBounds(200,150,500,550);
		contentPanel[5].setBounds(200,150,500,550);
		contentPanel[6].setBounds(200,150,500,550);
		contentPanel[7].setBounds(200,150,500,550);
		contentPanel[8].setBounds(200,150,500,550);
		contentPanel[9].setBounds(200,150,500,550);
		buttonPanel.setBounds(200,600,500,100);

		contentPanel[0].setVisible(true);
		contentPanel[1].setVisible(false);
		contentPanel[2].setVisible(false);
		contentPanel[3].setVisible(false);
		contentPanel[4].setVisible(false);
		contentPanel[5].setVisible(false);
		contentPanel[6].setVisible(false);
		contentPanel[7].setVisible(false);
		contentPanel[8].setVisible(false);
		contentPanel[9].setVisible(false);
		stagesPanel.setBounds(0,150,200,550);
		add(contentPanel[0]);
		add(contentPanel[1]);
		add(contentPanel[2]);
		add(contentPanel[3]);
		add(contentPanel[4]);
		add(contentPanel[5]);
		add(contentPanel[6]);
		add(contentPanel[7]);
		add(contentPanel[8]);
		add(contentPanel[9]);
		add(titlePanel);
		add(stagesPanel);
		
		setVisible(true);
}
catch(Exception e) {
}
	}

public void actionPerformed(ActionEvent e) { 
try
        {
	   String temp = ((JButton)e.getSource()).getText();
	   if(temp.equals("Quit")) {
		if (JOptionPane.showConfirmDialog(null, 
            "Are you sure to close this window?", "Really Closing?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	    hadoopService.dispose(this);
            System.exit(0);
        }
		
	   }
	else if(temp.equals("Finish")) {
		hadoopService.dispose(this);
	}
	else if(temp.equals("Install")) {
			max= 15+softwareToBeInstalled.size();
			
			if(!(pig.isSelected())) {
					max+=8;
				}
			if(!(hive.isSelected())) {
					max+=12;
				}
			progress.setMaximum(max);
			hadoopService.installSoftwares(softwareToBeInstalled,this);
			hadoopService.configureSsh(this);
			hadoopService.hadoopSetup(this,location.getText());
			
			
				hadoopService.javaVariablesSetup(this,location.getText(),javaLocation);
			
				hadoopService.configHadoop(this,location.getText(),javaLocation);
				hadoopService.format(this,location.getText());
			if(pig.isSelected()) {
				  hadoopService.installPig(this,location.getText());
			}
			if(hive.isSelected()) {
				  hadoopService.installHive(this,location.getText());
			}
			back.setVisible(false);	
			quit.setVisible(false);
			install.setVisible(false);
			finish.setVisible(true);
			welcome.setText(text[9]);
			label[8].setBorder(BorderFactory.createEmptyBorder());
			contentPanel[8].setVisible(false);
			label[9].setBorder(compound);
			contentPanel[9].setVisible(true);
			data6[ic][0]=new JLabel("");
data6[ic][1]=new JLabel("<html><font color='blue' size='5'>SSH configuration </font></html>");
data6[ic][2]=new JLabel("");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Generate rsa key pair </font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");

data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Add rsa to authorized keys</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Added</font></html>");

data6[++ic][0]=new JLabel("");
data6[ic][1]=new JLabel("<html><font color='blue' size='5'>Hadoop Directory </font></html>");
data6[ic][2]=new JLabel("");

data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Download Hadoop 1.2.1</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Downloaded</font></html>");

data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Set Hadoop Directory</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Hadoop Directory Set</font></html>");


data6[++ic][0]=new JLabel("");
data6[ic][1]=new JLabel("<html><font color='blue' size='5'>Java Setup</font></html>");
data6[ic][2]=new JLabel("");

data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Creating Directory usr/local/java</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Created</font></html>");
data6[++ic][0]=new ImageIcon(tick);

data6[ic][1]=new JLabel("<html><font color='green' size='3'>Setting HADOOP_HOME </font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Setting JAVA_HOME </font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>CONFIGURED</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Setting PATH </font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");

data6[++ic][0]=new JLabel("");
data6[ic][1]=new JLabel("<html><font color='blue' size='5'>Configure Hadoop </font></html>");
data6[ic][2]=new JLabel("");

data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Configuring Hadoop-env.sh</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>create directory /app/hadoop/tmp</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Created</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Configuring core-site.xml</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Configuring mapred-site.xml</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");
data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Configuring hdfs-site.xml</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Configured</font></html>");

data6[++ic][0]=new JLabel("");
data6[ic][1]=new JLabel("<html><font color='blue' size='5'>Optional Utilities</font></html>");
data6[ic][2]=new JLabel("");
if(pig.isSelected()) {
				data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Pig 0.10.0</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Installed</font></html>");
			}
			if(hive.isSelected()) {
				data6[++ic][0]=new ImageIcon(tick);
data6[ic][1]=new JLabel("<html><font color='green' size='3'>Hive 0.11.0</font></html>");
data6[ic][2]=new JLabel("<html><font color='green' size='3'>Installed</font></html>");
			}
	   }
	   else if(temp.equals("Next")) {
			back.setEnabled(true);
			label[labelCount].setBorder(BorderFactory.createEmptyBorder());
			contentPanel[labelCount].setVisible(false);
			label[++labelCount].setBorder(compound);
			
			welcome.setText(text[labelCount]);
			contentPanel[labelCount].setVisible(true);
			if(labelCount==8) {
				install.setVisible(true);
				next.setVisible(false);			
			}
			else if(labelCount==1) {
			data7[0][1]=new JLabel("<html><font color='green' size='3'>Sun Java</font></html>");
if(hadoopService.checkJava(this)) {
data7[0][0]=new ImageIcon(tick);
data7[0][2]=new JLabel("<html><font color='green' size='3'>Installed</font></html>");
data7[0][3]=new JLabel("<html><font color='green' size='3'>No problem</font></html>");
}
else {
data7[0][0]=new ImageIcon(cross);
data7[0][2]=new JLabel("<html><font color='red' size='3'>Sun java not installed</font></html>");
data7[0][3]=new JLabel("<html><font color='red' size='3'>Install Sun java 1.6 or 1.7 and start the setup again</font></html>");
back.setVisible(false);	
quit.setVisible(false);
install.setVisible(false);
next.setVisible(false);
finish.setVisible(true);
}

data7[1][1]=new JLabel("<html><font color='green' size='3'>Internet Connection</font></html>");
if(hadoopService.net()) {
data7[1][0]=new ImageIcon(tick);
data7[1][2]=new JLabel("<html><font color='green' size='3'>Connected</font></html>");
data7[1][3]=new JLabel("<html><font color='green' size='3'>No problem</font></html>");
}
else {
data7[1][0]=new ImageIcon(cross);
data7[1][2]=new JLabel("<html><font color='red' size='3'>Not Connected</font></html>");
data7[1][3]=new JLabel("<html><font color='red' size='3'>Connect to Internet and start the setup again</font></html>");
back.setVisible(false);	
quit.setVisible(false);
install.setVisible(false);
next.setVisible(false);
finish.setVisible(true);
}
}
			   }
		else if(temp.equals("Back")) {
			label[labelCount].setBorder(BorderFactory.createEmptyBorder());
			contentPanel[labelCount].setVisible(false);
			label[--labelCount].setBorder(compound);
			if(labelCount==0) {			
			back.setEnabled(false);
			}
			welcome.setText(text[labelCount]);
			contentPanel[labelCount].setVisible(true);
			if(labelCount==8) {
				install.setVisible(true);
				next.setVisible(false);			
			}
			
			
			   }
	else if(temp.equals("Browse")) {
		   int returnVal = fc.showOpenDialog(Introduction.this);

           if (returnVal == JFileChooser.APPROVE_OPTION) {
               File file = fc.getSelectedFile();
             
		location.setText(fc.getSelectedFile().getAbsolutePath());
           } 
           
	   }
	
}
catch(Exception ex) {
}

}

public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(null, 
            "Are you sure to close this window?", "Really Closing?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	    hadoopService.dispose(this);
            System.exit(0);
        }
}
public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
}
public void windowActivated(java.awt.event.WindowEvent windowEvent) {
}
public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
}
public void windowIconified(java.awt.event.WindowEvent windowEvent) {
}
public void windowClosed(java.awt.event.WindowEvent windowEvent) {
}
public void windowOpened(java.awt.event.WindowEvent windowEvent) {
}
}
