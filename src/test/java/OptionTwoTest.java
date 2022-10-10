import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTwoTest {

    @Test
    void should_create_ip_address_for_long_type_valid_number(){
        assertEquals("128.32.10.1", OptionTwo.transformNumberToIpAddress(2149583361L));
        assertEquals("55.233.115.227", OptionTwo.transformNumberToIpAddress(938046435L));
        assertEquals("0.0.0.32", OptionTwo.transformNumberToIpAddress(32L));
        assertEquals("0.0.0.0", OptionTwo.transformNumberToIpAddress(0L));
    }

    @Test
    void should_throw_error_when_trying_to_create_ip_address_for_null_number(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            OptionTwo.transformNumberToIpAddress(null);
        });

        assertEquals("Cannot convert \"null\" number to ip address", exception.getMessage());
    }


}
