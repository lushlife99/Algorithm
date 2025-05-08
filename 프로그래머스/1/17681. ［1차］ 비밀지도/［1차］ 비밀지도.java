class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] arr3 = new String[n];
		for(int i = 0; i < n; i++) {
			arr3[i] = String.format("%" + n + "s", Integer.toBinaryString(arr1[i] | arr2[i])).replace(" ", "0");
			arr3[i] = arr3[i].replace("1", "#").replace("0", " ");
		}
        return arr3;
    }
}