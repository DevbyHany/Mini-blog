package cz.dominik.miniblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateArticleRequest {

    @NotBlank
    @Size(max = 120)
    public String title;

    @NotBlank
    public String content;
}
