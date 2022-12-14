package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      User user = null;
      try {
         TypedQuery<User> query = sessionFactory.getCurrentSession()
                 .createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series")
                 .setParameter("model", model)
                 .setParameter("series", series);
         user = query.getSingleResult();
      } catch (Exception e) {
         System.out.println("Can't find user");
      }
      return user;
   }
}
