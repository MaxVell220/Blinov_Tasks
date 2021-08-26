import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Задания к главе 11
 * Один из способов шифрования данных, называемый «двойным шифрованием», заключается в том,
 * что исходные данные при помощи некоторого КОЛЛЕКЦИИ И STREAM API
 * преобразования последовательно шифруются на некоторые два ключа —
 * K1 и K2. Разработать и реализовать эффективный алгоритм, позволяющий
 * находить ключи K1 и K2 по исходной строке и ее зашифрованному варианту. Проверить,
 * оказался ли разработанный способ действительно эффективным, протестировав программу для случая,
 * когда оба ключа являются
 * 20-битными (время ее работы не должно превосходить одной минуты)
 */

public class Main {

  public static void main(String[] args) throws NoSuchAlgorithmException {
    long startTime = System.currentTimeMillis();

    String password = "123456";
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodedHash = digest.digest(
      password.getBytes(StandardCharsets.UTF_8));

    StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
    for (byte b : encodedHash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }

    System.out.println("Digest(in hex format):: " + hexString);
    long endTime = System.currentTimeMillis();
    System.out.println("Total execution time: " + (endTime - startTime) + "ms");
  }
}
