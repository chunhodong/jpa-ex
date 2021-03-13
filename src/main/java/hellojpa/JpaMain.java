package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[]args){
        //Application로딩할때 한번만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //요청당(트랜잭션당) 하나씩 생성
        //매니저가 데이터베이스 커넥션을 잡고있음
        EntityManager em = emf.createEntityManager();

        //JPA에서 모든 데이터의변경은 트랜잭션 안에서 이루어져야한다
        //단순 데이터 조회는 트랜잭션을 선언안해도 동작
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = em.find(Member.class,1L);
            member.setName("HelloJPA");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();

        }
    }
}
