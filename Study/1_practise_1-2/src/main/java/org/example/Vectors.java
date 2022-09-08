package org.example;

public class Vectors {
    public static int[] createVector(int x, int y, int z) {
        int[] vector = new int[]{x, y, z};
        return vector;
    }

    public static int scalarProduct(int[] v1, int[] v2) {
        int sum = 0;
        for (int i = 0; i < v1.length; i++) {
            sum += v1[i] * v2[i];
        }
        return sum;
    }

    public static int[] vectorProduct(int[] v1, int[] v2){
        return new int[]{v1[1] * v2[2] - v1[2] * v2[1], v1[2] * v2[0] - v1[0] * v2[2], v1[0] * v2[1] - v1[1] * v2[0]};
    }

    public static int[] additionVector(int[] v1, int[] v2){
        return new int[]{v1[0] + v2[0], v1[1] + v2[1], v1[2] + v2[2]};
    }

    public static int[] subtractionVector(int[] v1, int[] v2){
        return new int[]{ v1[0] - v2[0], v1[1] - v2[1], v1[2] - v2[2]};
    }

    public static double moduleVector(int[] v){
        int[] vsq = new int[]{(int) Math.pow(v[0], 2), (int) Math.pow(v[1], 2), (int) Math.pow(v[2], 2)};
        double sum = 0;
        for (int i = 0; i < vsq.length; i++) {
            sum += vsq[i];
        }
        sum = Math.sqrt(sum);
        return sum;
    }

    public static double angleBetweenVectors(int[] v1, int[] v2){
        int sc = scalarProduct(v1, v2);
        double pr = moduleVector(v1) * moduleVector(v2);
        return Math.acos(sc/pr);
    }

    public static void main(String[] args) {
        int[] v1 = createVector(1, 2, 3);
        int[] v2 = createVector(1, 1, 1);

        int sumS = scalarProduct(v1, v2);
        int[] sumV = vectorProduct(v1, v2);
        int[] sumV2 = additionVector(v1, v2);
        int[] subV = subtractionVector(v1, v2);

        double mod = moduleVector(v1);
        double angle = angleBetweenVectors(v1, v2) * (180/Math.PI);

        // print test
        // System.out.println("V1: " + Arrays.toString(v1));
        // System.out.println("V2: " + Arrays.toString(v2));
        // System.out.println("sum scalar: " + sumS);
        // System.out.println("vector sum: " + Arrays.toString(sumV));
        // System.out.println("sum vector: " + Arrays.toString(sumV2));
        // System.out.println("subst vector: " + Arrays.toString(subV));
        // System.out.println("mod vector: " + mod);
        // System.out.println("angle between vector: " + angle);
    }
}
