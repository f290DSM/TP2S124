package br.edu.fatecararas.devnotes.api.dto;

import br.edu.fatecararas.devnotes.domain.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {
    private Long id;
    @NotBlank(message = "O conteúdo da nota é obrigatório.")
    private String conteudo;
    @NotNull(message = "Categoria é obrigatória.")
    private Categoria categoria;
}
