public class ColocaPeso {
    public static int Peso(int torre[], int val) {

        for (int i : torre) {
            if (i != 0) {
                return i;
            }
        }
        return val;
    }
}
