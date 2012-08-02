package c.granger;

import java.io.*;

public class rallybox {
	private int max;
	private int maxlength;
	private int size;
	private int x;
	private int y;
	private boolean emptyedge;
	private boolean shortedge;
	private int [][] box;
	
	/**
	 * Class constructor.
	 */
	public rallybox(){
		super();
	}
	/**
	 * Initializes the variables needed for execution and calls method to print results.
	 * @throws java.lang.OutOfMemoryError if number is too large.
	 * @param number The max number to print out in a spiral.
	 */
	public void playBox(double number) throws java.lang.OutOfMemoryError{
		max = (int)Math.round(number);
		maxlength = String.valueOf(max).length();
		size =  (int) Math.ceil(Math.sqrt(number+1));
		size = (size%2==0)?size+1:size;
		x = y = (int) (size / 2);
		box = new int[size][size];
		initBox();
		printBox();
	}
	/** 
	 * Initializes array elements with -1 values.  
	 * Calls method to fill the box with the appropriate values.
	 */
	 private void initBox(){
		
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				box[i][j] = -1;
			}
		}
		fillBox();
	}

	/**
	 * Fills the two dimensional array with the numbers in a spiral order.
	 */
	private void fillBox(){
		
		int num = 0;
			for(int i = 0; i <= size && num<=max; i++){
				if(i % 2 != 0){
					for(int j = 0; j < i && num<=max; j++){
						box[x][y++]=num++; 
					}
					for(int j = 0; j < i && num<=max; j++){
						box[x++][y]=num++;
					}
				}else{
					for(int j = 0; j < i && num<=max; j++){
						box[x][y--]=num++;
					}
					for(int j = 0; j < i && num<=max; j++){
						box[x--][y]=num++;
					}
				}
		}
	}
	/**
	 *  Determines if the far left side of the array is empty 
	 *  or the edge numbers are shorter than the longest number.
	 */
	private void formatBox(){
		emptyedge = true;
		for(int i = 0; i<size; i++){
			if(box[i][0] != -1){
				emptyedge = false;
				break;
			}
		}
		
		shortedge = false;
		if(!emptyedge)return;
		for(int i = 0; i<size; i++){
			if(box[i][1]!=-1){
				if(String.valueOf(box[i][1]).length()<maxlength){
					shortedge = true;
				}else if(String.valueOf(box[i][1]).length()==maxlength){
					shortedge = false;
				}
			}
		}
	}
	/**
	 * Prints the array in the appropriate order.
	 */
	private void printBox(){
		System.out.println("Your number is: "+max);
		String space="";
		formatBox();
		for(int i = 0; i< size; i++){
			for(int j = 0; j<size; j++){
				space = "";
				if(box[i][j]==-1){
					for(int k=1; k<=maxlength; k++){
						space = space.concat(" ");	
					}
				}
				else if(String.valueOf(box[i][j]).length()<=maxlength){
					for(int k=1; (String.valueOf(box[i][j]).length()+k)<=maxlength; k++){
						space = space.concat(" ");	
					}
				}
				if(box[i][j]==-1){
					if(emptyedge && j==0){
						System.out.print("");
					}else if((emptyedge && j==1) && shortedge){
						System.out.print(space.substring(1)+" ");
					}
					else{ 
						System.out.print(space+" ");
					}
				}else{
					if(!emptyedge){
						System.out.print(space+box[i][j]+" ");
					}else{
						if(shortedge && j==1){
							System.out.print(box[i][j]+" ");
						}else{
							System.out.print(space+box[i][j]+" ");
						}
					}
				}
			}
			System.out.println("");
		}
	}
	/**
	 * Main method used to demonstrate the RallyBox program.
	 * 
	 * @param arg
	 */
	public static void main(String [] arg){
		rallybox rb = new rallybox();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line="";
	
			while(true){
				System.out.println("Please type a number and press enter. (Type 'q' to quit.)");
				try{
					line = br.readLine();
					if(Double.valueOf(line)>=0){
						rb.playBox(Double.valueOf(line));	
					}else{
						System.out.println("Your number needs to be larger than 0. Try again.");
					}
					
				}catch(NumberFormatException nfe){
					if(line.toLowerCase().startsWith("q")){
						System.out.println("Thank you, goodbye.");
						return;
					}else{
						System.out.println(line+" is not a valid entry.");
					}
				}catch(IOException e){
					System.out.println("You did something wrong. Bye!");
				}catch(java.lang.OutOfMemoryError e){
					System.out.println("Your number is too large.");
				}
			}
		
	}		
	
}
