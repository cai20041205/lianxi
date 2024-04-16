package com.lianxi.domain;

import java.util.Objects;

public class GirlFriend implements Comparable<GirlFriend>{
    private String name;
    private Integer  age;

    private Double height;
//Iterator<String>

    public GirlFriend() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GirlFriend that = (GirlFriend) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }

    public GirlFriend(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return height
     */
    public double getHeight() {
        return height;
    }



    /**
     * 设置
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }


    public String toString() {
        return "GirlFriend{name = " + name + ", age = " + age + ", height = " + height + "}";
    }

    @Override
    public int compareTo(GirlFriend o) {
        return this.getAge()-o.getAge();
    }
}
