package com.nikkah.app.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikkah.app.model.MatrimonyProfile;

@Repository
public interface MatrimonyProfileRepository extends JpaRepository<MatrimonyProfile, Long> {
	@Query("SELECT p FROM MatrimonyProfile p WHERE p.occupation = :occupation")
	List<MatrimonyProfile> findByOccupation(@Param("occupation") String occupation);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.income = :income")
	List<MatrimonyProfile> findByIncome(@Param("income") String income);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.education = :education")
	List<MatrimonyProfile> findByEducation(@Param("education") String education);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.gender = :gender")
	List<MatrimonyProfile> findByGender(@Param("gender") String gender);

//	@Query(value = "SELECT * FROM matrimony_profile WHERE LOWER(CAST(:field AS text)) = LOWER(:value)", nativeQuery = true)
	List<MatrimonyProfile> filterByField(@Param("field") String field, @Param("value") String value);

//	 @Query("SELECT p FROM MatrimonyProfile p WHERE (:income is null OR p.income = :income) AND (:occupation is null OR p.occupation = :occupation)")
//	 List<MatrimonyProfile> findByIncomeAndOccupation(@Param("income") String income, @Param("occupation") String occupation);
	List<MatrimonyProfile> filterByFields(Map<String, String> filterParams);

}
