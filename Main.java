import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        int[] array = {3, 4, -7, 3, 4, -7, 3, 1, 3, 1, -4, -2, -2};
        for (int i = 0; i < isZero(array).size(); i++) {
            System.out.println(Arrays.toString(isZero(array).get(i)));
        }


    }

    public static ArrayList<int[]> isZero(int[] array) {
        HashMap<Integer, ArrayList<Integer>> object = new HashMap<>();
        int sum = 0;
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (!object.containsKey(sum)) {
                object.put(sum, new ArrayList<>());
                object.get(sum).add(i);
            } else
                object.get(sum).add(i);
        }
        for (int x : object.keySet()) {
            for (int i = 0; i < object.get(x).size() - 1; i++) {
                for (int j = i + 1; j < object.get(x).size(); j++) {
                    int[] temp = {object.get(x).get(i)+1, object.get(x).get(j)};
                    list.add(temp);
                }
            }
        }
        if (object.containsKey(0)){
            for (int x : object.get(0)) {
                    int[] temp = {0, x};
                    list.add(temp);

            }
        }
        return list;
    }
}



