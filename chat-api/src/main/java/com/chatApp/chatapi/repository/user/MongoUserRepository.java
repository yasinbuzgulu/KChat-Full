package com.chatApp.chatapi.repository.user;

import com.chatApp.chatapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Kullanıcı verilerinin mongoDb database i tarafını yöneten sınıf
 */
@Repository
public class MongoUserRepository implements UserRepository {

    private DefaultMongoUserRepository defaultMongoUserRepository;

    public MongoUserRepository(DefaultMongoUserRepository defaultMongoUserRepository) {
        this.defaultMongoUserRepository = defaultMongoUserRepository;
    }

    /**
     * İsimden kullanıcı bulma metodu
     *
     * @param userName: aranan kullanıcı ismi
     * @return: kullanıcıyı döner
     */
    @Override
    public User findByName(String userName) {
        return defaultMongoUserRepository.findByName(userName);
    }

    /**
     * Id ye bağlı kullanıcı bulma metodu
     *
     * @param id: aranan id
     * @return: kullanıcı varsa kullanıyı yoksa null döner
     */
    @Override
    public User find(String id) {
        return defaultMongoUserRepository.findById(id).orElse(null);
    }

    /**
     * Kullanıcı kaydetme metodu
     *
     * @param userName: kaydedilecek kullanıcı ismi
     * @return: kullanıcı kaydını döner
     */
    @Override
    public User save(String userName) {
        return defaultMongoUserRepository.save(new User(null, userName));
    }

    @Override
    public User saveObject(User user) {
        return defaultMongoUserRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return defaultMongoUserRepository.findAll();
    }

}
