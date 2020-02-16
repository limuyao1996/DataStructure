public class Array {
    private int[] data;
    private int size;

    /**
     * 构造函数，传入数组容量capacity构造数组
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     *  无参构造函数，默认数组容量capacity = 10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组中元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
