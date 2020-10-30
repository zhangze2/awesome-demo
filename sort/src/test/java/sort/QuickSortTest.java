package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 快排-单元测试
 * @author by zz
 * @date 2020/8/22
 */
class QuickSortTest {

    @Test
    void sort() {
        int data[] = {7, 2, 4, 3, 12, 1, 9, 6, 8, 5,11,10};
        quickSort(data, 0, data.length-1);
    }

    void quickSort(int data[], int low, int hight) {
        if (low < hight) {
            int middle = getMiddle(data, low, hight);
            quickSort(data, 0, middle-1);
            quickSort(data, middle+1, hight);

            System.out.println(data.toString());
        }
    }

    int getMiddle(int data[], int low, int hight) {
        int temp = data[low];
        while (low < hight) {
            while (low < hight && temp < data[hight]) {
                hight--;
            }

            data[low] = data[hight];

            while (low < hight && temp > data[low]) {
                low++;
            }
            data[hight] = data[low];

        }

        data[low] = temp;

        return low;
    }
}