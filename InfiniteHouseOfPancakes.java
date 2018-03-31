import java.io.*;
import java.util.*;
public class InfiniteHouseOfPancakes{
	int minutes = 0;

	public static void main(String arg[]){
		new InfiniteHouseOfPancakes();
	}

	public InfiniteHouseOfPancakes(){
		String data[] = ReadFile("B-large-practice.in");
		/*Scanner miScanner = new Scanner(System.in);
		System.out.print("Number of Cases: ");
		int tests = Integer.parseInt(miScanner.nextLine());*/
		int tests = Integer.parseInt(data[0]);
		int index = 1;
		for(int i=0; i<tests; i++){
			int arraysize = Integer.parseInt(data[index]);
			index++;
			String[] arrayString = data[index].split(" ");
			//System.out.print(arraysize+" "+data[index]);
			index++;
			test2(i+1,arraysize,arrayString);
		}
		/*String[] arr = new String[]{"9"};
		test(0,1,arr);*/
	}

	public void test(int caseNumber, int arraysize, String[] arrayString){

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(String s : arrayString){
			array.add(Integer.parseInt(s));
		}

		boolean finished = false;

		while(!finished){
			
			/*for(int i : array)
				System.out.print(i+" ");
			System.out.println("");*/

			int max = Collections.max(array);
			if(max>0){
				boolean normal = isNormalMinute(max,array);
				//System.out.println(max+" "+normal);

				if(normal||max==1){
					normalMinute(array);
					minutes++;
				}
				else{
					//System.out.print("Minuto especial No encontre duplicados");
					specialMinute(array,max);
				}

				//System.out.println("Minutos: "+ minutes);
			}
			else
				finished = true;

		}

		System.out.println("Case #"+caseNumber+": "+ minutes);
		minutes = 0;
	}

	public boolean isNormalMinute(int max, ArrayList<Integer> array){

		int count = 0;

		for(int i : array){
			if(i==max)
				count++;
		}

		double minByDividing = max/2.0+count;
		//System.out.println("w"+minByDividing);
		if(Math.ceil(minByDividing)>=max)
			return true;//normal minute


		return false;//special minute
	}

	public void normalMinute(ArrayList<Integer> array){
		int size = array.size();
		for(int i=0; i<size; i++){
			if(array.get(i)>0)
				array.set(i, array.get(i)-1);
		}
	}

	public void specialMinute(ArrayList<Integer> array, int max){
		ArrayList<Integer> indexes = getIndexesOfMax(array,max);
		for(int i:indexes){

			if(max%2==0){
				array.set(i, max/2);
				array.add(max/2);
			}
			else{
				array.set(i, (max/2)+1);
				array.add((max/2));
			}

			minutes++;
		}
	}

	public ArrayList<Integer> getIndexesOfMax(ArrayList<Integer> array, int max){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for(int i=0; i<array.size(); i++){
			if(array.get(i)==max)
				indexes.add(i);
		}

		return indexes;

	}

	public String[] ReadFile(String fileName){

        // This will reference one line at a time
        String line = null;
        String fileString = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                fileString += line+",";
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }


        return fileString.split(",");
	}


	public void test2(int caseNumber, int arraysize, String[] arrayString){
            int[] ps = new int[arraysize];
            for (int i = 0; i < arraysize; i++)
                ps[i] = Integer.parseInt(arrayString[i]);

            int[] posibilities = new int[1001];
            for (int p : ps)
                posibilities[p]++;

            int min = 10000;
            for (int i = 1; i <= posibilities.length; i++)
            {
                int moves = 0;
                for (int j = 0; j < posibilities.length; j++){
                    moves += ((j - 1) / i) * posibilities[j];
                }
                if (moves + i < min){
                    min = moves + i;
                    //System.out.println("Min "+min);
                }
            }
            System.out.println("Case #"+caseNumber+": " + min);
	}
}