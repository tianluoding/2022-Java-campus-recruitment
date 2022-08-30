# Java线程池拒绝策略

关于handler参数

直接上源码

```java
public static class CallerRunsPolicy implements RejectedExecutionHandler {
    /**
         * Creates a {@code CallerRunsPolicy}.
         */
    public CallerRunsPolicy() { }

    /**
         * Executes task r in the caller's thread, unless the executor
         * has been shut down, in which case the task is discarded.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (!e.isShutdown()) {
            r.run();
        }
    }
}

/**
     * A handler for rejected tasks that throws a
     * {@code RejectedExecutionException}.
     */
public static class AbortPolicy implements RejectedExecutionHandler {
    /**
         * Creates an {@code AbortPolicy}.
         */
    public AbortPolicy() { }

    /**
         * Always throws RejectedExecutionException.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws RejectedExecutionException always
         */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        throw new RejectedExecutionException("Task " + r.toString() +
                                             " rejected from " +
                                             e.toString());
    }
}

/**
     * A handler for rejected tasks that silently discards the
     * rejected task.
     */
public static class DiscardPolicy implements RejectedExecutionHandler {
    /**
         * Creates a {@code DiscardPolicy}.
         */
    public DiscardPolicy() { }

    /**
         * Does nothing, which has the effect of discarding task r.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    }
}

/**
     * A handler for rejected tasks that discards the oldest unhandled
     * request and then retries {@code execute}, unless the executor
     * is shut down, in which case the task is discarded.
     */
public static class DiscardOldestPolicy implements RejectedExecutionHandler {
    /**
         * Creates a {@code DiscardOldestPolicy} for the given executor.
         */
    public DiscardOldestPolicy() { }

    /**
         * Obtains and ignores the next task that the executor
         * would otherwise execute, if one is immediately available,
         * and then retries execution of task r, unless the executor
         * is shut down, in which case task r is instead discarded.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (!e.isShutdown()) {
            e.getQueue().poll();
            e.execute(r);
        }
    }
}
```

## CallerRunsPolicy

被拒绝的任务由创建线程池的线程执行

## AbortPolicy

Always throws RejectedExecutionException. 抛出拒绝执行异常

## DiscardPolicy

丢弃任务

## DiscardOldestPolicy

Obtains and ignores the next task that the executor would otherwise execute, if one is immediately available, and then retries execution of task r,

抛弃任务队列中队头的任务，然后尝试将新任务加入任务队列