public class OptionTwo {

    /**
     * This is how you can transform a number into an Ipv4 Address with bit shifting
     * fourth = number & 0xff;
     * third = (number >> 8)  & 0xff;
     * second = (number >> 16)  & 0xff;
     * first = (number >> 24)  & 0xff;
     * <p>
     * 0xff       = 00000000 00000000 00000000 11111111
     *                      &
     * 938046435  = 00110111 11101001 01110011 11100011
     * ----------------------------------------------------
     * fourth val = 00000000 00000000 00000000 11100011 = 227 base 10
     * <p>
     * Bit shifting:
     * 938046435 >> 16 => move 16 bits to the right => 00000000 00000000 00110111 11101001
     */

    public static String transformNumberToIpAddress(Long number) {
        if (number != null) {
            long fourth = number & 0xff;
            long third = (number >> 8) & 0xff;
            long second = (number >> 16) & 0xff;
            long first = (number >> 24) & 0xff;

            return first + "." + second + "." + third + "." + fourth;
        }
        throw new IllegalArgumentException("Cannot convert \"null\" number to ip address");
    }

}
