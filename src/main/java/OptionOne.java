public class OptionOne {

    public static String transformNumberToIpAddress(Long number) {
        if(number != null) {
            String binaryRepresentation = OptionOne.getBinaryRepresentation(number);
            return createIpAddress(binaryRepresentation);
        }
        throw new IllegalArgumentException("Cannot convert \"null\" number to ip address");
    }

    private static String createIpAddress(String binaryRepresentation) {
        if (binaryRepresentation != null) {
            String output = "";
            for (int i = 0; i < 4; i++) {
                String binaryRepresentationOfOctet = binaryRepresentation.substring(i * 8, (i + 1) * 8);
                output = output.concat(String.valueOf(binaryToNumber(binaryRepresentationOfOctet))).concat(i <= 2 ? "." : "");
            }

            return output;
        }
        throw new IllegalArgumentException("Binary representation should not be null");
    }

    private static String getBinaryRepresentation(Long number) {
        if (number != null) {
            int bits = 32;
            String binaryRepresentaion = "";
            while (bits != 0) {
                binaryRepresentaion = String.valueOf(number % 2).concat(binaryRepresentaion);
                number = number / 2;
                bits--;
            }
            return binaryRepresentaion;
        }
        throw new IllegalArgumentException("Number should not be null");
    }

    private static long binaryToNumber(String binary) {
        if (binary != null) {
            long number = 0L;
            char[] chars = binary.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    number = number + calculatePowerOfTwo(chars.length - 1 - i);
                }
            }
            return number;
        }
        throw new IllegalArgumentException("Binary number should not be null");
    }

    private static int calculatePowerOfTwo(Integer power) {
        if (power != null) {
            int number = 1;
            for (int i = 0; i < power; i++) {
                number = number * 2;
            }
            return number;
        }
        throw new IllegalArgumentException("Power should not be null");
    }
}
