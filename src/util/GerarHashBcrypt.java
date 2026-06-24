import at.favre.lib.crypto.bcrypt.BCrypt;

public class GerarHashBcrypt {
    public static void main(String[] args) {
        String senha = "123456";  // sua senha
        String hash = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
        System.out.println("Hash: " + hash);
    }}