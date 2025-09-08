#For Constant: Use Enums

eg:
public class Person {
    Gender gender;
    int age;
    Person( Gender gender, int age){
    this.gender = gender;
    this.age = age;
    }
}

Enum:
public enum Gender {
Male, Female
}