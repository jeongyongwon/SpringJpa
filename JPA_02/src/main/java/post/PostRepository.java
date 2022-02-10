package post;

import com.example.jpa_02.MyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


//custom Repo도 같이 사용함
//public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository  이런식으로 repository 기존꺼랑 커스텀 혼용가능

@Component
public interface PostRepository extends MyRepository<Post, Long> {
}
