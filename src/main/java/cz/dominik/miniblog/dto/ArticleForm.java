package cz.dominik.miniblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleForm {
    @NotBlank @Size(max = 120)
    private String title;

    @NotBlank @Size(max = 10_000)
    private String content;
}