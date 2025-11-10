package com.sayra.umai.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {
    private int chapterNumber;
    private String chapterTitle;

    private Set<ChunkResponse> chunks;
}
