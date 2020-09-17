package com.opentext.lambda.demo;

public class Streams  {

    public enum Status {
        OPEN, CLOSED
    };

    //静态内部类
    public static final class Task {

        private final Status status;
        private final Integer points;

        //静态内部类的构造方法
        public Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points);
        }
    }

    public Streams(Task task, Status status) {

    }
}