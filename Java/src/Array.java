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

    /**
     * 向数组中添加元素
     * @param e 要添加的元素
     */
    public void addLast(int e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加元素
     * @param e 要添加的元素
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 向指定位置插入元素
     * @param index 插入位置
     * @param e 要插入的值
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException(("Add failed. Array is full."));
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index <= size");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append((", "));
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 获取指定位置的元素
     * @param index 元素下标
     * @return 元素
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. index is illegal.");
        }
        return data[index];
    }

    /**
     * 设置指定位置的元素值
     * @param index 元素下标
     * @param e 元素值
     */
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is Illegal.");
        }
        data[index] = e;
    }
}
