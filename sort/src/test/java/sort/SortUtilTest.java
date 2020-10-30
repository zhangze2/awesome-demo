package sort;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author by zz
 * @date 2020/8/24
 */
class SortUtilTest {

    @Test
    void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;

        Assert.isTrue(data[i] == data[j]);

    }
}