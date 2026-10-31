package office.api.repository;

import office.api.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, String> {

//    @Transactional
//    @Modifying
//    @Query(" UPDATE OfficeEntity o SET o.visible = false, o.deletedDate = CURRENT_TIMESTAMP WHERE o.id = ?1 and o.visible is true ")
//    int deleteByOfficeId(String officeId);
}

