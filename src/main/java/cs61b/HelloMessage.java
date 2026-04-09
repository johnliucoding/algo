package cs61b;

import java.util.Objects;

public class HelloMessage {

    private String message;
    private String subject;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        HelloMessage that = (HelloMessage) o;
//        return Objects.equals(message, that.message) && Objects.equals(subject, that.subject);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(message, subject);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloMessage that = (HelloMessage) o;
        return message.equals(that.message) && subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, subject);
    }
}
