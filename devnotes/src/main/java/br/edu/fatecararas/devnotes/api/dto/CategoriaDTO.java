package br.edu.fatecararas.devnotes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private Long id;
    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 4, max = 50, message = "A descrição deve ter entre 4 e 50 caracteres.")
    private String descricao;
}
