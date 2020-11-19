package com.distribution.uidgen;

import com.distribution.uidgen.uid.UidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class UidGenApplicationTests {
    @Autowired
    @Qualifier("snowFlakeGenerator")
    UidGenerator uidGenerator;

    @Test
    public void performance_test() throws Exception {
        int THREAD_POOL_SIZE = 100;
        int JOB_COUNT = 10000;
        List<Long> ids = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CountDownLatch latch = new CountDownLatch(JOB_COUNT);
        for (int i = 0; i < JOB_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    ids.add(uidGenerator.gen());
                    latch.countDown();
                } catch (Exception e) {
                    System.out.println(e);
                    Thread.currentThread().interrupt();
                }
            });
        }

        latch.await(); // wait JOB_COUNT jobs finish.
        executorService.shutdown();
        System.out.println("List size:" + ids.size());
        System.out.println("HashSet size:" + new HashSet<>(ids).size());
        ids.forEach(id -> System.out.println(id));
        if (ids.size() != new HashSet<>(ids).size()) {
            throw new Exception("Some duplicated ids were generated!!");
        }
    }
}
