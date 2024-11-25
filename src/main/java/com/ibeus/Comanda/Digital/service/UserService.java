package com.ibeus.Comanda.Digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        if (password.equals(user.getPassword())) {  // Compara a senha criptografada
            return Optional.of(user);  // Senha correta
        }
        return Optional.empty();    // Senha incorreta ou usuário não encontrado
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User create(User user) {
        try {
        return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    public User update(Long id, User userDetails) {
        User user = findById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}