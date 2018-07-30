package ru.gcsales.app;

import org.junit.Test;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ExampleUnitTest {

    @Test
    public void test() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.map(data -> data + " integer")
                .subscribe(System.out::println);

        Observable.just(1, 2, 3, 4, 5)
                .subscribe(subject);
    }
}