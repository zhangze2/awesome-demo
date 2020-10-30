package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author by zz
 * @date 2020/8/24
 */
class SelectionSortTest {

    @Test
    void sort() {
        int data[] = {7, 2, 4, 3, 12, 1, 9, 6, 8, 5,11,10};

        for (int i = 0; i < data.length; i++) {
            int tempIndex = i;

            for (int j = data.length - 1; j > i;  j--) {
                if (data[j] < data[tempIndex]) {
                    tempIndex = j;
                }
            }

            SortUtil.swap(data, i, tempIndex);
            SortUtil.printSortArgs(data);
        }
    }
}