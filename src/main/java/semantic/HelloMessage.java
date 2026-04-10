package semantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class HelloMessage {

    private static final Logger log = LoggerFactory.getLogger(HelloMessage.class);

    public String message;
    public String subject;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HelloMessage that)) return false;
        return Objects.equals(message, that.message) && Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, subject);
    }


    @Override
    public String toString() {
        return "HelloMessage{" +
                "message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
