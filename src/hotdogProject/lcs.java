package hotdogProject;

import java.util.ArrayList;


public class lcs {
	private static int[][] D; 
	private static int[][] via;//0=diagonal 1=left 2=up
	private static ArrayList<Integer> leftLCS = new ArrayList<Integer>();
	private static ArrayList<Integer> rightLCS = new ArrayList<Integer>();
	private static int lcsSize;
	
	public static int getLeftLCS(int i) {
		return leftLCS.get(i);
	}
	public static int getRightLCS(int i) {
		return rightLCS.get(i);
	}
	
	private static void setLeftLCS(int i) {
		leftLCS.add(0, i);
	}
	private static void setRightLCS(int i) {
		rightLCS.add(0, i);
	}
	
	public static int getLcsSize() {
		return lcsSize;
	}
	
	public static void findLcs(ArrayList<String> left,ArrayList<String> right){
		leftLCS.clear();
		rightLCS.clear();
		D = new int[left.size()+1][right.size()+1];
		via = new int[left.size()+1][right.size()+1];
		int i,j = 0;
		
		for(i = 0; i <= left.size(); i++) {
			D[i][0] = 0;
		}
		
		for(i = 0; i <= right.size(); i++) {
			D[0][i] = 0;
		}
		
		for(i=1;i<left.size()+1;i++){
			for(j=1;j<right.size()+1;j++){
				if(left.get(i-1).equals(right.get(j-1))) {
					D[i][j] = D[i-1][j-1] + 1;
					via[i][j] = 0;
				}
				else if(D[i-1][j] >= D[i][j-1]) {
					D[i][j] = D[i-1][j];
					via[i][j] = 2;
				}
				else {
					D[i][j] = D[i][j-1];
					via[i][j] = 1;
				}
			}
		}
		i--;
		j--;
		while(i!=0 && j!=0){
			if(via[i][j] == 0){
				lcs.setLeftLCS(i-1);
				lcs.setRightLCS(j-1);
				i--;j--;
			}
			else if(via[i][j] == 1){
				j--;
			}
			else if(via[i][j] == 2){
				i--;
			}
			
		}
		lcsSize = leftLCS.size();
		
		System.out.println("left");
		for(i = 0; i < lcsSize; i++) {
			System.out.println(lcs.getLeftLCS(i));
		}
		
		System.out.println("right");
		for(i = 0; i < lcs.lcsSize; i++) {
			System.out.println(lcs.getRightLCS(i));
		}
		
	}
}
