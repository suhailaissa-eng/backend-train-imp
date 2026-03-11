package com.example.demo.controllers;

import com.example.demo.entities.Member;
import com.example.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam String name) {
        return memberService.searchMembers(name);
    }
}