package inicio.spring_boot_aula1.externalDependency;


public class Connection {
    private String userName;
    private String password;
    private String host;


    public Connection(String host, String password, String userName) {
        this.host = host;
        this.password = password;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Conection{" +
                "host='" + host + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
