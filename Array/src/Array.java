public class Array<E> {
    private E[] data;
    private int size;

    /***
     * 构造函数
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public  Array() {
        this(10);
    }

    // 获取数组的容量
    public int getSize() {
        return size;
    }

    // 获取数组中的元素个数
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e
     */
    public void addLast(E e) {
        addIndex(size, e);
    }


    /**
     * 向所有元素后添加一个新元素
     * @param e
     */
    public void addFirst(E e) {
        addIndex(0, e);
    }

    /**
     * 第index个位置插入e
     * @param index
     * @param e
     */
    public void addIndex(int index, E e) {
        if(index <0 || index > size) {
            throw new IllegalArgumentException("Add failed. required , require index >0 || < size");
        }
        if(size == data.length) {
            resize(2*data.length);
        }
        for (int i = size -1; i >= index ; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    E get(int index) {
        if(index< 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return  data[index];
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    void set(int index, E e) {
        if(index< 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
       data[index] = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity =%d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i!= size -1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 这个数组中是否存在该元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                return  true;
            }
        }
        return  false;
    }

    /**
     * 查找是否存在该元素，有则返回index否则返回-1
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                return  i;
            }
        }
        return  -1;
    }

    /**
     * 移除指定元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. index is illegal");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size --;
        data[size] = null; // loitering objects != memory leak
        if(size == data.length / 4 && data.length /2 != 0) {
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 移除第一个元素
     * @return
     */
    public E removeFirst() {
        return  remove(0);
    }

    /**
     * 移除最后一个元素
     * @return
     */
    public E removeLast() {
        return  remove(size-1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if(index!= -1) {
            remove(index);
        }
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
