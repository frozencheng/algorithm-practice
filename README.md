# algorithm-practice
## 算法练习
###     1.数据结构 栈的算法练习 
    1）用数组模拟栈 计算 中缀表达式
    2）用栈 实现中缀表达式转换成后缀表达式的算法 计算后缀表达式（逆波兰表达式）

###     2. 递归回溯 算法练习
    1) 小球找路问题
    2) 八皇后问题
### 3.排序算法

- 冒泡排序

    ```java
    public static void sortAsc(int[] array) {
            int temp; // 临时变量
            boolean flag; // 标识符 用来判断 数组中的元素是否还在进行交换
            for (int i = 0; i < array.length - 1; i++) {
                flag = false;
                for (int j = 0; j < array.length - 1 - i; j++) {// 每次循环确定当前循环的最大值
                    if (array[j] > array[j + 1]) {
                        flag = true;
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
                if (!flag) { // 说明本次循环没有发生交换 也就是排序已经完毕了
                    break;
                }
            }
        }
    ```

- 选择排序

    ```java
    public static void sortAsc(int[] array) {
            int minIndex;// 临时最小值的索引
            int min;// 临时最小值
            for (int i = 0; i < array.length; i++) {
                min = array[i];
                minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (min > array[j]) {
                        min = array[j];
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    array[minIndex] = array[i];
                    array[i] = min;
                }
            }
        }
    ```

    

- 插入算法

    ```java
    public static void sortAsc(int[] array) {
    
        int insertVal;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertVal = array[i];
            insertIndex = i - 1;// array[i]前面一个数的下标
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
    
            array[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "轮" + Arrays.toString(array));
        }
    }
    ```

- 希尔排序

    ```java
    public static void sortAscAsChange(int[] array) {
            int temp;
            for (int avg = array.length / 2; avg > 0; avg /= 2) {
    
                for (int i = avg; i < array.length; i++) { // 对 分组的组数(avg)进行遍历
                    for (int j = i - avg; j >= 0; j -= avg) { // 对每组的元素 以步长为 avg 进行遍历判断大小决定是否交换
                        if (array[j] > array[j + avg]) { // 判断 大小 是否需要交换位置
                            temp = array[j];
                            array[j] = array[j + avg];
                            array[j + avg] = temp;
                        }
                    }
                }
            }
        }
    
        // 采用移动式(针对分组的每一组进行插入排序,对交换式进行的优化)
        public static void sortAscAsInsert(int[] array) {
            int insertVal;
            int insertIndex;
            for (int avg = array.length / 2; avg > 0; avg /= 2) { // 将数组分为 avg组
                for (int i = avg; i < array.length; i++) { // 对分组的 每组 进行 插入排序
                    insertIndex = i - avg;
                    insertVal = array[i];
                    while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                        array[insertIndex + avg] = array[insertIndex];
                        insertIndex -= avg;
                    }
                    if (insertIndex + avg != i) {
                        array[insertIndex + avg] = insertVal;
                    }
                }
            }
        }
    ```

    

- 快速排序

    ```java
    public static void sort(int[] array, int L, int R) {
            // 该算法需要用递归 所以 需要一个退出条件（说明该数组排序已经完毕了）
            if (L >= R || array.length == 1) {
                return;
            }
            int left = L;
            int right = R;
            int pivot = array[left];
    
            while (left < right) {
                // 先移动右边的值 直到碰到小于等于pivot 然后赋值给array[left]
                while (array[right] >= pivot && right > left) { //
                    right--;
                }
                // 右边可能已经找到了 所以要判断
                if (right > left) {
                    array[left] = array[right];
                    left++;
                }
    
                while (array[left] <= pivot && left < right) {
                    left++;
                }
                if (array[left] > pivot) {
                    array[right] = array[left];
                    right--;
                }
                if (left == right) {
                    array[left] = pivot;
                    break;
                }
    //            System.out.println(Arrays.toString(array));
            }
            if (left == right) {
                left++;
                right--;
            }
    
            if (right > L) {// 将左半边的 继续排序
                sort(array, L, right);
            }
            if (left < R) {// 将右半边的 继续排序
                sort(array, left, R);
            }
        }
    ```

    

- 归并排序

    ```java
    /**
         * 分+合并的方法
         * 递归使用此方法
         *
         * @param arr   需要分的有序数组
         * @param left  需要分的数组的左下标
         * @param right 需要分的数组右下标
         * @param temp  临时存放的数组
         */
        public static void mergeSort(int[] arr, int left, int right, int[] temp) {
    
            if (left < right) { //  将数组分为 每个序列只有一个为止
                int mid = (left + right) / 2;
                // 继续分左右序列 直到长度为1
                mergeSort(arr, left, mid, temp);  // 向左递归进行分解
                mergeSort(arr, mid + 1, right, temp); // 向右递归进行分解
                // 然后 开始合并 第一次为顶栈 mergeSort方法
                merge(arr, left, mid, right, temp);
            }
        }
    
        /**
         * 合并的方法
         *
         * @param arr   需要排序的数组
         * @param left  有序数组（分解后的部分）的坐下标
         * @param mid   有序数组的中间下标
         * @param right 有序数组的右下标
         * @param temp  临时存放合并的数组
         */
        public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
            int l = left; // 初始化l 左边序列的的初始索引
            int r = mid + 1; // 初始化 r 右边序列的初始索引
            int t = 0;// 临时数组的下标
            // 先把左右两边的数据 按照规则填充到temp中
            while (l <= mid && r <= right) {
                if (arr[l] <= arr[r]) { // 如果左下标 小于右下标的值 那么就把左下标的值存放在temp[t]中
                    temp[t] = arr[l];
                    l++;
                    t++;
                } else {// 反之 右边的比左边的小 那么存放右边到temp中
                    temp[t] = arr[r];
                    t++;
                    r++;
                }
            }
            // 如果此时左右序列中有一个序列中还有值那么就依次放入到temp中
            // 先判断左边
            while (l <= mid) {
                temp[t] = arr[l];
                l++;
                t++;
            }
            // 在判断右边
            while (r <= right) {
                temp[t] = arr[r];
                t++;
                r++;
            }
            // 此时 将temp中的数据copy到arr中 left:0,right:1 2,3 4,5 .... 0,arr.length-1
            t = 0;
            int leftTemp = left; // 临时左下标 并不总是为0
            while (leftTemp <= right) {
                arr[leftTemp] = temp[t];
                leftTemp++;
                t++;
            }
        }
    ```

    

- 基数排序

    ```java
    /**
         * 基数排序的方法(不能对含有负数的数组进行排序)
         * 1 创建一个二维数组 10个桶 按照规则将数据存放到 对应的桶
         * 2 创建一个辅助数组 针对每次循环存放到对应的桶的数据进行计数
         * 3 确定需要排序数组的最大值 根据最大值的位数来确定需要基数排序的次数
         */
        public static void sort(int[] arr) {
    
            int[][] bucket = new int[10][arr.length];
            int[] bucketEleCount = new int[bucket.length];
            int temp = arr[0]; // 临时变量 假设索引0的值位最大值
            int indexHelper;
            // 遍历 比较 来获取最大值
            for (int i = 1; i < arr.length; i++) {
                if (temp < arr[i]) {
                    temp = arr[i];
                }
            }
            String str = temp + "";
            int element;
            for (int k = 0, n = 1; k < str.length(); k++, n *= 10) {
                for (int value : arr) {
                    element = value / n % 10; // 当前第K次循环 arr中元素的基数
                    bucket[element][bucketEleCount[element]] = value; // 将arr[i] 放入对应的桶中
                    bucketEleCount[element]++; // 对放入对应桶的数据进行基数
                }
                // 将bucket中的数据依次插入到arr中
                int j = 0;
                for (int i = 0; i < bucketEleCount.length; i++) {
                    indexHelper = 0;
                    while (bucketEleCount[i] != 0) {
                        arr[j] = bucket[i][indexHelper];
                        indexHelper++;
                        bucketEleCount[i]--;
                        j++;
                    }
                }
            }
        }
    
        /**
         * 对包含负数的数组进行排序
         */
        public static void sort2(int[] arr) {
    
            int[][] bucket = new int[10][arr.length];
            int[] bucketEleCount = new int[bucket.length];
            int temp = Math.abs(arr[0]); // 临时变量 假设索引0的值位最大值（绝对值）
            int indexHelper;
            // 遍历 比较 来获取最大值(绝对值)
            for (int i = 1; i < arr.length; i++) {
                if (temp < Math.abs(arr[i])) {
                    temp = Math.abs(arr[i]);
                }
            }
            String str = temp + "";
            int element;
            for (int k = 0, n = 1; k < str.length(); k++, n *= 10) {
                for (int value : arr) {
                    element = value / n % 10; // 当前第K次循环 arr中元素的基数
                    // 对element 判断是否位负数 如果为负数就去绝对
                    element = Math.abs(element);
                    bucket[element][bucketEleCount[element]] = value; // 将arr[i] 放入对应的桶中
                    bucketEleCount[element]++; // 对放入对应桶的数据进行基数
                }
                // 将bucket中的数据依次插入到arr中
                int j = 0;
                // 先将桶从大到小将负数 添加  然后在从小到大添加正数
                for (int i = bucketEleCount.length - 1; i >= 0; i--) {
                    indexHelper = 0;
                    while (bucketEleCount[i] != 0 && indexHelper < bucketEleCount.length) {
                        if (bucket[i][indexHelper] < 0) {
                            arr[j] = bucket[i][indexHelper];
                            j++;
                            bucketEleCount[i]--;
                        }
                        indexHelper++;
                    }
                }
                // 然后将正数添加
                for (int i = 0; i < bucketEleCount.length; i++) {
                    indexHelper = 0;
                    while (bucketEleCount[i] != 0 && indexHelper < bucketEleCount.length) {
                        if (bucket[i][indexHelper] >= 0) {
                            arr[j] = bucket[i][indexHelper];
                            j++;
                            bucketEleCount[i]--;
                        }
                        indexHelper++;
                    }
                }
            }
        }
    ```

    