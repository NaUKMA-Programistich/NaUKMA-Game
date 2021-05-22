package com.naukma.game.worlds;

import com.naukma.game.entity.Student;

import java.util.HashMap;
import java.util.Map;


public class WorldController {

    public enum Keys {
        LEFT,
        RIGHT,
        JUMP,
        FIRE
    }

    private World world;
    private final Student student;

    static Map<Keys, Boolean> keys = new HashMap<>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    }

    public WorldController(World world) {
        this.world = world;
        this.student = world.getStudent();
    }

    public void leftPressed() {
        System.out.println("leftPressed");
        keys.get(keys.put(Keys.LEFT, true));
    }
    public void rightPressed() {
        System.out.println("rightPressed");
        keys.get(keys.put(Keys.RIGHT, true));
    }
    public void jumpPressed() {
        System.out.println("jumpPressed");
        keys.get(keys.put(Keys.JUMP, true));
    }
    public void firePressed() {
        System.out.println("firePressed");
        keys.get(keys.put(Keys.FIRE, true));
    }


    public void leftReleased() {
        System.out.println("leftReleased");
        keys.get(keys.put(Keys.LEFT, false));
    }
    public void rightReleased() {
        System.out.println("rightReleased");

        keys.get(keys.put(Keys.RIGHT, false));
    }
    public void jumpReleased() {
        System.out.println("jumpReleased");
        keys.get(keys.put(Keys.JUMP, false));
    }
    public void fireReleased() {
        System.out.println("fireReleased");
        keys.get(keys.put(Keys.FIRE, false));
    }

    public void update(float delta) {
        processInput();
        student.update(delta);
    }

    private void processInput() {
        if (keys.get(Keys.LEFT)) {
            System.out.println("left");
            student.setFacingLeft(true);
            student.setState(Student.State.WALKING);
            student.getVelocity().x = -Student.SPEED;
        }
        if (keys.get(Keys.RIGHT)) {
            System.out.println("right");
            student.setFacingLeft(false);
            student.setState(Student.State.WALKING);
            student.getVelocity().x = Student.SPEED;
        }

        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
//            System.out.println("afk");
            student.setState(Student.State.IDLE);
            student.getAcceleration().x = 0;
            student.getVelocity().x = 0;
        }

    }


}
