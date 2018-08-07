package com.ls;

import com.ls.interceptor.FirstInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
public class example {

    public static void main(String[] args) {
//        Person p = new Person("xxx");
//        Producer producer = new Producer(p);
//        Customer customer = new Customer(p);
//        Thread t = new Thread(producer);
//        Thread thread = new Thread(customer);
//        t.start();
//        thread.start();
//        Library library = new Library();
//        Producers producers1 = new Producers(library);
//        Producers producers2 = new Producers(library);
//        Producers producers3 = new Producers(library);
//
//        Customers customers1= new Customers(library);
//        Customers customers2= new Customers(library);
//
//
//        Thread thread1 = new Thread(producers1,"1号");
//        Thread thread2 = new Thread(producers2,"2号");
//        Thread thread3 = new Thread(producers3,"3号");
//        Thread thread4 = new Thread(customers1,"4号");
//        Thread thread5 = new Thread(customers2,"5号");
//        thread4.setPriority(10);
//        thread3.setPriority(8);
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
        SpringApplication.run(example.class,args);

    }
    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
        //增加拦截器
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(new FirstInterceptor())    //指定拦截器类
                    .addPathPatterns("/showInfo/**");        //指定该类拦截的url
        }
    }

}
class Producer implements Runnable{
    Person person;
    public Producer(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        for(int i=0;i<15;i++){
            if(i%2==0){
                person.push("li");
            }else{
                person.push("zhang");
            }
        }
    }
}

class Customer implements Runnable{
    Person person;
    public Customer(Person person){
        this.person = person;
    }
    @Override
    public void run() {
        for(int i = 0;i < 15;i++){
            person.pop();
        }
    }
}
class Person{
    public String name;

    public Person(){}
    public Person(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized void push(String name){
        this.name = name;
        try {
            Thread.sleep(10);
        }catch (Exception e){

        }
        System.out.println("生产了"+name);
    }
    public synchronized void pop(){
        try {
            Thread.sleep(10);
        }catch (Exception e){

        }
        System.out.println("消费了"+name);
    }
}
class Product{
    int id;
    public Product(int id){
        this.id = id;
    }
}
class Library{
    List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products){
        this.products = products;
    }

    public synchronized void push(Product p,String name){
        while(products.size() == 10){
            try {
                System.out.println("产品已满10个不能生产,进入等待");
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        products.add(p);
        System.out.println("一个产品已生产好，当前仓库有产品："+products.size()+"个");
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void pop(String name){
        while(products.size() == 0){
            try {
                System.out.println("产品不足，请等待生产");
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        products.remove(0);
        System.out.println("一个产品被消费");
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
class Producers implements Runnable{
    public Library library;
    public Producers(Library library){
        this.library = library;
    }
    @Override
    public void run() {
        Product product = new Product(10);
        library.push(product,Thread.currentThread().getName());
    }
}
class Customers implements Runnable{
    public Library library;
    public Customers(Library library){
        this.library = library;
    }
    @Override
    public void run() {
        library.pop(Thread.currentThread().getName());
    }
}