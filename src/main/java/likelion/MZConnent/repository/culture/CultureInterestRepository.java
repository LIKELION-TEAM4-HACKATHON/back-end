package likelion.MZConnent.repository.culture;

import likelion.MZConnent.domain.culture.CultureInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureInterestRepository extends JpaRepository<CultureInterest, Long> {
}
