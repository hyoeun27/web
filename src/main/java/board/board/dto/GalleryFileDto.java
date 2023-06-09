package board.board.dto;

import lombok.Data;

@Data
public class GalleryFileDto {
    private int idx;

    private int boardIdx;

    private String originalFileName;

    private String storedFilePath;

    private long fileSize;
}
