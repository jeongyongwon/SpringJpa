package post;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional  //이름 규칙 잘기억해두자
public class PostCustomRepositoryImpl implements PostCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("custom findMyPost");
        return  entityManager.createQuery("SELECT p FROM Post  AS p", Post.class).getResultList();
    }
}
