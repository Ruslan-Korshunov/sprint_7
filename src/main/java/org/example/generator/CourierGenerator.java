package org.example.generator;

import com.github.javafaker.Faker;
import org.example.objects.CourierObject;

public class CourierGenerator {
    static Faker faker = new Faker();
    public static CourierObject generateCourier() {
        return new CourierObject().setLogin(
                faker.animal().name()).setPassword(faker.food().dish()).setFirstName(faker.name().firstName());
    }

    public static CourierObject generateCourierEmptyPassword() {
        return new CourierObject().setFirstName(
                faker.name().firstName()).setLogin(faker.funnyName().name()).setPassword("");
    }

    public static CourierObject generateCourierNoPassword() {
        return new CourierObject().setFirstName(faker.name().firstName()).setLogin(faker.artist().name());
    }

    public static CourierObject generateCourierOnlyLoginPassword() {
        return new CourierObject().setLogin(faker.starTrek().character()).setPassword(faker.medical().hospitalName());
    }

    public static CourierObject generateCourierOnlyLoginEmptyPassword() {
        return new CourierObject().setLogin(faker.food().fruit()).setPassword("");
    }
}
