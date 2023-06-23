package org.example.generator;

import org.example.objects.CourierObject;

public class CourierGenerator {
    public static CourierObject generateCourier() {
        return new CourierObject().setLogin("login").setPassword("pass").setFirstName("Avacado");
    }

    public static CourierObject generateCourierEmptyPassword() {
        return new CourierObject().setFirstName("Tomato").setLogin("Meat").setPassword("");
    }

    public static CourierObject generateCourierNoPassword() {
        return new CourierObject().setFirstName("Orange").setLogin("Mouse");
    }

    public static CourierObject generateCourierOnlyLoginPassword() {
        return new CourierObject().setLogin("tested").setPassword("passwordik");
    }

    public static CourierObject generateCourierOnlyLoginEmptyPassword() {
        return new CourierObject().setLogin("water").setPassword("");
    }

    public static CourierObject generateCourierOnlyLogin() {
        return new CourierObject().setLogin("water");
    }
}
