package fr.ucbl.disp.vfos.util.file;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class IOFile {
	private String path;
	private EIOFileType type;

	private Scanner scanner;
	private PrintWriter printer;
	private ArrayList<String> buffer = new ArrayList<String>();


	public IOFile(String path, EIOFileType type) throws IOException {
		this.path = path;
		this.type = type;

		if(this.path == ""){
			return ;
		}


		if(type == EIOFileType.READ ){
			File f = new File(path);
			if(!f.exists()){
				throw new FileNotFoundException();
			}
			this.scanner=new Scanner(f);
		}
		else
		{
			this.printer =  new PrintWriter(new BufferedWriter
					(new FileWriter(path,true)));
		}
	}

	public IOFile(String path, EIOFileType type, boolean Overwrite) throws IOException {
		this.path = path;
		this.type = type;

		if(this.path == ""){
			return ;
		}


		if(type == EIOFileType.READ ){
			File f = new File(path);
			if(!f.exists()){
				throw new FileNotFoundException();
			}
			this.scanner=new Scanner(f);
		}
		else
		{
			if(!Overwrite){
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-kk-mm-ss");
				String tmp = path.substring(0,path.lastIndexOf('.'));
				String tmp1 = path.substring(path.lastIndexOf('.'), path.length());

				this.path = tmp+formater.format((new Date()))+tmp1;
			}
			this.printer =  new PrintWriter(new BufferedWriter
					(new FileWriter(this.path)));
		}
	}

	public static boolean exists(String path){
		File f = new File(path);
		return f.exists();
	}

	public static void Copy (String source, String dest)
	{
		if (exists (source)){
			FileChannel in = null; // canal d'entr�e
			FileChannel out = null; // canal de sortie

			try {
				// Init
				in = new FileInputStream(source).getChannel();
				out = new FileOutputStream(dest).getChannel();

				// Copie depuis le in vers le out
				in.transferTo(0, in.size(), out);
			} catch (Exception e) {
				e.printStackTrace(); // n'importe quelle exception
			} finally { // finalement on ferme
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) {}
				}
				if(out != null) {
					try {
						out.close();
					} catch (IOException e) {}
				}
			}
		}
	}

	public static void Delete (String path)
	{
		if (exists (path)){
			File f = new File(path);
			f.delete();
		}

	}

	public void addLine (String line)
	{
		if (this.type != EIOFileType.WRITE) {
			return;
		}

		if(printer==null)
			return;
		//System.out.println("rec : "+line);
		/*if(this.buffer.size()>10){
			System.out.println("rec : "+path);
			for (String l : this.buffer) {
				this.printer.println(l);
			}
			this.printer.println(line);
			this.printer.flush();
			this.buffer.clear();
		}
		else{*/
		try{
			this.buffer.add(line);
			 
			this.printer.println(line);
			this.printer.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	public String readLine ()
	{
		if (this.type != EIOFileType.READ)
			return "";

		String line = "";
		if (this.scanner != null) {
			if (this.scanner.hasNextLine()) {
				line = this.scanner.nextLine();
				return line;
			}
			return "EOF";
		}
		return "EOF";
	}

	public String readFile(){
		
		String data ="";
		String line ="";
		while (!line.equals("EOF")) {
			data+=line;
			line = this.readLine();
		}
		return data;
	}
	
	public void closeFile ()
	{
		if(this.printer!=null)
			this.printer.close();
		if(this.scanner!= null)
			this.scanner.close();
	}


}
