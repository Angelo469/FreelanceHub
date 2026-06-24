package model;

public class Usuario {
    private int id;
    private String email;
    private String senhaHash;
    private String role;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public String getEmail(){
        return email;

}
public void setSenhaHash(String senhaHash){
        this.senhaHash = senhaHash;
}
 public String getSenhaHash(){
        return senhaHash;
 }

public void setRole(String role){
        this.role = role;

}

public String getRole(){
        return role;
}
}