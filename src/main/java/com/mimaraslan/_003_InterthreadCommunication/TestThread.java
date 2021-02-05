package com.mimaraslan._003_InterthreadCommunication;

class Chat {
    boolean flag = false;

    public synchronized void Question(String msg) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        flag = true;
        notify();
    }

    public synchronized void Answer(String msg) {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        flag = false;
        notify();
    }
}

// Question
class T1 implements Runnable {
    Chat chat;
    String[] s1 = { "Q1", "Q2", "Q3" };

    public T1(Chat chat) {
        this.chat = chat;
        new Thread(this, "Question").start();
    }

    public void run() {
        for (int i = 0; i < s1.length; i++) {
            chat.Question(s1[i]);
        }
    }
}

// Answer
class T2 implements Runnable {
    Chat chat;
    String[] s2 = { "A1", "A2", "A3" };

    public T2(Chat chat) {
        this.chat = chat;
        new Thread(this, "Answer").start();
    }

    public void run() {
        for (int i = 0; i < s2.length; i++) {
            chat.Answer(s2[i]);
        }
    }
}

public class TestThread {
    public static void main(String[] args) {
        Chat chat = new Chat();
        new T1(chat);
        new T2(chat);
    }
}