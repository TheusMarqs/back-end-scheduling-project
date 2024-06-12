package com.projeto_integrador.projeto_integrador.modules.teacher.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projeto_integrador.projeto_integrador.exceptions.UserFoundException;
import com.projeto_integrador.projeto_integrador.modules.subjects.SubjectValidation;
import com.projeto_integrador.projeto_integrador.modules.subjects.repository.SubjectRepository;
import com.projeto_integrador.projeto_integrador.modules.teacher.entity.TeacherEntity;
import com.projeto_integrador.projeto_integrador.modules.teacher.repository.TeacherRepository;

@Service
public class CreateTeacher {
    
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    
    @Autowired
    private SubjectValidation subjectValidation;

    public TeacherEntity execute(TeacherEntity teacherEntity){
        this.teacherRepository.findByInstitutionalEmail(teacherEntity.getInstitutionalEmail())
                                .ifPresent(user -> {
                                    throw new UserFoundException();
        });
        
        List<Long> subjectIds = teacherEntity.getTeacherSubjects();
        subjectValidation.validateSubjectsExist(subjectIds);

        // Se todos os assuntos (subjects) existirem, salva o professor
        return this.teacherRepository.save(teacherEntity);
    }
        
}
