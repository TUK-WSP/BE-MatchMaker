package org.wsp.matchmaker.domain.recruitment.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FieldType {
    SHORT_TEXT("짧은 텍스트 입력"),
    LONG_TEXT("긴 텍스트 입력"),
    MULTIPLE_CHOICE("객관식 질문"),
    CHECKBOX("체크박스"),
    DROPDOWN("드롭다운 메뉴"),
    LINEAR_SCALE("선형 척도"),
    FILE_UPLOAD("파일 업로드"),
    DATE("날짜 입력"),
    TIME("시간 입력");

    private final String description;
}
