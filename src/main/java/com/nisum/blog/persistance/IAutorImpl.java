package com.nisum.blog.persistance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.nisum.blog.model.Author;

public class IAutorImpl implements IAutor {
	
	@Override
	public  void create(Author author) {

			try {
				String content = author.toString();
				File file = new File("src/main/resources/authors.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw =new BufferedWriter(fw);
				bw.write(content);
				bw.newLine();
				bw.close();
				
					
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}


	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		  try {
				final BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/authors.txt"));
				String line = "";
				File inFile = new File("src/main/resources/authors.txt"); 
		        //Construct the new file that will later be renamed to the original filename.
		        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
				while((line = reader.readLine())!= null) {
					
				    if(line.indexOf(";")!= -1){
				        if (line.split(";")[0].equalsIgnoreCase(id)) {
				            //System.out.println("se encontro el autor "+ id);
				        	//Aca borro la linea
					        pw.println(line);
					        pw.flush();
				        	//Fin borrar linea
				        }
				    }
				}
				pw.close();
				reader.close();
				//Delete the original file
		        if (!inFile.delete()) {
		            System.out.println("Could not delete file");
		            return;
		        }
		 
		        //Rename the new file to the filename the original file had.
		        if (!tempFile.renameTo(inFile)){
		            System.out.println("Could not rename file");
		 
		        }
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
}