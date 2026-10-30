package office.api.repository;

import jakarta.transaction.Transactional;
import office.api.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    @Query("select e from EmployeeEntity e where e.visible = true and e.id = ?1")
    Optional<EmployeeEntity> findByIdAndVisibleTrue(String id);

    @Transactional
    @Modifying
    @Query("update EmployeeEntity e set e.visible = false, e.deletedDate = CURRENT_TIMESTAMP where e.id = ?1 and e.visible is true")
    int deleteByEmployeeId(String id);

    @Query("select e.id from EmployeeEntity e where e.visible = true and e.officeId = ?1 ")
    List<String> findAllByEmployeeIdAndVisibleTrue(String officeId);
}