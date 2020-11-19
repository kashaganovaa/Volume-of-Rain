import java.util.Arrays;

public class Wheather {
    //for testing purposes
    private static int test[] = new int[] {3, 2, 4, 1, 2};

    //First approach dynamic programming
    static int[] CalcVolume(int[] height) {
        //right_max tallest surface from the right including current surface
        //right_max ... from the left ...
        //volume array to save how much water can be poured on top

        int n = height.length;
        int right_max[] = new int[n];
        int left_max[] = new int[n];
        int volume[] = new int[n];

        //end cases
        right_max[n - 1] = test[n - 1];
        left_max[0] = test[0];

        //starting from the right end till current
        for (int i = n - 2; i >= 0; i--) {
            right_max[i] = Math.max(test[i], right_max[i + 1]);
        }

        //starting from the left end till current
        for (int i = 1; i < n; i++) {
            left_max[i] = Math.max(test[i], left_max[i - 1]);
        }

        // difference between the min of 2 and current
        // will give us how much water can be poured on top
        for (int i = 0; i < n; i++) {
            volume[i] = Math.min(right_max[i], left_max[i]) - test[i];
//            System.out.println(water[i]);
        }
        return volume;
    }

    public static void main(String[] args) {
        int res[] = CalcVolume(test);
//        System.out.println(Arrays.toString(test));
//        System.out.println(Arrays.toString(res));
        System.out.println("Volume after rain: " + Arrays.stream(res).sum());
        //the dummest approach on Earth for printing UPSIDE DOWN
        //imagine that you are standing on top
        int max = Arrays.stream(test).max().getAsInt();
        while(max > 0) {
            for (int i = 0; i < test.length; i++) {
                if (test[i] == 0) {
                    if (res[i] == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("o");
                        res[i]--;
                    }
                }
                else {
                    System.out.print("x");
                    test[i]--;
                }
            }
            System.out.println();
            max--;
        }
    }
}
