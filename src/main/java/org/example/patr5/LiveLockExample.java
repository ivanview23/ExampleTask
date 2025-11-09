package org.example.patr5;


public class LiveLockExample {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            System.out.println("Ложка у мужа");
            owner = d;
        }

        public synchronized void use() {
            System.out.println(owner.name + " использует ложку!");
        }

        public synchronized void setOwner(Diner d) {
            owner = d;
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String n) {
            name = n;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            System.out.println("Начинает работать " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error");;
            }

            while (isHungry) {
                System.out.println("Начинается цикл в " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error");;
                }

                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    System.out.println("Сбрасываем цикл типа ждет " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    continue;
                }

                System.out.println("Продолжает есть " + Thread.currentThread().getName() + " т.к. ложка у него");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error");;
                }

                if (spouse.isHungry) {
                    System.out.println(spouse.name + " хочет есть просит ложку у " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    System.out.println(name + ": Дорогой " + spouse.name + ", я тебе отдам ложку!");
                    spoon.setOwner(spouse);
                    continue;
                }

                System.out.println(name + ": Я поел!");
                spoon.use();
                isHungry = false;
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        final Diner husband = new Diner("Муж");
        final Diner wife = new Diner("Жена");

        final Spoon spoon = new Spoon(husband);

        Thread tread1 = new Thread(() -> husband.eatWith(spoon, wife));
        Thread tread2 = new Thread(() -> wife.eatWith(spoon, husband));

        tread1.setName("Муж");
        tread2.setName("Жена");

        tread1.start();
        tread2.start();
    }
}