/**
 * 
 */
package com.tcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tcs.entity.CourseRegisration;

/**
 * @author springuser18
 *
 */
@Repository
public interface CourseRegisrationRepository extends CrudRepository<CourseRegisration, Integer> {
	/**
	 * This query is responsible fetch student for professor
	 * 
	 */
	@Query(value = "select student_id from course_registration where professor_id=?", nativeQuery = true)
	List<CourseRegisration> getStudentList(Integer id);

}