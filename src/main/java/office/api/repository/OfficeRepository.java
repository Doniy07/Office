package office.api.repository;

import jakarta.transaction.Transactional;
import office.api.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, String> {

//    @Transactional
//    @Modifying
//    @Query(" UPDATE OfficeEntity o SET o.visible = false, o.deletedDate = CURRENT_TIMESTAMP WHERE o.id = ?1 and o.visible is true ")
//    int deleteByOfficeId(String officeId);

//    @Query("select o from OfficeEntity o where o.id = ?1 and o.visible = true")
//    Optional<OfficeEntity> findByIdAndVisibleTrue(String officeId);
}

