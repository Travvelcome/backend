package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Chat")
public class ChatEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String chatId;
    
    // 랜드마크
    private Long landmarkId;

    // 태그
    private String tagId;

    // 카테고리
    private String categoryId;

    // userId
    // private String userId;

    private String sent; //내가 보낸 message
    private String received; //받은 message(gpt 답변)
}