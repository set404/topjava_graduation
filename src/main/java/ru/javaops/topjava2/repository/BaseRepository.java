package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import static ru.javaops.topjava2.util.validation.ValidationUtil.checkModification;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} u WHERE u.id=:id")
    int delete(int id);

    default void deleteExisted(int id) {
        ValidationUtil.checkModification(delete(id), id);
    }
}