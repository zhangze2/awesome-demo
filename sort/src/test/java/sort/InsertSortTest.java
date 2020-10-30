package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author by zz
 * @date 2020/8/24
 */
class InsertSortTest {

    @Test
    void sort() {

        int data[] = {7, 2, 4, 3, 12, 1, 9, 6, 8, 5,11,10};

        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j-1] > data[j]) {
                    SortUtil.swap(data, j-1, j);
                }
            }
            SortUtil.printSortArgs(data);
        }

    }




}