package lpms.backend.repository;


import lpms.backend.entity.SignalData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for SignalData entity.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface SignalDataRepository extends JpaRepository<SignalData, Long> {

<<<<<<< Updated upstream
=======

    /**
     * Find a SignalData entity by its file name.
     *
     * @param fileName the name of the file
     * @return the SignalData entity associated with the given file name
     */
    SignalData findByFileName(String fileName);
>>>>>>> Stashed changes
}
