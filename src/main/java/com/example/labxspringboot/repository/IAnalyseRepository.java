package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnalyseRepository extends JpaRepository<Analyse , Long> {
    Optional<Analyse> findByIdAndDeletedFalse(Long id);
    List<Analyse> findByDeletedFalse();

    @Query("SELECT a.nom AS nomAnalyse, p.nom AS nom, p.prenom AS prenom, " +
            "p.numeroTelephone AS numero, p.adresse AS adresse, p.dateNaissance AS dateNaissancePatient, " +
            "ta.nom AS nomTypeAnalyse, te.description AS nomTest, " +
            "te.statusResultat AS resultat, n.description AS libelleNorme, n.max AS maxValueNorme, " +
            "n.min AS minValueNorme, n.unite AS uniteNorme " +
            "FROM Analyse a JOIN a.echantillon e JOIN e.patient p JOIN a.typeAnalyses ta JOIN ta.testAnalyses te " +
            "JOIN te.norme n WHERE a.id = ?1")
    List<Object[]> getAnalysisReport(@Param("id") Long id);

}
