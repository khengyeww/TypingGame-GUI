import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class TypingList {

	private static TypingList typinglist;
	private ArrayList<String> listLine = new ArrayList<>();
	private ArrayList<Integer> trackDuplicates = new ArrayList<>();
	private RandNumGenerator rand = RandNumGenerator.getInstance();
	
	private TypingList() {
		try {
			readTextList();
		} catch (IOException e) {
			System.out.println("Problem in TypingList class");
			e.printStackTrace();
		}
	}
	
	public static TypingList getInstance() {
		if(typinglist == null)
			typinglist = new TypingList();
		return typinglist;
	}
	
	public void readTextList() throws IOException {
		
		URL textURL = getClass().getResource("textlist.txt");
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(textURL.openStream()))){
			String line;
			while ((line = reader.readLine()) != null)
				listLine.add(line);
		}
	}

	public String getTextList() {
		int random = rand.nextInt(listLine.size());
		
		while(trackDuplicates.contains(random)) {
			random = rand.nextInt(listLine.size());
		}
		trackDuplicates.add(random);
		
		if(trackDuplicates.size() == listLine.size())
			trackDuplicates = new ArrayList<>();
		
		return listLine.get(random);
	}
	
}