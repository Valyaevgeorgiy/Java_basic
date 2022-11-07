package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Vektors {

    @Test @DisplayName("testing (Vectors.scalarProduct)")
    void testScalarProduct() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        int[] v2 = Vectors.createVector(1, 1, 1);

        assertEquals(6, Vectors.scalarProduct(v1, v2));
    }

    @Test @DisplayName("testing (Vectors.vectorProduct)")
    void testVectorProduct() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        int[] v2 = Vectors.createVector(1, 1, 1);
        int[] v3 = new int[] {-1, 2, -1};

        assertEquals(Arrays.toString(v3), Arrays.toString(Vectors.vectorProduct(v1, v2)));
    }

    @Test @DisplayName("testing (Vectors.additionVector)")
    void testAdditionVector() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        int[] v2 = Vectors.createVector(1, 1, 1);
        int[] v3 = new int[] {2, 3, 4};

        assertEquals(Arrays.toString(v3), Arrays.toString(Vectors.additionVector(v1, v2)));
    }

    @Test @DisplayName("testing (Vectors.subtractionVector)")
    void testSubtractionVector() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        int[] v2 = Vectors.createVector(1, 1, 1);
        int[] v3 = Vectors.createVector(0, 1, 2);

        assertEquals(Arrays.toString(v3), Arrays.toString(Vectors.subtractionVector(v1, v2)));
    }

    @Test @DisplayName("testing (Vectors.moduleVector)")
    void testModuleVector() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        double t = 3.7416573867739413;

        assertEquals(Vectors.moduleVector(v1), t);
    }

    @Test @DisplayName("testing (Vectors.angleBetweenVectors)")
    void testAngleBetweenVectors() {
        int[] v1 = Vectors.createVector(1, 2, 3);
        int[] v2 = Vectors.createVector(1, 1, 1);
        double t = 22.207654298596477;

        assertEquals(Vectors.angleBetweenVectors(v1, v2) * (180 / Math.PI), t);
    }
}