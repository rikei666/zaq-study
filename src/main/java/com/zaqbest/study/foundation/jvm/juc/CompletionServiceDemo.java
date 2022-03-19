package com.zaqbest.study.foundation.jvm.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        ExecutorCompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executor);

        List<Future> futures = new ArrayList<>();

        for (int i =0; i < 5; i++){
            futures.add(completionService.submit(new CalcTask(i*20+1, i*20+20)));
        }

        int total = 0;
        for (int i =1; i <= 5; i++){
            Integer result = completionService.take().get();
            //System.out.println(result);
            total += result;
        }

        System.out.println(total);

        executor.shutdown();
    }
}

class CalcTask implements Callable<Integer>{
    int from;
    int to;

    public CalcTask(int from, int to) {
        this.from = from;
        this.to = to;
    }
    /**
    * @description  TODO(这里用一句话描述类的作用)
    * @param
    * @return
    * @author lipan
    * @date 2021/3/31 14:35
    */

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        TimeUnit.MICROSECONDS.sleep(1000);
        return (from + to) * (to - from + 1) /2;
    }
}
