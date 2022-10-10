import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Firstly, YES, I know that we should write unit tests for public methods, NOT PRIVATE like in my case,
 * but if it is possible to do this and thus learn something new, why not
 * <p>
 * Let's assume that this is a legacy application where I can't modify the visibilty of methods
 * <p>
 * Since the private methods are not visible from here, I should have found a way to change this.
 * Thus, using reflection, I could access the private methods outside the class.
 */

class OptionOneTest {

    @Test
    void should_return_power_of_two_for_specific_power() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(8, getCalculatePowerOfTwoMethod().invoke(null, 3));
        assertEquals(32, getCalculatePowerOfTwoMethod().invoke(null, 5));
    }

    /**
     * Because of reflection, the exception is being wraped into an InvocationTargetException.
     */

    @Test
    void should_throw_error_when_power_is_null() {
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {

            getCalculatePowerOfTwoMethod().invoke(null, (Object) null);
        });
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals("Power should not be null", exception.getCause().getMessage());
    }

    @Test
    void should_convert_binary_format_to_number_for_valid_input() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(3L, getBinaryToNumberMethod().invoke(null, "11"));
        assertEquals(5L, getBinaryToNumberMethod().invoke(null, "0101"));
        assertEquals(128L, getBinaryToNumberMethod().invoke(null, "10000000"));
        assertEquals(32L, getBinaryToNumberMethod().invoke(null, "00100000"));
    }

    /**
     * Because of reflection, the exception is being wraped into an InvocationTargetException.
     */

    @Test
    void should_throw_error_when_binary_number_is_null() {
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {

            getBinaryToNumberMethod().invoke(null, (Object) null);
        });
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals("Binary number should not be null", exception.getCause().getMessage());
    }

    @Test
    void should_convert_number_to_binary_representation_for_32_bits_for_valid_input() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals("10000000001000000000101000000001", getBinaryRepresentationMethod().invoke(null, 2149583361L));
        assertEquals("00000000000000000000000000000101", getBinaryRepresentationMethod().invoke(null, 5L));
    }

    /**
     * Because of reflection, the exception is being wraped into an InvocationTargetException.
     */

    @Test
    void should_throw_error_when_trying_to_tranform_to_binary_and_number_is_null() {
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {

            getBinaryRepresentationMethod().invoke(null, (Object) null);
        });
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals("Number should not be null", exception.getCause().getMessage());
    }

    @Test
    void should_create_ip_address_for_its_binary_representation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals("128.32.10.1", getCreateNumberMethod().invoke(null, "10000000001000000000101000000001"));
    }

    /**
     * Because of reflection, the exception is being wraped into an InvocationTargetException.
     */

    @Test
    void should_throw_error_when_trying_to_create_ip_address_and_binary_representation_is_null() {
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {

            getCreateNumberMethod().invoke(null, (Object) null);
        });
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals("Binary representation should not be null", exception.getCause().getMessage());
    }

    @Test
    void should_create_ip_address_for_long_type_valid_number() {
        assertEquals("128.32.10.1", OptionOne.transformNumberToIpAddress(2149583361L));
        assertEquals("55.233.115.227", OptionOne.transformNumberToIpAddress(938046435L));
        assertEquals("0.0.0.32", OptionOne.transformNumberToIpAddress(32L));
        assertEquals("0.0.0.0", OptionOne.transformNumberToIpAddress(0L));
    }

    @Test
    void should_throw_error_when_trying_to_create_ip_address_for_null_number() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            OptionOne.transformNumberToIpAddress(null);
        });

        assertEquals("Cannot convert \"null\" number to ip address", exception.getMessage());
    }

    private Method getCalculatePowerOfTwoMethod() throws NoSuchMethodException {
        Method method = OptionOne.class.getDeclaredMethod("calculatePowerOfTwo", Integer.class);
        method.setAccessible(true);
        return method;
    }

    private Method getBinaryToNumberMethod() throws NoSuchMethodException {
        Method method = OptionOne.class.getDeclaredMethod("binaryToNumber", String.class);
        method.setAccessible(true);
        return method;
    }

    private Method getBinaryRepresentationMethod() throws NoSuchMethodException {
        Method method = OptionOne.class.getDeclaredMethod("getBinaryRepresentation", Long.class);
        method.setAccessible(true);
        return method;
    }

    private Method getCreateNumberMethod() throws NoSuchMethodException {
        Method method = OptionOne.class.getDeclaredMethod("createIpAddress", String.class);
        method.setAccessible(true);
        return method;
    }


}
