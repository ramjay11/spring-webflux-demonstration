package com.ramjava.spring.webflux.demonstration.service;

import com.ramjava.spring.webflux.demonstration.entity.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // Synchronous way
    // It will wait for all records to be fetched before displaying the result
    public List<Student> getStudents() {
        Long start = System.currentTimeMillis();

        List<Student> studentList = new ArrayList<>();
        //
        for (int i = 1; i <= 20; i++) {
            studentList.add(new Student(i, "student " + 1));
            System.out.println("Student record : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Long end = System.currentTimeMillis();
        System.out.println("Total time taken by Synchronous API: " + (end-start));
        return studentList;
    }

    // Asynchronous non-blocking approach
    // Multiple thread will be executed simultaneously
    public Flux<Student> getStudentList() {
        var start = System.currentTimeMillis();
        Flux<Student> studentFlux = Flux.range(1, 20)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Student record by stream : " + i))
                // convert to student object
                .map(i -> new Student(i, "student " + i));
        var end = System.currentTimeMillis();
        System.out.println("Total time taken by Asynchronous API: " + (end-start));
        return studentFlux;
    }


}
