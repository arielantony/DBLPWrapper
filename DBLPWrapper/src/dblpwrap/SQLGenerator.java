package dblpwrap;

import java.io.FileWriter;
import java.io.IOException;

public class SQLGenerator {
	
	private String insert = null;
	
	public void generatePaperInsert(Inproceedings i) {
		insert = "INSERT INTO Artigo (Titulo,Ano,Autores,Veiculo_idVeiculo)" + " VALUES ('"+i.getTitle()+"',"+i.getYear()+",'" + i.getStringAuthors()+"',idVeiculo);\n";
		//System.out.println(insert);
		writeInsertToFile();
	}
	
	public void writeInsertToFile() {
		try {
			FileWriter fw = new FileWriter(DBLPWrapper.outputFile,true);
			fw.write(insert);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
