package com.opentext.poi.entity;

import java.util.HashMap;

class Key {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Key(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Key)) {
            return false;
        } else {
            return this.getId().equals(((Key) obj).getId());
        }
    }

}

    public class WithoutHashCode {
        public static void main(String[] args) {
            Key k1 = new Key(2);
            Key k2 = new Key(2);

            HashMap<Key, String> hashMap = new HashMap<>();
            hashMap.put(k1, "Key with id is 1");
            System.out.println(hashMap.get(k2));
        }

    }

//        public static void main(String[] args) {
//            List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//            // 获取对应的平方数
//            List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//            System.out.println(squaresList.toString());
//        }
