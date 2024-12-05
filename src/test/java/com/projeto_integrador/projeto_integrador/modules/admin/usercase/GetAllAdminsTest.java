package com.projeto_integrador.projeto_integrador.modules.admin.usercase;


import com.projeto_integrador.projeto_integrador.modules.admin.entity.AdminEntity;
import com.projeto_integrador.projeto_integrador.modules.admin.repository.AdminRepository;
import com.projeto_integrador.projeto_integrador.modules.admin.usecases.GetAllAdmins;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllAdminsTest {

    @Mock
    private AdminRepository repository;

    @InjectMocks
    private GetAllAdmins getAllAdmins;

    @BeforeEach
    public void setUp() {
        // O Mockito cuidará da injeção de dependências
    }

    @Test
    @DisplayName("Should return list of AdminEntity when admins are present")
    public void shouldReturnListOfAdminsWhenPresent() {
        // Arrange
        AdminEntity admin1 = new AdminEntity();
        admin1.setAdminId(1L);
        admin1.setAdminEmail("admin1@example.com");

        AdminEntity admin2 = new AdminEntity();
        admin2.setAdminId(2L);
        admin2.setAdminEmail("admin2@example.com");

        List<AdminEntity> admins = List.of(admin1, admin2);

        when(repository.findAll()).thenReturn(admins);

        // Act
        List<AdminEntity> result = getAllAdmins.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getAdminEmail()).isEqualTo("admin1@example.com");
        assertThat(result.get(1).getAdminEmail()).isEqualTo("admin2@example.com");

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when no admins are found")
    public void shouldThrowExceptionWhenNoAdminsFound() {
        // Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThatThrownBy(() -> getAllAdmins.execute())
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Admin not Register");

        verify(repository, times(1)).findAll();
    }
}