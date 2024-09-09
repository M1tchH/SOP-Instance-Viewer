import java.io.File;

public class Main{
	static final int[] colorArr = {0,67,134,201,17152,17219,17286,17353,34304,34371,34438,34505,51456,51523,51590,51657,4390912,4390979,4391046,4391113,4408064,4408131,4408198,4408265,4425216,4425283,4425350,4425417,4442368,4442435,4442502,4442569,8781824,8781891,8781958,8782025,8798976,8799043,8799110,8799177,8816128,8816195,8816262,8816329,8833280,8833347,8833414,8833481,13172736,13172803,13172870,13172937,13189888,13189955,13190022,13190089,13207040,13207107,13207174,13207241,13224192,13224259,13224326,13224393};
	static int screenWidth = 1400;
	static int screenHeight = 1400;
	
	static int threadCount = 0;
	static int instanceSize = 100;
	static int sampleCount = 0;
	static int[][][] paths;
	static int currentSample;
	static boolean valid = false;
	
	public static void createPaths(File file) {
		valid = false;
		try {
			EZFileRead fileReader = new EZFileRead(file.getAbsolutePath());
			threadCount = Integer.parseInt(fileReader.getNextLine());
			instanceSize = Integer.parseInt(fileReader.getNextLine());
			sampleCount = (fileReader.getNumLines() - 2) / threadCount;
			
			paths = new int[sampleCount][threadCount][instanceSize + 2];
			
			String line = fileReader.getNextLine();
			int i = 0; 
			while(!line.equals("END OF FILE")) {
				String[] segments = line.split(",");
				for(int j = 0; j < segments.length; j++) {
					if(segments[j].length() != 0)
						paths[i / threadCount][i % threadCount][j] = Integer.parseInt(segments[j]);
					paths[i / threadCount][i % threadCount][j + 1] = -1;
				}
				line = fileReader.getNextLine();
				i++;
			}
		}catch(Exception e){
			System.out.println(e);
			return;
		}
		valid = true;
	}
	
	public static void main(String[] args) {
		new MainFrame();
		
		
	}

}
