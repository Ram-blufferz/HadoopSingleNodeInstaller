package service;
import view.Introduction;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.net.*;
import java.nio.channels.*;
public class HadoopService{
	
        Process p;
	static int paint=0;
	public boolean net()
{
    try {
        
        URL url = new URL("http://www.google.com");

 	HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
	Object objData = urlConnect.getContent();
        return true;

    } catch (UnknownHostException e) {
       
	return false;
       
    }
    catch (IOException e) {
      
        return false;
        
    }
    }

	public void dispose(Introduction f){
		f.dispose();	
		f.log.setText(f.log.getText()+"\n"+"Uninstalling....Rolling back to original state....Removing hadoop directory");
		f.log.setText(f.log.getText()+"\n"+"Closing Setup");
	}
	
	public boolean checkJava(Introduction f){
		try {
			String[] sed2 = {"/bin/sh","-c","ls /etc | update-alternatives --config java"};	
			Process p = Runtime.getRuntime().exec(sed2);
		
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s;
			String javaLocation=""; 
			ArrayList<String> javaOptions=new ArrayList<String>();
			while ((s = r.readLine()) != null) {
				javaOptions.add(s);
			} 
			
			r.close();
			for(int i=4;i<=javaOptions.size()-3;i++) {
				if(!(javaOptions.get(i).contains("openjdk"))) {
				f.log.setText(f.log.getText()+"\n"+"Sun Java already installed");
				String[] ss=javaOptions.get(i).split("/");
					
					for(int j=1;!((ss[j].contains("jre")) || (ss[j].contains("bin")));j++) {
						javaLocation+="/"+ss[j];		
					}
					f.javaLocation=javaLocation;
					f.log.setText(f.log.getText()+"\n"+"Java location : "+javaLocation);
					return true;	
				}
						
			}

			
		   	f.log.setText(f.log.getText()+"\n"+"Sun Java not installed");
			return false;
		 }
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
			return false;
	}
	
	public void installPig(Introduction f,String hadoopLocation){
		try {
	   		f.progress.setValue(++paint);
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			f.progress.paintImmediately( progressRect );
			
			
			f.log.setText(f.log.getText()+"\n"+"Downloading Pig-0.10.1 from http://apache.claz.org/pig/pig-0.10.1/pig-0.10.1.tar.gz....");
			URL website = new URL("http://apache.claz.org/pig/pig-0.10.1/pig-0.10.1.tar.gz");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(hadoopLocation+"/hadoop/pig-0.10.1.tar.gz");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			f.log.setText(f.log.getText()+"\n"+"Pig-0.10.1 successfully downloaded");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			p = Runtime.getRuntime().exec("tar -xf "+hadoopLocation+"/hadoop/pig-0.10.1.tar.gz -C "+hadoopLocation+"/hadoop/");
			p.waitFor();
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	String[] sed1 = {"bash", "-c", "sed -i '/export PIG_HOME/d' $HOME/.bashrc"};
			String[] sed2 = {"bash", "-c", "sed -i '/export PATH=$PATH:$PIG/d' $HOME/.bashrc"};
			p = Runtime.getRuntime().exec(sed1);
		    	p.waitFor();
		    	p = Runtime.getRuntime().exec(sed2);
		    	p.waitFor();
		    	String[] cmd1 = {"/bin/sh","-c","ls /etc | printf '\nexport PIG_HOME="+hadoopLocation+"/hadoop/pig-0.10.1\nexport PATH=$PATH:$PIG_HOME/bin\n' >> $HOME/.bashrc"};
		    	p = Runtime.getRuntime().exec(cmd1);
		    	p.waitFor();
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
	    
	    		String[] sed3 = {"bash", "-c", "sed -i '/exectype=/d' "+hadoopLocation+"/hadoop/pig-0.10.1/conf/pig.properties"};
			String[] cmd2 = {"/bin/sh","-c","ls /etc | printf 'exectype=local' >> "+hadoopLocation+"/hadoop/pig-0.10.1/conf/pig.properties"};
			p = Runtime.getRuntime().exec(sed3);
		    	p.waitFor();
		    	p = Runtime.getRuntime().exec(cmd2);
		    	p.waitFor();
			f.log.setText(f.log.getText()+"\n"+"Pig sucessfully installed and configured in "+hadoopLocation+"/hadoop/");
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	
		}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}
	public void installHive(Introduction f,String hadoopLocation){
		try {
	   		f.progress.setValue(++paint );
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			f.progress.paintImmediately( progressRect );


			f.log.setText(f.log.getText()+"\n"+"Downloading Hive-0.11.0 from http://apache.claz.org/hive/hive-0.11.0/hive-0.11.0.tar.gz....");

			URL website = new URL("http://apache.claz.org/hive/hive-0.11.0/hive-0.11.0.tar.gz");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(hadoopLocation+"/hadoop/hive-0.11.0.tar.gz");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			f.log.setText(f.log.getText()+"\n"+"Hive-0.11.0 successfully downloaded");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			p = Runtime.getRuntime().exec("tar -xf "+hadoopLocation+"/hadoop/hive-0.11.0.tar.gz -C "+hadoopLocation+"/hadoop/");
			p.waitFor();
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );

			f.progress.setValue( ++paint );
	    		f.progress.paintImmediately( progressRect );
		   
	    		String[] sed1 = {"bash", "-c", "sed -i '/export HIVE_HOME=/d' $HOME/.bashrc"};
			String[] sed2 = {"bash", "-c", "sed -i '/export PATH=$HIVE/d' $HOME/.bashrc"};
			p = Runtime.getRuntime().exec(sed1);
		    	p.waitFor();
		    	p = Runtime.getRuntime().exec(sed2);
		    	p.waitFor();
		    	String[] cmd1 = {"/bin/sh","-c","ls /etc | printf '\nexport HIVE_HOME="+hadoopLocation+"/hadoop/hive-0.11.0\nexport PATH=$HIVE_HOME/bin:$PATH\n' >> $HOME/.bashrc"};
		    	p = Runtime.getRuntime().exec(cmd1);
		    	p.waitFor();
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	String[] cmd2 = {"/bin/sh","-c","ls /etc | $HADOOP_HOME/bin/hadoop fs -mkdir /tmp"};
		    	p = Runtime.getRuntime().exec(cmd2);
		    	p.waitFor();
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	String[] cmd3 = {"/bin/sh","-c","ls /etc | $HADOOP_HOME/bin/hadoop fs -mkdir /user/hive/warehouse"};
		    	p = Runtime.getRuntime().exec(cmd3);
		    	p.waitFor();
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	String[] cmd4 = {"/bin/sh","-c","ls /etc | $HADOOP_HOME/bin/hadoop fs -chmod g+w /tmp"};
		    	p = Runtime.getRuntime().exec(cmd4);
		    	p.waitFor();
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
		    	String[] cmd5 = {"/bin/sh","-c","ls /etc | $HADOOP_HOME/bin/hadoop fs -chmod g+w /user/hive/warehouse"};
		    	p = Runtime.getRuntime().exec(cmd5);
		    	p.waitFor();
			f.log.setText(f.log.getText()+"\n"+"Hive sucessfully installed and configured in "+hadoopLocation+"/hadoop/");
		    	f.progress.setValue( ++paint );
		    	f.progress.paintImmediately( progressRect );
			
		}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}
	public void format(Introduction f,String hadoopLocation){
		try {
		  	
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			
			String[] cmd1 = {"/bin/sh","-c","ls /etc | echo | "+hadoopLocation+"/hadoop/hadoop/bin/hadoop namenode -format ''"};
			p = Runtime.getRuntime().exec(cmd1);
   			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+"hadoop filesystem sucessfully formated");
   			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			}
	    catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}
	public void configHadoop(Introduction f,String hadoopLocation,String javaLocation){
		try {
	   		String[] sed2 = {"bash", "-c", "sed -i '/export JAVA_HOME/d' "+hadoopLocation+"/hadoop/hadoop/conf/hadoop-env.sh"};
		   	String[] cmd2 = {"/bin/sh","-c","ls /etc | printf 'export JAVA_HOME="+javaLocation+"/' >> "+hadoopLocation+"/hadoop/hadoop/conf/hadoop-env.sh"};
			String[] cmd3 = {"/bin/sh","-c","ls /etc | printf '<?xml version=\"1.0\"?>\n<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>\n<configuration>\n<property>\n<name>hadoop.tmp.dir</name>\n<value>/app/hadoop/tmp</value>\n<description>A base for other temporary directories.</description>\n</property>\n<property>\n<name>fs.default.name</name>\n<value>hdfs://localhost:9000</value>\n<description>The name of the default file system </description>\n</property>\n</configuration>' >> "+hadoopLocation+"/hadoop/hadoop/conf/core-site.xml"};
			String[] cmd4 = {"/bin/sh","-c","ls /etc | printf '<?xml version=\"1.0\"?>\n<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>\n<configuration>\n<property>\n<name>mapred.job.tracker</name>\n<value>localhost:9001</value>\n<description>The host and port that the MapReduce job tracker runs at.</description>\n</property>\n</configuration>' >> "+hadoopLocation+"/hadoop/hadoop/conf/mapred-site.xml"};
			String[] cmd5 = {"/bin/sh","-c","ls /etc | printf '<?xml version=\"1.0\"?>\n<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>\n<configuration>\n<property>\n<name>dfs.replication</name>\n<value>1</value>\n<description>Default block replication. </description>\n</property>\n</configuration>' >> "+hadoopLocation+"/hadoop/hadoop/conf/hdfs-site.xml"};
			String[] rm1 = {"/bin/sh","-c","ls /etc | rm /hadoop/hadoop/conf/core-site.xml"};
		    	String[] rm2 = {"/bin/sh","-c","ls /etc | rm /hadoop/hadoop/conf/mapred-site.xml"};
			String[] rm3 = {"/bin/sh","-c","ls /etc | rm /hadoop/hadoop/conf/hdfs-site.xml"};
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			
		 	p = Runtime.getRuntime().exec(sed2);
			p.waitFor();
			p = Runtime.getRuntime().exec(cmd2);
			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+hadoopLocation + "/hadoop/hadoop/conf/hadoop-env.sh is configured");
	    		
		   	f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			p = Runtime.getRuntime().exec("mkdir -p /app/hadoop/tmp");
			p.waitFor();
		   	f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
		   	
			p = Runtime.getRuntime().exec(rm1);
			p.waitFor();
			p = Runtime.getRuntime().exec(cmd3);
			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+hadoopLocation+"/hadoop/hadoop/conf/core-site.xml is configured");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			p = Runtime.getRuntime().exec(rm2);
			p.waitFor();
			p = Runtime.getRuntime().exec(cmd4);
			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+hadoopLocation+"/hadoop/hadoop/conf/mapred-site.xml is configured");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			p = Runtime.getRuntime().exec(rm3);
			p.waitFor();
			p = Runtime.getRuntime().exec(cmd5);
			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+hadoopLocation+ "/hadoop/hadoop/conf/hdfs-site.xml is configured");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately(progressRect );
			
			}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}
	
public void javaVariablesSetup(Introduction f,String hadoopLocation,String javaLocation){
		try {
			String[] cmd1 = {"/bin/sh","-c","ls /etc | printf '\nexport HADOOP_HOME="+hadoopLocation+"/hadoop/hadoop\nexport JAVA_HOME="+javaLocation+"\nexport PATH=$PATH:$HADOOP_HOME/bin\n' >> $HOME/.bashrc"};
			String[] sed1 = {"bash", "-c", "sed -i '/export HADOOP_HOME=/d' $HOME/.bashrc"};
			String[] sed2 = {"bash", "-c", "sed -i '/export JAVA_HOME/d' $HOME/.bashrc"};
			String[] sed3 = {"bash", "-c", "sed -i '/export PATH=$PATH:$HADOOP_HOME/d' $HOME/.bashrc"};
			
		   	
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
		
			p = Runtime.getRuntime().exec(sed1);
			p.waitFor();
		
			p = Runtime.getRuntime().exec(sed2);
			p.waitFor();
			
			p = Runtime.getRuntime().exec(sed3);
			p.waitFor();
			p = Runtime.getRuntime().exec(cmd1);
			p.waitFor();
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.log.setText(f.log.getText()+"\n"+"Java_HOME,HADOOP_HOME,PATH has been set in $HOME/.bashrc");
		}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}
	public void installSoftwares(ArrayList<String> softwareToBeInstalled,Introduction f) { 
		
		  f.progress.setValue( ++paint );
		  Rectangle progressRect = f.progress.getBounds();
		  progressRect.x = 0;
		  progressRect.y = 0;
		  f.progress.paintImmediately( progressRect );
		   for(int i=0;i<softwareToBeInstalled.size();i++) {
			    	
			install(softwareToBeInstalled.get(i),f);
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
		   }
	}

	public void hadoopSetup(Introduction f,String hadoopLocation){
		try {
		   	
			Rectangle progressRect = f.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			
			p = Runtime.getRuntime().exec("mkdir "+hadoopLocation+"/hadoop");
			p.waitFor();
			f.log.setText(f.log.getText()+"\n"+"Hadoop directory created in "+ hadoopLocation+"/hadoop");
		   	f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			p = Runtime.getRuntime().exec("chmod 777 -R "+hadoopLocation+"/hadoop");
			p.waitFor();
		 
			f.log.setText(f.log.getText()+"\n"+"Downloading Hadoop-1.2.1 from http://apache.claz.org/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz....");
			
			URL website = new URL("http://apache.claz.org/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(hadoopLocation+"/hadoop/hadoop-1.2.1.tar.gz");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			f.log.setText(f.log.getText()+"\n"+"Hadoop-1.2.1 successfully downloaded");
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			f.progress.setValue( ++paint );
			f.progress.paintImmediately( progressRect );
			
			p = Runtime.getRuntime().exec("tar -xf "+hadoopLocation+"/hadoop/hadoop-1.2.1.tar.gz -C "+hadoopLocation+"/hadoop/");
			p.waitFor();
			p = Runtime.getRuntime().exec("mv "+hadoopLocation+"/hadoop/hadoop-1.2.1 "+hadoopLocation+"/hadoop/hadoop");
			p.waitFor();
		   	p = Runtime.getRuntime().exec("chmod 777 -R "+hadoopLocation+"/hadoop/hadoop");
			p.waitFor();
			
			}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
	}



	public void configureSsh(Introduction s){ 
		    try {
		   	
			Rectangle progressRect = s.progress.getBounds();
			progressRect.x = 0;
			progressRect.y = 0;
			p = Runtime.getRuntime().exec("rm -r /root/.ssh");
		   	p.waitFor();
		   	String[] cmd={"/bin/sh","-c","ls /etc | echo | ssh-keygen -t rsa -P ''"};
			String[] cmd1={"/bin/sh", "-c", "/bin/cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys"};
		   	p = Runtime.getRuntime().exec(cmd);
		   	p.waitFor();
			s.log.setText(s.log.getText()+"\n"+"Key Pair generated");
		   	s.progress.setValue( ++paint );
			s.progress.paintImmediately( progressRect );
		   	
		   	p = Runtime.getRuntime().exec(cmd1);
		   	s.progress.setValue( ++paint );
			s.progress.paintImmediately( progressRect );
		   	p.waitFor();
			s.log.setText(s.log.getText()+"\n"+"rsa key pair copied to authorized keys");
			
			}
		catch(Exception e) {
			s.log.setText(s.log.getText()+"\n"+e.getMessage());
		}
	}




	public void install(String soft,Introduction f) {
		f.log.setText(f.log.getText()+"\n"+"Software "+soft+" not installed");
        	Process process;
		
		String[] sed1 = {"/bin/sh","-c","ls /etc | apt-get install "+soft+" -qy --force-yes"};
       		try {     
			f.log.setText(f.log.getText()+"\n"+" Installing "+soft);  
		    	process = Runtime.getRuntime().exec(sed1);
			process.waitFor();
                	f.log.setText(f.log.getText()+"\n"+soft+" installed"); 
		}
        	catch(Exception e) {
            		f.log.setText(f.log.getText()+"\n"+e.getMessage());
        	}  
	      
	}

 	public boolean softwareExists(String binaryName,Introduction f){
		try {
			String line;
			ProcessBuilder builder;
			BufferedReader reader;
			Process process;

			builder = new ProcessBuilder("/usr/bin/which", binaryName);
			builder.redirectErrorStream(true);
			process = builder.start();
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			process.waitFor();
			while ((line = reader.readLine ()) != null) {
				    f.log.setText(f.log.getText()+"\n"+"Software "+binaryName+" already installed");
				    break; 
       		 	}

        		return (line != null && !line.isEmpty());
		}
		catch(Exception e) {
			f.log.setText(f.log.getText()+"\n"+e.getMessage());
		}
		return false;
	}
}
