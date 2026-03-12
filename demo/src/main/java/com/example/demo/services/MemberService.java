package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Member;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member with id " + id + " not found"));
    }

    public void deleteMember(Long id) {
        memberRepo.deleteById(id);
    }

    public List<Member> searchMembers(String name) {
        return memberRepo.findByNameContainingIgnoreCase(name);
    }
}