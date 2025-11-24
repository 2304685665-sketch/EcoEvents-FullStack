package com.ecoevents.ecoevents.model;

//导入"JPA"
import jakarta.persistence.*; //JPA核心库
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // 1. 告诉JPA：这是一个“实体类”，请你来管理它
@Table(name = "events") //2. 明确告诉JPA:这个类对于数据库里那张名为"events"的表
public class Event {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //java里Long更安全

    @Column(nullable = false)// 告诉JPA: 这个字段对应一个列，并且它不能为空
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate event_date;

    private String location;

    @Column(name = "img_url", length = 500)
    private String imgUrl; //java用imgUrl，而SQL用img_url

    //这列由数据库自动生成（DEFAULT CURRENT_TIMESTAMP) 告诉JPA：不要更新或插入
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public LocalDate getEvent_date(){
        return event_date;
    }
    public void setEvent_date(LocalDate event_date){
        this.event_date = event_date;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
