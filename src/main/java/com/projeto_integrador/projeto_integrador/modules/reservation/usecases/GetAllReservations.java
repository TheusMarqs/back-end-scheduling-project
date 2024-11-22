package com.projeto_integrador.projeto_integrador.modules.reservation.usecases;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto_integrador.projeto_integrador.modules.courses.entity.CourseEntity;
import com.projeto_integrador.projeto_integrador.modules.subjects.entity.SubjectEntity;
import com.projeto_integrador.projeto_integrador.modules.subjects.repository.SubjectRepository;
import com.projeto_integrador.projeto_integrador.modules.teacher.entity.TeacherEntity;
import com.projeto_integrador.projeto_integrador.modules.teacher.repository.TeacherRepository;
import com.projeto_integrador.projeto_integrador.modules.time.entity.TimeEntity;
import com.projeto_integrador.projeto_integrador.modules.time.repository.TimeRepository;
import com.projeto_integrador.projeto_integrador.modules.courses.repository.CourseRepository;
import com.projeto_integrador.projeto_integrador.modules.reservation.entity.ReservationEntity;
import com.projeto_integrador.projeto_integrador.modules.reservation.repository.ReservationRepository;
import com.projeto_integrador.projeto_integrador.modules.rooms.entity.RoomEntity;
import com.projeto_integrador.projeto_integrador.modules.rooms.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GetAllReservations {
    
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RoomRepository roomRepository;
    

    public List<Map<String, Object>> execute() {
        var allReservations = reservationRepository.findAll();
        if (allReservations.isEmpty()) {
            throw new EntityNotFoundException("Reservation not Registered");
        }

        return allReservations.stream()
                          .map(this::convertReservationToMap)
                          .collect(Collectors.toList());
    }

    private Map<String, Object> convertReservationToMap(ReservationEntity reservation) {
        Map<String, Object> result = new HashMap<>();
        result.put("reservationId", reservation.getReservationId());

        Long subjectId = reservation.getSubject();
        Long teacherId = reservation.getTeacher();
        LocalDate date = reservation.getDate();
        Long timeId = reservation.getTime();
        Long courseId = reservation.getCourse();
        Long roomId = reservation.getRoom();

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, new Locale("pt", "BR"));

        Optional<SubjectEntity> subject = subjectRepository.findById(subjectId);
        String subjectName = subject.map(SubjectEntity::getSubjectName)
                                    .orElse("Unknown Subject");

        Optional<TeacherEntity> teacher = teacherRepository.findById(teacherId);
        String teacherName = teacher.map(TeacherEntity::getTeacherName)
                                    .orElse("Unknown Teacher");

        Optional<TimeEntity> time = timeRepository.findById(timeId);
        String timeText = time.map(t -> String.format("%s - %s (%s)", t.getStartTime(), t.getEndTime(), t.getWeekDay()))
                                    .orElse("Unknown Time");

        Optional<CourseEntity> course = courseRepository.findById(courseId);
        String courseText = course.map(c -> String.format("%s - %s", c.getCourseName(), c.getCourseSemester()))
                                    .orElse("Unknown Course");

        Optional<RoomEntity> room = roomRepository.findById(roomId);
        String roomText = room.map(r -> String.format("%s - %s", r.getRoomType(), r.getRoomNumber()))
                                    .orElse("Unknown Course");


        result.put("subject", subjectName);
        result.put("teacher", teacherName);
        result.put("date", date);
        result.put("weekDay", dayName);
        result.put("time", timeText);
        result.put("course", courseText);
        result.put("room", roomText);
        return result;
    }
}

