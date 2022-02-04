package yongwon;

import java.sql.*;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/jpa_pre";
        String username = "yongwon";
        String password = "pass";

        //Try 문법은 해당 코드가 끝나면 자원을 정리하는 메서드를 호출해줌
        try(Connection connection = DriverManager.getConnection(url, username,password)) {
            System.out.println("Connection created  :   " + connection);
            // "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255) )"
            // docker exec -it postgres_boot psql -U yongwon jpa_pre (한번에 처리하는거)

            String sql = "INSERT INTO ACCOUNT VALUES(1, 'keesun', 'pass')";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

        }
    }
}
