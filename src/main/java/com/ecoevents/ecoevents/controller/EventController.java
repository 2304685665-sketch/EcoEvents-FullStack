package com.ecoevents.ecoevents.controller;

import com.ecoevents.ecoevents.model.Event;
import com.ecoevents.ecoevents.model.EventRegistration;
import com.ecoevents.ecoevents.model.Volunteer;
import com.ecoevents.ecoevents.repository.EventRegistrationRepository;
import com.ecoevents.ecoevents.repository.EventRepository;
import com.ecoevents.ecoevents.repository.VolunteerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    // 我们需要三个仓库来操作三张表
    private final EventRepository eventRepository;
    private final VolunteerRepository volunteerRepository;
    private final EventRegistrationRepository registrationRepository;

    // 构造函数注入
    public EventController(EventRepository eventRepository,
                           VolunteerRepository volunteerRepository,
                           EventRegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.volunteerRepository = volunteerRepository;
        this.registrationRepository = registrationRepository;
    }

    // 1. 首页：显示所有活动
    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "index";
    }

    // 2. 显示报名表单 (GET)
    @GetMapping("/event/{id}/register")
    public String showRegistrationForm(@PathVariable Long id, Model model) {
        // 根据 ID 找到活动
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        return "register"; // 去显示 register.html
    }

    // 3. 处理报名提交 (POST) - 这里是全栈的核心逻辑！
    @PostMapping("/register")
    public String processRegistration(@RequestParam Long eventId,
                                      @RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String notes) {

        // A. 先找活动
        Event event = eventRepository.findById(eventId).orElseThrow();

        // B. 处理志愿者 (如果邮箱已存在，就用旧的；如果不存在，就创建新的)
        // 这是一个简单的去重逻辑
        Volunteer volunteer = volunteerRepository.findAll().stream()
                .filter(v -> v.getEmail().equals(email))
                .findFirst()
                .orElseGet(() -> {
                    // 创建新志愿者
                    Volunteer newVol = new Volunteer();
                    newVol.setName(name);
                    newVol.setEmail(email);
                    newVol.setMessage("Joined via web");
                    return volunteerRepository.save(newVol); // 保存到数据库！
                });

        // C. 创建报名记录 (关联表)
        EventRegistration registration = new EventRegistration();
        registration.setEvent(event);       // 关联活动
        registration.setVolunteer(volunteer); // 关联志愿者
        registration.setNotes(notes);
        registration.setRegistrationDate(LocalDateTime.now());

        registrationRepository.save(registration); // 保存到数据库！

        // D. 成功后，跳回首页
        return "redirect:/";
    }
}