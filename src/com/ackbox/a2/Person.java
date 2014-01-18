package com.ackbox.a2;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

/**
 * Model class representing a Person in the app.
 * 
 * @author trein
 * 
 */
public class Person {

    private final String name;
    private Integer age;
    private String food;

    private Person(String name) {
        this.name = name;
    }

    public static Person withName(String name) {
        return new Person(name);
    }

    public Person andAge(Integer age) {
        this.age = age;
        return this;
    }

    public Person andFood(String food) {
        this.food = food;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getFood() {
        return this.food;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.age, this.food);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person other = (Person) o;
            return Objects.equal(this.name, other.name) && Objects.equal(this.age, other.age)
                    && Objects.equal(this.food, other.food);
        }
        return false;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add("age", getAge()).add("food", getFood())
                .toString();
    }

    public boolean isValid() {
        return !Strings.isNullOrEmpty(this.name) && Utils.isValidAge(this.age) && !Strings.isNullOrEmpty(this.food);
    }

}
