package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
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
    public ApiResponse<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ApiResponse<List<Member>>(true, 200, "Members retrieved successfully", members);
    }

    @PostMapping
    public ApiResponse<Member> createMember(@RequestBody Member member) {
        Member savedMember = memberService.saveMember(member);
        return new ApiResponse<Member>(true, 201, "Member created successfully", savedMember);
    }

    @GetMapping("/search")
    public ApiResponse<List<Member>> searchMembers(@RequestParam String name) {
        List<Member> members = memberService.searchMembers(name);
        return new ApiResponse<List<Member>>(true, 200, "Members retrieved successfully", members);
    }
}