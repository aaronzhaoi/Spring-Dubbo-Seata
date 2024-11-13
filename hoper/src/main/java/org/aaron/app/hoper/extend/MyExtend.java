package org.aaron.app.hoper.extend;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class MyExtend {

    public static <T extends Person> void doCallSuper(T people) {
        people.printNameAge();
    }

    /**
     * 方法上定义的泛型
     *
     * @param people
     * @param <T>
     */
    public static <T extends Person> void doCallSelf(T people) {
        people.printNameAge();
        if (people instanceof Teacher) {
            ((Teacher) people).printCourse();
        }
        if (people instanceof Student) {
            ((Student) people).printGrad();
        }
    }

    /**
     * 泛型定义在类上
     *
     * @param annimal
     */
    public static void doCallClaasExtend(Annimal annimal) {
        annimal.printAnimal();
    }


    public static void main(String[] argu) {
        Person student = new Student(12, "小学生", "小学");
        Person teacher = new Teacher(25, "小学老师", "语文");
        // 调用父类的方法
        System.out.print("***********调用父类的方法***********\n");
        doCallSuper(student);
        doCallSuper(teacher);
        // 调用子类的方法
        System.out.print("***********调用子类的方法***********\n");

        doCallSelf(student);
        doCallSelf(teacher);

        // 定义在类上的泛型
        System.out.print("***********定义在类上的泛型***********\n");

        Student student02 = new Student(15, "初中生", "初中");
        Annimal<Student> annimalStu = new Annimal<>(student02);
        Teacher teacher02 = new Teacher(35, "初中老师", "物理化");
        Annimal<Teacher> annimalTea = new Annimal<>(teacher02);
        annimalStu.printAnimal();
        annimalTea.printAnimal();

        // 定义在类上传入子类
        System.out.print("***********定义在类上传入子类***********\n");
        Person student03 = new Student(25, "大学生", "大学");
        Annimal<Person> annimalStu03 = new Annimal<>(student03);
        Teacher teacher03 = new Teacher(35, "大学老师", "微机原理");
        Annimal<Person> annimalTea03 = new Annimal<>(teacher03);
        annimalStu03.printAnimal();
        annimalTea03.printAnimal();

        // 定义在接口上
        System.out.print("***********定义在接口上***********\n");
        Student student05 = new Student(35, "研究生", "研究院");
        Teacher teacher05 = new Teacher(55, "博士老师", "天文学");
        Tools<Student> toolsStu = new MyToolsStudent();
        toolsStu.printToolS(student05);
        Tools<Teacher> toolsTeacher = new MyToolsTeacher();
        toolsTeacher.printToolS(teacher05);

        System.out.print("***********定义在接口上但是接口不负责类型***********\n");
        Student student06 = new Student(39, "博士生", "中科院");
        Teacher teacher06 = new Teacher(60, "博导", "中科院博导办公室");
        MyTools<Student> toolsStu06 = new MyTools<>();
        toolsStu06.printToolS(student06);
        MyTools<Teacher> toolsTeach06 = new MyTools<>();
        toolsTeach06.printToolS(teacher06);
    }

}


@Data
class Person {
    int age;
    String Name;

    public Person(int age, String name) {
        this.age = age;
        Name = name;
    }

    public void printNameAge() {
        System.out.print(this.Name + "-" + this.age + "\n");
    }
}

@Getter
@Setter
@ToString(callSuper = true)
class Student extends Person {
    String grad;

    public Student(int age, String name, String grad) {
        super(age, name);
        this.grad = grad;
    }

    public void printGrad() {
        System.out.print(this.grad + "\n");
    }

}

@Setter
@Getter
@ToString(callSuper = true)
class Teacher extends Person {
    String course;

    public Teacher(int age, String name, String course) {
        super(age, name);
        this.course = course;
    }

    public void printCourse() {
        System.out.print(this.course + "\n");
    }
}

@Getter
@Setter
class Annimal<E> {
    public Annimal(E type) {
        this.type = type;
    }

    E type;

    public void printAnimal() {
        if (type instanceof Person) {
            ((Person) type).printNameAge();
        }

        System.out.print(type + "\n");
    }
}

interface Tools<E> {
    void printToolS(E e);
}

class MyToolsStudent implements Tools<Student> {

    @Override
    public void printToolS(Student e) {
        System.out.print(e.grad + "\n");
    }
}

class MyToolsTeacher implements Tools<Teacher> {

    @Override
    public void printToolS(Teacher e) {
        System.out.print(e.getCourse() + "\n");
    }
}


class MyTools<E> implements Tools<E> {

    @Override
    public void printToolS(E e) {
        if (e instanceof Teacher) {
            System.out.print("老师：" + e + "\n");
        }
        if (e instanceof Student) {
            System.out.print("学生：" + e + "\n");
        }

    }
}